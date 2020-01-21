package com.web.rest;

import com.EbusinesApp;
import com.domain.Bookingdetails;
import com.repository.BookingdetailsRepository;
import com.service.BookingdetailsService;
import com.service.dto.BookingdetailsDTO;
import com.service.mapper.BookingdetailsMapper;
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

import com.domain.enumeration.Shippingstate;
import com.domain.enumeration.Evenementstate;
/**
 * Integration tests for the {@link BookingdetailsResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class BookingdetailsResourceIT {

    private static final Double DEFAULT_SUBTOTAL = 1D;
    private static final Double UPDATED_SUBTOTAL = 2D;

    private static final Double DEFAULT_TOTAL = 1D;
    private static final Double UPDATED_TOTAL = 2D;

    private static final Integer DEFAULT_TAX = 1;
    private static final Integer UPDATED_TAX = 2;

    private static final Double DEFAULT_SHIPPING = 1D;
    private static final Double UPDATED_SHIPPING = 2D;

    private static final Shippingstate DEFAULT_SHIPPINGSTATE = Shippingstate.COMPLETED;
    private static final Shippingstate UPDATED_SHIPPINGSTATE = Shippingstate.SUSPEND;

    private static final String DEFAULT_ABOUT = "AAAAAAAAAA";
    private static final String UPDATED_ABOUT = "BBBBBBBBBB";

    private static final String DEFAULT_COUPONCONDE = "AAAAAAAAAA";
    private static final String UPDATED_COUPONCONDE = "BBBBBBBBBB";

    private static final String DEFAULT_QRCODE = "AAAAAAAAAA";
    private static final String UPDATED_QRCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENTMODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENTMODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_STARTDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STARTDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ENDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ENDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Evenementstate DEFAULT_ENVENTSTATE = Evenementstate.COMPLETED;
    private static final Evenementstate UPDATED_ENVENTSTATE = Evenementstate.SUSPEND;

    private static final Integer DEFAULT_PLACES = 1;
    private static final Integer UPDATED_PLACES = 2;

    @Autowired
    private BookingdetailsRepository bookingdetailsRepository;

    @Autowired
    private BookingdetailsMapper bookingdetailsMapper;

    @Autowired
    private BookingdetailsService bookingdetailsService;

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

    private MockMvc restBookingdetailsMockMvc;

    private Bookingdetails bookingdetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookingdetailsResource bookingdetailsResource = new BookingdetailsResource(bookingdetailsService);
        this.restBookingdetailsMockMvc = MockMvcBuilders.standaloneSetup(bookingdetailsResource)
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
    public static Bookingdetails createEntity(EntityManager em) {
        Bookingdetails bookingdetails = new Bookingdetails()
            .subtotal(DEFAULT_SUBTOTAL)
            .total(DEFAULT_TOTAL)
            .tax(DEFAULT_TAX)
            .shipping(DEFAULT_SHIPPING)
            .shippingstate(DEFAULT_SHIPPINGSTATE)
            .about(DEFAULT_ABOUT)
            .couponconde(DEFAULT_COUPONCONDE)
            .qrcode(DEFAULT_QRCODE)
            .code(DEFAULT_CODE)
            .url(DEFAULT_URL)
            .token(DEFAULT_TOKEN)
            .paymentmode(DEFAULT_PAYMENTMODE)
            .startdate(DEFAULT_STARTDATE)
            .enddate(DEFAULT_ENDDATE)
            .enventstate(DEFAULT_ENVENTSTATE)
            .places(DEFAULT_PLACES);
        return bookingdetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bookingdetails createUpdatedEntity(EntityManager em) {
        Bookingdetails bookingdetails = new Bookingdetails()
            .subtotal(UPDATED_SUBTOTAL)
            .total(UPDATED_TOTAL)
            .tax(UPDATED_TAX)
            .shipping(UPDATED_SHIPPING)
            .shippingstate(UPDATED_SHIPPINGSTATE)
            .about(UPDATED_ABOUT)
            .couponconde(UPDATED_COUPONCONDE)
            .qrcode(UPDATED_QRCODE)
            .code(UPDATED_CODE)
            .url(UPDATED_URL)
            .token(UPDATED_TOKEN)
            .paymentmode(UPDATED_PAYMENTMODE)
            .startdate(UPDATED_STARTDATE)
            .enddate(UPDATED_ENDDATE)
            .enventstate(UPDATED_ENVENTSTATE)
            .places(UPDATED_PLACES);
        return bookingdetails;
    }

    @BeforeEach
    public void initTest() {
        bookingdetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createBookingdetails() throws Exception {
        int databaseSizeBeforeCreate = bookingdetailsRepository.findAll().size();

        // Create the Bookingdetails
        BookingdetailsDTO bookingdetailsDTO = bookingdetailsMapper.toDto(bookingdetails);
        restBookingdetailsMockMvc.perform(post("/api/bookingdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingdetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the Bookingdetails in the database
        List<Bookingdetails> bookingdetailsList = bookingdetailsRepository.findAll();
        assertThat(bookingdetailsList).hasSize(databaseSizeBeforeCreate + 1);
        Bookingdetails testBookingdetails = bookingdetailsList.get(bookingdetailsList.size() - 1);
        assertThat(testBookingdetails.getSubtotal()).isEqualTo(DEFAULT_SUBTOTAL);
        assertThat(testBookingdetails.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testBookingdetails.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testBookingdetails.getShipping()).isEqualTo(DEFAULT_SHIPPING);
        assertThat(testBookingdetails.getShippingstate()).isEqualTo(DEFAULT_SHIPPINGSTATE);
        assertThat(testBookingdetails.getAbout()).isEqualTo(DEFAULT_ABOUT);
        assertThat(testBookingdetails.getCouponconde()).isEqualTo(DEFAULT_COUPONCONDE);
        assertThat(testBookingdetails.getQrcode()).isEqualTo(DEFAULT_QRCODE);
        assertThat(testBookingdetails.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testBookingdetails.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testBookingdetails.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testBookingdetails.getPaymentmode()).isEqualTo(DEFAULT_PAYMENTMODE);
        assertThat(testBookingdetails.getStartdate()).isEqualTo(DEFAULT_STARTDATE);
        assertThat(testBookingdetails.getEnddate()).isEqualTo(DEFAULT_ENDDATE);
        assertThat(testBookingdetails.getEnventstate()).isEqualTo(DEFAULT_ENVENTSTATE);
        assertThat(testBookingdetails.getPlaces()).isEqualTo(DEFAULT_PLACES);
    }

    @Test
    @Transactional
    public void createBookingdetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookingdetailsRepository.findAll().size();

        // Create the Bookingdetails with an existing ID
        bookingdetails.setId(1L);
        BookingdetailsDTO bookingdetailsDTO = bookingdetailsMapper.toDto(bookingdetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookingdetailsMockMvc.perform(post("/api/bookingdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingdetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bookingdetails in the database
        List<Bookingdetails> bookingdetailsList = bookingdetailsRepository.findAll();
        assertThat(bookingdetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBookingdetails() throws Exception {
        // Initialize the database
        bookingdetailsRepository.saveAndFlush(bookingdetails);

        // Get all the bookingdetailsList
        restBookingdetailsMockMvc.perform(get("/api/bookingdetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bookingdetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].subtotal").value(hasItem(DEFAULT_SUBTOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX)))
            .andExpect(jsonPath("$.[*].shipping").value(hasItem(DEFAULT_SHIPPING.doubleValue())))
            .andExpect(jsonPath("$.[*].shippingstate").value(hasItem(DEFAULT_SHIPPINGSTATE.toString())))
            .andExpect(jsonPath("$.[*].about").value(hasItem(DEFAULT_ABOUT)))
            .andExpect(jsonPath("$.[*].couponconde").value(hasItem(DEFAULT_COUPONCONDE)))
            .andExpect(jsonPath("$.[*].qrcode").value(hasItem(DEFAULT_QRCODE.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN)))
            .andExpect(jsonPath("$.[*].paymentmode").value(hasItem(DEFAULT_PAYMENTMODE)))
            .andExpect(jsonPath("$.[*].startdate").value(hasItem(DEFAULT_STARTDATE.toString())))
            .andExpect(jsonPath("$.[*].enddate").value(hasItem(DEFAULT_ENDDATE.toString())))
            .andExpect(jsonPath("$.[*].enventstate").value(hasItem(DEFAULT_ENVENTSTATE.toString())))
            .andExpect(jsonPath("$.[*].places").value(hasItem(DEFAULT_PLACES)));
    }
    
    @Test
    @Transactional
    public void getBookingdetails() throws Exception {
        // Initialize the database
        bookingdetailsRepository.saveAndFlush(bookingdetails);

        // Get the bookingdetails
        restBookingdetailsMockMvc.perform(get("/api/bookingdetails/{id}", bookingdetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bookingdetails.getId().intValue()))
            .andExpect(jsonPath("$.subtotal").value(DEFAULT_SUBTOTAL.doubleValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX))
            .andExpect(jsonPath("$.shipping").value(DEFAULT_SHIPPING.doubleValue()))
            .andExpect(jsonPath("$.shippingstate").value(DEFAULT_SHIPPINGSTATE.toString()))
            .andExpect(jsonPath("$.about").value(DEFAULT_ABOUT))
            .andExpect(jsonPath("$.couponconde").value(DEFAULT_COUPONCONDE))
            .andExpect(jsonPath("$.qrcode").value(DEFAULT_QRCODE.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN))
            .andExpect(jsonPath("$.paymentmode").value(DEFAULT_PAYMENTMODE))
            .andExpect(jsonPath("$.startdate").value(DEFAULT_STARTDATE.toString()))
            .andExpect(jsonPath("$.enddate").value(DEFAULT_ENDDATE.toString()))
            .andExpect(jsonPath("$.enventstate").value(DEFAULT_ENVENTSTATE.toString()))
            .andExpect(jsonPath("$.places").value(DEFAULT_PLACES));
    }

    @Test
    @Transactional
    public void getNonExistingBookingdetails() throws Exception {
        // Get the bookingdetails
        restBookingdetailsMockMvc.perform(get("/api/bookingdetails/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBookingdetails() throws Exception {
        // Initialize the database
        bookingdetailsRepository.saveAndFlush(bookingdetails);

        int databaseSizeBeforeUpdate = bookingdetailsRepository.findAll().size();

        // Update the bookingdetails
        Bookingdetails updatedBookingdetails = bookingdetailsRepository.findById(bookingdetails.getId()).get();
        // Disconnect from session so that the updates on updatedBookingdetails are not directly saved in db
        em.detach(updatedBookingdetails);
        updatedBookingdetails
            .subtotal(UPDATED_SUBTOTAL)
            .total(UPDATED_TOTAL)
            .tax(UPDATED_TAX)
            .shipping(UPDATED_SHIPPING)
            .shippingstate(UPDATED_SHIPPINGSTATE)
            .about(UPDATED_ABOUT)
            .couponconde(UPDATED_COUPONCONDE)
            .qrcode(UPDATED_QRCODE)
            .code(UPDATED_CODE)
            .url(UPDATED_URL)
            .token(UPDATED_TOKEN)
            .paymentmode(UPDATED_PAYMENTMODE)
            .startdate(UPDATED_STARTDATE)
            .enddate(UPDATED_ENDDATE)
            .enventstate(UPDATED_ENVENTSTATE)
            .places(UPDATED_PLACES);
        BookingdetailsDTO bookingdetailsDTO = bookingdetailsMapper.toDto(updatedBookingdetails);

        restBookingdetailsMockMvc.perform(put("/api/bookingdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingdetailsDTO)))
            .andExpect(status().isOk());

        // Validate the Bookingdetails in the database
        List<Bookingdetails> bookingdetailsList = bookingdetailsRepository.findAll();
        assertThat(bookingdetailsList).hasSize(databaseSizeBeforeUpdate);
        Bookingdetails testBookingdetails = bookingdetailsList.get(bookingdetailsList.size() - 1);
        assertThat(testBookingdetails.getSubtotal()).isEqualTo(UPDATED_SUBTOTAL);
        assertThat(testBookingdetails.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testBookingdetails.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testBookingdetails.getShipping()).isEqualTo(UPDATED_SHIPPING);
        assertThat(testBookingdetails.getShippingstate()).isEqualTo(UPDATED_SHIPPINGSTATE);
        assertThat(testBookingdetails.getAbout()).isEqualTo(UPDATED_ABOUT);
        assertThat(testBookingdetails.getCouponconde()).isEqualTo(UPDATED_COUPONCONDE);
        assertThat(testBookingdetails.getQrcode()).isEqualTo(UPDATED_QRCODE);
        assertThat(testBookingdetails.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testBookingdetails.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testBookingdetails.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testBookingdetails.getPaymentmode()).isEqualTo(UPDATED_PAYMENTMODE);
        assertThat(testBookingdetails.getStartdate()).isEqualTo(UPDATED_STARTDATE);
        assertThat(testBookingdetails.getEnddate()).isEqualTo(UPDATED_ENDDATE);
        assertThat(testBookingdetails.getEnventstate()).isEqualTo(UPDATED_ENVENTSTATE);
        assertThat(testBookingdetails.getPlaces()).isEqualTo(UPDATED_PLACES);
    }

    @Test
    @Transactional
    public void updateNonExistingBookingdetails() throws Exception {
        int databaseSizeBeforeUpdate = bookingdetailsRepository.findAll().size();

        // Create the Bookingdetails
        BookingdetailsDTO bookingdetailsDTO = bookingdetailsMapper.toDto(bookingdetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookingdetailsMockMvc.perform(put("/api/bookingdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bookingdetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bookingdetails in the database
        List<Bookingdetails> bookingdetailsList = bookingdetailsRepository.findAll();
        assertThat(bookingdetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBookingdetails() throws Exception {
        // Initialize the database
        bookingdetailsRepository.saveAndFlush(bookingdetails);

        int databaseSizeBeforeDelete = bookingdetailsRepository.findAll().size();

        // Delete the bookingdetails
        restBookingdetailsMockMvc.perform(delete("/api/bookingdetails/{id}", bookingdetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bookingdetails> bookingdetailsList = bookingdetailsRepository.findAll();
        assertThat(bookingdetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
