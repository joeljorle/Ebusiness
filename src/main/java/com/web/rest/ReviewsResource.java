package com.web.rest;

import com.service.ReviewsService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.ReviewsDTO;

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
 * REST controller for managing {@link com.domain.Reviews}.
 */
@RestController
@RequestMapping("/api")
public class ReviewsResource {

    private final Logger log = LoggerFactory.getLogger(ReviewsResource.class);

    private static final String ENTITY_NAME = "reviews";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReviewsService reviewsService;

    public ReviewsResource(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    /**
     * {@code POST  /reviews} : Create a new reviews.
     *
     * @param reviewsDTO the reviewsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reviewsDTO, or with status {@code 400 (Bad Request)} if the reviews has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reviews")
    public ResponseEntity<ReviewsDTO> createReviews(@Valid @RequestBody ReviewsDTO reviewsDTO) throws URISyntaxException {
        log.debug("REST request to save Reviews : {}", reviewsDTO);
        if (reviewsDTO.getId() != null) {
            throw new BadRequestAlertException("A new reviews cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReviewsDTO result = reviewsService.save(reviewsDTO);
        return ResponseEntity.created(new URI("/api/reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reviews} : Updates an existing reviews.
     *
     * @param reviewsDTO the reviewsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reviewsDTO,
     * or with status {@code 400 (Bad Request)} if the reviewsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reviewsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reviews")
    public ResponseEntity<ReviewsDTO> updateReviews(@Valid @RequestBody ReviewsDTO reviewsDTO) throws URISyntaxException {
        log.debug("REST request to update Reviews : {}", reviewsDTO);
        if (reviewsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReviewsDTO result = reviewsService.save(reviewsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reviewsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reviews} : get all the reviews.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reviews in body.
     */
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewsDTO>> getAllReviews(Pageable pageable) {
        log.debug("REST request to get a page of Reviews");
        Page<ReviewsDTO> page = reviewsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reviews/:id} : get the "id" reviews.
     *
     * @param id the id of the reviewsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reviewsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewsDTO> getReviews(@PathVariable Long id) {
        log.debug("REST request to get Reviews : {}", id);
        Optional<ReviewsDTO> reviewsDTO = reviewsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reviewsDTO);
    }

    /**
     * {@code DELETE  /reviews/:id} : delete the "id" reviews.
     *
     * @param id the id of the reviewsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReviews(@PathVariable Long id) {
        log.debug("REST request to delete Reviews : {}", id);
        reviewsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
