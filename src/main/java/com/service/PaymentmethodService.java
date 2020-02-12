package com.service;

import com.domain.Paymentmethod;
import com.repository.PaymentmethodRepository;
import com.service.dto.PaymentmethodDTO;
import com.service.mapper.PaymentmethodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Paymentmethod}.
 */
@Service
@Transactional
public class PaymentmethodService {

    private final Logger log = LoggerFactory.getLogger(PaymentmethodService.class);

    private final PaymentmethodRepository paymentmethodRepository;

    private final PaymentmethodMapper paymentmethodMapper;

    public PaymentmethodService(PaymentmethodRepository paymentmethodRepository, PaymentmethodMapper paymentmethodMapper) {
        this.paymentmethodRepository = paymentmethodRepository;
        this.paymentmethodMapper = paymentmethodMapper;
    }

    /**
     * Save a paymentmethod.
     *
     * @param paymentmethodDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentmethodDTO save(PaymentmethodDTO paymentmethodDTO) {
        log.debug("Request to save Paymentmethod : {}", paymentmethodDTO);
        Paymentmethod paymentmethod = paymentmethodMapper.toEntity(paymentmethodDTO);
        paymentmethod = paymentmethodRepository.save(paymentmethod);
        return paymentmethodMapper.toDto(paymentmethod);
    }

    /**
     * Get all the paymentmethods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentmethodDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Paymentmethods");
        return paymentmethodRepository.findAll(pageable)
            .map(paymentmethodMapper::toDto);
    }

    /**
     * Get one paymentmethod by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentmethodDTO> findOne(Long id) {
        log.debug("Request to get Paymentmethod : {}", id);
        return paymentmethodRepository.findById(id)
            .map(paymentmethodMapper::toDto);
    }

    /**
     * Delete the paymentmethod by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Paymentmethod : {}", id);
        paymentmethodRepository.deleteById(id);
    }
}
