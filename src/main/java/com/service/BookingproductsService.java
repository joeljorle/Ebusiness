package com.service;

import com.domain.Bookingproducts;
import com.repository.BookingproductsRepository;
import com.service.dto.BookingproductsDTO;
import com.service.mapper.BookingproductsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bookingproducts}.
 */
@Service
@Transactional
public class BookingproductsService {

    private final Logger log = LoggerFactory.getLogger(BookingproductsService.class);

    private final BookingproductsRepository bookingproductsRepository;

    private final BookingproductsMapper bookingproductsMapper;

    public BookingproductsService(BookingproductsRepository bookingproductsRepository, BookingproductsMapper bookingproductsMapper) {
        this.bookingproductsRepository = bookingproductsRepository;
        this.bookingproductsMapper = bookingproductsMapper;
    }

    /**
     * Save a bookingproducts.
     *
     * @param bookingproductsDTO the entity to save.
     * @return the persisted entity.
     */
    public BookingproductsDTO save(BookingproductsDTO bookingproductsDTO) {
        log.debug("Request to save Bookingproducts : {}", bookingproductsDTO);
        Bookingproducts bookingproducts = bookingproductsMapper.toEntity(bookingproductsDTO);
        bookingproducts = bookingproductsRepository.save(bookingproducts);
        return bookingproductsMapper.toDto(bookingproducts);
    }

    /**
     * Get all the bookingproducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BookingproductsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bookingproducts");
        return bookingproductsRepository.findAll(pageable)
            .map(bookingproductsMapper::toDto);
    }

    /**
     * Get one bookingproducts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BookingproductsDTO> findOne(Long id) {
        log.debug("Request to get Bookingproducts : {}", id);
        return bookingproductsRepository.findById(id)
            .map(bookingproductsMapper::toDto);
    }

    /**
     * Delete the bookingproducts by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bookingproducts : {}", id);
        bookingproductsRepository.deleteById(id);
    }
}
