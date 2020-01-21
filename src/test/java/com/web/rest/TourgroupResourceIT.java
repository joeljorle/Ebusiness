package com.web.rest;

import com.EbusinesApp;
import com.domain.Tourgroup;
import com.repository.TourgroupRepository;
import com.service.TourgroupService;
import com.service.dto.TourgroupDTO;
import com.service.mapper.TourgroupMapper;
import com.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.domain.enumeration.Tagcolor;
/**
 * Integration tests for the {@link TourgroupResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class TourgroupResourceIT {

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;

    private static final Integer DEFAULT_PRODUCTID = 1;
    private static final Integer UPDATED_PRODUCTID = 2;

    private static final Integer DEFAULT_EVENEMENTID = 1;
    private static final Integer UPDATED_EVENEMENTID = 2;

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISLOCK = false;
    private static final Boolean UPDATED_ISLOCK = true;

    private static final Instant DEFAULT_LOCKDELAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LOCKDELAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ABOUT = "AAAAAAAAAA";
    private static final String UPDATED_ABOUT = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final Tagcolor DEFAULT_TAGCOLOR = Tagcolor.RED;
    private static final Tagcolor UPDATED_TAGCOLOR = Tagcolor.BLUE;

    private static final String DEFAULT_POSTALCODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTALCODE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONES = "AAAAAAAAAA";
    private static final String UPDATED_PHONES = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_GPLUS = "AAAAAAAAAA";
    private static final String UPDATED_GPLUS = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_POSITION = 1;
    private static final Integer UPDATED_POSITION = 2;

    private static final String DEFAULT_OTHERCONTACTS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERCONTACTS = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERFIELDS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERFIELDS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TourgroupRepository tourgroupRepository;

    @Autowired
    private TourgroupMapper tourgroupMapper;

    @Autowired
    private TourgroupService tourgroupService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTourgroupMockMvc;

    private Tourgroup tourgroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TourgroupResource tourgroupResource = new TourgroupResource(tourgroupService);
        this.restTourgroupMockMvc = MockMvcBuilders.standaloneSetup(tourgroupResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tourgroup createEntity(EntityManager em) {
        Tourgroup tourgroup = new Tourgroup()
            .categoryid(DEFAULT_CATEGORYID)
            .productid(DEFAULT_PRODUCTID)
            .evenementid(DEFAULT_EVENEMENTID)
            .slug(DEFAULT_SLUG)
            .name(DEFAULT_NAME)
            .islock(DEFAULT_ISLOCK)
            .lockdelay(DEFAULT_LOCKDELAY)
            .about(DEFAULT_ABOUT)
            .title(DEFAULT_TITLE)
            .tag(DEFAULT_TAG)
            .tagcolor(DEFAULT_TAGCOLOR)
            .postalcode(DEFAULT_POSTALCODE)
            .phones(DEFAULT_PHONES)
            .website(DEFAULT_WEBSITE)
            .facebook(DEFAULT_FACEBOOK)
            .twitter(DEFAULT_TWITTER)
            .gplus(DEFAULT_GPLUS)
            .linkedin(DEFAULT_LINKEDIN)
            .instagram(DEFAULT_INSTAGRAM)
            .email(DEFAULT_EMAIL)
            .position(DEFAULT_POSITION)
            .othercontacts(DEFAULT_OTHERCONTACTS)
            .otherfields(DEFAULT_OTHERFIELDS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return tourgroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tourgroup createUpdatedEntity(EntityManager em) {
        Tourgroup tourgroup = new Tourgroup()
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .evenementid(UPDATED_EVENEMENTID)
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .islock(UPDATED_ISLOCK)
            .lockdelay(UPDATED_LOCKDELAY)
            .about(UPDATED_ABOUT)
            .title(UPDATED_TITLE)
            .tag(UPDATED_TAG)
            .tagcolor(UPDATED_TAGCOLOR)
            .postalcode(UPDATED_POSTALCODE)
            .phones(UPDATED_PHONES)
            .website(UPDATED_WEBSITE)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .gplus(UPDATED_GPLUS)
            .linkedin(UPDATED_LINKEDIN)
            .instagram(UPDATED_INSTAGRAM)
            .email(UPDATED_EMAIL)
            .position(UPDATED_POSITION)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return tourgroup;
    }

    @BeforeEach
    public void initTest() {
        tourgroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createTourgroup() throws Exception {
        int databaseSizeBeforeCreate = tourgroupRepository.findAll().size();

        // Create the Tourgroup
        TourgroupDTO tourgroupDTO = tourgroupMapper.toDto(tourgroup);
        restTourgroupMockMvc.perform(post("/api/tourgroups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourgroupDTO)))
            .andExpect(status().isCreated());

        // Validate the Tourgroup in the database
        List<Tourgroup> tourgroupList = tourgroupRepository.findAll();
        assertThat(tourgroupList).hasSize(databaseSizeBeforeCreate + 1);
        Tourgroup testTourgroup = tourgroupList.get(tourgroupList.size() - 1);
        assertThat(testTourgroup.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testTourgroup.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testTourgroup.getEvenementid()).isEqualTo(DEFAULT_EVENEMENTID);
        assertThat(testTourgroup.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testTourgroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTourgroup.isIslock()).isEqualTo(DEFAULT_ISLOCK);
        assertThat(testTourgroup.getLockdelay()).isEqualTo(DEFAULT_LOCKDELAY);
        assertThat(testTourgroup.getAbout()).isEqualTo(DEFAULT_ABOUT);
        assertThat(testTourgroup.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTourgroup.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testTourgroup.getTagcolor()).isEqualTo(DEFAULT_TAGCOLOR);
        assertThat(testTourgroup.getPostalcode()).isEqualTo(DEFAULT_POSTALCODE);
        assertThat(testTourgroup.getPhones()).isEqualTo(DEFAULT_PHONES);
        assertThat(testTourgroup.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testTourgroup.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testTourgroup.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testTourgroup.getGplus()).isEqualTo(DEFAULT_GPLUS);
        assertThat(testTourgroup.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testTourgroup.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testTourgroup.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTourgroup.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testTourgroup.getOthercontacts()).isEqualTo(DEFAULT_OTHERCONTACTS);
        assertThat(testTourgroup.getOtherfields()).isEqualTo(DEFAULT_OTHERFIELDS);
        assertThat(testTourgroup.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testTourgroup.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createTourgroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tourgroupRepository.findAll().size();

        // Create the Tourgroup with an existing ID
        tourgroup.setId(1L);
        TourgroupDTO tourgroupDTO = tourgroupMapper.toDto(tourgroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTourgroupMockMvc.perform(post("/api/tourgroups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourgroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tourgroup in the database
        List<Tourgroup> tourgroupList = tourgroupRepository.findAll();
        assertThat(tourgroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = tourgroupRepository.findAll().size();
        // set the field null
        tourgroup.setSlug(null);

        // Create the Tourgroup, which fails.
        TourgroupDTO tourgroupDTO = tourgroupMapper.toDto(tourgroup);

        restTourgroupMockMvc.perform(post("/api/tourgroups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourgroupDTO)))
            .andExpect(status().isBadRequest());

        List<Tourgroup> tourgroupList = tourgroupRepository.findAll();
        assertThat(tourgroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTourgroups() throws Exception {
        // Initialize the database
        tourgroupRepository.saveAndFlush(tourgroup);

        // Get all the tourgroupList
        restTourgroupMockMvc.perform(get("/api/tourgroups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tourgroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].evenementid").value(hasItem(DEFAULT_EVENEMENTID)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].islock").value(hasItem(DEFAULT_ISLOCK.booleanValue())))
            .andExpect(jsonPath("$.[*].lockdelay").value(hasItem(DEFAULT_LOCKDELAY.toString())))
            .andExpect(jsonPath("$.[*].about").value(hasItem(DEFAULT_ABOUT.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].tagcolor").value(hasItem(DEFAULT_TAGCOLOR.toString())))
            .andExpect(jsonPath("$.[*].postalcode").value(hasItem(DEFAULT_POSTALCODE)))
            .andExpect(jsonPath("$.[*].phones").value(hasItem(DEFAULT_PHONES)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK)))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER)))
            .andExpect(jsonPath("$.[*].gplus").value(hasItem(DEFAULT_GPLUS)))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN)))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].othercontacts").value(hasItem(DEFAULT_OTHERCONTACTS.toString())))
            .andExpect(jsonPath("$.[*].otherfields").value(hasItem(DEFAULT_OTHERFIELDS.toString())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getTourgroup() throws Exception {
        // Initialize the database
        tourgroupRepository.saveAndFlush(tourgroup);

        // Get the tourgroup
        restTourgroupMockMvc.perform(get("/api/tourgroups/{id}", tourgroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tourgroup.getId().intValue()))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.evenementid").value(DEFAULT_EVENEMENTID))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.islock").value(DEFAULT_ISLOCK.booleanValue()))
            .andExpect(jsonPath("$.lockdelay").value(DEFAULT_LOCKDELAY.toString()))
            .andExpect(jsonPath("$.about").value(DEFAULT_ABOUT.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG))
            .andExpect(jsonPath("$.tagcolor").value(DEFAULT_TAGCOLOR.toString()))
            .andExpect(jsonPath("$.postalcode").value(DEFAULT_POSTALCODE))
            .andExpect(jsonPath("$.phones").value(DEFAULT_PHONES))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK))
            .andExpect(jsonPath("$.twitter").value(DEFAULT_TWITTER))
            .andExpect(jsonPath("$.gplus").value(DEFAULT_GPLUS))
            .andExpect(jsonPath("$.linkedin").value(DEFAULT_LINKEDIN))
            .andExpect(jsonPath("$.instagram").value(DEFAULT_INSTAGRAM))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.othercontacts").value(DEFAULT_OTHERCONTACTS.toString()))
            .andExpect(jsonPath("$.otherfields").value(DEFAULT_OTHERFIELDS.toString()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTourgroup() throws Exception {
        // Get the tourgroup
        restTourgroupMockMvc.perform(get("/api/tourgroups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTourgroup() throws Exception {
        // Initialize the database
        tourgroupRepository.saveAndFlush(tourgroup);

        int databaseSizeBeforeUpdate = tourgroupRepository.findAll().size();

        // Update the tourgroup
        Tourgroup updatedTourgroup = tourgroupRepository.findById(tourgroup.getId()).get();
        // Disconnect from session so that the updates on updatedTourgroup are not directly saved in db
        em.detach(updatedTourgroup);
        updatedTourgroup
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .evenementid(UPDATED_EVENEMENTID)
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .islock(UPDATED_ISLOCK)
            .lockdelay(UPDATED_LOCKDELAY)
            .about(UPDATED_ABOUT)
            .title(UPDATED_TITLE)
            .tag(UPDATED_TAG)
            .tagcolor(UPDATED_TAGCOLOR)
            .postalcode(UPDATED_POSTALCODE)
            .phones(UPDATED_PHONES)
            .website(UPDATED_WEBSITE)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .gplus(UPDATED_GPLUS)
            .linkedin(UPDATED_LINKEDIN)
            .instagram(UPDATED_INSTAGRAM)
            .email(UPDATED_EMAIL)
            .position(UPDATED_POSITION)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        TourgroupDTO tourgroupDTO = tourgroupMapper.toDto(updatedTourgroup);

        restTourgroupMockMvc.perform(put("/api/tourgroups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourgroupDTO)))
            .andExpect(status().isOk());

        // Validate the Tourgroup in the database
        List<Tourgroup> tourgroupList = tourgroupRepository.findAll();
        assertThat(tourgroupList).hasSize(databaseSizeBeforeUpdate);
        Tourgroup testTourgroup = tourgroupList.get(tourgroupList.size() - 1);
        assertThat(testTourgroup.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testTourgroup.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testTourgroup.getEvenementid()).isEqualTo(UPDATED_EVENEMENTID);
        assertThat(testTourgroup.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testTourgroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTourgroup.isIslock()).isEqualTo(UPDATED_ISLOCK);
        assertThat(testTourgroup.getLockdelay()).isEqualTo(UPDATED_LOCKDELAY);
        assertThat(testTourgroup.getAbout()).isEqualTo(UPDATED_ABOUT);
        assertThat(testTourgroup.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTourgroup.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testTourgroup.getTagcolor()).isEqualTo(UPDATED_TAGCOLOR);
        assertThat(testTourgroup.getPostalcode()).isEqualTo(UPDATED_POSTALCODE);
        assertThat(testTourgroup.getPhones()).isEqualTo(UPDATED_PHONES);
        assertThat(testTourgroup.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testTourgroup.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testTourgroup.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testTourgroup.getGplus()).isEqualTo(UPDATED_GPLUS);
        assertThat(testTourgroup.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testTourgroup.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testTourgroup.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTourgroup.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testTourgroup.getOthercontacts()).isEqualTo(UPDATED_OTHERCONTACTS);
        assertThat(testTourgroup.getOtherfields()).isEqualTo(UPDATED_OTHERFIELDS);
        assertThat(testTourgroup.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testTourgroup.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingTourgroup() throws Exception {
        int databaseSizeBeforeUpdate = tourgroupRepository.findAll().size();

        // Create the Tourgroup
        TourgroupDTO tourgroupDTO = tourgroupMapper.toDto(tourgroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTourgroupMockMvc.perform(put("/api/tourgroups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourgroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tourgroup in the database
        List<Tourgroup> tourgroupList = tourgroupRepository.findAll();
        assertThat(tourgroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTourgroup() throws Exception {
        // Initialize the database
        tourgroupRepository.saveAndFlush(tourgroup);

        int databaseSizeBeforeDelete = tourgroupRepository.findAll().size();

        // Delete the tourgroup
        restTourgroupMockMvc.perform(delete("/api/tourgroups/{id}", tourgroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tourgroup> tourgroupList = tourgroupRepository.findAll();
        assertThat(tourgroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
