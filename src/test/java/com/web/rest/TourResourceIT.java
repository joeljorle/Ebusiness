package com.web.rest;

import com.EbusinesApp;
import com.domain.Tour;
import com.repository.TourRepository;
import com.service.TourService;
import com.service.dto.TourDTO;
import com.service.mapper.TourMapper;
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
 * Integration tests for the {@link TourResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class TourResourceIT {

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;

    private static final Integer DEFAULT_USERID = 1;
    private static final Integer UPDATED_USERID = 2;

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

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

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
    private TourRepository tourRepository;

    @Autowired
    private TourMapper tourMapper;

    @Autowired
    private TourService tourService;

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

    private MockMvc restTourMockMvc;

    private Tour tour;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TourResource tourResource = new TourResource(tourService);
        this.restTourMockMvc = MockMvcBuilders.standaloneSetup(tourResource)
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
    public static Tour createEntity(EntityManager em) {
        Tour tour = new Tour()
            .categoryid(DEFAULT_CATEGORYID)
            .userid(DEFAULT_USERID)
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
            .url(DEFAULT_URL)
            .position(DEFAULT_POSITION)
            .othercontacts(DEFAULT_OTHERCONTACTS)
            .otherfields(DEFAULT_OTHERFIELDS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return tour;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tour createUpdatedEntity(EntityManager em) {
        Tour tour = new Tour()
            .categoryid(UPDATED_CATEGORYID)
            .userid(UPDATED_USERID)
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
            .url(UPDATED_URL)
            .position(UPDATED_POSITION)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return tour;
    }

    @BeforeEach
    public void initTest() {
        tour = createEntity(em);
    }

    @Test
    @Transactional
    public void createTour() throws Exception {
        int databaseSizeBeforeCreate = tourRepository.findAll().size();

        // Create the Tour
        TourDTO tourDTO = tourMapper.toDto(tour);
        restTourMockMvc.perform(post("/api/tours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourDTO)))
            .andExpect(status().isCreated());

        // Validate the Tour in the database
        List<Tour> tourList = tourRepository.findAll();
        assertThat(tourList).hasSize(databaseSizeBeforeCreate + 1);
        Tour testTour = tourList.get(tourList.size() - 1);
        assertThat(testTour.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testTour.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testTour.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testTour.getEvenementid()).isEqualTo(DEFAULT_EVENEMENTID);
        assertThat(testTour.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testTour.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTour.isIslock()).isEqualTo(DEFAULT_ISLOCK);
        assertThat(testTour.getLockdelay()).isEqualTo(DEFAULT_LOCKDELAY);
        assertThat(testTour.getAbout()).isEqualTo(DEFAULT_ABOUT);
        assertThat(testTour.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTour.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testTour.getTagcolor()).isEqualTo(DEFAULT_TAGCOLOR);
        assertThat(testTour.getPostalcode()).isEqualTo(DEFAULT_POSTALCODE);
        assertThat(testTour.getPhones()).isEqualTo(DEFAULT_PHONES);
        assertThat(testTour.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testTour.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testTour.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testTour.getGplus()).isEqualTo(DEFAULT_GPLUS);
        assertThat(testTour.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testTour.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testTour.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTour.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testTour.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testTour.getOthercontacts()).isEqualTo(DEFAULT_OTHERCONTACTS);
        assertThat(testTour.getOtherfields()).isEqualTo(DEFAULT_OTHERFIELDS);
        assertThat(testTour.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testTour.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createTourWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tourRepository.findAll().size();

        // Create the Tour with an existing ID
        tour.setId(1L);
        TourDTO tourDTO = tourMapper.toDto(tour);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTourMockMvc.perform(post("/api/tours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tour in the database
        List<Tour> tourList = tourRepository.findAll();
        assertThat(tourList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = tourRepository.findAll().size();
        // set the field null
        tour.setSlug(null);

        // Create the Tour, which fails.
        TourDTO tourDTO = tourMapper.toDto(tour);

        restTourMockMvc.perform(post("/api/tours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourDTO)))
            .andExpect(status().isBadRequest());

        List<Tour> tourList = tourRepository.findAll();
        assertThat(tourList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTours() throws Exception {
        // Initialize the database
        tourRepository.saveAndFlush(tour);

        // Get all the tourList
        restTourMockMvc.perform(get("/api/tours?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tour.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
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
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].othercontacts").value(hasItem(DEFAULT_OTHERCONTACTS.toString())))
            .andExpect(jsonPath("$.[*].otherfields").value(hasItem(DEFAULT_OTHERFIELDS.toString())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getTour() throws Exception {
        // Initialize the database
        tourRepository.saveAndFlush(tour);

        // Get the tour
        restTourMockMvc.perform(get("/api/tours/{id}", tour.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tour.getId().intValue()))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
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
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.othercontacts").value(DEFAULT_OTHERCONTACTS.toString()))
            .andExpect(jsonPath("$.otherfields").value(DEFAULT_OTHERFIELDS.toString()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTour() throws Exception {
        // Get the tour
        restTourMockMvc.perform(get("/api/tours/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTour() throws Exception {
        // Initialize the database
        tourRepository.saveAndFlush(tour);

        int databaseSizeBeforeUpdate = tourRepository.findAll().size();

        // Update the tour
        Tour updatedTour = tourRepository.findById(tour.getId()).get();
        // Disconnect from session so that the updates on updatedTour are not directly saved in db
        em.detach(updatedTour);
        updatedTour
            .categoryid(UPDATED_CATEGORYID)
            .userid(UPDATED_USERID)
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
            .url(UPDATED_URL)
            .position(UPDATED_POSITION)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        TourDTO tourDTO = tourMapper.toDto(updatedTour);

        restTourMockMvc.perform(put("/api/tours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourDTO)))
            .andExpect(status().isOk());

        // Validate the Tour in the database
        List<Tour> tourList = tourRepository.findAll();
        assertThat(tourList).hasSize(databaseSizeBeforeUpdate);
        Tour testTour = tourList.get(tourList.size() - 1);
        assertThat(testTour.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testTour.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testTour.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testTour.getEvenementid()).isEqualTo(UPDATED_EVENEMENTID);
        assertThat(testTour.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testTour.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTour.isIslock()).isEqualTo(UPDATED_ISLOCK);
        assertThat(testTour.getLockdelay()).isEqualTo(UPDATED_LOCKDELAY);
        assertThat(testTour.getAbout()).isEqualTo(UPDATED_ABOUT);
        assertThat(testTour.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTour.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testTour.getTagcolor()).isEqualTo(UPDATED_TAGCOLOR);
        assertThat(testTour.getPostalcode()).isEqualTo(UPDATED_POSTALCODE);
        assertThat(testTour.getPhones()).isEqualTo(UPDATED_PHONES);
        assertThat(testTour.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testTour.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testTour.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testTour.getGplus()).isEqualTo(UPDATED_GPLUS);
        assertThat(testTour.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testTour.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testTour.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTour.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testTour.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testTour.getOthercontacts()).isEqualTo(UPDATED_OTHERCONTACTS);
        assertThat(testTour.getOtherfields()).isEqualTo(UPDATED_OTHERFIELDS);
        assertThat(testTour.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testTour.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingTour() throws Exception {
        int databaseSizeBeforeUpdate = tourRepository.findAll().size();

        // Create the Tour
        TourDTO tourDTO = tourMapper.toDto(tour);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTourMockMvc.perform(put("/api/tours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tourDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tour in the database
        List<Tour> tourList = tourRepository.findAll();
        assertThat(tourList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTour() throws Exception {
        // Initialize the database
        tourRepository.saveAndFlush(tour);

        int databaseSizeBeforeDelete = tourRepository.findAll().size();

        // Delete the tour
        restTourMockMvc.perform(delete("/api/tours/{id}", tour.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tour> tourList = tourRepository.findAll();
        assertThat(tourList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
