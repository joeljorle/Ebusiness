package com.web.rest;

import com.EbusinessApp;
import com.domain.Cherche;
import com.repository.ChercheRepository;
import com.service.ChercheService;
import com.service.dto.ChercheDTO;
import com.service.mapper.ChercheMapper;
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
 * Integration tests for the {@link ChercheResource} REST controller.
 */
@SpringBootTest(classes = EbusinessApp.class)
public class ChercheResourceIT {

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ABREV = "AAAAAAAAAA";
    private static final String UPDATED_ABREV = "BBBBBBBBBB";

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_DETAILS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ChercheRepository chercheRepository;

    @Autowired
    private ChercheMapper chercheMapper;

    @Autowired
    private ChercheService chercheService;

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

    private MockMvc restChercheMockMvc;

    private Cherche cherche;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChercheResource chercheResource = new ChercheResource(chercheService);
        this.restChercheMockMvc = MockMvcBuilders.standaloneSetup(chercheResource)
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
    public static Cherche createEntity(EntityManager em) {
        Cherche cherche = new Cherche()
            .slug(DEFAULT_SLUG)
            .name(DEFAULT_NAME)
            .abrev(DEFAULT_ABREV)
            .userid(DEFAULT_USERID)
            .details(DEFAULT_DETAILS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return cherche;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cherche createUpdatedEntity(EntityManager em) {
        Cherche cherche = new Cherche()
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .abrev(UPDATED_ABREV)
            .userid(UPDATED_USERID)
            .details(UPDATED_DETAILS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return cherche;
    }

    @BeforeEach
    public void initTest() {
        cherche = createEntity(em);
    }

    @Test
    @Transactional
    public void createCherche() throws Exception {
        int databaseSizeBeforeCreate = chercheRepository.findAll().size();

        // Create the Cherche
        ChercheDTO chercheDTO = chercheMapper.toDto(cherche);
        restChercheMockMvc.perform(post("/api/cherches")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chercheDTO)))
            .andExpect(status().isCreated());

        // Validate the Cherche in the database
        List<Cherche> chercheList = chercheRepository.findAll();
        assertThat(chercheList).hasSize(databaseSizeBeforeCreate + 1);
        Cherche testCherche = chercheList.get(chercheList.size() - 1);
        assertThat(testCherche.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testCherche.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCherche.getAbrev()).isEqualTo(DEFAULT_ABREV);
        assertThat(testCherche.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testCherche.getDetails()).isEqualTo(DEFAULT_DETAILS);
        assertThat(testCherche.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testCherche.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createChercheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chercheRepository.findAll().size();

        // Create the Cherche with an existing ID
        cherche.setId(1L);
        ChercheDTO chercheDTO = chercheMapper.toDto(cherche);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChercheMockMvc.perform(post("/api/cherches")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chercheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cherche in the database
        List<Cherche> chercheList = chercheRepository.findAll();
        assertThat(chercheList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = chercheRepository.findAll().size();
        // set the field null
        cherche.setSlug(null);

        // Create the Cherche, which fails.
        ChercheDTO chercheDTO = chercheMapper.toDto(cherche);

        restChercheMockMvc.perform(post("/api/cherches")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chercheDTO)))
            .andExpect(status().isBadRequest());

        List<Cherche> chercheList = chercheRepository.findAll();
        assertThat(chercheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCherches() throws Exception {
        // Initialize the database
        chercheRepository.saveAndFlush(cherche);

        // Get all the chercheList
        restChercheMockMvc.perform(get("/api/cherches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cherche.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].abrev").value(hasItem(DEFAULT_ABREV)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].details").value(hasItem(DEFAULT_DETAILS)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getCherche() throws Exception {
        // Initialize the database
        chercheRepository.saveAndFlush(cherche);

        // Get the cherche
        restChercheMockMvc.perform(get("/api/cherches/{id}", cherche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cherche.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.abrev").value(DEFAULT_ABREV))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.details").value(DEFAULT_DETAILS))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCherche() throws Exception {
        // Get the cherche
        restChercheMockMvc.perform(get("/api/cherches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCherche() throws Exception {
        // Initialize the database
        chercheRepository.saveAndFlush(cherche);

        int databaseSizeBeforeUpdate = chercheRepository.findAll().size();

        // Update the cherche
        Cherche updatedCherche = chercheRepository.findById(cherche.getId()).get();
        // Disconnect from session so that the updates on updatedCherche are not directly saved in db
        em.detach(updatedCherche);
        updatedCherche
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .abrev(UPDATED_ABREV)
            .userid(UPDATED_USERID)
            .details(UPDATED_DETAILS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        ChercheDTO chercheDTO = chercheMapper.toDto(updatedCherche);

        restChercheMockMvc.perform(put("/api/cherches")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chercheDTO)))
            .andExpect(status().isOk());

        // Validate the Cherche in the database
        List<Cherche> chercheList = chercheRepository.findAll();
        assertThat(chercheList).hasSize(databaseSizeBeforeUpdate);
        Cherche testCherche = chercheList.get(chercheList.size() - 1);
        assertThat(testCherche.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testCherche.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCherche.getAbrev()).isEqualTo(UPDATED_ABREV);
        assertThat(testCherche.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testCherche.getDetails()).isEqualTo(UPDATED_DETAILS);
        assertThat(testCherche.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testCherche.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingCherche() throws Exception {
        int databaseSizeBeforeUpdate = chercheRepository.findAll().size();

        // Create the Cherche
        ChercheDTO chercheDTO = chercheMapper.toDto(cherche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChercheMockMvc.perform(put("/api/cherches")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chercheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cherche in the database
        List<Cherche> chercheList = chercheRepository.findAll();
        assertThat(chercheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCherche() throws Exception {
        // Initialize the database
        chercheRepository.saveAndFlush(cherche);

        int databaseSizeBeforeDelete = chercheRepository.findAll().size();

        // Delete the cherche
        restChercheMockMvc.perform(delete("/api/cherches/{id}", cherche.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cherche> chercheList = chercheRepository.findAll();
        assertThat(chercheList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
