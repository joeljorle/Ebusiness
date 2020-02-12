package com.service;

import com.domain.Bookingview;
import com.repository.BookingviewRepository;
import com.service.dto.BookingviewDTO;
import com.service.mapper.BookingviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bookingview}.
 */
@Service
@Transactional
public class BookingviewService {

    private final Logger log = LoggerFactory.getLogger(BookingviewService.class);

    private final BookingviewRepository bookingviewRepository;

    private final BookingviewMapper bookingviewMapper;

    public BookingviewService(BookingviewRepository bookingviewRepository, BookingviewMapper bookingviewMapper) {
        this.bookingviewRepository = bookingviewRepository;
        this.bookingviewMapper = bookingviewMapper;
    }

    /**
     * Save a bookingview.
     *
     * @param bookingviewDTO the entity to save.
     * @return the persisted entity.
     */
    public BookingviewDTO save(BookingviewDTO bookingviewDTO) {
        log.debug("Request to save Bookingview : {}", bookingviewDTO);
        Bookingview bookingview = bookingviewMapper.toEntity(bookingviewDTO);
        bookingview = bookingviewRepository.save(bookingview);
        return bookingviewMapper.toDto(bookingview);
    }

    /**
     * Get all the bookingviews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BookingviewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bookingviews");
        return bookingviewRepository.findAll(pageable)
            .map(bookingviewMapper::toDto);
    }

    /**
     * Get one bookingview by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BookingviewDTO> findOne(Long id) {
        log.debug("Request to get Bookingview : {}", id);
        return bookingviewRepository.findById(id)
            .map(bookingviewMapper::toDto);
    }

    /**
     * Delete the bookingview by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bookingview : {}", id);
        bookingviewRepository.deleteById(id);
    }
}
