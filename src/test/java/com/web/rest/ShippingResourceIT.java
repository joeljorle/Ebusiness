package com.web.rest;

import com.EbusinessApp;
import com.domain.Shipping;
import com.repository.ShippingRepository;
import com.service.ShippingService;
import com.service.dto.ShippingDTO;
import com.service.mapper.ShippingMapper;
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
 * Integration tests for the {@link ShippingResource} REST controller.
 */
@SpringBootTest(classes = EbusinessApp.class)
public class ShippingResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private ShippingService shippingService;

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

    private MockMvc restShippingMockMvc;

    private Shipping shipping;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShippingResource shippingResource = new ShippingResource(shippingService);
        this.restShippingMockMvc = MockMvcBuilders.standaloneSetup(shippingResource)
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
    public static Shipping createEntity(EntityManager em) {
        Shipping shipping = new Shipping()
            .name(DEFAULT_NAME)
            .price(DEFAULT_PRICE)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return shipping;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shipping createUpdatedEntity(EntityManager em) {
        Shipping shipping = new Shipping()
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return shipping;
    }

    @BeforeEach
    public void initTest() {
        shipping = createEntity(em);
    }

    @Test
    @Transactional
    public void createShipping() throws Exception {
        int databaseSizeBeforeCreate = shippingRepository.findAll().size();

        // Create the Shipping
        ShippingDTO shippingDTO = shippingMapper.toDto(shipping);
        restShippingMockMvc.perform(post("/api/shippings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingDTO)))
            .andExpect(status().isCreated());

        // Validate the Shipping in the database
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeCreate + 1);
        Shipping testShipping = shippingList.get(shippingList.size() - 1);
        assertThat(testShipping.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testShipping.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testShipping.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testShipping.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createShippingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shippingRepository.findAll().size();

        // Create the Shipping with an existing ID
        shipping.setId(1L);
        ShippingDTO shippingDTO = shippingMapper.toDto(shipping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShippingMockMvc.perform(post("/api/shippings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Shipping in the database
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShippings() throws Exception {
        // Initialize the database
        shippingRepository.saveAndFlush(shipping);

        // Get all the shippingList
        restShippingMockMvc.perform(get("/api/shippings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shipping.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getShipping() throws Exception {
        // Initialize the database
        shippingRepository.saveAndFlush(shipping);

        // Get the shipping
        restShippingMockMvc.perform(get("/api/shippings/{id}", shipping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shipping.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingShipping() throws Exception {
        // Get the shipping
        restShippingMockMvc.perform(get("/api/shippings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShipping() throws Exception {
        // Initialize the database
        shippingRepository.saveAndFlush(shipping);

        int databaseSizeBeforeUpdate = shippingRepository.findAll().size();

        // Update the shipping
        Shipping updatedShipping = shippingRepository.findById(shipping.getId()).get();
        // Disconnect from session so that the updates on updatedShipping are not directly saved in db
        em.detach(updatedShipping);
        updatedShipping
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        ShippingDTO shippingDTO = shippingMapper.toDto(updatedShipping);

        restShippingMockMvc.perform(put("/api/shippings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingDTO)))
            .andExpect(status().isOk());

        // Validate the Shipping in the database
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeUpdate);
        Shipping testShipping = shippingList.get(shippingList.size() - 1);
        assertThat(testShipping.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShipping.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testShipping.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testShipping.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingShipping() throws Exception {
        int databaseSizeBeforeUpdate = shippingRepository.findAll().size();

        // Create the Shipping
        ShippingDTO shippingDTO = shippingMapper.toDto(shipping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShippingMockMvc.perform(put("/api/shippings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Shipping in the database
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShipping() throws Exception {
        // Initialize the database
        shippingRepository.saveAndFlush(shipping);

        int databaseSizeBeforeDelete = shippingRepository.findAll().size();

        // Delete the shipping
        restShippingMockMvc.perform(delete("/api/shippings/{id}", shipping.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
