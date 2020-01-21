package com.web.rest;

import com.EbusinesApp;
import com.domain.Reviews;
import com.repository.ReviewsRepository;
import com.service.ReviewsService;
import com.service.dto.ReviewsDTO;
import com.service.mapper.ReviewsMapper;
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
 * Integration tests for the {@link ReviewsResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class ReviewsResourceIT {

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

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private ReviewsMapper reviewsMapper;

    @Autowired
    private ReviewsService reviewsService;

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

    private MockMvc restReviewsMockMvc;

    private Reviews reviews;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReviewsResource reviewsResource = new ReviewsResource(reviewsService);
        this.restReviewsMockMvc = MockMvcBuilders.standaloneSetup(reviewsResource)
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
    public static Reviews createEntity(EntityManager em) {
        Reviews reviews = new Reviews()
            .slug(DEFAULT_SLUG)
            .userid(DEFAULT_USERID)
            .categoryid(DEFAULT_CATEGORYID)
            .productid(DEFAULT_PRODUCTID)
            .tourid(DEFAULT_TOURID)
            .tourgroupid(DEFAULT_TOURGROUPID)
            .evenementid(DEFAULT_EVENEMENTID)
            .text(DEFAULT_TEXT)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return reviews;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reviews createUpdatedEntity(EntityManager em) {
        Reviews reviews = new Reviews()
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .evenementid(UPDATED_EVENEMENTID)
            .text(UPDATED_TEXT)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return reviews;
    }

    @BeforeEach
    public void initTest() {
        reviews = createEntity(em);
    }

    @Test
    @Transactional
    public void createReviews() throws Exception {
        int databaseSizeBeforeCreate = reviewsRepository.findAll().size();

        // Create the Reviews
        ReviewsDTO reviewsDTO = reviewsMapper.toDto(reviews);
        restReviewsMockMvc.perform(post("/api/reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reviewsDTO)))
            .andExpect(status().isCreated());

        // Validate the Reviews in the database
        List<Reviews> reviewsList = reviewsRepository.findAll();
        assertThat(reviewsList).hasSize(databaseSizeBeforeCreate + 1);
        Reviews testReviews = reviewsList.get(reviewsList.size() - 1);
        assertThat(testReviews.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testReviews.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testReviews.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testReviews.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testReviews.getTourid()).isEqualTo(DEFAULT_TOURID);
        assertThat(testReviews.getTourgroupid()).isEqualTo(DEFAULT_TOURGROUPID);
        assertThat(testReviews.getEvenementid()).isEqualTo(DEFAULT_EVENEMENTID);
        assertThat(testReviews.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testReviews.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testReviews.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createReviewsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reviewsRepository.findAll().size();

        // Create the Reviews with an existing ID
        reviews.setId(1L);
        ReviewsDTO reviewsDTO = reviewsMapper.toDto(reviews);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReviewsMockMvc.perform(post("/api/reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reviewsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reviews in the database
        List<Reviews> reviewsList = reviewsRepository.findAll();
        assertThat(reviewsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = reviewsRepository.findAll().size();
        // set the field null
        reviews.setSlug(null);

        // Create the Reviews, which fails.
        ReviewsDTO reviewsDTO = reviewsMapper.toDto(reviews);

        restReviewsMockMvc.perform(post("/api/reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reviewsDTO)))
            .andExpect(status().isBadRequest());

        List<Reviews> reviewsList = reviewsRepository.findAll();
        assertThat(reviewsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUseridIsRequired() throws Exception {
        int databaseSizeBeforeTest = reviewsRepository.findAll().size();
        // set the field null
        reviews.setUserid(null);

        // Create the Reviews, which fails.
        ReviewsDTO reviewsDTO = reviewsMapper.toDto(reviews);

        restReviewsMockMvc.perform(post("/api/reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reviewsDTO)))
            .andExpect(status().isBadRequest());

        List<Reviews> reviewsList = reviewsRepository.findAll();
        assertThat(reviewsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReviews() throws Exception {
        // Initialize the database
        reviewsRepository.saveAndFlush(reviews);

        // Get all the reviewsList
        restReviewsMockMvc.perform(get("/api/reviews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reviews.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].tourid").value(hasItem(DEFAULT_TOURID)))
            .andExpect(jsonPath("$.[*].tourgroupid").value(hasItem(DEFAULT_TOURGROUPID)))
            .andExpect(jsonPath("$.[*].evenementid").value(hasItem(DEFAULT_EVENEMENTID)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getReviews() throws Exception {
        // Initialize the database
        reviewsRepository.saveAndFlush(reviews);

        // Get the reviews
        restReviewsMockMvc.perform(get("/api/reviews/{id}", reviews.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reviews.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.tourid").value(DEFAULT_TOURID))
            .andExpect(jsonPath("$.tourgroupid").value(DEFAULT_TOURGROUPID))
            .andExpect(jsonPath("$.evenementid").value(DEFAULT_EVENEMENTID))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReviews() throws Exception {
        // Get the reviews
        restReviewsMockMvc.perform(get("/api/reviews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReviews() throws Exception {
        // Initialize the database
        reviewsRepository.saveAndFlush(reviews);

        int databaseSizeBeforeUpdate = reviewsRepository.findAll().size();

        // Update the reviews
        Reviews updatedReviews = reviewsRepository.findById(reviews.getId()).get();
        // Disconnect from session so that the updates on updatedReviews are not directly saved in db
        em.detach(updatedReviews);
        updatedReviews
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .evenementid(UPDATED_EVENEMENTID)
            .text(UPDATED_TEXT)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        ReviewsDTO reviewsDTO = reviewsMapper.toDto(updatedReviews);

        restReviewsMockMvc.perform(put("/api/reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reviewsDTO)))
            .andExpect(status().isOk());

        // Validate the Reviews in the database
        List<Reviews> reviewsList = reviewsRepository.findAll();
        assertThat(reviewsList).hasSize(databaseSizeBeforeUpdate);
        Reviews testReviews = reviewsList.get(reviewsList.size() - 1);
        assertThat(testReviews.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testReviews.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testReviews.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testReviews.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testReviews.getTourid()).isEqualTo(UPDATED_TOURID);
        assertThat(testReviews.getTourgroupid()).isEqualTo(UPDATED_TOURGROUPID);
        assertThat(testReviews.getEvenementid()).isEqualTo(UPDATED_EVENEMENTID);
        assertThat(testReviews.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testReviews.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testReviews.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingReviews() throws Exception {
        int databaseSizeBeforeUpdate = reviewsRepository.findAll().size();

        // Create the Reviews
        ReviewsDTO reviewsDTO = reviewsMapper.toDto(reviews);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReviewsMockMvc.perform(put("/api/reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reviewsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reviews in the database
        List<Reviews> reviewsList = reviewsRepository.findAll();
        assertThat(reviewsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReviews() throws Exception {
        // Initialize the database
        reviewsRepository.saveAndFlush(reviews);

        int databaseSizeBeforeDelete = reviewsRepository.findAll().size();

        // Delete the reviews
        restReviewsMockMvc.perform(delete("/api/reviews/{id}", reviews.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Reviews> reviewsList = reviewsRepository.findAll();
        assertThat(reviewsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
