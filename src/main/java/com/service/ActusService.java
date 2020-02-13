package com.service;

import com.domain.Actus;
import com.repository.ActusRepository;
import com.service.dto.ActusDTO;
import com.service.mapper.ActusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Actus}.
 */
@Service
@Transactional
public class ActusService {

    private final Logger log = LoggerFactory.getLogger(ActusService.class);

    private final ActusRepository actusRepository;

    private final ActusMapper actusMapper;

    public ActusService(ActusRepository actusRepository, ActusMapper actusMapper) {
        this.actusRepository = actusRepository;
        this.actusMapper = actusMapper;
    }

    /**
     * Save a actus.
     *
     * @param actusDTO the entity to save.
     * @return the persisted entity.
     */
    public ActusDTO save(ActusDTO actusDTO) {
        log.debug("Request to save Actus : {}", actusDTO);
        Actus actus = actusMapper.toEntity(actusDTO);
        actus = actusRepository.save(actus);
        return actusMapper.toDto(actus);
    }

    /**
     * Get all the actuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ActusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Actuses");
        return actusRepository.findAll(pageable)
            .map(actusMapper::toDto);
    }

    /**
     * Get one actus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ActusDTO> findOne(Long id) {
        log.debug("Request to get Actus : {}", id);
        return actusRepository.findById(id)
            .map(actusMapper::toDto);
    }

    /**
     * Delete the actus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Actus : {}", id);
        actusRepository.deleteById(id);
    }
}
