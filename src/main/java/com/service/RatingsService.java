package com.service;

import com.domain.Ratings;
import com.repository.RatingsRepository;
import com.service.dto.RatingsDTO;
import com.service.mapper.RatingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Ratings}.
 */
@Service
@Transactional
public class RatingsService {

    private final Logger log = LoggerFactory.getLogger(RatingsService.class);

    private final RatingsRepository ratingsRepository;

    private final RatingsMapper ratingsMapper;

    public RatingsService(RatingsRepository ratingsRepository, RatingsMapper ratingsMapper) {
        this.ratingsRepository = ratingsRepository;
        this.ratingsMapper = ratingsMapper;
    }

    /**
     * Save a ratings.
     *
     * @param ratingsDTO the entity to save.
     * @return the persisted entity.
     */
    public RatingsDTO save(RatingsDTO ratingsDTO) {
        log.debug("Request to save Ratings : {}", ratingsDTO);
        Ratings ratings = ratingsMapper.toEntity(ratingsDTO);
        ratings = ratingsRepository.save(ratings);
        return ratingsMapper.toDto(ratings);
    }

    /**
     * Get all the ratings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RatingsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ratings");
        return ratingsRepository.findAll(pageable)
            .map(ratingsMapper::toDto);
    }


    /**
     * Get one ratings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RatingsDTO> findOne(Long id) {
        log.debug("Request to get Ratings : {}", id);
        return ratingsRepository.findById(id)
            .map(ratingsMapper::toDto);
    }

    /**
     * Delete the ratings by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ratings : {}", id);
        ratingsRepository.deleteById(id);
    }
}
