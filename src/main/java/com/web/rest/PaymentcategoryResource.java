package com.web.rest;

import com.service.PaymentcategoryService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.PaymentcategoryDTO;

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
 * REST controller for managing {@link com.domain.Paymentcategory}.
 */
@RestController
@RequestMapping("/api")
public class PaymentcategoryResource {

    private final Logger log = LoggerFactory.getLogger(PaymentcategoryResource.class);

    private static final String ENTITY_NAME = "paymentcategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentcategoryService paymentcategoryService;

    public PaymentcategoryResource(PaymentcategoryService paymentcategoryService) {
        this.paymentcategoryService = paymentcategoryService;
    }

    /**
     * {@code POST  /paymentcategories} : Create a new paymentcategory.
     *
     * @param paymentcategoryDTO the paymentcategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentcategoryDTO, or with status {@code 400 (Bad Request)} if the paymentcategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paymentcategories")
    public ResponseEntity<PaymentcategoryDTO> createPaymentcategory(@Valid @RequestBody PaymentcategoryDTO paymentcategoryDTO) throws URISyntaxException {
        log.debug("REST request to save Paymentcategory : {}", paymentcategoryDTO);
        if (paymentcategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentcategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentcategoryDTO result = paymentcategoryService.save(paymentcategoryDTO);
        return ResponseEntity.created(new URI("/api/paymentcategories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /paymentcategories} : Updates an existing paymentcategory.
     *
     * @param paymentcategoryDTO the paymentcategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentcategoryDTO,
     * or with status {@code 400 (Bad Request)} if the paymentcategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentcategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paymentcategories")
    public ResponseEntity<PaymentcategoryDTO> updatePaymentcategory(@Valid @RequestBody PaymentcategoryDTO paymentcategoryDTO) throws URISyntaxException {
        log.debug("REST request to update Paymentcategory : {}", paymentcategoryDTO);
        if (paymentcategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentcategoryDTO result = paymentcategoryService.save(paymentcategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, paymentcategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paymentcategories} : get all the paymentcategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentcategories in body.
     */
    @GetMapping("/paymentcategories")
    public ResponseEntity<List<PaymentcategoryDTO>> getAllPaymentcategories(Pageable pageable) {
        log.debug("REST request to get a page of Paymentcategories");
        Page<PaymentcategoryDTO> page = paymentcategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /paymentcategories/:id} : get the "id" paymentcategory.
     *
     * @param id the id of the paymentcategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentcategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paymentcategories/{id}")
    public ResponseEntity<PaymentcategoryDTO> getPaymentcategory(@PathVariable Long id) {
        log.debug("REST request to get Paymentcategory : {}", id);
        Optional<PaymentcategoryDTO> paymentcategoryDTO = paymentcategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentcategoryDTO);
    }

    /**
     * {@code DELETE  /paymentcategories/:id} : delete the "id" paymentcategory.
     *
     * @param id the id of the paymentcategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paymentcategories/{id}")
    public ResponseEntity<Void> deletePaymentcategory(@PathVariable Long id) {
        log.debug("REST request to delete Paymentcategory : {}", id);
        paymentcategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
