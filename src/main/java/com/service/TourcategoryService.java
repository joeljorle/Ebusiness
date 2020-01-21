package com.service;

import com.domain.Tourcategory;
import com.repository.TourcategoryRepository;
import com.service.dto.TourcategoryDTO;
import com.service.mapper.TourcategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tourcategory}.
 */
@Service
@Transactional
public class TourcategoryService {

    private final Logger log = LoggerFactory.getLogger(TourcategoryService.class);

    private final TourcategoryRepository tourcategoryRepository;

    private final TourcategoryMapper tourcategoryMapper;

    public TourcategoryService(TourcategoryRepository tourcategoryRepository, TourcategoryMapper tourcategoryMapper) {
        this.tourcategoryRepository = tourcategoryRepository;
        this.tourcategoryMapper = tourcategoryMapper;
    }

    /**
     * Save a tourcategory.
     *
     * @param tourcategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public TourcategoryDTO save(TourcategoryDTO tourcategoryDTO) {
        log.debug("Request to save Tourcategory : {}", tourcategoryDTO);
        Tourcategory tourcategory = tourcategoryMapper.toEntity(tourcategoryDTO);
        tourcategory = tourcategoryRepository.save(tourcategory);
        return tourcategoryMapper.toDto(tourcategory);
    }

    /**
     * Get all the tourcategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TourcategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tourcategories");
        return tourcategoryRepository.findAll(pageable)
            .map(tourcategoryMapper::toDto);
    }


    /**
     * Get one tourcategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TourcategoryDTO> findOne(Long id) {
        log.debug("Request to get Tourcategory : {}", id);
        return tourcategoryRepository.findById(id)
            .map(tourcategoryMapper::toDto);
    }

    /**
     * Delete the tourcategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Tourcategory : {}", id);
        tourcategoryRepository.deleteById(id);
    }
}
