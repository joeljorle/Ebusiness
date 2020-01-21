package com.service;

import com.domain.Categorydetails;
import com.repository.CategorydetailsRepository;
import com.service.dto.CategorydetailsDTO;
import com.service.mapper.CategorydetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Categorydetails}.
 */
@Service
@Transactional
public class CategorydetailsService {

    private final Logger log = LoggerFactory.getLogger(CategorydetailsService.class);

    private final CategorydetailsRepository categorydetailsRepository;

    private final CategorydetailsMapper categorydetailsMapper;

    public CategorydetailsService(CategorydetailsRepository categorydetailsRepository, CategorydetailsMapper categorydetailsMapper) {
        this.categorydetailsRepository = categorydetailsRepository;
        this.categorydetailsMapper = categorydetailsMapper;
    }

    /**
     * Save a categorydetails.
     *
     * @param categorydetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public CategorydetailsDTO save(CategorydetailsDTO categorydetailsDTO) {
        log.debug("Request to save Categorydetails : {}", categorydetailsDTO);
        Categorydetails categorydetails = categorydetailsMapper.toEntity(categorydetailsDTO);
        categorydetails = categorydetailsRepository.save(categorydetails);
        return categorydetailsMapper.toDto(categorydetails);
    }

    /**
     * Get all the categorydetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CategorydetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Categorydetails");
        return categorydetailsRepository.findAll(pageable)
            .map(categorydetailsMapper::toDto);
    }


    /**
     * Get one categorydetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CategorydetailsDTO> findOne(Long id) {
        log.debug("Request to get Categorydetails : {}", id);
        return categorydetailsRepository.findById(id)
            .map(categorydetailsMapper::toDto);
    }

    /**
     * Delete the categorydetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Categorydetails : {}", id);
        categorydetailsRepository.deleteById(id);
    }
}
