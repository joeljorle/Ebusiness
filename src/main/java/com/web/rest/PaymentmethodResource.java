package com.web.rest;

import com.service.PaymentmethodService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.PaymentmethodDTO;

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
 * REST controller for managing {@link com.domain.Paymentmethod}.
 */
@RestController
@RequestMapping("/api")
public class PaymentmethodResource {

    private final Logger log = LoggerFactory.getLogger(PaymentmethodResource.class);

    private static final String ENTITY_NAME = "paymentmethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentmethodService paymentmethodService;

    public PaymentmethodResource(PaymentmethodService paymentmethodService) {
        this.paymentmethodService = paymentmethodService;
    }

    /**
     * {@code POST  /paymentmethods} : Create a new paymentmethod.
     *
     * @param paymentmethodDTO the paymentmethodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentmethodDTO, or with status {@code 400 (Bad Request)} if the paymentmethod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paymentmethods")
    public ResponseEntity<PaymentmethodDTO> createPaymentmethod(@Valid @RequestBody PaymentmethodDTO paymentmethodDTO) throws URISyntaxException {
        log.debug("REST request to save Paymentmethod : {}", paymentmethodDTO);
        if (paymentmethodDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentmethod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentmethodDTO result = paymentmethodService.save(paymentmethodDTO);
        return ResponseEntity.created(new URI("/api/paymentmethods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /paymentmethods} : Updates an existing paymentmethod.
     *
     * @param paymentmethodDTO the paymentmethodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentmethodDTO,
     * or with status {@code 400 (Bad Request)} if the paymentmethodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentmethodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paymentmethods")
    public ResponseEntity<PaymentmethodDTO> updatePaymentmethod(@Valid @RequestBody PaymentmethodDTO paymentmethodDTO) throws URISyntaxException {
        log.debug("REST request to update Paymentmethod : {}", paymentmethodDTO);
        if (paymentmethodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentmethodDTO result = paymentmethodService.save(paymentmethodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentmethodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paymentmethods} : get all the paymentmethods.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentmethods in body.
     */
    @GetMapping("/paymentmethods")
    public ResponseEntity<List<PaymentmethodDTO>> getAllPaymentmethods(Pageable pageable) {
        log.debug("REST request to get a page of Paymentmethods");
        Page<PaymentmethodDTO> page = paymentmethodService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /paymentmethods/:id} : get the "id" paymentmethod.
     *
     * @param id the id of the paymentmethodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentmethodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paymentmethods/{id}")
    public ResponseEntity<PaymentmethodDTO> getPaymentmethod(@PathVariable Long id) {
        log.debug("REST request to get Paymentmethod : {}", id);
        Optional<PaymentmethodDTO> paymentmethodDTO = paymentmethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentmethodDTO);
    }

    /**
     * {@code DELETE  /paymentmethods/:id} : delete the "id" paymentmethod.
     *
     * @param id the id of the paymentmethodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paymentmethods/{id}")
    public ResponseEntity<Void> deletePaymentmethod(@PathVariable Long id) {
        log.debug("REST request to delete Paymentmethod : {}", id);
        paymentmethodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
