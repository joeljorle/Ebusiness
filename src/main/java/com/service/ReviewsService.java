package com.service;

import com.domain.Reviews;
import com.repository.ReviewsRepository;
import com.service.dto.ReviewsDTO;
import com.service.mapper.ReviewsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Reviews}.
 */
@Service
@Transactional
public class ReviewsService {

    private final Logger log = LoggerFactory.getLogger(ReviewsService.class);

    private final ReviewsRepository reviewsRepository;

    private final ReviewsMapper reviewsMapper;

    public ReviewsService(ReviewsRepository reviewsRepository, ReviewsMapper reviewsMapper) {
        this.reviewsRepository = reviewsRepository;
        this.reviewsMapper = reviewsMapper;
    }

    /**
     * Save a reviews.
     *
     * @param reviewsDTO the entity to save.
     * @return the persisted entity.
     */
    public ReviewsDTO save(ReviewsDTO reviewsDTO) {
        log.debug("Request to save Reviews : {}", reviewsDTO);
        Reviews reviews = reviewsMapper.toEntity(reviewsDTO);
        reviews = reviewsRepository.save(reviews);
        return reviewsMapper.toDto(reviews);
    }

    /**
     * Get all the reviews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReviewsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reviews");
        return reviewsRepository.findAll(pageable)
            .map(reviewsMapper::toDto);
    }


    /**
     * Get one reviews by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReviewsDTO> findOne(Long id) {
        log.debug("Request to get Reviews : {}", id);
        return reviewsRepository.findById(id)
            .map(reviewsMapper::toDto);
    }

    /**
     * Delete the reviews by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Reviews : {}", id);
        reviewsRepository.deleteById(id);
    }
}
