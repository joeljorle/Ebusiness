package com.web.rest;

import com.service.PaymentactionService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.PaymentactionDTO;

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
 * REST controller for managing {@link com.domain.Paymentaction}.
 */
@RestController
@RequestMapping("/api")
public class PaymentactionResource {

    private final Logger log = LoggerFactory.getLogger(PaymentactionResource.class);

    private static final String ENTITY_NAME = "paymentaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentactionService paymentactionService;

    public PaymentactionResource(PaymentactionService paymentactionService) {
        this.paymentactionService = paymentactionService;
    }

    /**
     * {@code POST  /paymentactions} : Create a new paymentaction.
     *
     * @param paymentactionDTO the paymentactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentactionDTO, or with status {@code 400 (Bad Request)} if the paymentaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paymentactions")
    public ResponseEntity<PaymentactionDTO> createPaymentaction(@Valid @RequestBody PaymentactionDTO paymentactionDTO) throws URISyntaxException {
        log.debug("REST request to save Paymentaction : {}", paymentactionDTO);
        if (paymentactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentactionDTO result = paymentactionService.save(paymentactionDTO);
        return ResponseEntity.created(new URI("/api/paymentactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /paymentactions} : Updates an existing paymentaction.
     *
     * @param paymentactionDTO the paymentactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentactionDTO,
     * or with status {@code 400 (Bad Request)} if the paymentactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paymentactions")
    public ResponseEntity<PaymentactionDTO> updatePaymentaction(@Valid @RequestBody PaymentactionDTO paymentactionDTO) throws URISyntaxException {
        log.debug("REST request to update Paymentaction : {}", paymentactionDTO);
        if (paymentactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentactionDTO result = paymentactionService.save(paymentactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paymentactions} : get all the paymentactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentactions in body.
     */
    @GetMapping("/paymentactions")
    public ResponseEntity<List<PaymentactionDTO>> getAllPaymentactions(Pageable pageable) {
        log.debug("REST request to get a page of Paymentactions");
        Page<PaymentactionDTO> page = paymentactionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /paymentactions/:id} : get the "id" paymentaction.
     *
     * @param id the id of the paymentactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paymentactions/{id}")
    public ResponseEntity<PaymentactionDTO> getPaymentaction(@PathVariable Long id) {
        log.debug("REST request to get Paymentaction : {}", id);
        Optional<PaymentactionDTO> paymentactionDTO = paymentactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentactionDTO);
    }

    /**
     * {@code DELETE  /paymentactions/:id} : delete the "id" paymentaction.
     *
     * @param id the id of the paymentactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paymentactions/{id}")
    public ResponseEntity<Void> deletePaymentaction(@PathVariable Long id) {
        log.debug("REST request to delete Paymentaction : {}", id);
        paymentactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
