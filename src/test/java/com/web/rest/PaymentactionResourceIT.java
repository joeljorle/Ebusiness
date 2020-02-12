package com.web.rest;

import com.EBusinessApp;
import com.domain.Paymentaction;
import com.repository.PaymentactionRepository;
import com.service.PaymentactionService;
import com.service.dto.PaymentactionDTO;
import com.service.mapper.PaymentactionMapper;
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
 * Integration tests for the {@link PaymentactionResource} REST controller.
 */
@SpringBootTest(classes = EBusinessApp.class)
public class PaymentactionResourceIT {

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final Integer DEFAULT_USERID = 1;
    private static final Integer UPDATED_USERID = 2;

    private static final Integer DEFAULT_PAYMENTMETHODID = 1;
    private static final Integer UPDATED_PAYMENTMETHODID = 2;

    private static final Integer DEFAULT_EVENEMENTID = 1;
    private static final Integer UPDATED_EVENEMENTID = 2;

    private static final Integer DEFAULT_PRODUCTID = 1;
    private static final Integer UPDATED_PRODUCTID = 2;

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;

    private static final Instant DEFAULT_EXPIREAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIREAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_1 = "AAAAAAAAAA";
    private static final String UPDATED_CODE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_CODE_2 = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PaymentactionRepository paymentactionRepository;

    @Autowired
    private PaymentactionMapper paymentactionMapper;

    @Autowired
    private PaymentactionService paymentactionService;

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

    private MockMvc restPaymentactionMockMvc;

    private Paymentaction paymentaction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymentactionResource paymentactionResource = new PaymentactionResource(paymentactionService);
        this.restPaymentactionMockMvc = MockMvcBuilders.standaloneSetup(paymentactionResource)
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
    public static Paymentaction createEntity(EntityManager em) {
        Paymentaction paymentaction = new Paymentaction()
            .slug(DEFAULT_SLUG)
            .userid(DEFAULT_USERID)
            .paymentmethodid(DEFAULT_PAYMENTMETHODID)
            .evenementid(DEFAULT_EVENEMENTID)
            .productid(DEFAULT_PRODUCTID)
            .categoryid(DEFAULT_CATEGORYID)
            .expireat(DEFAULT_EXPIREAT)
            .code(DEFAULT_CODE)
            .code1(DEFAULT_CODE_1)
            .code2(DEFAULT_CODE_2)
            .amount(DEFAULT_AMOUNT)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return paymentaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paymentaction createUpdatedEntity(EntityManager em) {
        Paymentaction paymentaction = new Paymentaction()
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .paymentmethodid(UPDATED_PAYMENTMETHODID)
            .evenementid(UPDATED_EVENEMENTID)
            .productid(UPDATED_PRODUCTID)
            .categoryid(UPDATED_CATEGORYID)
            .expireat(UPDATED_EXPIREAT)
            .code(UPDATED_CODE)
            .code1(UPDATED_CODE_1)
            .code2(UPDATED_CODE_2)
            .amount(UPDATED_AMOUNT)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return paymentaction;
    }

    @BeforeEach
    public void initTest() {
        paymentaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentaction() throws Exception {
        int databaseSizeBeforeCreate = paymentactionRepository.findAll().size();

        // Create the Paymentaction
        PaymentactionDTO paymentactionDTO = paymentactionMapper.toDto(paymentaction);
        restPaymentactionMockMvc.perform(post("/api/paymentactions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentactionDTO)))
            .andExpect(status().isCreated());

        // Validate the Paymentaction in the database
        List<Paymentaction> paymentactionList = paymentactionRepository.findAll();
        assertThat(paymentactionList).hasSize(databaseSizeBeforeCreate + 1);
        Paymentaction testPaymentaction = paymentactionList.get(paymentactionList.size() - 1);
        assertThat(testPaymentaction.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testPaymentaction.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testPaymentaction.getPaymentmethodid()).isEqualTo(DEFAULT_PAYMENTMETHODID);
        assertThat(testPaymentaction.getEvenementid()).isEqualTo(DEFAULT_EVENEMENTID);
        assertThat(testPaymentaction.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testPaymentaction.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testPaymentaction.getExpireat()).isEqualTo(DEFAULT_EXPIREAT);
        assertThat(testPaymentaction.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPaymentaction.getCode1()).isEqualTo(DEFAULT_CODE_1);
        assertThat(testPaymentaction.getCode2()).isEqualTo(DEFAULT_CODE_2);
        assertThat(testPaymentaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPaymentaction.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testPaymentaction.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createPaymentactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentactionRepository.findAll().size();

        // Create the Paymentaction with an existing ID
        paymentaction.setId(1L);
        PaymentactionDTO paymentactionDTO = paymentactionMapper.toDto(paymentaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentactionMockMvc.perform(post("/api/paymentactions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paymentaction in the database
        List<Paymentaction> paymentactionList = paymentactionRepository.findAll();
        assertThat(paymentactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentactionRepository.findAll().size();
        // set the field null
        paymentaction.setSlug(null);

        // Create the Paymentaction, which fails.
        PaymentactionDTO paymentactionDTO = paymentactionMapper.toDto(paymentaction);

        restPaymentactionMockMvc.perform(post("/api/paymentactions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentactionDTO)))
            .andExpect(status().isBadRequest());

        List<Paymentaction> paymentactionList = paymentactionRepository.findAll();
        assertThat(paymentactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUseridIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentactionRepository.findAll().size();
        // set the field null
        paymentaction.setUserid(null);

        // Create the Paymentaction, which fails.
        PaymentactionDTO paymentactionDTO = paymentactionMapper.toDto(paymentaction);

        restPaymentactionMockMvc.perform(post("/api/paymentactions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentactionDTO)))
            .andExpect(status().isBadRequest());

        List<Paymentaction> paymentactionList = paymentactionRepository.findAll();
        assertThat(paymentactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaymentactions() throws Exception {
        // Initialize the database
        paymentactionRepository.saveAndFlush(paymentaction);

        // Get all the paymentactionList
        restPaymentactionMockMvc.perform(get("/api/paymentactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].paymentmethodid").value(hasItem(DEFAULT_PAYMENTMETHODID)))
            .andExpect(jsonPath("$.[*].evenementid").value(hasItem(DEFAULT_EVENEMENTID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].expireat").value(hasItem(DEFAULT_EXPIREAT.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].code1").value(hasItem(DEFAULT_CODE_1)))
            .andExpect(jsonPath("$.[*].code2").value(hasItem(DEFAULT_CODE_2)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getPaymentaction() throws Exception {
        // Initialize the database
        paymentactionRepository.saveAndFlush(paymentaction);

        // Get the paymentaction
        restPaymentactionMockMvc.perform(get("/api/paymentactions/{id}", paymentaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentaction.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.paymentmethodid").value(DEFAULT_PAYMENTMETHODID))
            .andExpect(jsonPath("$.evenementid").value(DEFAULT_EVENEMENTID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.expireat").value(DEFAULT_EXPIREAT.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.code1").value(DEFAULT_CODE_1))
            .andExpect(jsonPath("$.code2").value(DEFAULT_CODE_2))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentaction() throws Exception {
        // Get the paymentaction
        restPaymentactionMockMvc.perform(get("/api/paymentactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentaction() throws Exception {
        // Initialize the database
        paymentactionRepository.saveAndFlush(paymentaction);

        int databaseSizeBeforeUpdate = paymentactionRepository.findAll().size();

        // Update the paymentaction
        Paymentaction updatedPaymentaction = paymentactionRepository.findById(paymentaction.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentaction are not directly saved in db
        em.detach(updatedPaymentaction);
        updatedPaymentaction
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .paymentmethodid(UPDATED_PAYMENTMETHODID)
            .evenementid(UPDATED_EVENEMENTID)
            .productid(UPDATED_PRODUCTID)
            .categoryid(UPDATED_CATEGORYID)
            .expireat(UPDATED_EXPIREAT)
            .code(UPDATED_CODE)
            .code1(UPDATED_CODE_1)
            .code2(UPDATED_CODE_2)
            .amount(UPDATED_AMOUNT)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        PaymentactionDTO paymentactionDTO = paymentactionMapper.toDto(updatedPaymentaction);

        restPaymentactionMockMvc.perform(put("/api/paymentactions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentactionDTO)))
            .andExpect(status().isOk());

        // Validate the Paymentaction in the database
        List<Paymentaction> paymentactionList = paymentactionRepository.findAll();
        assertThat(paymentactionList).hasSize(databaseSizeBeforeUpdate);
        Paymentaction testPaymentaction = paymentactionList.get(paymentactionList.size() - 1);
        assertThat(testPaymentaction.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testPaymentaction.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testPaymentaction.getPaymentmethodid()).isEqualTo(UPDATED_PAYMENTMETHODID);
        assertThat(testPaymentaction.getEvenementid()).isEqualTo(UPDATED_EVENEMENTID);
        assertThat(testPaymentaction.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testPaymentaction.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testPaymentaction.getExpireat()).isEqualTo(UPDATED_EXPIREAT);
        assertThat(testPaymentaction.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPaymentaction.getCode1()).isEqualTo(UPDATED_CODE_1);
        assertThat(testPaymentaction.getCode2()).isEqualTo(UPDATED_CODE_2);
        assertThat(testPaymentaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPaymentaction.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testPaymentaction.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentaction() throws Exception {
        int databaseSizeBeforeUpdate = paymentactionRepository.findAll().size();

        // Create the Paymentaction
        PaymentactionDTO paymentactionDTO = paymentactionMapper.toDto(paymentaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentactionMockMvc.perform(put("/api/paymentactions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paymentaction in the database
        List<Paymentaction> paymentactionList = paymentactionRepository.findAll();
        assertThat(paymentactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymentaction() throws Exception {
        // Initialize the database
        paymentactionRepository.saveAndFlush(paymentaction);

        int databaseSizeBeforeDelete = paymentactionRepository.findAll().size();

        // Delete the paymentaction
        restPaymentactionMockMvc.perform(delete("/api/paymentactions/{id}", paymentaction.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Paymentaction> paymentactionList = paymentactionRepository.findAll();
        assertThat(paymentactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
