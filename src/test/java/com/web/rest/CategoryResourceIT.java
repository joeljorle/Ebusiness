package com.web.rest;

import com.EbusinesApp;
import com.domain.Category;
import com.repository.CategoryRepository;
import com.service.CategoryService;
import com.service.dto.CategoryDTO;
import com.service.mapper.CategoryMapper;
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

import com.domain.enumeration.Type;
/**
 * Integration tests for the {@link CategoryResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class CategoryResourceIT {

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final Integer DEFAULT_USERID = 1;
    private static final Integer UPDATED_USERID = 2;

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISHEADER = false;
    private static final Boolean UPDATED_ISHEADER = true;

    private static final Boolean DEFAULT_ISGROUP = false;
    private static final Boolean UPDATED_ISGROUP = true;

    private static final Boolean DEFAULT_ISLOCK = false;
    private static final Boolean UPDATED_ISLOCK = true;

    private static final Instant DEFAULT_LOCKDELAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LOCKDELAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Type DEFAULT_TYPE = Type.SHOP;
    private static final Type UPDATED_TYPE = Type.FOOD;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

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

    private MockMvc restCategoryMockMvc;

    private Category category;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategoryResource categoryResource = new CategoryResource(categoryService);
        this.restCategoryMockMvc = MockMvcBuilders.standaloneSetup(categoryResource)
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
    public static Category createEntity(EntityManager em) {
        Category category = new Category()
            .slug(DEFAULT_SLUG)
            .userid(DEFAULT_USERID)
            .categoryid(DEFAULT_CATEGORYID)
            .name(DEFAULT_NAME)
            .route(DEFAULT_ROUTE)
            .isheader(DEFAULT_ISHEADER)
            .isgroup(DEFAULT_ISGROUP)
            .islock(DEFAULT_ISLOCK)
            .lockdelay(DEFAULT_LOCKDELAY)
            .type(DEFAULT_TYPE)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return category;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Category createUpdatedEntity(EntityManager em) {
        Category category = new Category()
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .name(UPDATED_NAME)
            .route(UPDATED_ROUTE)
            .isheader(UPDATED_ISHEADER)
            .isgroup(UPDATED_ISGROUP)
            .islock(UPDATED_ISLOCK)
            .lockdelay(UPDATED_LOCKDELAY)
            .type(UPDATED_TYPE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return category;
    }

    @BeforeEach
    public void initTest() {
        category = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategory() throws Exception {
        int databaseSizeBeforeCreate = categoryRepository.findAll().size();

        // Create the Category
        CategoryDTO categoryDTO = categoryMapper.toDto(category);
        restCategoryMockMvc.perform(post("/api/categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryDTO)))
            .andExpect(status().isCreated());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeCreate + 1);
        Category testCategory = categoryList.get(categoryList.size() - 1);
        assertThat(testCategory.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testCategory.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testCategory.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testCategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCategory.getRoute()).isEqualTo(DEFAULT_ROUTE);
        assertThat(testCategory.isIsheader()).isEqualTo(DEFAULT_ISHEADER);
        assertThat(testCategory.isIsgroup()).isEqualTo(DEFAULT_ISGROUP);
        assertThat(testCategory.isIslock()).isEqualTo(DEFAULT_ISLOCK);
        assertThat(testCategory.getLockdelay()).isEqualTo(DEFAULT_LOCKDELAY);
        assertThat(testCategory.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCategory.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testCategory.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoryRepository.findAll().size();

        // Create the Category with an existing ID
        category.setId(1L);
        CategoryDTO categoryDTO = categoryMapper.toDto(category);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryMockMvc.perform(post("/api/categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryRepository.findAll().size();
        // set the field null
        category.setSlug(null);

        // Create the Category, which fails.
        CategoryDTO categoryDTO = categoryMapper.toDto(category);

        restCategoryMockMvc.perform(post("/api/categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryDTO)))
            .andExpect(status().isBadRequest());

        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategories() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList
        restCategoryMockMvc.perform(get("/api/categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(category.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].route").value(hasItem(DEFAULT_ROUTE)))
            .andExpect(jsonPath("$.[*].isheader").value(hasItem(DEFAULT_ISHEADER.booleanValue())))
            .andExpect(jsonPath("$.[*].isgroup").value(hasItem(DEFAULT_ISGROUP.booleanValue())))
            .andExpect(jsonPath("$.[*].islock").value(hasItem(DEFAULT_ISLOCK.booleanValue())))
            .andExpect(jsonPath("$.[*].lockdelay").value(hasItem(DEFAULT_LOCKDELAY.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getCategory() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get the category
        restCategoryMockMvc.perform(get("/api/categories/{id}", category.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(category.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.route").value(DEFAULT_ROUTE))
            .andExpect(jsonPath("$.isheader").value(DEFAULT_ISHEADER.booleanValue()))
            .andExpect(jsonPath("$.isgroup").value(DEFAULT_ISGROUP.booleanValue()))
            .andExpect(jsonPath("$.islock").value(DEFAULT_ISLOCK.booleanValue()))
            .andExpect(jsonPath("$.lockdelay").value(DEFAULT_LOCKDELAY.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategory() throws Exception {
        // Get the category
        restCategoryMockMvc.perform(get("/api/categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategory() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        int databaseSizeBeforeUpdate = categoryRepository.findAll().size();

        // Update the category
        Category updatedCategory = categoryRepository.findById(category.getId()).get();
        // Disconnect from session so that the updates on updatedCategory are not directly saved in db
        em.detach(updatedCategory);
        updatedCategory
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .name(UPDATED_NAME)
            .route(UPDATED_ROUTE)
            .isheader(UPDATED_ISHEADER)
            .isgroup(UPDATED_ISGROUP)
            .islock(UPDATED_ISLOCK)
            .lockdelay(UPDATED_LOCKDELAY)
            .type(UPDATED_TYPE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        CategoryDTO categoryDTO = categoryMapper.toDto(updatedCategory);

        restCategoryMockMvc.perform(put("/api/categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryDTO)))
            .andExpect(status().isOk());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate);
        Category testCategory = categoryList.get(categoryList.size() - 1);
        assertThat(testCategory.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testCategory.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testCategory.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testCategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCategory.getRoute()).isEqualTo(UPDATED_ROUTE);
        assertThat(testCategory.isIsheader()).isEqualTo(UPDATED_ISHEADER);
        assertThat(testCategory.isIsgroup()).isEqualTo(UPDATED_ISGROUP);
        assertThat(testCategory.isIslock()).isEqualTo(UPDATED_ISLOCK);
        assertThat(testCategory.getLockdelay()).isEqualTo(UPDATED_LOCKDELAY);
        assertThat(testCategory.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCategory.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testCategory.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingCategory() throws Exception {
        int databaseSizeBeforeUpdate = categoryRepository.findAll().size();

        // Create the Category
        CategoryDTO categoryDTO = categoryMapper.toDto(category);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryMockMvc.perform(put("/api/categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategory() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        int databaseSizeBeforeDelete = categoryRepository.findAll().size();

        // Delete the category
        restCategoryMockMvc.perform(delete("/api/categories/{id}", category.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
