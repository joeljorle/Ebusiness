package com.web.rest;

import com.EBusinessApp;
import com.domain.Categorydetails;
import com.repository.CategorydetailsRepository;
import com.service.CategorydetailsService;
import com.service.dto.CategorydetailsDTO;
import com.service.mapper.CategorydetailsMapper;
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
import com.domain.enumeration.Tagcolor;
/**
 * Integration tests for the {@link CategorydetailsResource} REST controller.
 */
@SpringBootTest(classes = EBusinessApp.class)
public class CategorydetailsResourceIT {

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

    private static final Boolean DEFAULT_GENERALABOUT = false;
    private static final Boolean UPDATED_GENERALABOUT = true;

    private static final Integer DEFAULT_GENERALTAX = 1;
    private static final Integer UPDATED_GENERALTAX = 2;

    private static final Integer DEFAULT_GENERALSTOCK = 1;
    private static final Integer UPDATED_GENERALSTOCK = 2;

    private static final Double DEFAULT_GENERALPRICE = 1D;
    private static final Double UPDATED_GENERALPRICE = 2D;

    private static final Integer DEFAULT_GENERALMAXSTOCK = 1;
    private static final Integer UPDATED_GENERALMAXSTOCK = 2;

    private static final String DEFAULT_GENERALTAG = "AAAAAAAAAA";
    private static final String UPDATED_GENERALTAG = "BBBBBBBBBB";

    private static final Tagcolor DEFAULT_GENERALTAGCOLOR = Tagcolor.RED;
    private static final Tagcolor UPDATED_GENERALTAGCOLOR = Tagcolor.BLUE;

    private static final Boolean DEFAULT_GENERALHIDDEN = false;
    private static final Boolean UPDATED_GENERALHIDDEN = true;

    private static final Instant DEFAULT_GENERALHIDDENDELAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_GENERALHIDDENDELAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_GENERALLOCK = false;
    private static final Boolean UPDATED_GENERALLOCK = true;

    private static final Instant DEFAULT_GENERALLOCKDELAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_GENERALLOCKDELAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_GENERALEXPIRE = false;
    private static final Boolean UPDATED_GENERALEXPIRE = true;

    private static final Instant DEFAULT_GENERALEXPIREDELAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_GENERALEXPIREDELAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CHILDSNAME = "AAAAAAAAAA";
    private static final String UPDATED_CHILDSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTSNAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTSNAME = "BBBBBBBBBB";

    @Autowired
    private CategorydetailsRepository categorydetailsRepository;

    @Autowired
    private CategorydetailsMapper categorydetailsMapper;

    @Autowired
    private CategorydetailsService categorydetailsService;

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

    private MockMvc restCategorydetailsMockMvc;

    private Categorydetails categorydetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategorydetailsResource categorydetailsResource = new CategorydetailsResource(categorydetailsService);
        this.restCategorydetailsMockMvc = MockMvcBuilders.standaloneSetup(categorydetailsResource)
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
    public static Categorydetails createEntity(EntityManager em) {
        Categorydetails categorydetails = new Categorydetails()
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
            .generalabout(DEFAULT_GENERALABOUT)
            .generaltax(DEFAULT_GENERALTAX)
            .generalstock(DEFAULT_GENERALSTOCK)
            .generalprice(DEFAULT_GENERALPRICE)
            .generalmaxstock(DEFAULT_GENERALMAXSTOCK)
            .generaltag(DEFAULT_GENERALTAG)
            .generaltagcolor(DEFAULT_GENERALTAGCOLOR)
            .generalhidden(DEFAULT_GENERALHIDDEN)
            .generalhiddendelay(DEFAULT_GENERALHIDDENDELAY)
            .generallock(DEFAULT_GENERALLOCK)
            .generallockdelay(DEFAULT_GENERALLOCKDELAY)
            .generalexpire(DEFAULT_GENERALEXPIRE)
            .generalexpiredelay(DEFAULT_GENERALEXPIREDELAY)
            .childsname(DEFAULT_CHILDSNAME)
            .productsname(DEFAULT_PRODUCTSNAME);
        return categorydetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Categorydetails createUpdatedEntity(EntityManager em) {
        Categorydetails categorydetails = new Categorydetails()
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
            .generalabout(UPDATED_GENERALABOUT)
            .generaltax(UPDATED_GENERALTAX)
            .generalstock(UPDATED_GENERALSTOCK)
            .generalprice(UPDATED_GENERALPRICE)
            .generalmaxstock(UPDATED_GENERALMAXSTOCK)
            .generaltag(UPDATED_GENERALTAG)
            .generaltagcolor(UPDATED_GENERALTAGCOLOR)
            .generalhidden(UPDATED_GENERALHIDDEN)
            .generalhiddendelay(UPDATED_GENERALHIDDENDELAY)
            .generallock(UPDATED_GENERALLOCK)
            .generallockdelay(UPDATED_GENERALLOCKDELAY)
            .generalexpire(UPDATED_GENERALEXPIRE)
            .generalexpiredelay(UPDATED_GENERALEXPIREDELAY)
            .childsname(UPDATED_CHILDSNAME)
            .productsname(UPDATED_PRODUCTSNAME);
        return categorydetails;
    }

    @BeforeEach
    public void initTest() {
        categorydetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorydetails() throws Exception {
        int databaseSizeBeforeCreate = categorydetailsRepository.findAll().size();

        // Create the Categorydetails
        CategorydetailsDTO categorydetailsDTO = categorydetailsMapper.toDto(categorydetails);
        restCategorydetailsMockMvc.perform(post("/api/categorydetails")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorydetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the Categorydetails in the database
        List<Categorydetails> categorydetailsList = categorydetailsRepository.findAll();
        assertThat(categorydetailsList).hasSize(databaseSizeBeforeCreate + 1);
        Categorydetails testCategorydetails = categorydetailsList.get(categorydetailsList.size() - 1);
        assertThat(testCategorydetails.getAbout()).isEqualTo(DEFAULT_ABOUT);
        assertThat(testCategorydetails.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCategorydetails.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testCategorydetails.getTagcolor()).isEqualTo(DEFAULT_TAGCOLOR);
        assertThat(testCategorydetails.getDefaultlanguage()).isEqualTo(DEFAULT_DEFAULTLANGUAGE);
        assertThat(testCategorydetails.getPostalcode()).isEqualTo(DEFAULT_POSTALCODE);
        assertThat(testCategorydetails.getPhones()).isEqualTo(DEFAULT_PHONES);
        assertThat(testCategorydetails.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testCategorydetails.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCategorydetails.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testCategorydetails.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testCategorydetails.getGplus()).isEqualTo(DEFAULT_GPLUS);
        assertThat(testCategorydetails.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testCategorydetails.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testCategorydetails.getOpentimes()).isEqualTo(DEFAULT_OPENTIMES);
        assertThat(testCategorydetails.getOthercontacts()).isEqualTo(DEFAULT_OTHERCONTACTS);
        assertThat(testCategorydetails.getOtherfields()).isEqualTo(DEFAULT_OTHERFIELDS);
        assertThat(testCategorydetails.isGeneralabout()).isEqualTo(DEFAULT_GENERALABOUT);
        assertThat(testCategorydetails.getGeneraltax()).isEqualTo(DEFAULT_GENERALTAX);
        assertThat(testCategorydetails.getGeneralstock()).isEqualTo(DEFAULT_GENERALSTOCK);
        assertThat(testCategorydetails.getGeneralprice()).isEqualTo(DEFAULT_GENERALPRICE);
        assertThat(testCategorydetails.getGeneralmaxstock()).isEqualTo(DEFAULT_GENERALMAXSTOCK);
        assertThat(testCategorydetails.getGeneraltag()).isEqualTo(DEFAULT_GENERALTAG);
        assertThat(testCategorydetails.getGeneraltagcolor()).isEqualTo(DEFAULT_GENERALTAGCOLOR);
        assertThat(testCategorydetails.isGeneralhidden()).isEqualTo(DEFAULT_GENERALHIDDEN);
        assertThat(testCategorydetails.getGeneralhiddendelay()).isEqualTo(DEFAULT_GENERALHIDDENDELAY);
        assertThat(testCategorydetails.isGenerallock()).isEqualTo(DEFAULT_GENERALLOCK);
        assertThat(testCategorydetails.getGenerallockdelay()).isEqualTo(DEFAULT_GENERALLOCKDELAY);
        assertThat(testCategorydetails.isGeneralexpire()).isEqualTo(DEFAULT_GENERALEXPIRE);
        assertThat(testCategorydetails.getGeneralexpiredelay()).isEqualTo(DEFAULT_GENERALEXPIREDELAY);
        assertThat(testCategorydetails.getChildsname()).isEqualTo(DEFAULT_CHILDSNAME);
        assertThat(testCategorydetails.getProductsname()).isEqualTo(DEFAULT_PRODUCTSNAME);
    }

    @Test
    @Transactional
    public void createCategorydetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorydetailsRepository.findAll().size();

        // Create the Categorydetails with an existing ID
        categorydetails.setId(1L);
        CategorydetailsDTO categorydetailsDTO = categorydetailsMapper.toDto(categorydetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorydetailsMockMvc.perform(post("/api/categorydetails")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorydetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Categorydetails in the database
        List<Categorydetails> categorydetailsList = categorydetailsRepository.findAll();
        assertThat(categorydetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategorydetails() throws Exception {
        // Initialize the database
        categorydetailsRepository.saveAndFlush(categorydetails);

        // Get all the categorydetailsList
        restCategorydetailsMockMvc.perform(get("/api/categorydetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorydetails.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].generalabout").value(hasItem(DEFAULT_GENERALABOUT.booleanValue())))
            .andExpect(jsonPath("$.[*].generaltax").value(hasItem(DEFAULT_GENERALTAX)))
            .andExpect(jsonPath("$.[*].generalstock").value(hasItem(DEFAULT_GENERALSTOCK)))
            .andExpect(jsonPath("$.[*].generalprice").value(hasItem(DEFAULT_GENERALPRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].generalmaxstock").value(hasItem(DEFAULT_GENERALMAXSTOCK)))
            .andExpect(jsonPath("$.[*].generaltag").value(hasItem(DEFAULT_GENERALTAG)))
            .andExpect(jsonPath("$.[*].generaltagcolor").value(hasItem(DEFAULT_GENERALTAGCOLOR.toString())))
            .andExpect(jsonPath("$.[*].generalhidden").value(hasItem(DEFAULT_GENERALHIDDEN.booleanValue())))
            .andExpect(jsonPath("$.[*].generalhiddendelay").value(hasItem(DEFAULT_GENERALHIDDENDELAY.toString())))
            .andExpect(jsonPath("$.[*].generallock").value(hasItem(DEFAULT_GENERALLOCK.booleanValue())))
            .andExpect(jsonPath("$.[*].generallockdelay").value(hasItem(DEFAULT_GENERALLOCKDELAY.toString())))
            .andExpect(jsonPath("$.[*].generalexpire").value(hasItem(DEFAULT_GENERALEXPIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].generalexpiredelay").value(hasItem(DEFAULT_GENERALEXPIREDELAY.toString())))
            .andExpect(jsonPath("$.[*].childsname").value(hasItem(DEFAULT_CHILDSNAME)))
            .andExpect(jsonPath("$.[*].productsname").value(hasItem(DEFAULT_PRODUCTSNAME)));
    }
    
    @Test
    @Transactional
    public void getCategorydetails() throws Exception {
        // Initialize the database
        categorydetailsRepository.saveAndFlush(categorydetails);

        // Get the categorydetails
        restCategorydetailsMockMvc.perform(get("/api/categorydetails/{id}", categorydetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categorydetails.getId().intValue()))
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
            .andExpect(jsonPath("$.generalabout").value(DEFAULT_GENERALABOUT.booleanValue()))
            .andExpect(jsonPath("$.generaltax").value(DEFAULT_GENERALTAX))
            .andExpect(jsonPath("$.generalstock").value(DEFAULT_GENERALSTOCK))
            .andExpect(jsonPath("$.generalprice").value(DEFAULT_GENERALPRICE.doubleValue()))
            .andExpect(jsonPath("$.generalmaxstock").value(DEFAULT_GENERALMAXSTOCK))
            .andExpect(jsonPath("$.generaltag").value(DEFAULT_GENERALTAG))
            .andExpect(jsonPath("$.generaltagcolor").value(DEFAULT_GENERALTAGCOLOR.toString()))
            .andExpect(jsonPath("$.generalhidden").value(DEFAULT_GENERALHIDDEN.booleanValue()))
            .andExpect(jsonPath("$.generalhiddendelay").value(DEFAULT_GENERALHIDDENDELAY.toString()))
            .andExpect(jsonPath("$.generallock").value(DEFAULT_GENERALLOCK.booleanValue()))
            .andExpect(jsonPath("$.generallockdelay").value(DEFAULT_GENERALLOCKDELAY.toString()))
            .andExpect(jsonPath("$.generalexpire").value(DEFAULT_GENERALEXPIRE.booleanValue()))
            .andExpect(jsonPath("$.generalexpiredelay").value(DEFAULT_GENERALEXPIREDELAY.toString()))
            .andExpect(jsonPath("$.childsname").value(DEFAULT_CHILDSNAME))
            .andExpect(jsonPath("$.productsname").value(DEFAULT_PRODUCTSNAME));
    }

    @Test
    @Transactional
    public void getNonExistingCategorydetails() throws Exception {
        // Get the categorydetails
        restCategorydetailsMockMvc.perform(get("/api/categorydetails/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorydetails() throws Exception {
        // Initialize the database
        categorydetailsRepository.saveAndFlush(categorydetails);

        int databaseSizeBeforeUpdate = categorydetailsRepository.findAll().size();

        // Update the categorydetails
        Categorydetails updatedCategorydetails = categorydetailsRepository.findById(categorydetails.getId()).get();
        // Disconnect from session so that the updates on updatedCategorydetails are not directly saved in db
        em.detach(updatedCategorydetails);
        updatedCategorydetails
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
            .generalabout(UPDATED_GENERALABOUT)
            .generaltax(UPDATED_GENERALTAX)
            .generalstock(UPDATED_GENERALSTOCK)
            .generalprice(UPDATED_GENERALPRICE)
            .generalmaxstock(UPDATED_GENERALMAXSTOCK)
            .generaltag(UPDATED_GENERALTAG)
            .generaltagcolor(UPDATED_GENERALTAGCOLOR)
            .generalhidden(UPDATED_GENERALHIDDEN)
            .generalhiddendelay(UPDATED_GENERALHIDDENDELAY)
            .generallock(UPDATED_GENERALLOCK)
            .generallockdelay(UPDATED_GENERALLOCKDELAY)
            .generalexpire(UPDATED_GENERALEXPIRE)
            .generalexpiredelay(UPDATED_GENERALEXPIREDELAY)
            .childsname(UPDATED_CHILDSNAME)
            .productsname(UPDATED_PRODUCTSNAME);
        CategorydetailsDTO categorydetailsDTO = categorydetailsMapper.toDto(updatedCategorydetails);

        restCategorydetailsMockMvc.perform(put("/api/categorydetails")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorydetailsDTO)))
            .andExpect(status().isOk());

        // Validate the Categorydetails in the database
        List<Categorydetails> categorydetailsList = categorydetailsRepository.findAll();
        assertThat(categorydetailsList).hasSize(databaseSizeBeforeUpdate);
        Categorydetails testCategorydetails = categorydetailsList.get(categorydetailsList.size() - 1);
        assertThat(testCategorydetails.getAbout()).isEqualTo(UPDATED_ABOUT);
        assertThat(testCategorydetails.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCategorydetails.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testCategorydetails.getTagcolor()).isEqualTo(UPDATED_TAGCOLOR);
        assertThat(testCategorydetails.getDefaultlanguage()).isEqualTo(UPDATED_DEFAULTLANGUAGE);
        assertThat(testCategorydetails.getPostalcode()).isEqualTo(UPDATED_POSTALCODE);
        assertThat(testCategorydetails.getPhones()).isEqualTo(UPDATED_PHONES);
        assertThat(testCategorydetails.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testCategorydetails.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCategorydetails.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testCategorydetails.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testCategorydetails.getGplus()).isEqualTo(UPDATED_GPLUS);
        assertThat(testCategorydetails.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testCategorydetails.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testCategorydetails.getOpentimes()).isEqualTo(UPDATED_OPENTIMES);
        assertThat(testCategorydetails.getOthercontacts()).isEqualTo(UPDATED_OTHERCONTACTS);
        assertThat(testCategorydetails.getOtherfields()).isEqualTo(UPDATED_OTHERFIELDS);
        assertThat(testCategorydetails.isGeneralabout()).isEqualTo(UPDATED_GENERALABOUT);
        assertThat(testCategorydetails.getGeneraltax()).isEqualTo(UPDATED_GENERALTAX);
        assertThat(testCategorydetails.getGeneralstock()).isEqualTo(UPDATED_GENERALSTOCK);
        assertThat(testCategorydetails.getGeneralprice()).isEqualTo(UPDATED_GENERALPRICE);
        assertThat(testCategorydetails.getGeneralmaxstock()).isEqualTo(UPDATED_GENERALMAXSTOCK);
        assertThat(testCategorydetails.getGeneraltag()).isEqualTo(UPDATED_GENERALTAG);
        assertThat(testCategorydetails.getGeneraltagcolor()).isEqualTo(UPDATED_GENERALTAGCOLOR);
        assertThat(testCategorydetails.isGeneralhidden()).isEqualTo(UPDATED_GENERALHIDDEN);
        assertThat(testCategorydetails.getGeneralhiddendelay()).isEqualTo(UPDATED_GENERALHIDDENDELAY);
        assertThat(testCategorydetails.isGenerallock()).isEqualTo(UPDATED_GENERALLOCK);
        assertThat(testCategorydetails.getGenerallockdelay()).isEqualTo(UPDATED_GENERALLOCKDELAY);
        assertThat(testCategorydetails.isGeneralexpire()).isEqualTo(UPDATED_GENERALEXPIRE);
        assertThat(testCategorydetails.getGeneralexpiredelay()).isEqualTo(UPDATED_GENERALEXPIREDELAY);
        assertThat(testCategorydetails.getChildsname()).isEqualTo(UPDATED_CHILDSNAME);
        assertThat(testCategorydetails.getProductsname()).isEqualTo(UPDATED_PRODUCTSNAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorydetails() throws Exception {
        int databaseSizeBeforeUpdate = categorydetailsRepository.findAll().size();

        // Create the Categorydetails
        CategorydetailsDTO categorydetailsDTO = categorydetailsMapper.toDto(categorydetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorydetailsMockMvc.perform(put("/api/categorydetails")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorydetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Categorydetails in the database
        List<Categorydetails> categorydetailsList = categorydetailsRepository.findAll();
        assertThat(categorydetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorydetails() throws Exception {
        // Initialize the database
        categorydetailsRepository.saveAndFlush(categorydetails);

        int databaseSizeBeforeDelete = categorydetailsRepository.findAll().size();

        // Delete the categorydetails
        restCategorydetailsMockMvc.perform(delete("/api/categorydetails/{id}", categorydetails.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Categorydetails> categorydetailsList = categorydetailsRepository.findAll();
        assertThat(categorydetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
