package com.web.rest;

import com.EbusinesApp;
import com.domain.Actus;
import com.repository.ActusRepository;
import com.service.ActusService;
import com.service.dto.ActusDTO;
import com.service.mapper.ActusMapper;
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

/**
 * Integration tests for the {@link ActusResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class ActusResourceIT {

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ABREV = "AAAAAAAAAA";
    private static final String UPDATED_ABREV = "BBBBBBBBBB";

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_DETAILS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ActusRepository actusRepository;

    @Autowired
    private ActusMapper actusMapper;

    @Autowired
    private ActusService actusService;

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

    private MockMvc restActusMockMvc;

    private Actus actus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActusResource actusResource = new ActusResource(actusService);
        this.restActusMockMvc = MockMvcBuilders.standaloneSetup(actusResource)
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
    public static Actus createEntity(EntityManager em) {
        Actus actus = new Actus()
            .slug(DEFAULT_SLUG)
            .name(DEFAULT_NAME)
            .abrev(DEFAULT_ABREV)
            .userid(DEFAULT_USERID)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .details(DEFAULT_DETAILS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return actus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Actus createUpdatedEntity(EntityManager em) {
        Actus actus = new Actus()
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .abrev(UPDATED_ABREV)
            .userid(UPDATED_USERID)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .details(UPDATED_DETAILS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return actus;
    }

    @BeforeEach
    public void initTest() {
        actus = createEntity(em);
    }

    @Test
    @Transactional
    public void createActus() throws Exception {
        int databaseSizeBeforeCreate = actusRepository.findAll().size();

        // Create the Actus
        ActusDTO actusDTO = actusMapper.toDto(actus);
        restActusMockMvc.perform(post("/api/actuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actusDTO)))
            .andExpect(status().isCreated());

        // Validate the Actus in the database
        List<Actus> actusList = actusRepository.findAll();
        assertThat(actusList).hasSize(databaseSizeBeforeCreate + 1);
        Actus testActus = actusList.get(actusList.size() - 1);
        assertThat(testActus.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testActus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testActus.getAbrev()).isEqualTo(DEFAULT_ABREV);
        assertThat(testActus.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testActus.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testActus.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testActus.getDetails()).isEqualTo(DEFAULT_DETAILS);
        assertThat(testActus.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testActus.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createActusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = actusRepository.findAll().size();

        // Create the Actus with an existing ID
        actus.setId(1L);
        ActusDTO actusDTO = actusMapper.toDto(actus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActusMockMvc.perform(post("/api/actuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actus in the database
        List<Actus> actusList = actusRepository.findAll();
        assertThat(actusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = actusRepository.findAll().size();
        // set the field null
        actus.setSlug(null);

        // Create the Actus, which fails.
        ActusDTO actusDTO = actusMapper.toDto(actus);

        restActusMockMvc.perform(post("/api/actuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actusDTO)))
            .andExpect(status().isBadRequest());

        List<Actus> actusList = actusRepository.findAll();
        assertThat(actusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllActuses() throws Exception {
        // Initialize the database
        actusRepository.saveAndFlush(actus);

        // Get all the actusList
        restActusMockMvc.perform(get("/api/actuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(actus.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].abrev").value(hasItem(DEFAULT_ABREV)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].details").value(hasItem(DEFAULT_DETAILS)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getActus() throws Exception {
        // Initialize the database
        actusRepository.saveAndFlush(actus);

        // Get the actus
        restActusMockMvc.perform(get("/api/actuses/{id}", actus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(actus.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.abrev").value(DEFAULT_ABREV))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.details").value(DEFAULT_DETAILS))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingActus() throws Exception {
        // Get the actus
        restActusMockMvc.perform(get("/api/actuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActus() throws Exception {
        // Initialize the database
        actusRepository.saveAndFlush(actus);

        int databaseSizeBeforeUpdate = actusRepository.findAll().size();

        // Update the actus
        Actus updatedActus = actusRepository.findById(actus.getId()).get();
        // Disconnect from session so that the updates on updatedActus are not directly saved in db
        em.detach(updatedActus);
        updatedActus
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .abrev(UPDATED_ABREV)
            .userid(UPDATED_USERID)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .details(UPDATED_DETAILS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        ActusDTO actusDTO = actusMapper.toDto(updatedActus);

        restActusMockMvc.perform(put("/api/actuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actusDTO)))
            .andExpect(status().isOk());

        // Validate the Actus in the database
        List<Actus> actusList = actusRepository.findAll();
        assertThat(actusList).hasSize(databaseSizeBeforeUpdate);
        Actus testActus = actusList.get(actusList.size() - 1);
        assertThat(testActus.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testActus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testActus.getAbrev()).isEqualTo(UPDATED_ABREV);
        assertThat(testActus.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testActus.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testActus.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testActus.getDetails()).isEqualTo(UPDATED_DETAILS);
        assertThat(testActus.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testActus.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingActus() throws Exception {
        int databaseSizeBeforeUpdate = actusRepository.findAll().size();

        // Create the Actus
        ActusDTO actusDTO = actusMapper.toDto(actus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActusMockMvc.perform(put("/api/actuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actus in the database
        List<Actus> actusList = actusRepository.findAll();
        assertThat(actusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActus() throws Exception {
        // Initialize the database
        actusRepository.saveAndFlush(actus);

        int databaseSizeBeforeDelete = actusRepository.findAll().size();

        // Delete the actus
        restActusMockMvc.perform(delete("/api/actuses/{id}", actus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Actus> actusList = actusRepository.findAll();
        assertThat(actusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
