package com.web.rest;

import com.EBusinessApp;
import com.domain.Bookingproducts;
import com.repository.BookingproductsRepository;
import com.service.BookingproductsService;
import com.service.dto.BookingproductsDTO;
import com.service.mapper.BookingproductsMapper;
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
import java.util.List;

import static com.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BookingproductsResource} REST controller.
 */
@SpringBootTest(classes = EBusinessApp.class)
public class BookingproductsResourceIT {

    private static final Integer DEFAULT_BOOKINGID = 1;
    private static final Integer UPDATED_BOOKINGID = 2;

    private static final Integer DEFAULT_PRODUCTID = 1;
    private static final Integer UPDATED_PRODUCTID = 2;

    private static final Integer DEFAULT_BOOKINGQTY = 0;
    private static final Integer UPDATED_BOOKINGQTY = 1;

    @Autowired
    private BookingproductsRepository bookingproductsRepository;

    @Autowired
    private BookingproductsMapper bookingproductsMapper;

    @Autowired
    private BookingproductsService bookingproductsService;

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

    private MockMvc restBookingproductsMockMvc;

    private Bookingproducts bookingproducts;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookingproductsResource bookingproductsResource = new BookingproductsResource(bookingproductsService);
        this.restBookingproductsMockMvc = MockMvcBuilders.standaloneSetup(bookingproductsResource)
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
    public static Bookingproducts createEntity(EntityManager em) {
        Bookingproducts bookingproducts = new Bookingproducts()
            .bookingid(DEFAULT_BOOKINGID)
            .productid(DEFAULT_PRODUCTID)
            .bookingqty(DEFAULT_BOOKINGQTY);
        return bookingproducts;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bookingproducts createUpdatedEntity(EntityManager em) {
        Bookingproducts bookingproducts = new Bookingproducts()
            .bookingid(UPDATED_BOOKINGID)
            .productid(UPDATED_PRODUCTID)
            .bookingqty(UPDATED_BOOKINGQTY);
        return bookingproducts;
    }

    @BeforeEach
    public void initTest() {
        bookingproducts = createEntity(em);
    }

    @Test
    @Transactional
    public void createBookingproducts() throws Exception {
        int databaseSizeBeforeCreate = bookingproductsRepository.findAll().size();

        // Create the Bookingproducts
        BookingproductsDTO bookingproductsDTO = bookingproductsMapper.toDto(bookingproducts);
        restBookingproductsMockMvc.perform(post("/api/bookingproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingproductsDTO)))
            .andExpect(status().isCreated());

        // Validate the Bookingproducts in the database
        List<Bookingproducts> bookingproductsList = bookingproductsRepository.findAll();
        assertThat(bookingproductsList).hasSize(databaseSizeBeforeCreate + 1);
        Bookingproducts testBookingproducts = bookingproductsList.get(bookingproductsList.size() - 1);
        assertThat(testBookingproducts.getBookingid()).isEqualTo(DEFAULT_BOOKINGID);
        assertThat(testBookingproducts.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testBookingproducts.getBookingqty()).isEqualTo(DEFAULT_BOOKINGQTY);
    }

    @Test
    @Transactional
    public void createBookingproductsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookingproductsRepository.findAll().size();

        // Create the Bookingproducts with an existing ID
        bookingproducts.setId(1L);
        BookingproductsDTO bookingproductsDTO = bookingproductsMapper.toDto(bookingproducts);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookingproductsMockMvc.perform(post("/api/bookingproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingproductsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bookingproducts in the database
        List<Bookingproducts> bookingproductsList = bookingproductsRepository.findAll();
        assertThat(bookingproductsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBookingidIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookingproductsRepository.findAll().size();
        // set the field null
        bookingproducts.setBookingid(null);

        // Create the Bookingproducts, which fails.
        BookingproductsDTO bookingproductsDTO = bookingproductsMapper.toDto(bookingproducts);

        restBookingproductsMockMvc.perform(post("/api/bookingproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingproductsDTO)))
            .andExpect(status().isBadRequest());

        List<Bookingproducts> bookingproductsList = bookingproductsRepository.findAll();
        assertThat(bookingproductsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProductidIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookingproductsRepository.findAll().size();
        // set the field null
        bookingproducts.setProductid(null);

        // Create the Bookingproducts, which fails.
        BookingproductsDTO bookingproductsDTO = bookingproductsMapper.toDto(bookingproducts);

        restBookingproductsMockMvc.perform(post("/api/bookingproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingproductsDTO)))
            .andExpect(status().isBadRequest());

        List<Bookingproducts> bookingproductsList = bookingproductsRepository.findAll();
        assertThat(bookingproductsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBookingproducts() throws Exception {
        // Initialize the database
        bookingproductsRepository.saveAndFlush(bookingproducts);

        // Get all the bookingproductsList
        restBookingproductsMockMvc.perform(get("/api/bookingproducts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bookingproducts.getId().intValue())))
            .andExpect(jsonPath("$.[*].bookingid").value(hasItem(DEFAULT_BOOKINGID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].bookingqty").value(hasItem(DEFAULT_BOOKINGQTY)));
    }
    
    @Test
    @Transactional
    public void getBookingproducts() throws Exception {
        // Initialize the database
        bookingproductsRepository.saveAndFlush(bookingproducts);

        // Get the bookingproducts
        restBookingproductsMockMvc.perform(get("/api/bookingproducts/{id}", bookingproducts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bookingproducts.getId().intValue()))
            .andExpect(jsonPath("$.bookingid").value(DEFAULT_BOOKINGID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.bookingqty").value(DEFAULT_BOOKINGQTY));
    }

    @Test
    @Transactional
    public void getNonExistingBookingproducts() throws Exception {
        // Get the bookingproducts
        restBookingproductsMockMvc.perform(get("/api/bookingproducts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBookingproducts() throws Exception {
        // Initialize the database
        bookingproductsRepository.saveAndFlush(bookingproducts);

        int databaseSizeBeforeUpdate = bookingproductsRepository.findAll().size();

        // Update the bookingproducts
        Bookingproducts updatedBookingproducts = bookingproductsRepository.findById(bookingproducts.getId()).get();
        // Disconnect from session so that the updates on updatedBookingproducts are not directly saved in db
        em.detach(updatedBookingproducts);
        updatedBookingproducts
            .bookingid(UPDATED_BOOKINGID)
            .productid(UPDATED_PRODUCTID)
            .bookingqty(UPDATED_BOOKINGQTY);
        BookingproductsDTO bookingproductsDTO = bookingproductsMapper.toDto(updatedBookingproducts);

        restBookingproductsMockMvc.perform(put("/api/bookingproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingproductsDTO)))
            .andExpect(status().isOk());

        // Validate the Bookingproducts in the database
        List<Bookingproducts> bookingproductsList = bookingproductsRepository.findAll();
        assertThat(bookingproductsList).hasSize(databaseSizeBeforeUpdate);
        Bookingproducts testBookingproducts = bookingproductsList.get(bookingproductsList.size() - 1);
        assertThat(testBookingproducts.getBookingid()).isEqualTo(UPDATED_BOOKINGID);
        assertThat(testBookingproducts.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testBookingproducts.getBookingqty()).isEqualTo(UPDATED_BOOKINGQTY);
    }

    @Test
    @Transactional
    public void updateNonExistingBookingproducts() throws Exception {
        int databaseSizeBeforeUpdate = bookingproductsRepository.findAll().size();

        // Create the Bookingproducts
        BookingproductsDTO bookingproductsDTO = bookingproductsMapper.toDto(bookingproducts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookingproductsMockMvc.perform(put("/api/bookingproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingproductsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bookingproducts in the database
        List<Bookingproducts> bookingproductsList = bookingproductsRepository.findAll();
        assertThat(bookingproductsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBookingproducts() throws Exception {
        // Initialize the database
        bookingproductsRepository.saveAndFlush(bookingproducts);

        int databaseSizeBeforeDelete = bookingproductsRepository.findAll().size();

        // Delete the bookingproducts
        restBookingproductsMockMvc.perform(delete("/api/bookingproducts/{id}", bookingproducts.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bookingproducts> bookingproductsList = bookingproductsRepository.findAll();
        assertThat(bookingproductsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
