package com.web.rest;

import com.service.ActusService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.ActusDTO;

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
 * REST controller for managing {@link com.domain.Actus}.
 */
@RestController
@RequestMapping("/api")
public class ActusResource {

    private final Logger log = LoggerFactory.getLogger(ActusResource.class);

    private static final String ENTITY_NAME = "actus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActusService actusService;

    public ActusResource(ActusService actusService) {
        this.actusService = actusService;
    }

    /**
     * {@code POST  /actuses} : Create a new actus.
     *
     * @param actusDTO the actusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new actusDTO, or with status {@code 400 (Bad Request)} if the actus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/actuses")
    public ResponseEntity<ActusDTO> createActus(@Valid @RequestBody ActusDTO actusDTO) throws URISyntaxException {
        log.debug("REST request to save Actus : {}", actusDTO);
        if (actusDTO.getId() != null) {
            throw new BadRequestAlertException("A new actus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActusDTO result = actusService.save(actusDTO);
        return ResponseEntity.created(new URI("/api/actuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /actuses} : Updates an existing actus.
     *
     * @param actusDTO the actusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated actusDTO,
     * or with status {@code 400 (Bad Request)} if the actusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the actusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/actuses")
    public ResponseEntity<ActusDTO> updateActus(@Valid @RequestBody ActusDTO actusDTO) throws URISyntaxException {
        log.debug("REST request to update Actus : {}", actusDTO);
        if (actusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActusDTO result = actusService.save(actusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, actusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /actuses} : get all the actuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of actuses in body.
     */
    @GetMapping("/actuses")
    public ResponseEntity<List<ActusDTO>> getAllActuses(Pageable pageable) {
        log.debug("REST request to get a page of Actuses");
        Page<ActusDTO> page = actusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /actuses/:id} : get the "id" actus.
     *
     * @param id the id of the actusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the actusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/actuses/{id}")
    public ResponseEntity<ActusDTO> getActus(@PathVariable Long id) {
        log.debug("REST request to get Actus : {}", id);
        Optional<ActusDTO> actusDTO = actusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(actusDTO);
    }

    /**
     * {@code DELETE  /actuses/:id} : delete the "id" actus.
     *
     * @param id the id of the actusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/actuses/{id}")
    public ResponseEntity<Void> deleteActus(@PathVariable Long id) {
        log.debug("REST request to delete Actus : {}", id);
        actusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
