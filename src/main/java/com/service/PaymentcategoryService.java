package com.service;

import com.domain.Paymentcategory;
import com.repository.PaymentcategoryRepository;
import com.service.dto.PaymentcategoryDTO;
import com.service.mapper.PaymentcategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Paymentcategory}.
 */
@Service
@Transactional
public class PaymentcategoryService {

    private final Logger log = LoggerFactory.getLogger(PaymentcategoryService.class);

    private final PaymentcategoryRepository paymentcategoryRepository;

    private final PaymentcategoryMapper paymentcategoryMapper;

    public PaymentcategoryService(PaymentcategoryRepository paymentcategoryRepository, PaymentcategoryMapper paymentcategoryMapper) {
        this.paymentcategoryRepository = paymentcategoryRepository;
        this.paymentcategoryMapper = paymentcategoryMapper;
    }

    /**
     * Save a paymentcategory.
     *
     * @param paymentcategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentcategoryDTO save(PaymentcategoryDTO paymentcategoryDTO) {
        log.debug("Request to save Paymentcategory : {}", paymentcategoryDTO);
        Paymentcategory paymentcategory = paymentcategoryMapper.toEntity(paymentcategoryDTO);
        paymentcategory = paymentcategoryRepository.save(paymentcategory);
        return paymentcategoryMapper.toDto(paymentcategory);
    }

    /**
     * Get all the paymentcategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentcategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Paymentcategories");
        return paymentcategoryRepository.findAll(pageable)
            .map(paymentcategoryMapper::toDto);
    }


    /**
     * Get one paymentcategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentcategoryDTO> findOne(Long id) {
        log.debug("Request to get Paymentcategory : {}", id);
        return paymentcategoryRepository.findById(id)
            .map(paymentcategoryMapper::toDto);
    }

    /**
     * Delete the paymentcategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Paymentcategory : {}", id);
        paymentcategoryRepository.deleteById(id);
    }
}
