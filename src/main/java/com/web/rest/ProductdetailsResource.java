package com.web.rest;

import com.service.ProductdetailsService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.ProductdetailsDTO;

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
 * REST controller for managing {@link com.domain.Productdetails}.
 */
@RestController
@RequestMapping("/api")
public class ProductdetailsResource {

    private final Logger log = LoggerFactory.getLogger(ProductdetailsResource.class);

    private static final String ENTITY_NAME = "productdetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductdetailsService productdetailsService;

    public ProductdetailsResource(ProductdetailsService productdetailsService) {
        this.productdetailsService = productdetailsService;
    }

    /**
     * {@code POST  /productdetails} : Create a new productdetails.
     *
     * @param productdetailsDTO the productdetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productdetailsDTO, or with status {@code 400 (Bad Request)} if the productdetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/productdetails")
    public ResponseEntity<ProductdetailsDTO> createProductdetails(@RequestBody ProductdetailsDTO productdetailsDTO) throws URISyntaxException {
        log.debug("REST request to save Productdetails : {}", productdetailsDTO);
        if (productdetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new productdetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductdetailsDTO result = productdetailsService.save(productdetailsDTO);
        return ResponseEntity.created(new URI("/api/productdetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /productdetails} : Updates an existing productdetails.
     *
     * @param productdetailsDTO the productdetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productdetailsDTO,
     * or with status {@code 400 (Bad Request)} if the productdetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productdetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/productdetails")
    public ResponseEntity<ProductdetailsDTO> updateProductdetails(@RequestBody ProductdetailsDTO productdetailsDTO) throws URISyntaxException {
        log.debug("REST request to update Productdetails : {}", productdetailsDTO);
        if (productdetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductdetailsDTO result = productdetailsService.save(productdetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productdetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /productdetails} : get all the productdetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productdetails in body.
     */
    @GetMapping("/productdetails")
    public ResponseEntity<List<ProductdetailsDTO>> getAllProductdetails(Pageable pageable) {
        log.debug("REST request to get a page of Productdetails");
        Page<ProductdetailsDTO> page = productdetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /productdetails/:id} : get the "id" productdetails.
     *
     * @param id the id of the productdetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productdetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/productdetails/{id}")
    public ResponseEntity<ProductdetailsDTO> getProductdetails(@PathVariable Long id) {
        log.debug("REST request to get Productdetails : {}", id);
        Optional<ProductdetailsDTO> productdetailsDTO = productdetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productdetailsDTO);
    }

    /**
     * {@code DELETE  /productdetails/:id} : delete the "id" productdetails.
     *
     * @param id the id of the productdetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/productdetails/{id}")
    public ResponseEntity<Void> deleteProductdetails(@PathVariable Long id) {
        log.debug("REST request to delete Productdetails : {}", id);
        productdetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
