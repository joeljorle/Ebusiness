package com.web.rest;

import com.EbusinesApp;
import com.domain.Ratings;
import com.repository.RatingsRepository;
import com.service.RatingsService;
import com.service.dto.RatingsDTO;
import com.service.mapper.RatingsMapper;
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

/**
 * Integration tests for the {@link RatingsResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class RatingsResourceIT {

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final Integer DEFAULT_USERID = 1;
    private static final Integer UPDATED_USERID = 2;

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;

    private static final Integer DEFAULT_PRODUCTID = 1;
    private static final Integer UPDATED_PRODUCTID = 2;

    private static final Integer DEFAULT_TOURID = 1;
    private static final Integer UPDATED_TOURID = 2;

    private static final Integer DEFAULT_TOURGROUPID = 1;
    private static final Integer UPDATED_TOURGROUPID = 2;

    private static final Integer DEFAULT_EVENEMENTID = 1;
    private static final Integer UPDATED_EVENEMENTID = 2;

    private static final Integer DEFAULT_VALUE = 0;
    private static final Integer UPDATED_VALUE = 1;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private RatingsMapper ratingsMapper;

    @Autowired
    private RatingsService ratingsService;

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

    private MockMvc restRatingsMockMvc;

    private Ratings ratings;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RatingsResource ratingsResource = new RatingsResource(ratingsService);
        this.restRatingsMockMvc = MockMvcBuilders.standaloneSetup(ratingsResource)
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
    public static Ratings createEntity(EntityManager em) {
        Ratings ratings = new Ratings()
            .slug(DEFAULT_SLUG)
            .userid(DEFAULT_USERID)
            .categoryid(DEFAULT_CATEGORYID)
            .productid(DEFAULT_PRODUCTID)
            .tourid(DEFAULT_TOURID)
            .tourgroupid(DEFAULT_TOURGROUPID)
            .evenementid(DEFAULT_EVENEMENTID)
            .value(DEFAULT_VALUE)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return ratings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ratings createUpdatedEntity(EntityManager em) {
        Ratings ratings = new Ratings()
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .evenementid(UPDATED_EVENEMENTID)
            .value(UPDATED_VALUE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return ratings;
    }

    @BeforeEach
    public void initTest() {
        ratings = createEntity(em);
    }

    @Test
    @Transactional
    public void createRatings() throws Exception {
        int databaseSizeBeforeCreate = ratingsRepository.findAll().size();

        // Create the Ratings
        RatingsDTO ratingsDTO = ratingsMapper.toDto(ratings);
        restRatingsMockMvc.perform(post("/api/ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingsDTO)))
            .andExpect(status().isCreated());

        // Validate the Ratings in the database
        List<Ratings> ratingsList = ratingsRepository.findAll();
        assertThat(ratingsList).hasSize(databaseSizeBeforeCreate + 1);
        Ratings testRatings = ratingsList.get(ratingsList.size() - 1);
        assertThat(testRatings.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testRatings.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testRatings.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testRatings.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testRatings.getTourid()).isEqualTo(DEFAULT_TOURID);
        assertThat(testRatings.getTourgroupid()).isEqualTo(DEFAULT_TOURGROUPID);
        assertThat(testRatings.getEvenementid()).isEqualTo(DEFAULT_EVENEMENTID);
        assertThat(testRatings.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testRatings.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testRatings.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createRatingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ratingsRepository.findAll().size();

        // Create the Ratings with an existing ID
        ratings.setId(1L);
        RatingsDTO ratingsDTO = ratingsMapper.toDto(ratings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRatingsMockMvc.perform(post("/api/ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ratings in the database
        List<Ratings> ratingsList = ratingsRepository.findAll();
        assertThat(ratingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = ratingsRepository.findAll().size();
        // set the field null
        ratings.setSlug(null);

        // Create the Ratings, which fails.
        RatingsDTO ratingsDTO = ratingsMapper.toDto(ratings);

        restRatingsMockMvc.perform(post("/api/ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingsDTO)))
            .andExpect(status().isBadRequest());

        List<Ratings> ratingsList = ratingsRepository.findAll();
        assertThat(ratingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUseridIsRequired() throws Exception {
        int databaseSizeBeforeTest = ratingsRepository.findAll().size();
        // set the field null
        ratings.setUserid(null);

        // Create the Ratings, which fails.
        RatingsDTO ratingsDTO = ratingsMapper.toDto(ratings);

        restRatingsMockMvc.perform(post("/api/ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingsDTO)))
            .andExpect(status().isBadRequest());

        List<Ratings> ratingsList = ratingsRepository.findAll();
        assertThat(ratingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRatings() throws Exception {
        // Initialize the database
        ratingsRepository.saveAndFlush(ratings);

        // Get all the ratingsList
        restRatingsMockMvc.perform(get("/api/ratings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ratings.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].tourid").value(hasItem(DEFAULT_TOURID)))
            .andExpect(jsonPath("$.[*].tourgroupid").value(hasItem(DEFAULT_TOURGROUPID)))
            .andExpect(jsonPath("$.[*].evenementid").value(hasItem(DEFAULT_EVENEMENTID)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getRatings() throws Exception {
        // Initialize the database
        ratingsRepository.saveAndFlush(ratings);

        // Get the ratings
        restRatingsMockMvc.perform(get("/api/ratings/{id}", ratings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ratings.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.tourid").value(DEFAULT_TOURID))
            .andExpect(jsonPath("$.tourgroupid").value(DEFAULT_TOURGROUPID))
            .andExpect(jsonPath("$.evenementid").value(DEFAULT_EVENEMENTID))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRatings() throws Exception {
        // Get the ratings
        restRatingsMockMvc.perform(get("/api/ratings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRatings() throws Exception {
        // Initialize the database
        ratingsRepository.saveAndFlush(ratings);

        int databaseSizeBeforeUpdate = ratingsRepository.findAll().size();

        // Update the ratings
        Ratings updatedRatings = ratingsRepository.findById(ratings.getId()).get();
        // Disconnect from session so that the updates on updatedRatings are not directly saved in db
        em.detach(updatedRatings);
        updatedRatings
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .evenementid(UPDATED_EVENEMENTID)
            .value(UPDATED_VALUE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        RatingsDTO ratingsDTO = ratingsMapper.toDto(updatedRatings);

        restRatingsMockMvc.perform(put("/api/ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingsDTO)))
            .andExpect(status().isOk());

        // Validate the Ratings in the database
        List<Ratings> ratingsList = ratingsRepository.findAll();
        assertThat(ratingsList).hasSize(databaseSizeBeforeUpdate);
        Ratings testRatings = ratingsList.get(ratingsList.size() - 1);
        assertThat(testRatings.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testRatings.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testRatings.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testRatings.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testRatings.getTourid()).isEqualTo(UPDATED_TOURID);
        assertThat(testRatings.getTourgroupid()).isEqualTo(UPDATED_TOURGROUPID);
        assertThat(testRatings.getEvenementid()).isEqualTo(UPDATED_EVENEMENTID);
        assertThat(testRatings.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testRatings.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testRatings.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingRatings() throws Exception {
        int databaseSizeBeforeUpdate = ratingsRepository.findAll().size();

        // Create the Ratings
        RatingsDTO ratingsDTO = ratingsMapper.toDto(ratings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRatingsMockMvc.perform(put("/api/ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ratings in the database
        List<Ratings> ratingsList = ratingsRepository.findAll();
        assertThat(ratingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRatings() throws Exception {
        // Initialize the database
        ratingsRepository.saveAndFlush(ratings);

        int databaseSizeBeforeDelete = ratingsRepository.findAll().size();

        // Delete the ratings
        restRatingsMockMvc.perform(delete("/api/ratings/{id}", ratings.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ratings> ratingsList = ratingsRepository.findAll();
        assertThat(ratingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
