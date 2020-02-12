package com.web.rest;

import com.service.FollowsService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.FollowsDTO;

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
 * REST controller for managing {@link com.domain.Follows}.
 */
@RestController
@RequestMapping("/api")
public class FollowsResource {

    private final Logger log = LoggerFactory.getLogger(FollowsResource.class);

    private static final String ENTITY_NAME = "follows";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FollowsService followsService;

    public FollowsResource(FollowsService followsService) {
        this.followsService = followsService;
    }

    /**
     * {@code POST  /follows} : Create a new follows.
     *
     * @param followsDTO the followsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new followsDTO, or with status {@code 400 (Bad Request)} if the follows has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/follows")
    public ResponseEntity<FollowsDTO> createFollows(@Valid @RequestBody FollowsDTO followsDTO) throws URISyntaxException {
        log.debug("REST request to save Follows : {}", followsDTO);
        if (followsDTO.getId() != null) {
            throw new BadRequestAlertException("A new follows cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FollowsDTO result = followsService.save(followsDTO);
        return ResponseEntity.created(new URI("/api/follows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /follows} : Updates an existing follows.
     *
     * @param followsDTO the followsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated followsDTO,
     * or with status {@code 400 (Bad Request)} if the followsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the followsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/follows")
    public ResponseEntity<FollowsDTO> updateFollows(@Valid @RequestBody FollowsDTO followsDTO) throws URISyntaxException {
        log.debug("REST request to update Follows : {}", followsDTO);
        if (followsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FollowsDTO result = followsService.save(followsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, followsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /follows} : get all the follows.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of follows in body.
     */
    @GetMapping("/follows")
    public ResponseEntity<List<FollowsDTO>> getAllFollows(Pageable pageable) {
        log.debug("REST request to get a page of Follows");
        Page<FollowsDTO> page = followsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /follows/:id} : get the "id" follows.
     *
     * @param id the id of the followsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the followsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/follows/{id}")
    public ResponseEntity<FollowsDTO> getFollows(@PathVariable Long id) {
        log.debug("REST request to get Follows : {}", id);
        Optional<FollowsDTO> followsDTO = followsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(followsDTO);
    }

    /**
     * {@code DELETE  /follows/:id} : delete the "id" follows.
     *
     * @param id the id of the followsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/follows/{id}")
    public ResponseEntity<Void> deleteFollows(@PathVariable Long id) {
        log.debug("REST request to delete Follows : {}", id);
        followsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
