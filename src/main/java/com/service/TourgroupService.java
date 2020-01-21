package com.service;

import com.domain.Tourgroup;
import com.repository.TourgroupRepository;
import com.service.dto.TourgroupDTO;
import com.service.mapper.TourgroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tourgroup}.
 */
@Service
@Transactional
public class TourgroupService {

    private final Logger log = LoggerFactory.getLogger(TourgroupService.class);

    private final TourgroupRepository tourgroupRepository;

    private final TourgroupMapper tourgroupMapper;

    public TourgroupService(TourgroupRepository tourgroupRepository, TourgroupMapper tourgroupMapper) {
        this.tourgroupRepository = tourgroupRepository;
        this.tourgroupMapper = tourgroupMapper;
    }

    /**
     * Save a tourgroup.
     *
     * @param tourgroupDTO the entity to save.
     * @return the persisted entity.
     */
    public TourgroupDTO save(TourgroupDTO tourgroupDTO) {
        log.debug("Request to save Tourgroup : {}", tourgroupDTO);
        Tourgroup tourgroup = tourgroupMapper.toEntity(tourgroupDTO);
        tourgroup = tourgroupRepository.save(tourgroup);
        return tourgroupMapper.toDto(tourgroup);
    }

    /**
     * Get all the tourgroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TourgroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tourgroups");
        return tourgroupRepository.findAll(pageable)
            .map(tourgroupMapper::toDto);
    }


    /**
     * Get one tourgroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TourgroupDTO> findOne(Long id) {
        log.debug("Request to get Tourgroup : {}", id);
        return tourgroupRepository.findById(id)
            .map(tourgroupMapper::toDto);
    }

    /**
     * Delete the tourgroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Tourgroup : {}", id);
        tourgroupRepository.deleteById(id);
    }
}
