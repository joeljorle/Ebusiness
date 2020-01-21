package com.web.rest;

import com.EbusinesApp;
import com.domain.Tourcategory;
import com.repository.TourcategoryRepository;
import com.service.TourcategoryService;
import com.service.dto.TourcategoryDTO;
import com.service.mapper.TourcategoryMapper;
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
 * Integration tests for the {@link TourcategoryResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class TourcategoryResourceIT {

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

    private static final String DEFAULT_OTHERCONTACTS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERCONTACTS = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERFIELDS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERFIELDS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TourcategoryRepository tourcategoryRepository;

    @Autowired
    private TourcategoryMapper tourcategoryMapper;

    @Autowired
    private TourcategoryService tourcategoryService;

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

    private MockMvc restTourcategoryMockMvc;

    private Tourcategory tourcategory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TourcategoryResource tourcategoryResource = new TourcategoryResource(tourcategoryService);
        this.restTourcategoryMockMvc = MockMvcBuilders.standaloneSetup(tourcategoryResource)
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
    public static Tourcategory createEntity(EntityManager em) {
        Tourcategory tourcategory = new Tourcategory()
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
            .othercontacts(DEFAULT_OTHERCONTACTS)
            .otherfields(DEFAULT_OTHERFIELDS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return tourcategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tourcategory createUpdatedEntity(EntityManager em) {
        Tourcategory tourcategory = new Tourcategory()
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
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return tourcategory;
    }

    @BeforeEach
    public void initTest() {
        tourcategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createTourcategory() throws Exception {
        int databaseSizeBeforeCreate = tourcategoryRepository.findAll().size();

        // Create the Tourcategory
        TourcategoryDTO tourcategoryDTO = tourcategoryMapper.toDto(tourcategory);
        restTourcategoryMockMvc.perform(post("/api/tourcategories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourcategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the Tourcategory in the database
        List<Tourcategory> tourcategoryList = tourcategoryRepository.findAll();
        assertThat(tourcategoryList).hasSize(databaseSizeBeforeCreate + 1);
        Tourcategory testTourcategory = tourcategoryList.get(tourcategoryList.size() - 1);
        assertThat(testTourcategory.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testTourcategory.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testTourcategory.getEvenementid()).isEqualTo(DEFAULT_EVENEMENTID);
        assertThat(testTourcategory.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testTourcategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTourcategory.isIslock()).isEqualTo(DEFAULT_ISLOCK);
        assertThat(testTourcategory.getLockdelay()).isEqualTo(DEFAULT_LOCKDELAY);
        assertThat(testTourcategory.getAbout()).isEqualTo(DEFAULT_ABOUT);
        assertThat(testTourcategory.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTourcategory.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testTourcategory.getTagcolor()).isEqualTo(DEFAULT_TAGCOLOR);
        assertThat(testTourcategory.getPostalcode()).isEqualTo(DEFAULT_POSTALCODE);
        assertThat(testTourcategory.getPhones()).isEqualTo(DEFAULT_PHONES);
        assertThat(testTourcategory.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testTourcategory.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testTourcategory.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testTourcategory.getGplus()).isEqualTo(DEFAULT_GPLUS);
        assertThat(testTourcategory.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testTourcategory.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testTourcategory.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTourcategory.getOthercontacts()).isEqualTo(DEFAULT_OTHERCONTACTS);
        assertThat(testTourcategory.getOtherfields()).isEqualTo(DEFAULT_OTHERFIELDS);
        assertThat(testTourcategory.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testTourcategory.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createTourcategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tourcategoryRepository.findAll().size();

        // Create the Tourcategory with an existing ID
        tourcategory.setId(1L);
        TourcategoryDTO tourcategoryDTO = tourcategoryMapper.toDto(tourcategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTourcategoryMockMvc.perform(post("/api/tourcategories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourcategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tourcategory in the database
        List<Tourcategory> tourcategoryList = tourcategoryRepository.findAll();
        assertThat(tourcategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = tourcategoryRepository.findAll().size();
        // set the field null
        tourcategory.setSlug(null);

        // Create the Tourcategory, which fails.
        TourcategoryDTO tourcategoryDTO = tourcategoryMapper.toDto(tourcategory);

        restTourcategoryMockMvc.perform(post("/api/tourcategories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourcategoryDTO)))
            .andExpect(status().isBadRequest());

        List<Tourcategory> tourcategoryList = tourcategoryRepository.findAll();
        assertThat(tourcategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTourcategories() throws Exception {
        // Initialize the database
        tourcategoryRepository.saveAndFlush(tourcategory);

        // Get all the tourcategoryList
        restTourcategoryMockMvc.perform(get("/api/tourcategories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tourcategory.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].othercontacts").value(hasItem(DEFAULT_OTHERCONTACTS.toString())))
            .andExpect(jsonPath("$.[*].otherfields").value(hasItem(DEFAULT_OTHERFIELDS.toString())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getTourcategory() throws Exception {
        // Initialize the database
        tourcategoryRepository.saveAndFlush(tourcategory);

        // Get the tourcategory
        restTourcategoryMockMvc.perform(get("/api/tourcategories/{id}", tourcategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tourcategory.getId().intValue()))
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
            .andExpect(jsonPath("$.othercontacts").value(DEFAULT_OTHERCONTACTS.toString()))
            .andExpect(jsonPath("$.otherfields").value(DEFAULT_OTHERFIELDS.toString()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTourcategory() throws Exception {
        // Get the tourcategory
        restTourcategoryMockMvc.perform(get("/api/tourcategories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTourcategory() throws Exception {
        // Initialize the database
        tourcategoryRepository.saveAndFlush(tourcategory);

        int databaseSizeBeforeUpdate = tourcategoryRepository.findAll().size();

        // Update the tourcategory
        Tourcategory updatedTourcategory = tourcategoryRepository.findById(tourcategory.getId()).get();
        // Disconnect from session so that the updates on updatedTourcategory are not directly saved in db
        em.detach(updatedTourcategory);
        updatedTourcategory
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
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        TourcategoryDTO tourcategoryDTO = tourcategoryMapper.toDto(updatedTourcategory);

        restTourcategoryMockMvc.perform(put("/api/tourcategories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourcategoryDTO)))
            .andExpect(status().isOk());

        // Validate the Tourcategory in the database
        List<Tourcategory> tourcategoryList = tourcategoryRepository.findAll();
        assertThat(tourcategoryList).hasSize(databaseSizeBeforeUpdate);
        Tourcategory testTourcategory = tourcategoryList.get(tourcategoryList.size() - 1);
        assertThat(testTourcategory.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testTourcategory.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testTourcategory.getEvenementid()).isEqualTo(UPDATED_EVENEMENTID);
        assertThat(testTourcategory.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testTourcategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTourcategory.isIslock()).isEqualTo(UPDATED_ISLOCK);
        assertThat(testTourcategory.getLockdelay()).isEqualTo(UPDATED_LOCKDELAY);
        assertThat(testTourcategory.getAbout()).isEqualTo(UPDATED_ABOUT);
        assertThat(testTourcategory.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTourcategory.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testTourcategory.getTagcolor()).isEqualTo(UPDATED_TAGCOLOR);
        assertThat(testTourcategory.getPostalcode()).isEqualTo(UPDATED_POSTALCODE);
        assertThat(testTourcategory.getPhones()).isEqualTo(UPDATED_PHONES);
        assertThat(testTourcategory.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testTourcategory.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testTourcategory.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testTourcategory.getGplus()).isEqualTo(UPDATED_GPLUS);
        assertThat(testTourcategory.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testTourcategory.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testTourcategory.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTourcategory.getOthercontacts()).isEqualTo(UPDATED_OTHERCONTACTS);
        assertThat(testTourcategory.getOtherfields()).isEqualTo(UPDATED_OTHERFIELDS);
        assertThat(testTourcategory.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testTourcategory.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingTourcategory() throws Exception {
        int databaseSizeBeforeUpdate = tourcategoryRepository.findAll().size();

        // Create the Tourcategory
        TourcategoryDTO tourcategoryDTO = tourcategoryMapper.toDto(tourcategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTourcategoryMockMvc.perform(put("/api/tourcategories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourcategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tourcategory in the database
        List<Tourcategory> tourcategoryList = tourcategoryRepository.findAll();
        assertThat(tourcategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTourcategory() throws Exception {
        // Initialize the database
        tourcategoryRepository.saveAndFlush(tourcategory);

        int databaseSizeBeforeDelete = tourcategoryRepository.findAll().size();

        // Delete the tourcategory
        restTourcategoryMockMvc.perform(delete("/api/tourcategories/{id}", tourcategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tourcategory> tourcategoryList = tourcategoryRepository.findAll();
        assertThat(tourcategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
