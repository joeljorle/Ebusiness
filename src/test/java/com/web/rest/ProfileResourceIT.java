package com.web.rest;

import com.EbusinesApp;
import com.domain.Profile;
import com.repository.ProfileRepository;
import com.service.ProfileService;
import com.service.dto.ProfileDTO;
import com.service.mapper.ProfileMapper;
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

/**
 * Integration tests for the {@link ProfileResource} REST controller.
 */
@SpringBootTest(classes = EbusinesApp.class)
public class ProfileResourceIT {

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final Integer DEFAULT_USERID = 1;
    private static final Integer UPDATED_USERID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISLOCK = false;
    private static final Boolean UPDATED_ISLOCK = true;

    private static final Instant DEFAULT_LOCKDELAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LOCKDELAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ABOUT = "AAAAAAAAAA";
    private static final String UPDATED_ABOUT = "BBBBBBBBBB";

    private static final String DEFAULT_FULLNAME = "AAAAAAAAAA";
    private static final String UPDATED_FULLNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULTLANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULTLANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTALCODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTALCODE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONES = "AAAAAAAAAA";
    private static final String UPDATED_PHONES = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

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

    private static final String DEFAULT_OTHERCONTACTS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERCONTACTS = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERFIELDS = "AAAAAAAAAA";
    private static final String UPDATED_OTHERFIELDS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private ProfileService profileService;

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

    private MockMvc restProfileMockMvc;

    private Profile profile;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfileResource profileResource = new ProfileResource(profileService);
        this.restProfileMockMvc = MockMvcBuilders.standaloneSetup(profileResource)
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
    public static Profile createEntity(EntityManager em) {
        Profile profile = new Profile()
            .slug(DEFAULT_SLUG)
            .userid(DEFAULT_USERID)
            .name(DEFAULT_NAME)
            .islock(DEFAULT_ISLOCK)
            .lockdelay(DEFAULT_LOCKDELAY)
            .about(DEFAULT_ABOUT)
            .fullname(DEFAULT_FULLNAME)
            .defaultlanguage(DEFAULT_DEFAULTLANGUAGE)
            .postalcode(DEFAULT_POSTALCODE)
            .phones(DEFAULT_PHONES)
            .website(DEFAULT_WEBSITE)
            .facebook(DEFAULT_FACEBOOK)
            .twitter(DEFAULT_TWITTER)
            .gplus(DEFAULT_GPLUS)
            .linkedin(DEFAULT_LINKEDIN)
            .instagram(DEFAULT_INSTAGRAM)
            .othercontacts(DEFAULT_OTHERCONTACTS)
            .otherfields(DEFAULT_OTHERFIELDS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT);
        return profile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Profile createUpdatedEntity(EntityManager em) {
        Profile profile = new Profile()
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .name(UPDATED_NAME)
            .islock(UPDATED_ISLOCK)
            .lockdelay(UPDATED_LOCKDELAY)
            .about(UPDATED_ABOUT)
            .fullname(UPDATED_FULLNAME)
            .defaultlanguage(UPDATED_DEFAULTLANGUAGE)
            .postalcode(UPDATED_POSTALCODE)
            .phones(UPDATED_PHONES)
            .website(UPDATED_WEBSITE)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .gplus(UPDATED_GPLUS)
            .linkedin(UPDATED_LINKEDIN)
            .instagram(UPDATED_INSTAGRAM)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        return profile;
    }

    @BeforeEach
    public void initTest() {
        profile = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfile() throws Exception {
        int databaseSizeBeforeCreate = profileRepository.findAll().size();

        // Create the Profile
        ProfileDTO profileDTO = profileMapper.toDto(profile);
        restProfileMockMvc.perform(post("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isCreated());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeCreate + 1);
        Profile testProfile = profileList.get(profileList.size() - 1);
        assertThat(testProfile.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testProfile.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testProfile.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProfile.isIslock()).isEqualTo(DEFAULT_ISLOCK);
        assertThat(testProfile.getLockdelay()).isEqualTo(DEFAULT_LOCKDELAY);
        assertThat(testProfile.getAbout()).isEqualTo(DEFAULT_ABOUT);
        assertThat(testProfile.getFullname()).isEqualTo(DEFAULT_FULLNAME);
        assertThat(testProfile.getDefaultlanguage()).isEqualTo(DEFAULT_DEFAULTLANGUAGE);
        assertThat(testProfile.getPostalcode()).isEqualTo(DEFAULT_POSTALCODE);
        assertThat(testProfile.getPhones()).isEqualTo(DEFAULT_PHONES);
        assertThat(testProfile.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testProfile.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testProfile.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testProfile.getGplus()).isEqualTo(DEFAULT_GPLUS);
        assertThat(testProfile.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testProfile.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testProfile.getOthercontacts()).isEqualTo(DEFAULT_OTHERCONTACTS);
        assertThat(testProfile.getOtherfields()).isEqualTo(DEFAULT_OTHERFIELDS);
        assertThat(testProfile.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testProfile.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
    }

    @Test
    @Transactional
    public void createProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profileRepository.findAll().size();

        // Create the Profile with an existing ID
        profile.setId(1L);
        ProfileDTO profileDTO = profileMapper.toDto(profile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfileMockMvc.perform(post("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setSlug(null);

        // Create the Profile, which fails.
        ProfileDTO profileDTO = profileMapper.toDto(profile);

        restProfileMockMvc.perform(post("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProfiles() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList
        restProfileMockMvc.perform(get("/api/profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profile.getId().intValue())))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].islock").value(hasItem(DEFAULT_ISLOCK.booleanValue())))
            .andExpect(jsonPath("$.[*].lockdelay").value(hasItem(DEFAULT_LOCKDELAY.toString())))
            .andExpect(jsonPath("$.[*].about").value(hasItem(DEFAULT_ABOUT.toString())))
            .andExpect(jsonPath("$.[*].fullname").value(hasItem(DEFAULT_FULLNAME)))
            .andExpect(jsonPath("$.[*].defaultlanguage").value(hasItem(DEFAULT_DEFAULTLANGUAGE)))
            .andExpect(jsonPath("$.[*].postalcode").value(hasItem(DEFAULT_POSTALCODE)))
            .andExpect(jsonPath("$.[*].phones").value(hasItem(DEFAULT_PHONES)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK)))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER)))
            .andExpect(jsonPath("$.[*].gplus").value(hasItem(DEFAULT_GPLUS)))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN)))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM)))
            .andExpect(jsonPath("$.[*].othercontacts").value(hasItem(DEFAULT_OTHERCONTACTS.toString())))
            .andExpect(jsonPath("$.[*].otherfields").value(hasItem(DEFAULT_OTHERFIELDS.toString())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())));
    }
    
    @Test
    @Transactional
    public void getProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get the profile
        restProfileMockMvc.perform(get("/api/profiles/{id}", profile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profile.getId().intValue()))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.islock").value(DEFAULT_ISLOCK.booleanValue()))
            .andExpect(jsonPath("$.lockdelay").value(DEFAULT_LOCKDELAY.toString()))
            .andExpect(jsonPath("$.about").value(DEFAULT_ABOUT.toString()))
            .andExpect(jsonPath("$.fullname").value(DEFAULT_FULLNAME))
            .andExpect(jsonPath("$.defaultlanguage").value(DEFAULT_DEFAULTLANGUAGE))
            .andExpect(jsonPath("$.postalcode").value(DEFAULT_POSTALCODE))
            .andExpect(jsonPath("$.phones").value(DEFAULT_PHONES))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK))
            .andExpect(jsonPath("$.twitter").value(DEFAULT_TWITTER))
            .andExpect(jsonPath("$.gplus").value(DEFAULT_GPLUS))
            .andExpect(jsonPath("$.linkedin").value(DEFAULT_LINKEDIN))
            .andExpect(jsonPath("$.instagram").value(DEFAULT_INSTAGRAM))
            .andExpect(jsonPath("$.othercontacts").value(DEFAULT_OTHERCONTACTS.toString()))
            .andExpect(jsonPath("$.otherfields").value(DEFAULT_OTHERFIELDS.toString()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProfile() throws Exception {
        // Get the profile
        restProfileMockMvc.perform(get("/api/profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        int databaseSizeBeforeUpdate = profileRepository.findAll().size();

        // Update the profile
        Profile updatedProfile = profileRepository.findById(profile.getId()).get();
        // Disconnect from session so that the updates on updatedProfile are not directly saved in db
        em.detach(updatedProfile);
        updatedProfile
            .slug(UPDATED_SLUG)
            .userid(UPDATED_USERID)
            .name(UPDATED_NAME)
            .islock(UPDATED_ISLOCK)
            .lockdelay(UPDATED_LOCKDELAY)
            .about(UPDATED_ABOUT)
            .fullname(UPDATED_FULLNAME)
            .defaultlanguage(UPDATED_DEFAULTLANGUAGE)
            .postalcode(UPDATED_POSTALCODE)
            .phones(UPDATED_PHONES)
            .website(UPDATED_WEBSITE)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .gplus(UPDATED_GPLUS)
            .linkedin(UPDATED_LINKEDIN)
            .instagram(UPDATED_INSTAGRAM)
            .othercontacts(UPDATED_OTHERCONTACTS)
            .otherfields(UPDATED_OTHERFIELDS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT);
        ProfileDTO profileDTO = profileMapper.toDto(updatedProfile);

        restProfileMockMvc.perform(put("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isOk());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeUpdate);
        Profile testProfile = profileList.get(profileList.size() - 1);
        assertThat(testProfile.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testProfile.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testProfile.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProfile.isIslock()).isEqualTo(UPDATED_ISLOCK);
        assertThat(testProfile.getLockdelay()).isEqualTo(UPDATED_LOCKDELAY);
        assertThat(testProfile.getAbout()).isEqualTo(UPDATED_ABOUT);
        assertThat(testProfile.getFullname()).isEqualTo(UPDATED_FULLNAME);
        assertThat(testProfile.getDefaultlanguage()).isEqualTo(UPDATED_DEFAULTLANGUAGE);
        assertThat(testProfile.getPostalcode()).isEqualTo(UPDATED_POSTALCODE);
        assertThat(testProfile.getPhones()).isEqualTo(UPDATED_PHONES);
        assertThat(testProfile.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testProfile.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testProfile.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testProfile.getGplus()).isEqualTo(UPDATED_GPLUS);
        assertThat(testProfile.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testProfile.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testProfile.getOthercontacts()).isEqualTo(UPDATED_OTHERCONTACTS);
        assertThat(testProfile.getOtherfields()).isEqualTo(UPDATED_OTHERFIELDS);
        assertThat(testProfile.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testProfile.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingProfile() throws Exception {
        int databaseSizeBeforeUpdate = profileRepository.findAll().size();

        // Create the Profile
        ProfileDTO profileDTO = profileMapper.toDto(profile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfileMockMvc.perform(put("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        int databaseSizeBeforeDelete = profileRepository.findAll().size();

        // Delete the profile
        restProfileMockMvc.perform(delete("/api/profiles/{id}", profile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
