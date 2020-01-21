package com.service;

import com.domain.Follows;
import com.repository.FollowsRepository;
import com.service.dto.FollowsDTO;
import com.service.mapper.FollowsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Follows}.
 */
@Service
@Transactional
public class FollowsService {

    private final Logger log = LoggerFactory.getLogger(FollowsService.class);

    private final FollowsRepository followsRepository;

    private final FollowsMapper followsMapper;

    public FollowsService(FollowsRepository followsRepository, FollowsMapper followsMapper) {
        this.followsRepository = followsRepository;
        this.followsMapper = followsMapper;
    }

    /**
     * Save a follows.
     *
     * @param followsDTO the entity to save.
     * @return the persisted entity.
     */
    public FollowsDTO save(FollowsDTO followsDTO) {
        log.debug("Request to save Follows : {}", followsDTO);
        Follows follows = followsMapper.toEntity(followsDTO);
        follows = followsRepository.save(follows);
        return followsMapper.toDto(follows);
    }

    /**
     * Get all the follows.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FollowsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Follows");
        return followsRepository.findAll(pageable)
            .map(followsMapper::toDto);
    }


    /**
     * Get one follows by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FollowsDTO> findOne(Long id) {
        log.debug("Request to get Follows : {}", id);
        return followsRepository.findById(id)
            .map(followsMapper::toDto);
    }

    /**
     * Delete the follows by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Follows : {}", id);
        followsRepository.deleteById(id);
    }
}
