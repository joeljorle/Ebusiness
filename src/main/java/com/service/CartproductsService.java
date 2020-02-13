package com.service;

import com.domain.Cartproducts;
import com.repository.CartproductsRepository;
import com.service.dto.CartproductsDTO;
import com.service.mapper.CartproductsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cartproducts}.
 */
@Service
@Transactional
public class CartproductsService {

    private final Logger log = LoggerFactory.getLogger(CartproductsService.class);

    private final CartproductsRepository cartproductsRepository;

    private final CartproductsMapper cartproductsMapper;

    public CartproductsService(CartproductsRepository cartproductsRepository, CartproductsMapper cartproductsMapper) {
        this.cartproductsRepository = cartproductsRepository;
        this.cartproductsMapper = cartproductsMapper;
    }

    /**
     * Save a cartproducts.
     *
     * @param cartproductsDTO the entity to save.
     * @return the persisted entity.
     */
    public CartproductsDTO save(CartproductsDTO cartproductsDTO) {
        log.debug("Request to save Cartproducts : {}", cartproductsDTO);
        Cartproducts cartproducts = cartproductsMapper.toEntity(cartproductsDTO);
        cartproducts = cartproductsRepository.save(cartproducts);
        return cartproductsMapper.toDto(cartproducts);
    }

    /**
     * Get all the cartproducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CartproductsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cartproducts");
        return cartproductsRepository.findAll(pageable)
            .map(cartproductsMapper::toDto);
    }

    /**
     * Get one cartproducts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CartproductsDTO> findOne(Long id) {
        log.debug("Request to get Cartproducts : {}", id);
        return cartproductsRepository.findById(id)
            .map(cartproductsMapper::toDto);
    }

    /**
     * Delete the cartproducts by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cartproducts : {}", id);
        cartproductsRepository.deleteById(id);
    }
}
