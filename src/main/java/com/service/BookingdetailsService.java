package com.service;

import com.domain.Bookingdetails;
import com.repository.BookingdetailsRepository;
import com.service.dto.BookingdetailsDTO;
import com.service.mapper.BookingdetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bookingdetails}.
 */
@Service
@Transactional
public class BookingdetailsService {

    private final Logger log = LoggerFactory.getLogger(BookingdetailsService.class);

    private final BookingdetailsRepository bookingdetailsRepository;

    private final BookingdetailsMapper bookingdetailsMapper;

    public BookingdetailsService(BookingdetailsRepository bookingdetailsRepository, BookingdetailsMapper bookingdetailsMapper) {
        this.bookingdetailsRepository = bookingdetailsRepository;
        this.bookingdetailsMapper = bookingdetailsMapper;
    }

    /**
     * Save a bookingdetails.
     *
     * @param bookingdetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public BookingdetailsDTO save(BookingdetailsDTO bookingdetailsDTO) {
        log.debug("Request to save Bookingdetails : {}", bookingdetailsDTO);
        Bookingdetails bookingdetails = bookingdetailsMapper.toEntity(bookingdetailsDTO);
        bookingdetails = bookingdetailsRepository.save(bookingdetails);
        return bookingdetailsMapper.toDto(bookingdetails);
    }

    /**
     * Get all the bookingdetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BookingdetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bookingdetails");
        return bookingdetailsRepository.findAll(pageable)
            .map(bookingdetailsMapper::toDto);
    }


    /**
     * Get one bookingdetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BookingdetailsDTO> findOne(Long id) {
        log.debug("Request to get Bookingdetails : {}", id);
        return bookingdetailsRepository.findById(id)
            .map(bookingdetailsMapper::toDto);
    }

    /**
     * Delete the bookingdetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bookingdetails : {}", id);
        bookingdetailsRepository.deleteById(id);
    }
}
