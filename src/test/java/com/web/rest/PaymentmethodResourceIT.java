package com.web.rest;

import com.EbusinessApp;
import com.domain.Paymentmethod;
import com.repository.PaymentmethodRepository;
import com.service.PaymentmethodService;
import com.service.dto.PaymentmethodDTO;
import com.service.mapper.PaymentmethodMapper;
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
 * Integration tests for the {@link PaymentmethodResource} REST controller.
 */
@SpringBootTest(classes = EbusinessApp.class)
public class PaymentmethodResourceIT {

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_LOGO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_LOGODATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGODATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGODATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGODATA_CONTENT_TYPE = "image/png";

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PaymentmethodRepository paymentmethodRepository;

    @Autowired
    private PaymentmethodMapper paymentmethodMapper;

    @Autowired
    private PaymentmethodService paymentmethodService;

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

    private MockMvc restPaymentmethodMockMvc;

    private Paymentmethod paymentmethod;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymentmethodResource paymentmethodResource = new PaymentmethodResource(paymentmethodService);
        this.restPaymentmethodMockMvc = MockMvcBuilders.standaloneSetup(paymentmethodResource)
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
    public static Paymentmethod createEntity(EntityManager em) {
        Paymentmethod paymentmethod = new Paymentmethod()
            .slug(DEFAULT_SLUG)
            .name(DEFAULT_NAME)
            .logo(DEFAULT_LOGO)
            .logodata(DEFAULT_LOGODATA)
            .logodataContentType(DEFAULT_LOGODATA_CONTENT_TYPE)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return paymentmethod;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paymentmethod createUpdatedEntity(EntityManager em) {
        Paymentmethod paymentmethod = new Paymentmethod()
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .logo(UPDATED_LOGO)
            .logodata(UPDATED_LOGODATA)
            .logodataContentType(UPDATED_LOGODATA_CONTENT_TYPE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return paymentmethod;
    }

    @BeforeEach
    public void initTest() {
        paymentmethod = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentmethod() throws Exception {
        int databaseSizeBeforeCreate = paymentmethodRepository.findAll().size();

        // Create the Paymentmethod
        PaymentmethodDTO paymentmethodDTO = paymentmethodMapper.toDto(paymentmethod);
        restPaymentmethodMockMvc.perform(post("/api/paymentmethods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentmethodDTO)))
            .andExpect(status().isCreated());

        // Validate the Paymentmethod in the database
        List<Paymentmethod> paymentmethodList = paymentmethodRepository.findAll();
        assertThat(paymentmethodList).hasSize(databaseSizeBeforeCreate + 1);
        Paymentmethod testPaymentmethod = paymentmethodList.get(paymentmethodList.size() - 1);
        assertThat(testPaymentmethod.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testPaymentmethod.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPaymentmethod.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testPaymentmethod.getLogodata()).isEqualTo(DEFAULT_LOGODATA);
        assertThat(testPaymentmethod.getLogodataContentType()).isEqualTo(DEFAULT_LOGODATA_CONTENT_TYPE);
        assertThat(testPaymentmethod.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testPaymentmethod.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createPaymentmethodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentmethodRepository.findAll().size();

        // Create the Paymentmethod with an existing ID
        paymentmethod.setId(1L);
        PaymentmethodDTO paymentmethodDTO = paymentmethodMapper.toDto(paymentmethod);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentmethodMockMvc.perform(post("/api/paymentmethods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentmethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paymentmethod in the database
        List<Paymentmethod> paymentmethodList = paymentmethodRepository.findAll();
        assertThat(paymentmethodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentmethodRepository.findAll().size();
        // set the field null
        paymentmethod.setSlug(null);

        // Create the Paymentmethod, which fails.
        PaymentmethodDTO paymentmethodDTO = paymentmethodMapper.toDto(paymentmethod);

        restPaymentmethodMockMvc.perform(post("/api/paymentmethods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentmethodDTO)))
            .andExpect(status().isBadRequest());

        List<Paymentmethod> paymentmethodList = paymentmethodRepository.findAll();
        assertThat(paymentmethodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaymentmethods() throws Exception {
        // Initialize the database
        paymentmethodRepository.saveAndFlush(paymentmethod);

        // Get all the paymentmethodList
        restPaymentmethodMockMvc.perform(get("/api/paymentmethods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentmethod.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.[*].logodataContentType").value(hasItem(DEFAULT_LOGODATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logodata").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGODATA))))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getPaymentmethod() throws Exception {
        // Initialize the database
        paymentmethodRepository.saveAndFlush(paymentmethod);

        // Get the paymentmethod
        restPaymentmethodMockMvc.perform(get("/api/paymentmethods/{id}", paymentmethod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentmethod.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.logo").value(DEFAULT_LOGO))
            .andExpect(jsonPath("$.logodataContentType").value(DEFAULT_LOGODATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.logodata").value(Base64Utils.encodeToString(DEFAULT_LOGODATA)))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentmethod() throws Exception {
        // Get the paymentmethod
        restPaymentmethodMockMvc.perform(get("/api/paymentmethods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentmethod() throws Exception {
        // Initialize the database
        paymentmethodRepository.saveAndFlush(paymentmethod);

        int databaseSizeBeforeUpdate = paymentmethodRepository.findAll().size();

        // Update the paymentmethod
        Paymentmethod updatedPaymentmethod = paymentmethodRepository.findById(paymentmethod.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentmethod are not directly saved in db
        em.detach(updatedPaymentmethod);
        updatedPaymentmethod
            .slug(UPDATED_SLUG)
            .name(UPDATED_NAME)
            .logo(UPDATED_LOGO)
            .logodata(UPDATED_LOGODATA)
            .logodataContentType(UPDATED_LOGODATA_CONTENT_TYPE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        PaymentmethodDTO paymentmethodDTO = paymentmethodMapper.toDto(updatedPaymentmethod);

        restPaymentmethodMockMvc.perform(put("/api/paymentmethods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentmethodDTO)))
            .andExpect(status().isOk());

        // Validate the Paymentmethod in the database
        List<Paymentmethod> paymentmethodList = paymentmethodRepository.findAll();
        assertThat(paymentmethodList).hasSize(databaseSizeBeforeUpdate);
        Paymentmethod testPaymentmethod = paymentmethodList.get(paymentmethodList.size() - 1);
        assertThat(testPaymentmethod.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testPaymentmethod.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPaymentmethod.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testPaymentmethod.getLogodata()).isEqualTo(UPDATED_LOGODATA);
        assertThat(testPaymentmethod.getLogodataContentType()).isEqualTo(UPDATED_LOGODATA_CONTENT_TYPE);
        assertThat(testPaymentmethod.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testPaymentmethod.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentmethod() throws Exception {
        int databaseSizeBeforeUpdate = paymentmethodRepository.findAll().size();

        // Create the Paymentmethod
        PaymentmethodDTO paymentmethodDTO = paymentmethodMapper.toDto(paymentmethod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentmethodMockMvc.perform(put("/api/paymentmethods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentmethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paymentmethod in the database
        List<Paymentmethod> paymentmethodList = paymentmethodRepository.findAll();
        assertThat(paymentmethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymentmethod() throws Exception {
        // Initialize the database
        paymentmethodRepository.saveAndFlush(paymentmethod);

        int databaseSizeBeforeDelete = paymentmethodRepository.findAll().size();

        // Delete the paymentmethod
        restPaymentmethodMockMvc.perform(delete("/api/paymentmethods/{id}", paymentmethod.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Paymentmethod> paymentmethodList = paymentmethodRepository.findAll();
        assertThat(paymentmethodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
