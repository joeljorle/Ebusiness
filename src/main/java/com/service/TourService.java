package com.service;

import com.domain.Tour;
import com.repository.TourRepository;
import com.service.dto.TourDTO;
import com.service.mapper.TourMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tour}.
 */
@Service
@Transactional
public class TourService {

    private final Logger log = LoggerFactory.getLogger(TourService.class);

    private final TourRepository tourRepository;

    private final TourMapper tourMapper;

    public TourService(TourRepository tourRepository, TourMapper tourMapper) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
    }

    /**
     * Save a tour.
     *
     * @param tourDTO the entity to save.
     * @return the persisted entity.
     */
    public TourDTO save(TourDTO tourDTO) {
        log.debug("Request to save Tour : {}", tourDTO);
        Tour tour = tourMapper.toEntity(tourDTO);
        tour = tourRepository.save(tour);
        return tourMapper.toDto(tour);
    }

    /**
     * Get all the tours.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TourDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tours");
        return tourRepository.findAll(pageable)
            .map(tourMapper::toDto);
    }

    /**
     * Get one tour by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TourDTO> findOne(Long id) {
        log.debug("Request to get Tour : {}", id);
        return tourRepository.findById(id)
            .map(tourMapper::toDto);
    }

    /**
     * Delete the tour by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Tour : {}", id);
        tourRepository.deleteById(id);
    }
}
