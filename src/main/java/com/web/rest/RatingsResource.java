package com.web.rest;

import com.service.RatingsService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.RatingsDTO;

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
 * REST controller for managing {@link com.domain.Ratings}.
 */
@RestController
@RequestMapping("/api")
public class RatingsResource {

    private final Logger log = LoggerFactory.getLogger(RatingsResource.class);

    private static final String ENTITY_NAME = "ratings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RatingsService ratingsService;

    public RatingsResource(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    /**
     * {@code POST  /ratings} : Create a new ratings.
     *
     * @param ratingsDTO the ratingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ratingsDTO, or with status {@code 400 (Bad Request)} if the ratings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ratings")
    public ResponseEntity<RatingsDTO> createRatings(@Valid @RequestBody RatingsDTO ratingsDTO) throws URISyntaxException {
        log.debug("REST request to save Ratings : {}", ratingsDTO);
        if (ratingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new ratings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RatingsDTO result = ratingsService.save(ratingsDTO);
        return ResponseEntity.created(new URI("/api/ratings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ratings} : Updates an existing ratings.
     *
     * @param ratingsDTO the ratingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ratingsDTO,
     * or with status {@code 400 (Bad Request)} if the ratingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ratingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ratings")
    public ResponseEntity<RatingsDTO> updateRatings(@Valid @RequestBody RatingsDTO ratingsDTO) throws URISyntaxException {
        log.debug("REST request to update Ratings : {}", ratingsDTO);
        if (ratingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RatingsDTO result = ratingsService.save(ratingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ratingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ratings} : get all the ratings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ratings in body.
     */
    @GetMapping("/ratings")
    public ResponseEntity<List<RatingsDTO>> getAllRatings(Pageable pageable) {
        log.debug("REST request to get a page of Ratings");
        Page<RatingsDTO> page = ratingsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ratings/:id} : get the "id" ratings.
     *
     * @param id the id of the ratingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ratingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ratings/{id}")
    public ResponseEntity<RatingsDTO> getRatings(@PathVariable Long id) {
        log.debug("REST request to get Ratings : {}", id);
        Optional<RatingsDTO> ratingsDTO = ratingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ratingsDTO);
    }

    /**
     * {@code DELETE  /ratings/:id} : delete the "id" ratings.
     *
     * @param id the id of the ratingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ratings/{id}")
    public ResponseEntity<Void> deleteRatings(@PathVariable Long id) {
        log.debug("REST request to delete Ratings : {}", id);
        ratingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
