package com.web.rest;

import com.service.CartproductsService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.CartproductsDTO;

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
 * REST controller for managing {@link com.domain.Cartproducts}.
 */
@RestController
@RequestMapping("/api")
public class CartproductsResource {

    private final Logger log = LoggerFactory.getLogger(CartproductsResource.class);

    private static final String ENTITY_NAME = "cartproducts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CartproductsService cartproductsService;

    public CartproductsResource(CartproductsService cartproductsService) {
        this.cartproductsService = cartproductsService;
    }

    /**
     * {@code POST  /cartproducts} : Create a new cartproducts.
     *
     * @param cartproductsDTO the cartproductsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cartproductsDTO, or with status {@code 400 (Bad Request)} if the cartproducts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cartproducts")
    public ResponseEntity<CartproductsDTO> createCartproducts(@Valid @RequestBody CartproductsDTO cartproductsDTO) throws URISyntaxException {
        log.debug("REST request to save Cartproducts : {}", cartproductsDTO);
        if (cartproductsDTO.getId() != null) {
            throw new BadRequestAlertException("A new cartproducts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CartproductsDTO result = cartproductsService.save(cartproductsDTO);
        return ResponseEntity.created(new URI("/api/cartproducts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cartproducts} : Updates an existing cartproducts.
     *
     * @param cartproductsDTO the cartproductsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cartproductsDTO,
     * or with status {@code 400 (Bad Request)} if the cartproductsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cartproductsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cartproducts")
    public ResponseEntity<CartproductsDTO> updateCartproducts(@Valid @RequestBody CartproductsDTO cartproductsDTO) throws URISyntaxException {
        log.debug("REST request to update Cartproducts : {}", cartproductsDTO);
        if (cartproductsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CartproductsDTO result = cartproductsService.save(cartproductsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cartproductsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cartproducts} : get all the cartproducts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartproducts in body.
     */
    @GetMapping("/cartproducts")
    public ResponseEntity<List<CartproductsDTO>> getAllCartproducts(Pageable pageable) {
        log.debug("REST request to get a page of Cartproducts");
        Page<CartproductsDTO> page = cartproductsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cartproducts/:id} : get the "id" cartproducts.
     *
     * @param id the id of the cartproductsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cartproductsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cartproducts/{id}")
    public ResponseEntity<CartproductsDTO> getCartproducts(@PathVariable Long id) {
        log.debug("REST request to get Cartproducts : {}", id);
        Optional<CartproductsDTO> cartproductsDTO = cartproductsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cartproductsDTO);
    }

    /**
     * {@code DELETE  /cartproducts/:id} : delete the "id" cartproducts.
     *
     * @param id the id of the cartproductsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cartproducts/{id}")
    public ResponseEntity<Void> deleteCartproducts(@PathVariable Long id) {
        log.debug("REST request to delete Cartproducts : {}", id);
        cartproductsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
