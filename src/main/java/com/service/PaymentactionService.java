package com.service;

import com.domain.Paymentaction;
import com.repository.PaymentactionRepository;
import com.service.dto.PaymentactionDTO;
import com.service.mapper.PaymentactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Paymentaction}.
 */
@Service
@Transactional
public class PaymentactionService {

    private final Logger log = LoggerFactory.getLogger(PaymentactionService.class);

    private final PaymentactionRepository paymentactionRepository;

    private final PaymentactionMapper paymentactionMapper;

    public PaymentactionService(PaymentactionRepository paymentactionRepository, PaymentactionMapper paymentactionMapper) {
        this.paymentactionRepository = paymentactionRepository;
        this.paymentactionMapper = paymentactionMapper;
    }

    /**
     * Save a paymentaction.
     *
     * @param paymentactionDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentactionDTO save(PaymentactionDTO paymentactionDTO) {
        log.debug("Request to save Paymentaction : {}", paymentactionDTO);
        Paymentaction paymentaction = paymentactionMapper.toEntity(paymentactionDTO);
        paymentaction = paymentactionRepository.save(paymentaction);
        return paymentactionMapper.toDto(paymentaction);
    }

    /**
     * Get all the paymentactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Paymentactions");
        return paymentactionRepository.findAll(pageable)
            .map(paymentactionMapper::toDto);
    }

    /**
     * Get one paymentaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentactionDTO> findOne(Long id) {
        log.debug("Request to get Paymentaction : {}", id);
        return paymentactionRepository.findById(id)
            .map(paymentactionMapper::toDto);
    }

    /**
     * Delete the paymentaction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Paymentaction : {}", id);
        paymentactionRepository.deleteById(id);
    }
}
