package com.web.rest;

import com.EBusinessApp;
import com.domain.Likes;
import com.repository.LikesRepository;
import com.service.LikesService;
import com.service.dto.LikesDTO;
import com.service.mapper.LikesMapper;
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
 * Integration tests for the {@link LikesResource} REST controller.
 */
@SpringBootTest(classes = EBusinessApp.class)
public class LikesResourceIT {

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

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private LikesService likesService;

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

    private MockMvc restLikesMockMvc;

    private Likes likes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LikesResource likesResource = new LikesResource(likesService);
        this.restLikesMockMvc = MockMvcBuilders.standaloneSetup(likesResource)
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
    public static Likes createEntity(EntityManager em) {
        Likes likes = new Likes()
            .slug(DEFAULT_SLUG)
            .userid(DEFAULT_USERID)
            .categoryid(DEFAULT_CATEGORYID)
            .productid(DEFAULT_PRODUCTID)
            .tourid(DEFAULT_TOURID)
            .tourgroupid(DEFAULT_TOURGROUPID)
            .evenementid(DEFAULT_EVENEMENTID)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return likes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Likes createUpdatedEntity(EntityManager em) {
        Likes likes = new Likes()
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .evenementid(UPDATED_EVENEMENTID)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return likes;
    }

    @BeforeEach
    public void initTest() {
        likes = createEntity(em);
    }

    @Test
    @Transactional
    public void createLikes() throws Exception {
        int databaseSizeBeforeCreate = likesRepository.findAll().size();

        // Create the Likes
        LikesDTO likesDTO = likesMapper.toDto(likes);
        restLikesMockMvc.perform(post("/api/likes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(likesDTO)))
            .andExpect(status().isCreated());

        // Validate the Likes in the database
        List<Likes> likesList = likesRepository.findAll();
        assertThat(likesList).hasSize(databaseSizeBeforeCreate + 1);
        Likes testLikes = likesList.get(likesList.size() - 1);
        assertThat(testLikes.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testLikes.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testLikes.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testLikes.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testLikes.getTourid()).isEqualTo(DEFAULT_TOURID);
        assertThat(testLikes.getTourgroupid()).isEqualTo(DEFAULT_TOURGROUPID);
        assertThat(testLikes.getEvenementid()).isEqualTo(DEFAULT_EVENEMENTID);
        assertThat(testLikes.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testLikes.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createLikesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = likesRepository.findAll().size();

        // Create the Likes with an existing ID
        likes.setId(1L);
        LikesDTO likesDTO = likesMapper.toDto(likes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLikesMockMvc.perform(post("/api/likes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(likesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Likes in the database
        List<Likes> likesList = likesRepository.findAll();
        assertThat(likesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = likesRepository.findAll().size();
        // set the field null
        likes.setSlug(null);

        // Create the Likes, which fails.
        LikesDTO likesDTO = likesMapper.toDto(likes);

        restLikesMockMvc.perform(post("/api/likes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(likesDTO)))
            .andExpect(status().isBadRequest());

        List<Likes> likesList = likesRepository.findAll();
        assertThat(likesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUseridIsRequired() throws Exception {
        int databaseSizeBeforeTest = likesRepository.findAll().size();
        // set the field null
        likes.setUserid(null);

        // Create the Likes, which fails.
        LikesDTO likesDTO = likesMapper.toDto(likes);

        restLikesMockMvc.perform(post("/api/likes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(likesDTO)))
            .andExpect(status().isBadRequest());

        List<Likes> likesList = likesRepository.findAll();
        assertThat(likesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLikes() throws Exception {
        // Initialize the database
        likesRepository.saveAndFlush(likes);

        // Get all the likesList
        restLikesMockMvc.perform(get("/api/likes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(likes.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].tourid").value(hasItem(DEFAULT_TOURID)))
            .andExpect(jsonPath("$.[*].tourgroupid").value(hasItem(DEFAULT_TOURGROUPID)))
            .andExpect(jsonPath("$.[*].evenementid").value(hasItem(DEFAULT_EVENEMENTID)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getLikes() throws Exception {
        // Initialize the database
        likesRepository.saveAndFlush(likes);

        // Get the likes
        restLikesMockMvc.perform(get("/api/likes/{id}", likes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(likes.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.tourid").value(DEFAULT_TOURID))
            .andExpect(jsonPath("$.tourgroupid").value(DEFAULT_TOURGROUPID))
            .andExpect(jsonPath("$.evenementid").value(DEFAULT_EVENEMENTID))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLikes() throws Exception {
        // Get the likes
        restLikesMockMvc.perform(get("/api/likes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLikes() throws Exception {
        // Initialize the database
        likesRepository.saveAndFlush(likes);

        int databaseSizeBeforeUpdate = likesRepository.findAll().size();

        // Update the likes
        Likes updatedLikes = likesRepository.findById(likes.getId()).get();
        // Disconnect from session so that the updates on updatedLikes are not directly saved in db
        em.detach(updatedLikes);
        updatedLikes
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .evenementid(UPDATED_EVENEMENTID)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        LikesDTO likesDTO = likesMapper.toDto(updatedLikes);

        restLikesMockMvc.perform(put("/api/likes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(likesDTO)))
            .andExpect(status().isOk());

        // Validate the Likes in the database
        List<Likes> likesList = likesRepository.findAll();
        assertThat(likesList).hasSize(databaseSizeBeforeUpdate);
        Likes testLikes = likesList.get(likesList.size() - 1);
        assertThat(testLikes.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testLikes.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testLikes.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testLikes.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testLikes.getTourid()).isEqualTo(UPDATED_TOURID);
        assertThat(testLikes.getTourgroupid()).isEqualTo(UPDATED_TOURGROUPID);
        assertThat(testLikes.getEvenementid()).isEqualTo(UPDATED_EVENEMENTID);
        assertThat(testLikes.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testLikes.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingLikes() throws Exception {
        int databaseSizeBeforeUpdate = likesRepository.findAll().size();

        // Create the Likes
        LikesDTO likesDTO = likesMapper.toDto(likes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLikesMockMvc.perform(put("/api/likes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(likesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Likes in the database
        List<Likes> likesList = likesRepository.findAll();
        assertThat(likesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLikes() throws Exception {
        // Initialize the database
        likesRepository.saveAndFlush(likes);

        int databaseSizeBeforeDelete = likesRepository.findAll().size();

        // Delete the likes
        restLikesMockMvc.perform(delete("/api/likes/{id}", likes.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Likes> likesList = likesRepository.findAll();
        assertThat(likesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
