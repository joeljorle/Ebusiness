package com.web.rest;

import com.service.ChercheService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.ChercheDTO;

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
 * REST controller for managing {@link com.domain.Cherche}.
 */
@RestController
@RequestMapping("/api")
public class ChercheResource {

    private final Logger log = LoggerFactory.getLogger(ChercheResource.class);

    private static final String ENTITY_NAME = "cherche";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChercheService chercheService;

    public ChercheResource(ChercheService chercheService) {
        this.chercheService = chercheService;
    }

    /**
     * {@code POST  /cherches} : Create a new cherche.
     *
     * @param chercheDTO the chercheDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chercheDTO, or with status {@code 400 (Bad Request)} if the cherche has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cherches")
    public ResponseEntity<ChercheDTO> createCherche(@Valid @RequestBody ChercheDTO chercheDTO) throws URISyntaxException {
        log.debug("REST request to save Cherche : {}", chercheDTO);
        if (chercheDTO.getId() != null) {
            throw new BadRequestAlertException("A new cherche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChercheDTO result = chercheService.save(chercheDTO);
        return ResponseEntity.created(new URI("/api/cherches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cherches} : Updates an existing cherche.
     *
     * @param chercheDTO the chercheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chercheDTO,
     * or with status {@code 400 (Bad Request)} if the chercheDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chercheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cherches")
    public ResponseEntity<ChercheDTO> updateCherche(@Valid @RequestBody ChercheDTO chercheDTO) throws URISyntaxException {
        log.debug("REST request to update Cherche : {}", chercheDTO);
        if (chercheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChercheDTO result = chercheService.save(chercheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, chercheDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cherches} : get all the cherches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cherches in body.
     */
    @GetMapping("/cherches")
    public ResponseEntity<List<ChercheDTO>> getAllCherches(Pageable pageable) {
        log.debug("REST request to get a page of Cherches");
        Page<ChercheDTO> page = chercheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cherches/:id} : get the "id" cherche.
     *
     * @param id the id of the chercheDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chercheDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cherches/{id}")
    public ResponseEntity<ChercheDTO> getCherche(@PathVariable Long id) {
        log.debug("REST request to get Cherche : {}", id);
        Optional<ChercheDTO> chercheDTO = chercheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chercheDTO);
    }

    /**
     * {@code DELETE  /cherches/:id} : delete the "id" cherche.
     *
     * @param id the id of the chercheDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cherches/{id}")
    public ResponseEntity<Void> deleteCherche(@PathVariable Long id) {
        log.debug("REST request to delete Cherche : {}", id);
        chercheService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
