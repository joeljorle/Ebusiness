package com.web.rest;

import com.EBusinessApp;
import com.domain.Paymentcategory;
import com.repository.PaymentcategoryRepository;
import com.service.PaymentcategoryService;
import com.service.dto.PaymentcategoryDTO;
import com.service.mapper.PaymentcategoryMapper;
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
 * Integration tests for the {@link PaymentcategoryResource} REST controller.
 */
@SpringBootTest(classes = EBusinessApp.class)
public class PaymentcategoryResourceIT {

    private static final Integer DEFAULT_PAYMENTMETHODID = 1;
    private static final Integer UPDATED_PAYMENTMETHODID = 2;

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_APIURL = "AAAAAAAAAA";
    private static final String UPDATED_APIURL = "BBBBBBBBBB";

    private static final String DEFAULT_APIKEY = "AAAAAAAAAA";
    private static final String UPDATED_APIKEY = "BBBBBBBBBB";

    private static final String DEFAULT_KEY_2 = "AAAAAAAAAA";
    private static final String UPDATED_KEY_2 = "BBBBBBBBBB";

    private static final String DEFAULT_KEY_3 = "AAAAAAAAAA";
    private static final String UPDATED_KEY_3 = "BBBBBBBBBB";

    private static final String DEFAULT_KEY_4 = "AAAAAAAAAA";
    private static final String UPDATED_KEY_4 = "BBBBBBBBBB";

    private static final String DEFAULT_PHONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PaymentcategoryRepository paymentcategoryRepository;

    @Autowired
    private PaymentcategoryMapper paymentcategoryMapper;

    @Autowired
    private PaymentcategoryService paymentcategoryService;

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

    private MockMvc restPaymentcategoryMockMvc;

    private Paymentcategory paymentcategory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymentcategoryResource paymentcategoryResource = new PaymentcategoryResource(paymentcategoryService);
        this.restPaymentcategoryMockMvc = MockMvcBuilders.standaloneSetup(paymentcategoryResource)
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
    public static Paymentcategory createEntity(EntityManager em) {
        Paymentcategory paymentcategory = new Paymentcategory()
            .paymentmethodid(DEFAULT_PAYMENTMETHODID)
            .categoryid(DEFAULT_CATEGORYID)
            .url(DEFAULT_URL)
            .apiurl(DEFAULT_APIURL)
            .apikey(DEFAULT_APIKEY)
            .key2(DEFAULT_KEY_2)
            .key3(DEFAULT_KEY_3)
            .key4(DEFAULT_KEY_4)
            .phonenumber(DEFAULT_PHONENUMBER)
            .chanel(DEFAULT_CHANEL)
            .code(DEFAULT_CODE)
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return paymentcategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paymentcategory createUpdatedEntity(EntityManager em) {
        Paymentcategory paymentcategory = new Paymentcategory()
            .paymentmethodid(UPDATED_PAYMENTMETHODID)
            .categoryid(UPDATED_CATEGORYID)
            .url(UPDATED_URL)
            .apiurl(UPDATED_APIURL)
            .apikey(UPDATED_APIKEY)
            .key2(UPDATED_KEY_2)
            .key3(UPDATED_KEY_3)
            .key4(UPDATED_KEY_4)
            .phonenumber(UPDATED_PHONENUMBER)
            .chanel(UPDATED_CHANEL)
            .code(UPDATED_CODE)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return paymentcategory;
    }

    @BeforeEach
    public void initTest() {
        paymentcategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentcategory() throws Exception {
        int databaseSizeBeforeCreate = paymentcategoryRepository.findAll().size();

        // Create the Paymentcategory
        PaymentcategoryDTO paymentcategoryDTO = paymentcategoryMapper.toDto(paymentcategory);
        restPaymentcategoryMockMvc.perform(post("/api/paymentcategories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentcategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the Paymentcategory in the database
        List<Paymentcategory> paymentcategoryList = paymentcategoryRepository.findAll();
        assertThat(paymentcategoryList).hasSize(databaseSizeBeforeCreate + 1);
        Paymentcategory testPaymentcategory = paymentcategoryList.get(paymentcategoryList.size() - 1);
        assertThat(testPaymentcategory.getPaymentmethodid()).isEqualTo(DEFAULT_PAYMENTMETHODID);
        assertThat(testPaymentcategory.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testPaymentcategory.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testPaymentcategory.getApiurl()).isEqualTo(DEFAULT_APIURL);
        assertThat(testPaymentcategory.getApikey()).isEqualTo(DEFAULT_APIKEY);
        assertThat(testPaymentcategory.getKey2()).isEqualTo(DEFAULT_KEY_2);
        assertThat(testPaymentcategory.getKey3()).isEqualTo(DEFAULT_KEY_3);
        assertThat(testPaymentcategory.getKey4()).isEqualTo(DEFAULT_KEY_4);
        assertThat(testPaymentcategory.getPhonenumber()).isEqualTo(DEFAULT_PHONENUMBER);
        assertThat(testPaymentcategory.getChanel()).isEqualTo(DEFAULT_CHANEL);
        assertThat(testPaymentcategory.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPaymentcategory.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testPaymentcategory.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testPaymentcategory.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testPaymentcategory.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createPaymentcategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentcategoryRepository.findAll().size();

        // Create the Paymentcategory with an existing ID
        paymentcategory.setId(1L);
        PaymentcategoryDTO paymentcategoryDTO = paymentcategoryMapper.toDto(paymentcategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentcategoryMockMvc.perform(post("/api/paymentcategories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentcategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paymentcategory in the database
        List<Paymentcategory> paymentcategoryList = paymentcategoryRepository.findAll();
        assertThat(paymentcategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCategoryidIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentcategoryRepository.findAll().size();
        // set the field null
        paymentcategory.setCategoryid(null);

        // Create the Paymentcategory, which fails.
        PaymentcategoryDTO paymentcategoryDTO = paymentcategoryMapper.toDto(paymentcategory);

        restPaymentcategoryMockMvc.perform(post("/api/paymentcategories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentcategoryDTO)))
            .andExpect(status().isBadRequest());

        List<Paymentcategory> paymentcategoryList = paymentcategoryRepository.findAll();
        assertThat(paymentcategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaymentcategories() throws Exception {
        // Initialize the database
        paymentcategoryRepository.saveAndFlush(paymentcategory);

        // Get all the paymentcategoryList
        restPaymentcategoryMockMvc.perform(get("/api/paymentcategories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentcategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].paymentmethodid").value(hasItem(DEFAULT_PAYMENTMETHODID)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].apiurl").value(hasItem(DEFAULT_APIURL)))
            .andExpect(jsonPath("$.[*].apikey").value(hasItem(DEFAULT_APIKEY)))
            .andExpect(jsonPath("$.[*].key2").value(hasItem(DEFAULT_KEY_2)))
            .andExpect(jsonPath("$.[*].key3").value(hasItem(DEFAULT_KEY_3)))
            .andExpect(jsonPath("$.[*].key4").value(hasItem(DEFAULT_KEY_4)))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER)))
            .andExpect(jsonPath("$.[*].chanel").value(hasItem(DEFAULT_CHANEL)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getPaymentcategory() throws Exception {
        // Initialize the database
        paymentcategoryRepository.saveAndFlush(paymentcategory);

        // Get the paymentcategory
        restPaymentcategoryMockMvc.perform(get("/api/paymentcategories/{id}", paymentcategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentcategory.getId().intValue()))
            .andExpect(jsonPath("$.paymentmethodid").value(DEFAULT_PAYMENTMETHODID))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.apiurl").value(DEFAULT_APIURL))
            .andExpect(jsonPath("$.apikey").value(DEFAULT_APIKEY))
            .andExpect(jsonPath("$.key2").value(DEFAULT_KEY_2))
            .andExpect(jsonPath("$.key3").value(DEFAULT_KEY_3))
            .andExpect(jsonPath("$.key4").value(DEFAULT_KEY_4))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER))
            .andExpect(jsonPath("$.chanel").value(DEFAULT_CHANEL))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentcategory() throws Exception {
        // Get the paymentcategory
        restPaymentcategoryMockMvc.perform(get("/api/paymentcategories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentcategory() throws Exception {
        // Initialize the database
        paymentcategoryRepository.saveAndFlush(paymentcategory);

        int databaseSizeBeforeUpdate = paymentcategoryRepository.findAll().size();

        // Update the paymentcategory
        Paymentcategory updatedPaymentcategory = paymentcategoryRepository.findById(paymentcategory.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentcategory are not directly saved in db
        em.detach(updatedPaymentcategory);
        updatedPaymentcategory
            .paymentmethodid(UPDATED_PAYMENTMETHODID)
            .categoryid(UPDATED_CATEGORYID)
            .url(UPDATED_URL)
            .apiurl(UPDATED_APIURL)
            .apikey(UPDATED_APIKEY)
            .key2(UPDATED_KEY_2)
            .key3(UPDATED_KEY_3)
            .key4(UPDATED_KEY_4)
            .phonenumber(UPDATED_PHONENUMBER)
            .chanel(UPDATED_CHANEL)
            .code(UPDATED_CODE)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        PaymentcategoryDTO paymentcategoryDTO = paymentcategoryMapper.toDto(updatedPaymentcategory);

        restPaymentcategoryMockMvc.perform(put("/api/paymentcategories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentcategoryDTO)))
            .andExpect(status().isOk());

        // Validate the Paymentcategory in the database
        List<Paymentcategory> paymentcategoryList = paymentcategoryRepository.findAll();
        assertThat(paymentcategoryList).hasSize(databaseSizeBeforeUpdate);
        Paymentcategory testPaymentcategory = paymentcategoryList.get(paymentcategoryList.size() - 1);
        assertThat(testPaymentcategory.getPaymentmethodid()).isEqualTo(UPDATED_PAYMENTMETHODID);
        assertThat(testPaymentcategory.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testPaymentcategory.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testPaymentcategory.getApiurl()).isEqualTo(UPDATED_APIURL);
        assertThat(testPaymentcategory.getApikey()).isEqualTo(UPDATED_APIKEY);
        assertThat(testPaymentcategory.getKey2()).isEqualTo(UPDATED_KEY_2);
        assertThat(testPaymentcategory.getKey3()).isEqualTo(UPDATED_KEY_3);
        assertThat(testPaymentcategory.getKey4()).isEqualTo(UPDATED_KEY_4);
        assertThat(testPaymentcategory.getPhonenumber()).isEqualTo(UPDATED_PHONENUMBER);
        assertThat(testPaymentcategory.getChanel()).isEqualTo(UPDATED_CHANEL);
        assertThat(testPaymentcategory.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPaymentcategory.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testPaymentcategory.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testPaymentcategory.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testPaymentcategory.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentcategory() throws Exception {
        int databaseSizeBeforeUpdate = paymentcategoryRepository.findAll().size();

        // Create the Paymentcategory
        PaymentcategoryDTO paymentcategoryDTO = paymentcategoryMapper.toDto(paymentcategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentcategoryMockMvc.perform(put("/api/paymentcategories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentcategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paymentcategory in the database
        List<Paymentcategory> paymentcategoryList = paymentcategoryRepository.findAll();
        assertThat(paymentcategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymentcategory() throws Exception {
        // Initialize the database
        paymentcategoryRepository.saveAndFlush(paymentcategory);

        int databaseSizeBeforeDelete = paymentcategoryRepository.findAll().size();

        // Delete the paymentcategory
        restPaymentcategoryMockMvc.perform(delete("/api/paymentcategories/{id}", paymentcategory.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Paymentcategory> paymentcategoryList = paymentcategoryRepository.findAll();
        assertThat(paymentcategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
