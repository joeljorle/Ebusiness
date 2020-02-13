package com.service;

import com.domain.Productdetails;
import com.repository.ProductdetailsRepository;
import com.service.dto.ProductdetailsDTO;
import com.service.mapper.ProductdetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Productdetails}.
 */
@Service
@Transactional
public class ProductdetailsService {

    private final Logger log = LoggerFactory.getLogger(ProductdetailsService.class);

    private final ProductdetailsRepository productdetailsRepository;

    private final ProductdetailsMapper productdetailsMapper;

    public ProductdetailsService(ProductdetailsRepository productdetailsRepository, ProductdetailsMapper productdetailsMapper) {
        this.productdetailsRepository = productdetailsRepository;
        this.productdetailsMapper = productdetailsMapper;
    }

    /**
     * Save a productdetails.
     *
     * @param productdetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductdetailsDTO save(ProductdetailsDTO productdetailsDTO) {
        log.debug("Request to save Productdetails : {}", productdetailsDTO);
        Productdetails productdetails = productdetailsMapper.toEntity(productdetailsDTO);
        productdetails = productdetailsRepository.save(productdetails);
        return productdetailsMapper.toDto(productdetails);
    }

    /**
     * Get all the productdetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductdetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Productdetails");
        return productdetailsRepository.findAll(pageable)
            .map(productdetailsMapper::toDto);
    }

    /**
     * Get one productdetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductdetailsDTO> findOne(Long id) {
        log.debug("Request to get Productdetails : {}", id);
        return productdetailsRepository.findById(id)
            .map(productdetailsMapper::toDto);
    }

    /**
     * Delete the productdetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Productdetails : {}", id);
        productdetailsRepository.deleteById(id);
    }
}
