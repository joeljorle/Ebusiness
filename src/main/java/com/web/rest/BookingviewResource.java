package com.web.rest;

import com.service.BookingviewService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.BookingviewDTO;

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
 * REST controller for managing {@link com.domain.Bookingview}.
 */
@RestController
@RequestMapping("/api")
public class BookingviewResource {

    private final Logger log = LoggerFactory.getLogger(BookingviewResource.class);

    private static final String ENTITY_NAME = "bookingview";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookingviewService bookingviewService;

    public BookingviewResource(BookingviewService bookingviewService) {
        this.bookingviewService = bookingviewService;
    }

    /**
     * {@code POST  /bookingviews} : Create a new bookingview.
     *
     * @param bookingviewDTO the bookingviewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bookingviewDTO, or with status {@code 400 (Bad Request)} if the bookingview has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bookingviews")
    public ResponseEntity<BookingviewDTO> createBookingview(@RequestBody BookingviewDTO bookingviewDTO) throws URISyntaxException {
        log.debug("REST request to save Bookingview : {}", bookingviewDTO);
        if (bookingviewDTO.getId() != null) {
            throw new BadRequestAlertException("A new bookingview cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookingviewDTO result = bookingviewService.save(bookingviewDTO);
        return ResponseEntity.created(new URI("/api/bookingviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bookingviews} : Updates an existing bookingview.
     *
     * @param bookingviewDTO the bookingviewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookingviewDTO,
     * or with status {@code 400 (Bad Request)} if the bookingviewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bookingviewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bookingviews")
    public ResponseEntity<BookingviewDTO> updateBookingview(@RequestBody BookingviewDTO bookingviewDTO) throws URISyntaxException {
        log.debug("REST request to update Bookingview : {}", bookingviewDTO);
        if (bookingviewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BookingviewDTO result = bookingviewService.save(bookingviewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bookingviewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bookingviews} : get all the bookingviews.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bookingviews in body.
     */
    @GetMapping("/bookingviews")
    public ResponseEntity<List<BookingviewDTO>> getAllBookingviews(Pageable pageable) {
        log.debug("REST request to get a page of Bookingviews");
        Page<BookingviewDTO> page = bookingviewService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bookingviews/:id} : get the "id" bookingview.
     *
     * @param id the id of the bookingviewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bookingviewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bookingviews/{id}")
    public ResponseEntity<BookingviewDTO> getBookingview(@PathVariable Long id) {
        log.debug("REST request to get Bookingview : {}", id);
        Optional<BookingviewDTO> bookingviewDTO = bookingviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookingviewDTO);
    }

    /**
     * {@code DELETE  /bookingviews/:id} : delete the "id" bookingview.
     *
     * @param id the id of the bookingviewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bookingviews/{id}")
    public ResponseEntity<Void> deleteBookingview(@PathVariable Long id) {
        log.debug("REST request to delete Bookingview : {}", id);
        bookingviewService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
