package com.service;

import com.domain.Evenement;
import com.repository.EvenementRepository;
import com.service.dto.EvenementDTO;
import com.service.mapper.EvenementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Evenement}.
 */
@Service
@Transactional
public class EvenementService {

    private final Logger log = LoggerFactory.getLogger(EvenementService.class);

    private final EvenementRepository evenementRepository;

    private final EvenementMapper evenementMapper;

    public EvenementService(EvenementRepository evenementRepository, EvenementMapper evenementMapper) {
        this.evenementRepository = evenementRepository;
        this.evenementMapper = evenementMapper;
    }

    /**
     * Save a evenement.
     *
     * @param evenementDTO the entity to save.
     * @return the persisted entity.
     */
    public EvenementDTO save(EvenementDTO evenementDTO) {
        log.debug("Request to save Evenement : {}", evenementDTO);
        Evenement evenement = evenementMapper.toEntity(evenementDTO);
        evenement = evenementRepository.save(evenement);
        return evenementMapper.toDto(evenement);
    }

    /**
     * Get all the evenements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EvenementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Evenements");
        return evenementRepository.findAll(pageable)
            .map(evenementMapper::toDto);
    }

    /**
     * Get one evenement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EvenementDTO> findOne(Long id) {
        log.debug("Request to get Evenement : {}", id);
        return evenementRepository.findById(id)
            .map(evenementMapper::toDto);
    }

    /**
     * Delete the evenement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Evenement : {}", id);
        evenementRepository.deleteById(id);
    }
}
