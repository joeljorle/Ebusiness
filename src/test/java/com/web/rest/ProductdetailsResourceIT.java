package com.web.rest;

import com.EbusinesApp;
import com.domain.Productdetails;
import com.repository.ProductdetailsRepository;
import com.service.ProductdetailsService;
import com.service.dto.ProductdetailsDTO;
import com.service.mapper.ProductdetailsMapper;
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

import com.domain.enumeration.Tagcolor;
/**
 * Integration tests for the {@link ProductdetailsResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class ProductdetailsResourceIT {

    private static final String DEFAULT_ABOUT = "AAAAAAAAAA";
    private static final String UPDATED_ABOUT = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final Tagcolor DEFAULT_TAGCOLOR = Tagcolor.RED;
    private static final Tagcolor UPDATED_TAGCOLOR = Tagcolor.BLUE;

    private static final String DEFAULT_DEFAULTLANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULTLANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTALCODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTALCODE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONES = "AAAAAAAAAA";
    private static final String UPDATED_PHONES = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_GPLUS = "AAAAAAAAAA";
    private static final String UPDATED_GPLUS = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_OPENTIMES = "AAAAAAAAAA";
    private static final String UPDATED_OPENTIMES = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERCONTACTS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERCONTACTS = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERFIELDS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERFIELDS = "BBBBBBBBBB";

    private static final String DEFAULT_SIZES = "AAAAAAAAAA";
    private static final String UPDATED_SIZES = "BBBBBBBBBB";

    private static final String DEFAULT_COLORS = "AAAAAAAAAA";
    private static final String UPDATED_COLORS = "BBBBBBBBBB";

    private static final String DEFAULT_MODELS = "AAAAAAAAAA";
    private static final String UPDATED_MODELS = "BBBBBBBBBB";

    private static final Double DEFAULT_SHIPPINGPRICE = 1D;
    private static final Double UPDATED_SHIPPINGPRICE = 2D;

    private static final String DEFAULT_SERIALNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIALNUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_TAX = 1;
    private static final Integer UPDATED_TAX = 2;

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Integer DEFAULT_MAXQTY = 1;
    private static final Integer UPDATED_MAXQTY = 2;

    private static final Instant DEFAULT_AVAILABLEAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_AVAILABLEAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LOCKACTIONDELAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LOCKACTIONDELAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_LOCKACTION = false;
    private static final Boolean UPDATED_LOCKACTION = true;

    private static final Instant DEFAULT_EXPIREAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIREAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ProductdetailsRepository productdetailsRepository;

    @Autowired
    private ProductdetailsMapper productdetailsMapper;

    @Autowired
    private ProductdetailsService productdetailsService;

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

    private MockMvc restProductdetailsMockMvc;

    private Productdetails productdetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductdetailsResource productdetailsResource = new ProductdetailsResource(productdetailsService);
        this.restProductdetailsMockMvc = MockMvcBuilders.standaloneSetup(productdetailsResource)
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
    public static Productdetails createEntity(EntityManager em) {
        Productdetails productdetails = new Productdetails()
            .about(DEFAULT_ABOUT)
            .title(DEFAULT_TITLE)
            .tag(DEFAULT_TAG)
            .tagcolor(DEFAULT_TAGCOLOR)
            .defaultlanguage(DEFAULT_DEFAULTLANGUAGE)
            .postalcode(DEFAULT_POSTALCODE)
            .phones(DEFAULT_PHONES)
            .website(DEFAULT_WEBSITE)
            .email(DEFAULT_EMAIL)
            .facebook(DEFAULT_FACEBOOK)
            .twitter(DEFAULT_TWITTER)
            .gplus(DEFAULT_GPLUS)
            .linkedin(DEFAULT_LINKEDIN)
            .instagram(DEFAULT_INSTAGRAM)
            .opentimes(DEFAULT_OPENTIMES)
            .othercontacts(DEFAULT_OTHERCONTACTS)
            .otherfields(DEFAULT_OTHERFIELDS)
            .sizes(DEFAULT_SIZES)
            .colors(DEFAULT_COLORS)
            .models(DEFAULT_MODELS)
            .shippingprice(DEFAULT_SHIPPINGPRICE)
            .serialnumber(DEFAULT_SERIALNUMBER)
            .tax(DEFAULT_TAX)
            .stock(DEFAULT_STOCK)
            .price(DEFAULT_PRICE)
            .maxqty(DEFAULT_MAXQTY)
            .availableat(DEFAULT_AVAILABLEAT)
            .lockactiondelay(DEFAULT_LOCKACTIONDELAY)
            .lockaction(DEFAULT_LOCKACTION)
            .expireat(DEFAULT_EXPIREAT);
        return productdetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Productdetails createUpdatedEntity(EntityManager em) {
        Productdetails productdetails = new Productdetails()
            .about(UPDATED_ABOUT)
            .title(UPDATED_TITLE)
            .tag(UPDATED_TAG)
            .tagcolor(UPDATED_TAGCOLOR)
            .defaultlanguage(UPDATED_DEFAULTLANGUAGE)
            .postalcode(UPDATED_POSTALCODE)
            .phones(UPDATED_PHONES)
            .website(UPDATED_WEBSITE)
            .email(UPDATED_EMAIL)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .gplus(UPDATED_GPLUS)
            .linkedin(UPDATED_LINKEDIN)
            .instagram(UPDATED_INSTAGRAM)
            .opentimes(UPDATED_OPENTIMES)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .sizes(UPDATED_SIZES)
            .colors(UPDATED_COLORS)
            .models(UPDATED_MODELS)
            .shippingprice(UPDATED_SHIPPINGPRICE)
            .serialnumber(UPDATED_SERIALNUMBER)
            .tax(UPDATED_TAX)
            .stock(UPDATED_STOCK)
            .price(UPDATED_PRICE)
            .maxqty(UPDATED_MAXQTY)
            .availableat(UPDATED_AVAILABLEAT)
            .lockactiondelay(UPDATED_LOCKACTIONDELAY)
            .lockaction(UPDATED_LOCKACTION)
            .expireat(UPDATED_EXPIREAT);
        return productdetails;
    }

    @BeforeEach
    public void initTest() {
        productdetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductdetails() throws Exception {
        int databaseSizeBeforeCreate = productdetailsRepository.findAll().size();

        // Create the Productdetails
        ProductdetailsDTO productdetailsDTO = productdetailsMapper.toDto(productdetails);
        restProductdetailsMockMvc.perform(post("/api/productdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productdetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the Productdetails in the database
        List<Productdetails> productdetailsList = productdetailsRepository.findAll();
        assertThat(productdetailsList).hasSize(databaseSizeBeforeCreate + 1);
        Productdetails testProductdetails = productdetailsList.get(productdetailsList.size() - 1);
        assertThat(testProductdetails.getAbout()).isEqualTo(DEFAULT_ABOUT);
        assertThat(testProductdetails.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testProductdetails.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testProductdetails.getTagcolor()).isEqualTo(DEFAULT_TAGCOLOR);
        assertThat(testProductdetails.getDefaultlanguage()).isEqualTo(DEFAULT_DEFAULTLANGUAGE);
        assertThat(testProductdetails.getPostalcode()).isEqualTo(DEFAULT_POSTALCODE);
        assertThat(testProductdetails.getPhones()).isEqualTo(DEFAULT_PHONES);
        assertThat(testProductdetails.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testProductdetails.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testProductdetails.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testProductdetails.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testProductdetails.getGplus()).isEqualTo(DEFAULT_GPLUS);
        assertThat(testProductdetails.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testProductdetails.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testProductdetails.getOpentimes()).isEqualTo(DEFAULT_OPENTIMES);
        assertThat(testProductdetails.getOthercontacts()).isEqualTo(DEFAULT_OTHERCONTACTS);
        assertThat(testProductdetails.getOtherfields()).isEqualTo(DEFAULT_OTHERFIELDS);
        assertThat(testProductdetails.getSizes()).isEqualTo(DEFAULT_SIZES);
        assertThat(testProductdetails.getColors()).isEqualTo(DEFAULT_COLORS);
        assertThat(testProductdetails.getModels()).isEqualTo(DEFAULT_MODELS);
        assertThat(testProductdetails.getShippingprice()).isEqualTo(DEFAULT_SHIPPINGPRICE);
        assertThat(testProductdetails.getSerialnumber()).isEqualTo(DEFAULT_SERIALNUMBER);
        assertThat(testProductdetails.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testProductdetails.getStock()).isEqualTo(DEFAULT_STOCK);
        assertThat(testProductdetails.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testProductdetails.getMaxqty()).isEqualTo(DEFAULT_MAXQTY);
        assertThat(testProductdetails.getAvailableat()).isEqualTo(DEFAULT_AVAILABLEAT);
        assertThat(testProductdetails.getLockactiondelay()).isEqualTo(DEFAULT_LOCKACTIONDELAY);
        assertThat(testProductdetails.isLockaction()).isEqualTo(DEFAULT_LOCKACTION);
        assertThat(testProductdetails.getExpireat()).isEqualTo(DEFAULT_EXPIREAT);
    }

    @Test
    @Transactional
    public void createProductdetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productdetailsRepository.findAll().size();

        // Create the Productdetails with an existing ID
        productdetails.setId(1L);
        ProductdetailsDTO productdetailsDTO = productdetailsMapper.toDto(productdetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductdetailsMockMvc.perform(post("/api/productdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productdetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Productdetails in the database
        List<Productdetails> productdetailsList = productdetailsRepository.findAll();
        assertThat(productdetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductdetails() throws Exception {
        // Initialize the database
        productdetailsRepository.saveAndFlush(productdetails);

        // Get all the productdetailsList
        restProductdetailsMockMvc.perform(get("/api/productdetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productdetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].about").value(hasItem(DEFAULT_ABOUT.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].tagcolor").value(hasItem(DEFAULT_TAGCOLOR.toString())))
            .andExpect(jsonPath("$.[*].defaultlanguage").value(hasItem(DEFAULT_DEFAULTLANGUAGE)))
            .andExpect(jsonPath("$.[*].postalcode").value(hasItem(DEFAULT_POSTALCODE)))
            .andExpect(jsonPath("$.[*].phones").value(hasItem(DEFAULT_PHONES)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK)))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER)))
            .andExpect(jsonPath("$.[*].gplus").value(hasItem(DEFAULT_GPLUS)))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN)))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM)))
            .andExpect(jsonPath("$.[*].opentimes").value(hasItem(DEFAULT_OPENTIMES.toString())))
            .andExpect(jsonPath("$.[*].othercontacts").value(hasItem(DEFAULT_OTHERCONTACTS.toString())))
            .andExpect(jsonPath("$.[*].otherfields").value(hasItem(DEFAULT_OTHERFIELDS.toString())))
            .andExpect(jsonPath("$.[*].sizes").value(hasItem(DEFAULT_SIZES.toString())))
            .andExpect(jsonPath("$.[*].colors").value(hasItem(DEFAULT_COLORS.toString())))
            .andExpect(jsonPath("$.[*].models").value(hasItem(DEFAULT_MODELS.toString())))
            .andExpect(jsonPath("$.[*].shippingprice").value(hasItem(DEFAULT_SHIPPINGPRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].serialnumber").value(hasItem(DEFAULT_SERIALNUMBER)))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX)))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].maxqty").value(hasItem(DEFAULT_MAXQTY)))
            .andExpect(jsonPath("$.[*].availableat").value(hasItem(DEFAULT_AVAILABLEAT.toString())))
            .andExpect(jsonPath("$.[*].lockactiondelay").value(hasItem(DEFAULT_LOCKACTIONDELAY.toString())))
            .andExpect(jsonPath("$.[*].lockaction").value(hasItem(DEFAULT_LOCKACTION.booleanValue())))
            .andExpect(jsonPath("$.[*].expireat").value(hasItem(DEFAULT_EXPIREAT.toString())));
    }
    
    @Test
    @Transactional
    public void getProductdetails() throws Exception {
        // Initialize the database
        productdetailsRepository.saveAndFlush(productdetails);

        // Get the productdetails
        restProductdetailsMockMvc.perform(get("/api/productdetails/{id}", productdetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productdetails.getId().intValue()))
            .andExpect(jsonPath("$.about").value(DEFAULT_ABOUT.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG))
            .andExpect(jsonPath("$.tagcolor").value(DEFAULT_TAGCOLOR.toString()))
            .andExpect(jsonPath("$.defaultlanguage").value(DEFAULT_DEFAULTLANGUAGE))
            .andExpect(jsonPath("$.postalcode").value(DEFAULT_POSTALCODE))
            .andExpect(jsonPath("$.phones").value(DEFAULT_PHONES))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK))
            .andExpect(jsonPath("$.twitter").value(DEFAULT_TWITTER))
            .andExpect(jsonPath("$.gplus").value(DEFAULT_GPLUS))
            .andExpect(jsonPath("$.linkedin").value(DEFAULT_LINKEDIN))
            .andExpect(jsonPath("$.instagram").value(DEFAULT_INSTAGRAM))
            .andExpect(jsonPath("$.opentimes").value(DEFAULT_OPENTIMES.toString()))
            .andExpect(jsonPath("$.othercontacts").value(DEFAULT_OTHERCONTACTS.toString()))
            .andExpect(jsonPath("$.otherfields").value(DEFAULT_OTHERFIELDS.toString()))
            .andExpect(jsonPath("$.sizes").value(DEFAULT_SIZES.toString()))
            .andExpect(jsonPath("$.colors").value(DEFAULT_COLORS.toString()))
            .andExpect(jsonPath("$.models").value(DEFAULT_MODELS.toString()))
            .andExpect(jsonPath("$.shippingprice").value(DEFAULT_SHIPPINGPRICE.doubleValue()))
            .andExpect(jsonPath("$.serialnumber").value(DEFAULT_SERIALNUMBER))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.maxqty").value(DEFAULT_MAXQTY))
            .andExpect(jsonPath("$.availableat").value(DEFAULT_AVAILABLEAT.toString()))
            .andExpect(jsonPath("$.lockactiondelay").value(DEFAULT_LOCKACTIONDELAY.toString()))
            .andExpect(jsonPath("$.lockaction").value(DEFAULT_LOCKACTION.booleanValue()))
            .andExpect(jsonPath("$.expireat").value(DEFAULT_EXPIREAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProductdetails() throws Exception {
        // Get the productdetails
        restProductdetailsMockMvc.perform(get("/api/productdetails/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductdetails() throws Exception {
        // Initialize the database
        productdetailsRepository.saveAndFlush(productdetails);

        int databaseSizeBeforeUpdate = productdetailsRepository.findAll().size();

        // Update the productdetails
        Productdetails updatedProductdetails = productdetailsRepository.findById(productdetails.getId()).get();
        // Disconnect from session so that the updates on updatedProductdetails are not directly saved in db
        em.detach(updatedProductdetails);
        updatedProductdetails
            .about(UPDATED_ABOUT)
            .title(UPDATED_TITLE)
            .tag(UPDATED_TAG)
            .tagcolor(UPDATED_TAGCOLOR)
            .defaultlanguage(UPDATED_DEFAULTLANGUAGE)
            .postalcode(UPDATED_POSTALCODE)
            .phones(UPDATED_PHONES)
            .website(UPDATED_WEBSITE)
            .email(UPDATED_EMAIL)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .gplus(UPDATED_GPLUS)
            .linkedin(UPDATED_LINKEDIN)
            .instagram(UPDATED_INSTAGRAM)
            .opentimes(UPDATED_OPENTIMES)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .sizes(UPDATED_SIZES)
            .colors(UPDATED_COLORS)
            .models(UPDATED_MODELS)
            .shippingprice(UPDATED_SHIPPINGPRICE)
            .serialnumber(UPDATED_SERIALNUMBER)
            .tax(UPDATED_TAX)
            .stock(UPDATED_STOCK)
            .price(UPDATED_PRICE)
            .maxqty(UPDATED_MAXQTY)
            .availableat(UPDATED_AVAILABLEAT)
            .lockactiondelay(UPDATED_LOCKACTIONDELAY)
            .lockaction(UPDATED_LOCKACTION)
            .expireat(UPDATED_EXPIREAT);
        ProductdetailsDTO productdetailsDTO = productdetailsMapper.toDto(updatedProductdetails);

        restProductdetailsMockMvc.perform(put("/api/productdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productdetailsDTO)))
            .andExpect(status().isOk());

        // Validate the Productdetails in the database
        List<Productdetails> productdetailsList = productdetailsRepository.findAll();
        assertThat(productdetailsList).hasSize(databaseSizeBeforeUpdate);
        Productdetails testProductdetails = productdetailsList.get(productdetailsList.size() - 1);
        assertThat(testProductdetails.getAbout()).isEqualTo(UPDATED_ABOUT);
        assertThat(testProductdetails.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testProductdetails.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testProductdetails.getTagcolor()).isEqualTo(UPDATED_TAGCOLOR);
        assertThat(testProductdetails.getDefaultlanguage()).isEqualTo(UPDATED_DEFAULTLANGUAGE);
        assertThat(testProductdetails.getPostalcode()).isEqualTo(UPDATED_POSTALCODE);
        assertThat(testProductdetails.getPhones()).isEqualTo(UPDATED_PHONES);
        assertThat(testProductdetails.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testProductdetails.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProductdetails.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testProductdetails.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testProductdetails.getGplus()).isEqualTo(UPDATED_GPLUS);
        assertThat(testProductdetails.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testProductdetails.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testProductdetails.getOpentimes()).isEqualTo(UPDATED_OPENTIMES);
        assertThat(testProductdetails.getOthercontacts()).isEqualTo(UPDATED_OTHERCONTACTS);
        assertThat(testProductdetails.getOtherfields()).isEqualTo(UPDATED_OTHERFIELDS);
        assertThat(testProductdetails.getSizes()).isEqualTo(UPDATED_SIZES);
        assertThat(testProductdetails.getColors()).isEqualTo(UPDATED_COLORS);
        assertThat(testProductdetails.getModels()).isEqualTo(UPDATED_MODELS);
        assertThat(testProductdetails.getShippingprice()).isEqualTo(UPDATED_SHIPPINGPRICE);
        assertThat(testProductdetails.getSerialnumber()).isEqualTo(UPDATED_SERIALNUMBER);
        assertThat(testProductdetails.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testProductdetails.getStock()).isEqualTo(UPDATED_STOCK);
        assertThat(testProductdetails.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProductdetails.getMaxqty()).isEqualTo(UPDATED_MAXQTY);
        assertThat(testProductdetails.getAvailableat()).isEqualTo(UPDATED_AVAILABLEAT);
        assertThat(testProductdetails.getLockactiondelay()).isEqualTo(UPDATED_LOCKACTIONDELAY);
        assertThat(testProductdetails.isLockaction()).isEqualTo(UPDATED_LOCKACTION);
        assertThat(testProductdetails.getExpireat()).isEqualTo(UPDATED_EXPIREAT);
    }

    @Test
    @Transactional
    public void updateNonExistingProductdetails() throws Exception {
        int databaseSizeBeforeUpdate = productdetailsRepository.findAll().size();

        // Create the Productdetails
        ProductdetailsDTO productdetailsDTO = productdetailsMapper.toDto(productdetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductdetailsMockMvc.perform(put("/api/productdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productdetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Productdetails in the database
        List<Productdetails> productdetailsList = productdetailsRepository.findAll();
        assertThat(productdetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductdetails() throws Exception {
        // Initialize the database
        productdetailsRepository.saveAndFlush(productdetails);

        int databaseSizeBeforeDelete = productdetailsRepository.findAll().size();

        // Delete the productdetails
        restProductdetailsMockMvc.perform(delete("/api/productdetails/{id}", productdetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Productdetails> productdetailsList = productdetailsRepository.findAll();
        assertThat(productdetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
