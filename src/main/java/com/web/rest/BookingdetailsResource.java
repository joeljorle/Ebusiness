package com.web.rest;

import com.service.BookingdetailsService;
import com.web.rest.errors.BadRequestAlertException;
import com.service.dto.BookingdetailsDTO;

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
 * REST controller for managing {@link com.domain.Bookingdetails}.
 */
@RestController
@RequestMapping("/api")
public class BookingdetailsResource {

    private final Logger log = LoggerFactory.getLogger(BookingdetailsResource.class);

    private static final String ENTITY_NAME = "bookingdetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookingdetailsService bookingdetailsService;

    public BookingdetailsResource(BookingdetailsService bookingdetailsService) {
        this.bookingdetailsService = bookingdetailsService;
    }

    /**
     * {@code POST  /bookingdetails} : Create a new bookingdetails.
     *
     * @param bookingdetailsDTO the bookingdetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bookingdetailsDTO, or with status {@code 400 (Bad Request)} if the bookingdetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bookingdetails")
    public ResponseEntity<BookingdetailsDTO> createBookingdetails(@RequestBody BookingdetailsDTO bookingdetailsDTO) throws URISyntaxException {
        log.debug("REST request to save Bookingdetails : {}", bookingdetailsDTO);
        if (bookingdetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new bookingdetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookingdetailsDTO result = bookingdetailsService.save(bookingdetailsDTO);
        return ResponseEntity.created(new URI("/api/bookingdetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bookingdetails} : Updates an existing bookingdetails.
     *
     * @param bookingdetailsDTO the bookingdetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookingdetailsDTO,
     * or with status {@code 400 (Bad Request)} if the bookingdetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bookingdetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bookingdetails")
    public ResponseEntity<BookingdetailsDTO> updateBookingdetails(@RequestBody BookingdetailsDTO bookingdetailsDTO) throws URISyntaxException {
        log.debug("REST request to update Bookingdetails : {}", bookingdetailsDTO);
        if (bookingdetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BookingdetailsDTO result = bookingdetailsService.save(bookingdetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bookingdetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bookingdetails} : get all the bookingdetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bookingdetails in body.
     */
    @GetMapping("/bookingdetails")
    public ResponseEntity<List<BookingdetailsDTO>> getAllBookingdetails(Pageable pageable) {
        log.debug("REST request to get a page of Bookingdetails");
        Page<BookingdetailsDTO> page = bookingdetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bookingdetails/:id} : get the "id" bookingdetails.
     *
     * @param id the id of the bookingdetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bookingdetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bookingdetails/{id}")
    public ResponseEntity<BookingdetailsDTO> getBookingdetails(@PathVariable Long id) {
        log.debug("REST request to get Bookingdetails : {}", id);
        Optional<BookingdetailsDTO> bookingdetailsDTO = bookingdetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookingdetailsDTO);
    }

    /**
     * {@code DELETE  /bookingdetails/:id} : delete the "id" bookingdetails.
     *
     * @param id the id of the bookingdetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bookingdetails/{id}")
    public ResponseEntity<Void> deleteBookingdetails(@PathVariable Long id) {
        log.debug("REST request to delete Bookingdetails : {}", id);
        bookingdetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
