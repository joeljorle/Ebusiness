package com.web.rest;

import com.service.CategorydetailsService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.CategorydetailsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.domain.Categorydetails}.
 */
@RestController
@RequestMapping("/api")
public class CategorydetailsResource {

    private final Logger log = LoggerFactory.getLogger(CategorydetailsResource.class);

    private static final String ENTITY_NAME = "categorydetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategorydetailsService categorydetailsService;

    public CategorydetailsResource(CategorydetailsService categorydetailsService) {
        this.categorydetailsService = categorydetailsService;
    }

    /**
     * {@code POST  /categorydetails} : Create a new categorydetails.
     *
     * @param categorydetailsDTO the categorydetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorydetailsDTO, or with status {@code 400 (Bad Request)} if the categorydetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categorydetails")
    public ResponseEntity<CategorydetailsDTO> createCategorydetails(@RequestBody CategorydetailsDTO categorydetailsDTO) throws URISyntaxException {
        log.debug("REST request to save Categorydetails : {}", categorydetailsDTO);
        if (categorydetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorydetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorydetailsDTO result = categorydetailsService.save(categorydetailsDTO);
        return ResponseEntity.created(new URI("/api/categorydetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categorydetails} : Updates an existing categorydetails.
     *
     * @param categorydetailsDTO the categorydetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorydetailsDTO,
     * or with status {@code 400 (Bad Request)} if the categorydetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorydetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categorydetails")
    public ResponseEntity<CategorydetailsDTO> updateCategorydetails(@RequestBody CategorydetailsDTO categorydetailsDTO) throws URISyntaxException {
        log.debug("REST request to update Categorydetails : {}", categorydetailsDTO);
        if (categorydetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorydetailsDTO result = categorydetailsService.save(categorydetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categorydetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categorydetails} : get all the categorydetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categorydetails in body.
     */
    @GetMapping("/categorydetails")
    public ResponseEntity<List<CategorydetailsDTO>> getAllCategorydetails(Pageable pageable) {
        log.debug("REST request to get a page of Categorydetails");
        Page<CategorydetailsDTO> page = categorydetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categorydetails/:id} : get the "id" categorydetails.
     *
     * @param id the id of the categorydetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorydetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categorydetails/{id}")
    public ResponseEntity<CategorydetailsDTO> getCategorydetails(@PathVariable Long id) {
        log.debug("REST request to get Categorydetails : {}", id);
        Optional<CategorydetailsDTO> categorydetailsDTO = categorydetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorydetailsDTO);
    }

    /**
     * {@code DELETE  /categorydetails/:id} : delete the "id" categorydetails.
     *
     * @param id the id of the categorydetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categorydetails/{id}")
    public ResponseEntity<Void> deleteCategorydetails(@PathVariable Long id) {
        log.debug("REST request to delete Categorydetails : {}", id);
        categorydetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
