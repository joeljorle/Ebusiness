package com.web.rest;

import com.service.BookingproductsService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.BookingproductsDTO;

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
 * REST controller for managing {@link com.domain.Bookingproducts}.
 */
@RestController
@RequestMapping("/api")
public class BookingproductsResource {

    private final Logger log = LoggerFactory.getLogger(BookingproductsResource.class);

    private static final String ENTITY_NAME = "bookingproducts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookingproductsService bookingproductsService;

    public BookingproductsResource(BookingproductsService bookingproductsService) {
        this.bookingproductsService = bookingproductsService;
    }

    /**
     * {@code POST  /bookingproducts} : Create a new bookingproducts.
     *
     * @param bookingproductsDTO the bookingproductsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bookingproductsDTO, or with status {@code 400 (Bad Request)} if the bookingproducts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bookingproducts")
    public ResponseEntity<BookingproductsDTO> createBookingproducts(@Valid @RequestBody BookingproductsDTO bookingproductsDTO) throws URISyntaxException {
        log.debug("REST request to save Bookingproducts : {}", bookingproductsDTO);
        if (bookingproductsDTO.getId() != null) {
            throw new BadRequestAlertException("A new bookingproducts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookingproductsDTO result = bookingproductsService.save(bookingproductsDTO);
        return ResponseEntity.created(new URI("/api/bookingproducts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bookingproducts} : Updates an existing bookingproducts.
     *
     * @param bookingproductsDTO the bookingproductsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookingproductsDTO,
     * or with status {@code 400 (Bad Request)} if the bookingproductsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bookingproductsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bookingproducts")
    public ResponseEntity<BookingproductsDTO> updateBookingproducts(@Valid @RequestBody BookingproductsDTO bookingproductsDTO) throws URISyntaxException {
        log.debug("REST request to update Bookingproducts : {}", bookingproductsDTO);
        if (bookingproductsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BookingproductsDTO result = bookingproductsService.save(bookingproductsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bookingproductsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bookingproducts} : get all the bookingproducts.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bookingproducts in body.
     */
    @GetMapping("/bookingproducts")
    public ResponseEntity<List<BookingproductsDTO>> getAllBookingproducts(Pageable pageable) {
        log.debug("REST request to get a page of Bookingproducts");
        Page<BookingproductsDTO> page = bookingproductsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bookingproducts/:id} : get the "id" bookingproducts.
     *
     * @param id the id of the bookingproductsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bookingproductsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bookingproducts/{id}")
    public ResponseEntity<BookingproductsDTO> getBookingproducts(@PathVariable Long id) {
        log.debug("REST request to get Bookingproducts : {}", id);
        Optional<BookingproductsDTO> bookingproductsDTO = bookingproductsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookingproductsDTO);
    }

    /**
     * {@code DELETE  /bookingproducts/:id} : delete the "id" bookingproducts.
     *
     * @param id the id of the bookingproductsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bookingproducts/{id}")
    public ResponseEntity<Void> deleteBookingproducts(@PathVariable Long id) {
        log.debug("REST request to delete Bookingproducts : {}", id);
        bookingproductsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
