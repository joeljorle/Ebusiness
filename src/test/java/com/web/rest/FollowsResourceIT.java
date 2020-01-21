package com.web.rest;

import com.EbusinesApp;
import com.domain.Follows;
import com.repository.FollowsRepository;
import com.service.FollowsService;
import com.service.dto.FollowsDTO;
import com.service.mapper.FollowsMapper;
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
 * Integration tests for the {@link FollowsResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class FollowsResourceIT {

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

    private static final Integer DEFAULT_EVENEMENTID = 1;
    private static final Integer UPDATED_EVENEMENTID = 2;

    private static final Integer DEFAULT_TOURGROUPID = 1;
    private static final Integer UPDATED_TOURGROUPID = 2;

    private static final Boolean DEFAULT_ALERT = false;
    private static final Boolean UPDATED_ALERT = true;

    private static final Boolean DEFAULT_ALERT_EVENEMENT = false;
    private static final Boolean UPDATED_ALERT_EVENEMENT = true;

    private static final Boolean DEFAULT_FOLLOWCHILD = false;
    private static final Boolean UPDATED_FOLLOWCHILD = true;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FollowsRepository followsRepository;

    @Autowired
    private FollowsMapper followsMapper;

    @Autowired
    private FollowsService followsService;

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

    private MockMvc restFollowsMockMvc;

    private Follows follows;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FollowsResource followsResource = new FollowsResource(followsService);
        this.restFollowsMockMvc = MockMvcBuilders.standaloneSetup(followsResource)
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
    public static Follows createEntity(EntityManager em) {
        Follows follows = new Follows()
            .slug(DEFAULT_SLUG)
            .userid(DEFAULT_USERID)
            .categoryid(DEFAULT_CATEGORYID)
            .productid(DEFAULT_PRODUCTID)
            .tourid(DEFAULT_TOURID)
            .evenementid(DEFAULT_EVENEMENTID)
            .tourgroupid(DEFAULT_TOURGROUPID)
            .alert(DEFAULT_ALERT)
            .alertEvenement(DEFAULT_ALERT_EVENEMENT)
            .followchild(DEFAULT_FOLLOWCHILD)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return follows;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Follows createUpdatedEntity(EntityManager em) {
        Follows follows = new Follows()
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .evenementid(UPDATED_EVENEMENTID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .alert(UPDATED_ALERT)
            .alertEvenement(UPDATED_ALERT_EVENEMENT)
            .followchild(UPDATED_FOLLOWCHILD)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return follows;
    }

    @BeforeEach
    public void initTest() {
        follows = createEntity(em);
    }

    @Test
    @Transactional
    public void createFollows() throws Exception {
        int databaseSizeBeforeCreate = followsRepository.findAll().size();

        // Create the Follows
        FollowsDTO followsDTO = followsMapper.toDto(follows);
        restFollowsMockMvc.perform(post("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followsDTO)))
            .andExpect(status().isCreated());

        // Validate the Follows in the database
        List<Follows> followsList = followsRepository.findAll();
        assertThat(followsList).hasSize(databaseSizeBeforeCreate + 1);
        Follows testFollows = followsList.get(followsList.size() - 1);
        assertThat(testFollows.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testFollows.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testFollows.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testFollows.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testFollows.getTourid()).isEqualTo(DEFAULT_TOURID);
        assertThat(testFollows.getEvenementid()).isEqualTo(DEFAULT_EVENEMENTID);
        assertThat(testFollows.getTourgroupid()).isEqualTo(DEFAULT_TOURGROUPID);
        assertThat(testFollows.isAlert()).isEqualTo(DEFAULT_ALERT);
        assertThat(testFollows.isAlertEvenement()).isEqualTo(DEFAULT_ALERT_EVENEMENT);
        assertThat(testFollows.isFollowchild()).isEqualTo(DEFAULT_FOLLOWCHILD);
        assertThat(testFollows.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testFollows.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createFollowsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = followsRepository.findAll().size();

        // Create the Follows with an existing ID
        follows.setId(1L);
        FollowsDTO followsDTO = followsMapper.toDto(follows);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFollowsMockMvc.perform(post("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Follows in the database
        List<Follows> followsList = followsRepository.findAll();
        assertThat(followsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = followsRepository.findAll().size();
        // set the field null
        follows.setSlug(null);

        // Create the Follows, which fails.
        FollowsDTO followsDTO = followsMapper.toDto(follows);

        restFollowsMockMvc.perform(post("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followsDTO)))
            .andExpect(status().isBadRequest());

        List<Follows> followsList = followsRepository.findAll();
        assertThat(followsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUseridIsRequired() throws Exception {
        int databaseSizeBeforeTest = followsRepository.findAll().size();
        // set the field null
        follows.setUserid(null);

        // Create the Follows, which fails.
        FollowsDTO followsDTO = followsMapper.toDto(follows);

        restFollowsMockMvc.perform(post("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followsDTO)))
            .andExpect(status().isBadRequest());

        List<Follows> followsList = followsRepository.findAll();
        assertThat(followsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFollows() throws Exception {
        // Initialize the database
        followsRepository.saveAndFlush(follows);

        // Get all the followsList
        restFollowsMockMvc.perform(get("/api/follows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(follows.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].tourid").value(hasItem(DEFAULT_TOURID)))
            .andExpect(jsonPath("$.[*].evenementid").value(hasItem(DEFAULT_EVENEMENTID)))
            .andExpect(jsonPath("$.[*].tourgroupid").value(hasItem(DEFAULT_TOURGROUPID)))
            .andExpect(jsonPath("$.[*].alert").value(hasItem(DEFAULT_ALERT.booleanValue())))
            .andExpect(jsonPath("$.[*].alertEvenement").value(hasItem(DEFAULT_ALERT_EVENEMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].followchild").value(hasItem(DEFAULT_FOLLOWCHILD.booleanValue())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getFollows() throws Exception {
        // Initialize the database
        followsRepository.saveAndFlush(follows);

        // Get the follows
        restFollowsMockMvc.perform(get("/api/follows/{id}", follows.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(follows.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.tourid").value(DEFAULT_TOURID))
            .andExpect(jsonPath("$.evenementid").value(DEFAULT_EVENEMENTID))
            .andExpect(jsonPath("$.tourgroupid").value(DEFAULT_TOURGROUPID))
            .andExpect(jsonPath("$.alert").value(DEFAULT_ALERT.booleanValue()))
            .andExpect(jsonPath("$.alertEvenement").value(DEFAULT_ALERT_EVENEMENT.booleanValue()))
            .andExpect(jsonPath("$.followchild").value(DEFAULT_FOLLOWCHILD.booleanValue()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFollows() throws Exception {
        // Get the follows
        restFollowsMockMvc.perform(get("/api/follows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFollows() throws Exception {
        // Initialize the database
        followsRepository.saveAndFlush(follows);

        int databaseSizeBeforeUpdate = followsRepository.findAll().size();

        // Update the follows
        Follows updatedFollows = followsRepository.findById(follows.getId()).get();
        // Disconnect from session so that the updates on updatedFollows are not directly saved in db
        em.detach(updatedFollows);
        updatedFollows
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .evenementid(UPDATED_EVENEMENTID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .alert(UPDATED_ALERT)
            .alertEvenement(UPDATED_ALERT_EVENEMENT)
            .followchild(UPDATED_FOLLOWCHILD)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        FollowsDTO followsDTO = followsMapper.toDto(updatedFollows);

        restFollowsMockMvc.perform(put("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followsDTO)))
            .andExpect(status().isOk());

        // Validate the Follows in the database
        List<Follows> followsList = followsRepository.findAll();
        assertThat(followsList).hasSize(databaseSizeBeforeUpdate);
        Follows testFollows = followsList.get(followsList.size() - 1);
        assertThat(testFollows.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testFollows.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testFollows.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testFollows.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testFollows.getTourid()).isEqualTo(UPDATED_TOURID);
        assertThat(testFollows.getEvenementid()).isEqualTo(UPDATED_EVENEMENTID);
        assertThat(testFollows.getTourgroupid()).isEqualTo(UPDATED_TOURGROUPID);
        assertThat(testFollows.isAlert()).isEqualTo(UPDATED_ALERT);
        assertThat(testFollows.isAlertEvenement()).isEqualTo(UPDATED_ALERT_EVENEMENT);
        assertThat(testFollows.isFollowchild()).isEqualTo(UPDATED_FOLLOWCHILD);
        assertThat(testFollows.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testFollows.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingFollows() throws Exception {
        int databaseSizeBeforeUpdate = followsRepository.findAll().size();

        // Create the Follows
        FollowsDTO followsDTO = followsMapper.toDto(follows);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFollowsMockMvc.perform(put("/api/follows")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(followsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Follows in the database
        List<Follows> followsList = followsRepository.findAll();
        assertThat(followsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFollows() throws Exception {
        // Initialize the database
        followsRepository.saveAndFlush(follows);

        int databaseSizeBeforeDelete = followsRepository.findAll().size();

        // Delete the follows
        restFollowsMockMvc.perform(delete("/api/follows/{id}", follows.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Follows> followsList = followsRepository.findAll();
        assertThat(followsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
