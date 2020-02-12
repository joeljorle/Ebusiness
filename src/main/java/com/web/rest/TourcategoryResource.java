package com.web.rest;

import com.service.TourcategoryService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.TourcategoryDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.domain.Tourcategory}.
 */
@RestController
@RequestMapping("/api")
public class TourcategoryResource {

    private final Logger log = LoggerFactory.getLogger(TourcategoryResource.class);

    private static final String ENTITY_NAME = "tourcategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TourcategoryService tourcategoryService;

    public TourcategoryResource(TourcategoryService tourcategoryService) {
        this.tourcategoryService = tourcategoryService;
    }

    /**
     * {@code POST  /tourcategories} : Create a new tourcategory.
     *
     * @param tourcategoryDTO the tourcategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tourcategoryDTO, or with status {@code 400 (Bad Request)} if the tourcategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tourcategories")
    public ResponseEntity<TourcategoryDTO> createTourcategory(@Valid @RequestBody TourcategoryDTO tourcategoryDTO) throws URISyntaxException {
        log.debug("REST request to save Tourcategory : {}", tourcategoryDTO);
        if (tourcategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new tourcategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TourcategoryDTO result = tourcategoryService.save(tourcategoryDTO);
        return ResponseEntity.created(new URI("/api/tourcategories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tourcategories} : Updates an existing tourcategory.
     *
     * @param tourcategoryDTO the tourcategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tourcategoryDTO,
     * or with status {@code 400 (Bad Request)} if the tourcategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tourcategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tourcategories")
    public ResponseEntity<TourcategoryDTO> updateTourcategory(@Valid @RequestBody TourcategoryDTO tourcategoryDTO) throws URISyntaxException {
        log.debug("REST request to update Tourcategory : {}", tourcategoryDTO);
        if (tourcategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TourcategoryDTO result = tourcategoryService.save(tourcategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tourcategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tourcategories} : get all the tourcategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tourcategories in body.
     */
    @GetMapping("/tourcategories")
    public ResponseEntity<List<TourcategoryDTO>> getAllTourcategories(Pageable pageable) {
        log.debug("REST request to get a page of Tourcategories");
        Page<TourcategoryDTO> page = tourcategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tourcategories/:id} : get the "id" tourcategory.
     *
     * @param id the id of the tourcategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tourcategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tourcategories/{id}")
    public ResponseEntity<TourcategoryDTO> getTourcategory(@PathVariable Long id) {
        log.debug("REST request to get Tourcategory : {}", id);
        Optional<TourcategoryDTO> tourcategoryDTO = tourcategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tourcategoryDTO);
    }

    /**
     * {@code DELETE  /tourcategories/:id} : delete the "id" tourcategory.
     *
     * @param id the id of the tourcategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tourcategories/{id}")
    public ResponseEntity<Void> deleteTourcategory(@PathVariable Long id) {
        log.debug("REST request to delete Tourcategory : {}", id);
        tourcategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
