package com.service;

import com.domain.Location;
import com.repository.LocationRepository;
import com.service.dto.LocationDTO;
import com.service.mapper.LocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Location}.
 */
@Service
@Transactional
public class LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationService.class);

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    /**
     * Save a location.
     *
     * @param locationDTO the entity to save.
     * @return the persisted entity.
     */
    public LocationDTO save(LocationDTO locationDTO) {
        log.debug("Request to save Location : {}", locationDTO);
        Location location = locationMapper.toEntity(locationDTO);
        location = locationRepository.save(location);
        return locationMapper.toDto(location);
    }

    /**
     * Get all the locations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Locations");
        return locationRepository.findAll(pageable)
            .map(locationMapper::toDto);
    }


    /**
     *  Get all the locations where Category is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereCategoryIsNull() {
        log.debug("Request to get all locations where Category is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getCategory() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the locations where Product is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereProductIsNull() {
        log.debug("Request to get all locations where Product is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getProduct() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the locations where Evenement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereEvenementIsNull() {
        log.debug("Request to get all locations where Evenement is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getEvenement() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the locations where Profile is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereProfileIsNull() {
        log.debug("Request to get all locations where Profile is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getProfile() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the locations where Tour is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereTourIsNull() {
        log.debug("Request to get all locations where Tour is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getTour() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the locations where Tourgroup is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LocationDTO> findAllWhereTourgroupIsNull() {
        log.debug("Request to get all locations where Tourgroup is null");
        return StreamSupport
            .stream(locationRepository.findAll().spliterator(), false)
            .filter(location -> location.getTourgroup() == null)
            .map(locationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one location by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LocationDTO> findOne(Long id) {
        log.debug("Request to get Location : {}", id);
        return locationRepository.findById(id)
            .map(locationMapper::toDto);
    }

    /**
     * Delete the location by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Location : {}", id);
        locationRepository.deleteById(id);
    }
}
