package com.web.rest;

import com.service.TourgroupService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.TourgroupDTO;

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
 * REST controller for managing {@link com.domain.Tourgroup}.
 */
@RestController
@RequestMapping("/api")
public class TourgroupResource {

    private final Logger log = LoggerFactory.getLogger(TourgroupResource.class);

    private static final String ENTITY_NAME = "tourgroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TourgroupService tourgroupService;

    public TourgroupResource(TourgroupService tourgroupService) {
        this.tourgroupService = tourgroupService;
    }

    /**
     * {@code POST  /tourgroups} : Create a new tourgroup.
     *
     * @param tourgroupDTO the tourgroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tourgroupDTO, or with status {@code 400 (Bad Request)} if the tourgroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tourgroups")
    public ResponseEntity<TourgroupDTO> createTourgroup(@Valid @RequestBody TourgroupDTO tourgroupDTO) throws URISyntaxException {
        log.debug("REST request to save Tourgroup : {}", tourgroupDTO);
        if (tourgroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new tourgroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TourgroupDTO result = tourgroupService.save(tourgroupDTO);
        return ResponseEntity.created(new URI("/api/tourgroups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tourgroups} : Updates an existing tourgroup.
     *
     * @param tourgroupDTO the tourgroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tourgroupDTO,
     * or with status {@code 400 (Bad Request)} if the tourgroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tourgroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tourgroups")
    public ResponseEntity<TourgroupDTO> updateTourgroup(@Valid @RequestBody TourgroupDTO tourgroupDTO) throws URISyntaxException {
        log.debug("REST request to update Tourgroup : {}", tourgroupDTO);
        if (tourgroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TourgroupDTO result = tourgroupService.save(tourgroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tourgroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tourgroups} : get all the tourgroups.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tourgroups in body.
     */
    @GetMapping("/tourgroups")
    public ResponseEntity<List<TourgroupDTO>> getAllTourgroups(Pageable pageable) {
        log.debug("REST request to get a page of Tourgroups");
        Page<TourgroupDTO> page = tourgroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tourgroups/:id} : get the "id" tourgroup.
     *
     * @param id the id of the tourgroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tourgroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tourgroups/{id}")
    public ResponseEntity<TourgroupDTO> getTourgroup(@PathVariable Long id) {
        log.debug("REST request to get Tourgroup : {}", id);
        Optional<TourgroupDTO> tourgroupDTO = tourgroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tourgroupDTO);
    }

    /**
     * {@code DELETE  /tourgroups/:id} : delete the "id" tourgroup.
     *
     * @param id the id of the tourgroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tourgroups/{id}")
    public ResponseEntity<Void> deleteTourgroup(@PathVariable Long id) {
        log.debug("REST request to delete Tourgroup : {}", id);
        tourgroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
