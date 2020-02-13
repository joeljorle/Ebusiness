package com.web.rest;

import com.service.TourService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.TourDTO;

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
 * REST controller for managing {@link com.domain.Tour}.
 */
@RestController
@RequestMapping("/api")
public class TourResource {

    private final Logger log = LoggerFactory.getLogger(TourResource.class);

    private static final String ENTITY_NAME = "tour";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TourService tourService;

    public TourResource(TourService tourService) {
        this.tourService = tourService;
    }

    /**
     * {@code POST  /tours} : Create a new tour.
     *
     * @param tourDTO the tourDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tourDTO, or with status {@code 400 (Bad Request)} if the tour has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tours")
    public ResponseEntity<TourDTO> createTour(@Valid @RequestBody TourDTO tourDTO) throws URISyntaxException {
        log.debug("REST request to save Tour : {}", tourDTO);
        if (tourDTO.getId() != null) {
            throw new BadRequestAlertException("A new tour cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TourDTO result = tourService.save(tourDTO);
        return ResponseEntity.created(new URI("/api/tours/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tours} : Updates an existing tour.
     *
     * @param tourDTO the tourDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tourDTO,
     * or with status {@code 400 (Bad Request)} if the tourDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tourDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tours")
    public ResponseEntity<TourDTO> updateTour(@Valid @RequestBody TourDTO tourDTO) throws URISyntaxException {
        log.debug("REST request to update Tour : {}", tourDTO);
        if (tourDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TourDTO result = tourService.save(tourDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tourDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tours} : get all the tours.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tours in body.
     */
    @GetMapping("/tours")
    public ResponseEntity<List<TourDTO>> getAllTours(Pageable pageable) {
        log.debug("REST request to get a page of Tours");
        Page<TourDTO> page = tourService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tours/:id} : get the "id" tour.
     *
     * @param id the id of the tourDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tourDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tours/{id}")
    public ResponseEntity<TourDTO> getTour(@PathVariable Long id) {
        log.debug("REST request to get Tour : {}", id);
        Optional<TourDTO> tourDTO = tourService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tourDTO);
    }

    /**
     * {@code DELETE  /tours/:id} : delete the "id" tour.
     *
     * @param id the id of the tourDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tours/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        log.debug("REST request to delete Tour : {}", id);
        tourService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
