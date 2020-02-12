package com.web.rest;

import com.EBusinessApp;
import com.domain.Files;
import com.repository.FilesRepository;
import com.service.FilesService;
import com.service.dto.FilesDTO;
import com.service.mapper.FilesMapper;
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
 * Integration tests for the {@link FilesResource} REST controller.
 */
@SpringBootTest(classes = EBusinessApp.class)
public class FilesResourceIT {

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final Integer DEFAULT_USERID = 1;
    private static final Integer UPDATED_USERID = 2;

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;

    private static final Integer DEFAULT_PRODUCTID = 1;
    private static final Integer UPDATED_PRODUCTID = 2;

    private static final Integer DEFAULT_TOURID = 1;
    private static final Integer UPDATED_TOURID = 2;

    private static final Integer DEFAULT_TOURGROUPID = 1;
    private static final Integer UPDATED_TOURGROUPID = 2;

    private static final Integer DEFAULT_TOURCATEGORYID = 1;
    private static final Integer UPDATED_TOURCATEGORYID = 2;

    private static final Integer DEFAULT_EVENEMENTID = 1;
    private static final Integer UPDATED_EVENEMENTID = 2;

    private static final Boolean DEFAULT_ISLOGOIMG = false;
    private static final Boolean UPDATED_ISLOGOIMG = true;

    private static final Boolean DEFAULT_ISPROFILEIMG = false;
    private static final Boolean UPDATED_ISPROFILEIMG = true;

    private static final Boolean DEFAULT_ISCOVERIMG = false;
    private static final Boolean UPDATED_ISCOVERIMG = true;

    private static final Boolean DEFAULT_ISSLIDERIMG = false;
    private static final Boolean UPDATED_ISSLIDERIMG = true;

    private static final Boolean DEFAULT_ISOTHERIMG = false;
    private static final Boolean UPDATED_ISOTHERIMG = true;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private FilesMapper filesMapper;

    @Autowired
    private FilesService filesService;

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

    private MockMvc restFilesMockMvc;

    private Files files;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FilesResource filesResource = new FilesResource(filesService);
        this.restFilesMockMvc = MockMvcBuilders.standaloneSetup(filesResource)
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
    public static Files createEntity(EntityManager em) {
        Files files = new Files()
            .slug(DEFAULT_SLUG)
            .userid(DEFAULT_USERID)
            .categoryid(DEFAULT_CATEGORYID)
            .productid(DEFAULT_PRODUCTID)
            .tourid(DEFAULT_TOURID)
            .tourgroupid(DEFAULT_TOURGROUPID)
            .tourcategoryid(DEFAULT_TOURCATEGORYID)
            .evenementid(DEFAULT_EVENEMENTID)
            .islogoimg(DEFAULT_ISLOGOIMG)
            .isprofileimg(DEFAULT_ISPROFILEIMG)
            .iscoverimg(DEFAULT_ISCOVERIMG)
            .issliderimg(DEFAULT_ISSLIDERIMG)
            .isotherimg(DEFAULT_ISOTHERIMG)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return files;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Files createUpdatedEntity(EntityManager em) {
        Files files = new Files()
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .tourcategoryid(UPDATED_TOURCATEGORYID)
            .evenementid(UPDATED_EVENEMENTID)
            .islogoimg(UPDATED_ISLOGOIMG)
            .isprofileimg(UPDATED_ISPROFILEIMG)
            .iscoverimg(UPDATED_ISCOVERIMG)
            .issliderimg(UPDATED_ISSLIDERIMG)
            .isotherimg(UPDATED_ISOTHERIMG)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return files;
    }

    @BeforeEach
    public void initTest() {
        files = createEntity(em);
    }

    @Test
    @Transactional
    public void createFiles() throws Exception {
        int databaseSizeBeforeCreate = filesRepository.findAll().size();

        // Create the Files
        FilesDTO filesDTO = filesMapper.toDto(files);
        restFilesMockMvc.perform(post("/api/files")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isCreated());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeCreate + 1);
        Files testFiles = filesList.get(filesList.size() - 1);
        assertThat(testFiles.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testFiles.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testFiles.getCategoryid()).isEqualTo(DEFAULT_CATEGORYID);
        assertThat(testFiles.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testFiles.getTourid()).isEqualTo(DEFAULT_TOURID);
        assertThat(testFiles.getTourgroupid()).isEqualTo(DEFAULT_TOURGROUPID);
        assertThat(testFiles.getTourcategoryid()).isEqualTo(DEFAULT_TOURCATEGORYID);
        assertThat(testFiles.getEvenementid()).isEqualTo(DEFAULT_EVENEMENTID);
        assertThat(testFiles.isIslogoimg()).isEqualTo(DEFAULT_ISLOGOIMG);
        assertThat(testFiles.isIsprofileimg()).isEqualTo(DEFAULT_ISPROFILEIMG);
        assertThat(testFiles.isIscoverimg()).isEqualTo(DEFAULT_ISCOVERIMG);
        assertThat(testFiles.isIssliderimg()).isEqualTo(DEFAULT_ISSLIDERIMG);
        assertThat(testFiles.isIsotherimg()).isEqualTo(DEFAULT_ISOTHERIMG);
        assertThat(testFiles.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testFiles.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createFilesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = filesRepository.findAll().size();

        // Create the Files with an existing ID
        files.setId(1L);
        FilesDTO filesDTO = filesMapper.toDto(files);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFilesMockMvc.perform(post("/api/files")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = filesRepository.findAll().size();
        // set the field null
        files.setSlug(null);

        // Create the Files, which fails.
        FilesDTO filesDTO = filesMapper.toDto(files);

        restFilesMockMvc.perform(post("/api/files")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isBadRequest());

        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        // Get all the filesList
        restFilesMockMvc.perform(get("/api/files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(files.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID)))
            .andExpect(jsonPath("$.[*].tourid").value(hasItem(DEFAULT_TOURID)))
            .andExpect(jsonPath("$.[*].tourgroupid").value(hasItem(DEFAULT_TOURGROUPID)))
            .andExpect(jsonPath("$.[*].tourcategoryid").value(hasItem(DEFAULT_TOURCATEGORYID)))
            .andExpect(jsonPath("$.[*].evenementid").value(hasItem(DEFAULT_EVENEMENTID)))
            .andExpect(jsonPath("$.[*].islogoimg").value(hasItem(DEFAULT_ISLOGOIMG.booleanValue())))
            .andExpect(jsonPath("$.[*].isprofileimg").value(hasItem(DEFAULT_ISPROFILEIMG.booleanValue())))
            .andExpect(jsonPath("$.[*].iscoverimg").value(hasItem(DEFAULT_ISCOVERIMG.booleanValue())))
            .andExpect(jsonPath("$.[*].issliderimg").value(hasItem(DEFAULT_ISSLIDERIMG.booleanValue())))
            .andExpect(jsonPath("$.[*].isotherimg").value(hasItem(DEFAULT_ISOTHERIMG.booleanValue())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        // Get the files
        restFilesMockMvc.perform(get("/api/files/{id}", files.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(files.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID))
            .andExpect(jsonPath("$.tourid").value(DEFAULT_TOURID))
            .andExpect(jsonPath("$.tourgroupid").value(DEFAULT_TOURGROUPID))
            .andExpect(jsonPath("$.tourcategoryid").value(DEFAULT_TOURCATEGORYID))
            .andExpect(jsonPath("$.evenementid").value(DEFAULT_EVENEMENTID))
            .andExpect(jsonPath("$.islogoimg").value(DEFAULT_ISLOGOIMG.booleanValue()))
            .andExpect(jsonPath("$.isprofileimg").value(DEFAULT_ISPROFILEIMG.booleanValue()))
            .andExpect(jsonPath("$.iscoverimg").value(DEFAULT_ISCOVERIMG.booleanValue()))
            .andExpect(jsonPath("$.issliderimg").value(DEFAULT_ISSLIDERIMG.booleanValue()))
            .andExpect(jsonPath("$.isotherimg").value(DEFAULT_ISOTHERIMG.booleanValue()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFiles() throws Exception {
        // Get the files
        restFilesMockMvc.perform(get("/api/files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        int databaseSizeBeforeUpdate = filesRepository.findAll().size();

        // Update the files
        Files updatedFiles = filesRepository.findById(files.getId()).get();
        // Disconnect from session so that the updates on updatedFiles are not directly saved in db
        em.detach(updatedFiles);
        updatedFiles
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .categoryid(UPDATED_CATEGORYID)
            .productid(UPDATED_PRODUCTID)
            .tourid(UPDATED_TOURID)
            .tourgroupid(UPDATED_TOURGROUPID)
            .tourcategoryid(UPDATED_TOURCATEGORYID)
            .evenementid(UPDATED_EVENEMENTID)
            .islogoimg(UPDATED_ISLOGOIMG)
            .isprofileimg(UPDATED_ISPROFILEIMG)
            .iscoverimg(UPDATED_ISCOVERIMG)
            .issliderimg(UPDATED_ISSLIDERIMG)
            .isotherimg(UPDATED_ISOTHERIMG)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        FilesDTO filesDTO = filesMapper.toDto(updatedFiles);

        restFilesMockMvc.perform(put("/api/files")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isOk());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeUpdate);
        Files testFiles = filesList.get(filesList.size() - 1);
        assertThat(testFiles.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testFiles.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testFiles.getCategoryid()).isEqualTo(UPDATED_CATEGORYID);
        assertThat(testFiles.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testFiles.getTourid()).isEqualTo(UPDATED_TOURID);
        assertThat(testFiles.getTourgroupid()).isEqualTo(UPDATED_TOURGROUPID);
        assertThat(testFiles.getTourcategoryid()).isEqualTo(UPDATED_TOURCATEGORYID);
        assertThat(testFiles.getEvenementid()).isEqualTo(UPDATED_EVENEMENTID);
        assertThat(testFiles.isIslogoimg()).isEqualTo(UPDATED_ISLOGOIMG);
        assertThat(testFiles.isIsprofileimg()).isEqualTo(UPDATED_ISPROFILEIMG);
        assertThat(testFiles.isIscoverimg()).isEqualTo(UPDATED_ISCOVERIMG);
        assertThat(testFiles.isIssliderimg()).isEqualTo(UPDATED_ISSLIDERIMG);
        assertThat(testFiles.isIsotherimg()).isEqualTo(UPDATED_ISOTHERIMG);
        assertThat(testFiles.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testFiles.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingFiles() throws Exception {
        int databaseSizeBeforeUpdate = filesRepository.findAll().size();

        // Create the Files
        FilesDTO filesDTO = filesMapper.toDto(files);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFilesMockMvc.perform(put("/api/files")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        int databaseSizeBeforeDelete = filesRepository.findAll().size();

        // Delete the files
        restFilesMockMvc.perform(delete("/api/files/{id}", files.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
