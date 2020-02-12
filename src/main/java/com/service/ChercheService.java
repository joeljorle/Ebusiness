package com.service;

import com.domain.Cherche;
import com.repository.ChercheRepository;
import com.service.dto.ChercheDTO;
import com.service.mapper.ChercheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cherche}.
 */
@Service
@Transactional
public class ChercheService {

    private final Logger log = LoggerFactory.getLogger(ChercheService.class);

    private final ChercheRepository chercheRepository;

    private final ChercheMapper chercheMapper;

    public ChercheService(ChercheRepository chercheRepository, ChercheMapper chercheMapper) {
        this.chercheRepository = chercheRepository;
        this.chercheMapper = chercheMapper;
    }

    /**
     * Save a cherche.
     *
     * @param chercheDTO the entity to save.
     * @return the persisted entity.
     */
    public ChercheDTO save(ChercheDTO chercheDTO) {
        log.debug("Request to save Cherche : {}", chercheDTO);
        Cherche cherche = chercheMapper.toEntity(chercheDTO);
        cherche = chercheRepository.save(cherche);
        return chercheMapper.toDto(cherche);
    }

    /**
     * Get all the cherches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ChercheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cherches");
        return chercheRepository.findAll(pageable)
            .map(chercheMapper::toDto);
    }

    /**
     * Get one cherche by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChercheDTO> findOne(Long id) {
        log.debug("Request to get Cherche : {}", id);
        return chercheRepository.findById(id)
            .map(chercheMapper::toDto);
    }

    /**
     * Delete the cherche by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cherche : {}", id);
        chercheRepository.deleteById(id);
    }
}
