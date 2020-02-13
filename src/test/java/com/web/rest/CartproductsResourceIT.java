package com.web.rest;

import com.EbusinessApp;
import com.domain.Cartproducts;
import com.repository.CartproductsRepository;
import com.service.CartproductsService;
import com.service.dto.CartproductsDTO;
import com.service.mapper.CartproductsMapper;
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
 * Integration tests for the {@link CartproductsResource} REST controller.
 */
@SpringBootTest(classes = EbusinessApp.class)
public class CartproductsResourceIT {

    private static final Integer DEFAULT_CARTID = 1;
    private static final Integer UPDATED_CARTID = 2;

    private static final Integer DEFAULT_PRODUCTID = 1;
    private static final Integer UPDATED_PRODUCTID = 2;

    private static final Integer DEFAULT_CARTQTY = 0;
    private static final Integer UPDATED_CARTQTY = 1;

    @Autowired
    private CartproductsRepository cartproductsRepository;

    @Autowired
    private CartproductsMapper cartproductsMapper;

    @Autowired
    private CartproductsService cartproductsService;

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

    private MockMvc restCartproductsMockMvc;

    private Cartproducts cartproducts;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CartproductsResource cartproductsResource = new CartproductsResource(cartproductsService);
        this.restCartproductsMockMvc = MockMvcBuilders.standaloneSetup(cartproductsResource)
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
    public static Cartproducts createEntity(EntityManager em) {
        Cartproducts cartproducts = new Cartproducts()
            .cartid(DEFAULT_CARTID)
            .productid(DEFAULT_PRODUCTID)
            .cartqty(DEFAULT_CARTQTY);
        return cartproducts;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cartproducts createUpdatedEntity(EntityManager em) {
        Cartproducts cartproducts = new Cartproducts()
            .cartid(UPDATED_CARTID)
            .productid(UPDATED_PRODUCTID)
            .cartqty(UPDATED_CARTQTY);
        return cartproducts;
    }

    @BeforeEach
    public void initTest() {
        cartproducts = createEntity(em);
    }

    @Test
    @Transactional
    public void createCartproducts() throws Exception {
        int databaseSizeBeforeCreate = cartproductsRepository.findAll().size();

        // Create the Cartproducts
        CartproductsDTO cartproductsDTO = cartproductsMapper.toDto(cartproducts);
        restCartproductsMockMvc.perform(post("/api/cartproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartproductsDTO)))
            .andExpect(status().isCreated());

        // Validate the Cartproducts in the database
        List<Cartproducts> cartproductsList = cartproductsRepository.findAll();
        assertThat(cartproductsList).hasSize(databaseSizeBeforeCreate + 1);
        Cartproducts testCartproducts = cartproductsList.get(cartproductsList.size() - 1);
        assertThat(testCartproducts.getCartid()).isEqualTo(DEFAULT_CARTID);
        assertThat(testCartproducts.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testCartproducts.getCartqty()).isEqualTo(DEFAULT_CARTQTY);
    }

    @Test
    @Transactional
    public void createCartproductsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cartproductsRepository.findAll().size();

        // Create the Cartproducts with an existing ID
        cartproducts.setId(1L);
        CartproductsDTO cartproductsDTO = cartproductsMapper.toDto(cartproducts);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCartproductsMockMvc.perform(post("/api/cartproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartproductsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cartproducts in the database
        List<Cartproducts> cartproductsList = cartproductsRepository.findAll();
        assertThat(cartproductsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCartidIsRequired() throws Exception {
        int databaseSizeBeforeTest = cartproductsRepository.findAll().size();
        // set the field null
        cartproducts.setCartid(null);

        // Create the Cartproducts, which fails.
        CartproductsDTO cartproductsDTO = cartproductsMapper.toDto(cartproducts);

        restCartproductsMockMvc.perform(post("/api/cartproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartproductsDTO)))
            .andExpect(status().isBadRequest());

        List<Cartproducts> cartproductsList = cartproductsRepository.findAll();
        assertThat(cartproductsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProductidIsRequired() throws Exception {
        int databaseSizeBeforeTest = cartproductsRepository.findAll().size();
        // set the field null
        cartproducts.setProductid(null);

        // Create the Cartproducts, which fails.
        CartproductsDTO cartproductsDTO = cartproductsMapper.toDto(cartproducts);

        restCartproductsMockMvc.perform(post("/api/cartproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartproductsDTO)))
            .andExpect(status().isBadRequest());

        List<Cartproducts> cartproductsList = cartproductsRepository.findAll();
        assertThat(cartproductsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCartproducts() throws Exception {
        // Initialize the database
        cartproductsRepository.saveAndFlush(cartproducts);

        // Get all the cartproductsList
        restCartproductsMockMvc.perform(get("/api/cartproducts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cartproducts.getId().intValue())))
            .andExpect(jsonPath("$.[*].cartid").value(hasItem(DEFAULT_CARTID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].cartqty").value(hasItem(DEFAULT_CARTQTY)));
    }
    
    @Test
    @Transactional
    public void getCartproducts() throws Exception {
        // Initialize the database
        cartproductsRepository.saveAndFlush(cartproducts);

        // Get the cartproducts
        restCartproductsMockMvc.perform(get("/api/cartproducts/{id}", cartproducts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cartproducts.getId().intValue()))
            .andExpect(jsonPath("$.cartid").value(DEFAULT_CARTID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.cartqty").value(DEFAULT_CARTQTY));
    }

    @Test
    @Transactional
    public void getNonExistingCartproducts() throws Exception {
        // Get the cartproducts
        restCartproductsMockMvc.perform(get("/api/cartproducts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCartproducts() throws Exception {
        // Initialize the database
        cartproductsRepository.saveAndFlush(cartproducts);

        int databaseSizeBeforeUpdate = cartproductsRepository.findAll().size();

        // Update the cartproducts
        Cartproducts updatedCartproducts = cartproductsRepository.findById(cartproducts.getId()).get();
        // Disconnect from session so that the updates on updatedCartproducts are not directly saved in db
        em.detach(updatedCartproducts);
        updatedCartproducts
            .cartid(UPDATED_CARTID)
            .productid(UPDATED_PRODUCTID)
            .cartqty(UPDATED_CARTQTY);
        CartproductsDTO cartproductsDTO = cartproductsMapper.toDto(updatedCartproducts);

        restCartproductsMockMvc.perform(put("/api/cartproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartproductsDTO)))
            .andExpect(status().isOk());

        // Validate the Cartproducts in the database
        List<Cartproducts> cartproductsList = cartproductsRepository.findAll();
        assertThat(cartproductsList).hasSize(databaseSizeBeforeUpdate);
        Cartproducts testCartproducts = cartproductsList.get(cartproductsList.size() - 1);
        assertThat(testCartproducts.getCartid()).isEqualTo(UPDATED_CARTID);
        assertThat(testCartproducts.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testCartproducts.getCartqty()).isEqualTo(UPDATED_CARTQTY);
    }

    @Test
    @Transactional
    public void updateNonExistingCartproducts() throws Exception {
        int databaseSizeBeforeUpdate = cartproductsRepository.findAll().size();

        // Create the Cartproducts
        CartproductsDTO cartproductsDTO = cartproductsMapper.toDto(cartproducts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCartproductsMockMvc.perform(put("/api/cartproducts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartproductsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cartproducts in the database
        List<Cartproducts> cartproductsList = cartproductsRepository.findAll();
        assertThat(cartproductsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCartproducts() throws Exception {
        // Initialize the database
        cartproductsRepository.saveAndFlush(cartproducts);

        int databaseSizeBeforeDelete = cartproductsRepository.findAll().size();

        // Delete the cartproducts
        restCartproductsMockMvc.perform(delete("/api/cartproducts/{id}", cartproducts.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cartproducts> cartproductsList = cartproductsRepository.findAll();
        assertThat(cartproductsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
