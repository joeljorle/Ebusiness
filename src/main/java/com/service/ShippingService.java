package com.service;

import com.domain.Shipping;
import com.repository.ShippingRepository;
import com.service.dto.ShippingDTO;
import com.service.mapper.ShippingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Shipping}.
 */
@Service
@Transactional
public class ShippingService {

    private final Logger log = LoggerFactory.getLogger(ShippingService.class);

    private final ShippingRepository shippingRepository;

    private final ShippingMapper shippingMapper;

    public ShippingService(ShippingRepository shippingRepository, ShippingMapper shippingMapper) {
        this.shippingRepository = shippingRepository;
        this.shippingMapper = shippingMapper;
    }

    /**
     * Save a shipping.
     *
     * @param shippingDTO the entity to save.
     * @return the persisted entity.
     */
    public ShippingDTO save(ShippingDTO shippingDTO) {
        log.debug("Request to save Shipping : {}", shippingDTO);
        Shipping shipping = shippingMapper.toEntity(shippingDTO);
        shipping = shippingRepository.save(shipping);
        return shippingMapper.toDto(shipping);
    }

    /**
     * Get all the shippings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ShippingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Shippings");
        return shippingRepository.findAll(pageable)
            .map(shippingMapper::toDto);
    }


    /**
     * Get one shipping by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShippingDTO> findOne(Long id) {
        log.debug("Request to get Shipping : {}", id);
        return shippingRepository.findById(id)
            .map(shippingMapper::toDto);
    }

    /**
     * Delete the shipping by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Shipping : {}", id);
        shippingRepository.deleteById(id);
    }
}
