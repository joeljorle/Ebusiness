package com.web.rest;

import com.EBusinessApp;
import com.domain.Bookingview;
import com.repository.BookingviewRepository;
import com.service.BookingviewService;
import com.service.dto.BookingviewDTO;
import com.service.mapper.BookingviewMapper;
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
import java.util.List;

import static com.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BookingviewResource} REST controller.
 */
@SpringBootTest(classes = EBusinessApp.class)
public class BookingviewResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBTITLE = "AAAAAAAAAA";
    private static final String UPDATED_SUBTITLE = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_LOGO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_LOGODATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGODATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGODATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGODATA_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BG = "AAAAAAAAAA";
    private static final String UPDATED_BG = "BBBBBBBBBB";

    private static final byte[] DEFAULT_BGDATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BGDATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BGDATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BGDATA_CONTENT_TYPE = "image/png";

    @Autowired
    private BookingviewRepository bookingviewRepository;

    @Autowired
    private BookingviewMapper bookingviewMapper;

    @Autowired
    private BookingviewService bookingviewService;

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

    private MockMvc restBookingviewMockMvc;

    private Bookingview bookingview;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookingviewResource bookingviewResource = new BookingviewResource(bookingviewService);
        this.restBookingviewMockMvc = MockMvcBuilders.standaloneSetup(bookingviewResource)
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
    public static Bookingview createEntity(EntityManager em) {
        Bookingview bookingview = new Bookingview()
            .title(DEFAULT_TITLE)
            .subtitle(DEFAULT_SUBTITLE)
            .logo(DEFAULT_LOGO)
            .logodata(DEFAULT_LOGODATA)
            .logodataContentType(DEFAULT_LOGODATA_CONTENT_TYPE)
            .bg(DEFAULT_BG)
            .bgdata(DEFAULT_BGDATA)
            .bgdataContentType(DEFAULT_BGDATA_CONTENT_TYPE);
        return bookingview;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bookingview createUpdatedEntity(EntityManager em) {
        Bookingview bookingview = new Bookingview()
            .title(UPDATED_TITLE)
            .subtitle(UPDATED_SUBTITLE)
            .logo(UPDATED_LOGO)
            .logodata(UPDATED_LOGODATA)
            .logodataContentType(UPDATED_LOGODATA_CONTENT_TYPE)
            .bg(UPDATED_BG)
            .bgdata(UPDATED_BGDATA)
            .bgdataContentType(UPDATED_BGDATA_CONTENT_TYPE);
        return bookingview;
    }

    @BeforeEach
    public void initTest() {
        bookingview = createEntity(em);
    }

    @Test
    @Transactional
    public void createBookingview() throws Exception {
        int databaseSizeBeforeCreate = bookingviewRepository.findAll().size();

        // Create the Bookingview
        BookingviewDTO bookingviewDTO = bookingviewMapper.toDto(bookingview);
        restBookingviewMockMvc.perform(post("/api/bookingviews")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingviewDTO)))
            .andExpect(status().isCreated());

        // Validate the Bookingview in the database
        List<Bookingview> bookingviewList = bookingviewRepository.findAll();
        assertThat(bookingviewList).hasSize(databaseSizeBeforeCreate + 1);
        Bookingview testBookingview = bookingviewList.get(bookingviewList.size() - 1);
        assertThat(testBookingview.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testBookingview.getSubtitle()).isEqualTo(DEFAULT_SUBTITLE);
        assertThat(testBookingview.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testBookingview.getLogodata()).isEqualTo(DEFAULT_LOGODATA);
        assertThat(testBookingview.getLogodataContentType()).isEqualTo(DEFAULT_LOGODATA_CONTENT_TYPE);
        assertThat(testBookingview.getBg()).isEqualTo(DEFAULT_BG);
        assertThat(testBookingview.getBgdata()).isEqualTo(DEFAULT_BGDATA);
        assertThat(testBookingview.getBgdataContentType()).isEqualTo(DEFAULT_BGDATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createBookingviewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookingviewRepository.findAll().size();

        // Create the Bookingview with an existing ID
        bookingview.setId(1L);
        BookingviewDTO bookingviewDTO = bookingviewMapper.toDto(bookingview);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookingviewMockMvc.perform(post("/api/bookingviews")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingviewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bookingview in the database
        List<Bookingview> bookingviewList = bookingviewRepository.findAll();
        assertThat(bookingviewList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBookingviews() throws Exception {
        // Initialize the database
        bookingviewRepository.saveAndFlush(bookingview);

        // Get all the bookingviewList
        restBookingviewMockMvc.perform(get("/api/bookingviews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bookingview.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].subtitle").value(hasItem(DEFAULT_SUBTITLE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.[*].logodataContentType").value(hasItem(DEFAULT_LOGODATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logodata").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGODATA))))
            .andExpect(jsonPath("$.[*].bg").value(hasItem(DEFAULT_BG)))
            .andExpect(jsonPath("$.[*].bgdataContentType").value(hasItem(DEFAULT_BGDATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].bgdata").value(hasItem(Base64Utils.encodeToString(DEFAULT_BGDATA))));
    }
    
    @Test
    @Transactional
    public void getBookingview() throws Exception {
        // Initialize the database
        bookingviewRepository.saveAndFlush(bookingview);

        // Get the bookingview
        restBookingviewMockMvc.perform(get("/api/bookingviews/{id}", bookingview.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bookingview.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.subtitle").value(DEFAULT_SUBTITLE))
            .andExpect(jsonPath("$.logo").value(DEFAULT_LOGO))
            .andExpect(jsonPath("$.logodataContentType").value(DEFAULT_LOGODATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.logodata").value(Base64Utils.encodeToString(DEFAULT_LOGODATA)))
            .andExpect(jsonPath("$.bg").value(DEFAULT_BG))
            .andExpect(jsonPath("$.bgdataContentType").value(DEFAULT_BGDATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.bgdata").value(Base64Utils.encodeToString(DEFAULT_BGDATA)));
    }

    @Test
    @Transactional
    public void getNonExistingBookingview() throws Exception {
        // Get the bookingview
        restBookingviewMockMvc.perform(get("/api/bookingviews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBookingview() throws Exception {
        // Initialize the database
        bookingviewRepository.saveAndFlush(bookingview);

        int databaseSizeBeforeUpdate = bookingviewRepository.findAll().size();

        // Update the bookingview
        Bookingview updatedBookingview = bookingviewRepository.findById(bookingview.getId()).get();
        // Disconnect from session so that the updates on updatedBookingview are not directly saved in db
        em.detach(updatedBookingview);
        updatedBookingview
            .title(UPDATED_TITLE)
            .subtitle(UPDATED_SUBTITLE)
            .logo(UPDATED_LOGO)
            .logodata(UPDATED_LOGODATA)
            .logodataContentType(UPDATED_LOGODATA_CONTENT_TYPE)
            .bg(UPDATED_BG)
            .bgdata(UPDATED_BGDATA)
            .bgdataContentType(UPDATED_BGDATA_CONTENT_TYPE);
        BookingviewDTO bookingviewDTO = bookingviewMapper.toDto(updatedBookingview);

        restBookingviewMockMvc.perform(put("/api/bookingviews")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingviewDTO)))
            .andExpect(status().isOk());

        // Validate the Bookingview in the database
        List<Bookingview> bookingviewList = bookingviewRepository.findAll();
        assertThat(bookingviewList).hasSize(databaseSizeBeforeUpdate);
        Bookingview testBookingview = bookingviewList.get(bookingviewList.size() - 1);
        assertThat(testBookingview.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testBookingview.getSubtitle()).isEqualTo(UPDATED_SUBTITLE);
        assertThat(testBookingview.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testBookingview.getLogodata()).isEqualTo(UPDATED_LOGODATA);
        assertThat(testBookingview.getLogodataContentType()).isEqualTo(UPDATED_LOGODATA_CONTENT_TYPE);
        assertThat(testBookingview.getBg()).isEqualTo(UPDATED_BG);
        assertThat(testBookingview.getBgdata()).isEqualTo(UPDATED_BGDATA);
        assertThat(testBookingview.getBgdataContentType()).isEqualTo(UPDATED_BGDATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingBookingview() throws Exception {
        int databaseSizeBeforeUpdate = bookingviewRepository.findAll().size();

        // Create the Bookingview
        BookingviewDTO bookingviewDTO = bookingviewMapper.toDto(bookingview);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookingviewMockMvc.perform(put("/api/bookingviews")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bookingviewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bookingview in the database
        List<Bookingview> bookingviewList = bookingviewRepository.findAll();
        assertThat(bookingviewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBookingview() throws Exception {
        // Initialize the database
        bookingviewRepository.saveAndFlush(bookingview);

        int databaseSizeBeforeDelete = bookingviewRepository.findAll().size();

        // Delete the bookingview
        restBookingviewMockMvc.perform(delete("/api/bookingviews/{id}", bookingview.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bookingview> bookingviewList = bookingviewRepository.findAll();
        assertThat(bookingviewList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
