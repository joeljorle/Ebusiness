package com.web.rest;

import com.EbusinessApp;
import com.domain.Evenement;
import com.repository.EvenementRepository;
import com.service.EvenementService;
import com.service.dto.EvenementDTO;
import com.service.mapper.EvenementMapper;
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
 * Integration tests for the {@link EvenementResource} REST controller.
 */
@SpringBootTest(classes = EbusinessApp.class)
public class EvenementResourceIT {

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;

    private static final Integer DEFAULT_USERID = 1;
    private static final Integer UPDATED_USERID = 2;

    private static final Integer DEFAULT_PRODUCTID = 1;
    private static final Integer UPDATED_PRODUCTID = 2;

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISLOCK = false;
    private static final Boolean UPDATED_ISLOCK = true;

    private static final Instant DEFAULT_LOCKDELAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LOCKDELAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ISLIMITED = false;
    private static final Boolean UPDATED_ISLIMITED = true;

    private static final Instant DEFAULT_LIMITEDDELAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LIMITEDDELAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LIMITEDBOOKING = 1;
    private static final Integer UPDATED_LIMITEDBOOKING = 2;

    private static final Instant DEFAULT_STARTDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STARTDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ENDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ENDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERCONTACTS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERCONTACTS = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERFIELDS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERFIELDS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private EvenementMapper evenementMapper;

    @Autowired
    private EvenementService evenementService;

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

    private MockMvc restEvenementMockMvc;

    private Evenement evenement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EvenementResource evenementResource = new EvenementResource(evenementService);
        this.restEvenementMockMvc = MockMvcBuilders.standaloneSetup(evenementResource)
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
    public static Evenement createEntity(EntityManager em) {
        Evenement evenement = new Evenement()
            .categoryid(DEFAULT_CATEGORYID)
            .userid(DEFAULT_USERID)
            .productid(DEFAULT_PRODUCTID)
            .slug(DEFAULT_SLUG)
            .name(DEFAULT_NAME)
            .islock(DEFAULT_ISLOCK)
            .lockdelay(DEFAULT_LOCKDELAY)
            .islimited(DEFAULT_ISLIMITED)
            .limiteddelay(DEFAULT_LIMITEDDELAY)
            .limitedbooking(DEFAULT_LIMITEDBOOKING)
            .startdate(DEFAULT_STARTDATE)
            .enddate(DEFAULT_ENDDATE)
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
            .url(DEFAULT_URL)
            .othercontacts(DEFAULT_OTHERCONTACTS)
            .otherfields(DEFAULT_OTHERFIELDS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return evenement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Evenement createUpdatedEntity(EntityManager em) {
        Evenement evenement = new Evenement()
            .categoryid(UPDATED_CATEGORYID)
            .userid(UPDATED_USERID)
            .productid(UPDATED_PRODUCTID)
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .islock(UPDATED_ISLOCK)
            .lockdelay(UPDATED_LOCKDELAY)
            .islimited(UPDATED_ISLIMITED)
            .limiteddelay(UPDATED_LIMITEDDELAY)
            .limitedbooking(UPDATED_LIMITEDBOOKING)
            .startdate(UPDATED_STARTDATE)
            .enddate(UPDATED_ENDDATE)
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
            .url(UPDATED_URL)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return evenement;
    }

    @BeforeEach
    public void initTest() {
        evenement = createEntity(em);
    }

    @Test
    @Transactional
    public void createEvenement() throws Exception {
        int databaseSizeBeforeCreate = evenementRepository.findAll().size();

        // Create the Evenement
        EvenementDTO evenementDTO = evenementMapper.toDto(evenement);
        restEvenementMockMvc.perform(post("/api/evenements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(evenementDTO)))
            .andExpect(status().isCreated());

        // Validate the Evenement in the database
        List<Evenement> evenementList = evenementRepository.findAll();
        assertThat(evenementList).hasSize(databaseSizeBeforeCreate + 1);
        Evenement testEvenement = evenementList.get(evenementList.size() - 1);
        assertThat(testEvenement.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testEvenement.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testEvenement.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testEvenement.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testEvenement.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEvenement.isIslock()).isEqualTo(DEFAULT_ISLOCK);
        assertThat(testEvenement.getLockdelay()).isEqualTo(DEFAULT_LOCKDELAY);
        assertThat(testEvenement.isIslimited()).isEqualTo(DEFAULT_ISLIMITED);
        assertThat(testEvenement.getLimiteddelay()).isEqualTo(DEFAULT_LIMITEDDELAY);
        assertThat(testEvenement.getLimitedbooking()).isEqualTo(DEFAULT_LIMITEDBOOKING);
        assertThat(testEvenement.getStartdate()).isEqualTo(DEFAULT_STARTDATE);
        assertThat(testEvenement.getEnddate()).isEqualTo(DEFAULT_ENDDATE);
        assertThat(testEvenement.getAbout()).isEqualTo(DEFAULT_ABOUT);
        assertThat(testEvenement.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testEvenement.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testEvenement.getTagcolor()).isEqualTo(DEFAULT_TAGCOLOR);
        assertThat(testEvenement.getPostalcode()).isEqualTo(DEFAULT_POSTALCODE);
        assertThat(testEvenement.getPhones()).isEqualTo(DEFAULT_PHONES);
        assertThat(testEvenement.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testEvenement.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testEvenement.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testEvenement.getGplus()).isEqualTo(DEFAULT_GPLUS);
        assertThat(testEvenement.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testEvenement.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testEvenement.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEvenement.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testEvenement.getOthercontacts()).isEqualTo(DEFAULT_OTHERCONTACTS);
        assertThat(testEvenement.getOtherfields()).isEqualTo(DEFAULT_OTHERFIELDS);
        assertThat(testEvenement.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testEvenement.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createEvenementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = evenementRepository.findAll().size();

        // Create the Evenement with an existing ID
        evenement.setId(1L);
        EvenementDTO evenementDTO = evenementMapper.toDto(evenement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEvenementMockMvc.perform(post("/api/evenements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(evenementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Evenement in the database
        List<Evenement> evenementList = evenementRepository.findAll();
        assertThat(evenementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = evenementRepository.findAll().size();
        // set the field null
        evenement.setSlug(null);

        // Create the Evenement, which fails.
        EvenementDTO evenementDTO = evenementMapper.toDto(evenement);

        restEvenementMockMvc.perform(post("/api/evenements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(evenementDTO)))
            .andExpect(status().isBadRequest());

        List<Evenement> evenementList = evenementRepository.findAll();
        assertThat(evenementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEvenements() throws Exception {
        // Initialize the database
        evenementRepository.saveAndFlush(evenement);

        // Get all the evenementList
        restEvenementMockMvc.perform(get("/api/evenements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evenement.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].islock").value(hasItem(DEFAULT_ISLOCK.booleanValue())))
            .andExpect(jsonPath("$.[*].lockdelay").value(hasItem(DEFAULT_LOCKDELAY.toString())))
            .andExpect(jsonPath("$.[*].islimited").value(hasItem(DEFAULT_ISLIMITED.booleanValue())))
            .andExpect(jsonPath("$.[*].limiteddelay").value(hasItem(DEFAULT_LIMITEDDELAY.toString())))
            .andExpect(jsonPath("$.[*].limitedbooking").value(hasItem(DEFAULT_LIMITEDBOOKING)))
            .andExpect(jsonPath("$.[*].startdate").value(hasItem(DEFAULT_STARTDATE.toString())))
            .andExpect(jsonPath("$.[*].enddate").value(hasItem(DEFAULT_ENDDATE.toString())))
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
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].othercontacts").value(hasItem(DEFAULT_OTHERCONTACTS.toString())))
            .andExpect(jsonPath("$.[*].otherfields").value(hasItem(DEFAULT_OTHERFIELDS.toString())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getEvenement() throws Exception {
        // Initialize the database
        evenementRepository.saveAndFlush(evenement);

        // Get the evenement
        restEvenementMockMvc.perform(get("/api/evenements/{id}", evenement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evenement.getId().intValue()))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.islock").value(DEFAULT_ISLOCK.booleanValue()))
            .andExpect(jsonPath("$.lockdelay").value(DEFAULT_LOCKDELAY.toString()))
            .andExpect(jsonPath("$.islimited").value(DEFAULT_ISLIMITED.booleanValue()))
            .andExpect(jsonPath("$.limiteddelay").value(DEFAULT_LIMITEDDELAY.toString()))
            .andExpect(jsonPath("$.limitedbooking").value(DEFAULT_LIMITEDBOOKING))
            .andExpect(jsonPath("$.startdate").value(DEFAULT_STARTDATE.toString()))
            .andExpect(jsonPath("$.enddate").value(DEFAULT_ENDDATE.toString()))
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
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.othercontacts").value(DEFAULT_OTHERCONTACTS.toString()))
            .andExpect(jsonPath("$.otherfields").value(DEFAULT_OTHERFIELDS.toString()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEvenement() throws Exception {
        // Get the evenement
        restEvenementMockMvc.perform(get("/api/evenements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEvenement() throws Exception {
        // Initialize the database
        evenementRepository.saveAndFlush(evenement);

        int databaseSizeBeforeUpdate = evenementRepository.findAll().size();

        // Update the evenement
        Evenement updatedEvenement = evenementRepository.findById(evenement.getId()).get();
        // Disconnect from session so that the updates on updatedEvenement are not directly saved in db
        em.detach(updatedEvenement);
        updatedEvenement
            .categoryid(UPDATED_CATEGORYID)
            .userid(UPDATED_USERID)
            .productid(UPDATED_PRODUCTID)
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .islock(UPDATED_ISLOCK)
            .lockdelay(UPDATED_LOCKDELAY)
            .islimited(UPDATED_ISLIMITED)
            .limiteddelay(UPDATED_LIMITEDDELAY)
            .limitedbooking(UPDATED_LIMITEDBOOKING)
            .startdate(UPDATED_STARTDATE)
            .enddate(UPDATED_ENDDATE)
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
            .url(UPDATED_URL)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        EvenementDTO evenementDTO = evenementMapper.toDto(updatedEvenement);

        restEvenementMockMvc.perform(put("/api/evenements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(evenementDTO)))
            .andExpect(status().isOk());

        // Validate the Evenement in the database
        List<Evenement> evenementList = evenementRepository.findAll();
        assertThat(evenementList).hasSize(databaseSizeBeforeUpdate);
        Evenement testEvenement = evenementList.get(evenementList.size() - 1);
        assertThat(testEvenement.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testEvenement.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testEvenement.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testEvenement.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testEvenement.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEvenement.isIslock()).isEqualTo(UPDATED_ISLOCK);
        assertThat(testEvenement.getLockdelay()).isEqualTo(UPDATED_LOCKDELAY);
        assertThat(testEvenement.isIslimited()).isEqualTo(UPDATED_ISLIMITED);
        assertThat(testEvenement.getLimiteddelay()).isEqualTo(UPDATED_LIMITEDDELAY);
        assertThat(testEvenement.getLimitedbooking()).isEqualTo(UPDATED_LIMITEDBOOKING);
        assertThat(testEvenement.getStartdate()).isEqualTo(UPDATED_STARTDATE);
        assertThat(testEvenement.getEnddate()).isEqualTo(UPDATED_ENDDATE);
        assertThat(testEvenement.getAbout()).isEqualTo(UPDATED_ABOUT);
        assertThat(testEvenement.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testEvenement.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testEvenement.getTagcolor()).isEqualTo(UPDATED_TAGCOLOR);
        assertThat(testEvenement.getPostalcode()).isEqualTo(UPDATED_POSTALCODE);
        assertThat(testEvenement.getPhones()).isEqualTo(UPDATED_PHONES);
        assertThat(testEvenement.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testEvenement.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testEvenement.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testEvenement.getGplus()).isEqualTo(UPDATED_GPLUS);
        assertThat(testEvenement.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testEvenement.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testEvenement.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEvenement.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testEvenement.getOthercontacts()).isEqualTo(UPDATED_OTHERCONTACTS);
        assertThat(testEvenement.getOtherfields()).isEqualTo(UPDATED_OTHERFIELDS);
        assertThat(testEvenement.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testEvenement.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingEvenement() throws Exception {
        int databaseSizeBeforeUpdate = evenementRepository.findAll().size();

        // Create the Evenement
        EvenementDTO evenementDTO = evenementMapper.toDto(evenement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvenementMockMvc.perform(put("/api/evenements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(evenementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Evenement in the database
        List<Evenement> evenementList = evenementRepository.findAll();
        assertThat(evenementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEvenement() throws Exception {
        // Initialize the database
        evenementRepository.saveAndFlush(evenement);

        int databaseSizeBeforeDelete = evenementRepository.findAll().size();

        // Delete the evenement
        restEvenementMockMvc.perform(delete("/api/evenements/{id}", evenement.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Evenement> evenementList = evenementRepository.findAll();
        assertThat(evenementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
