package com.repository;

import com.domain.Cartproducts;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Cartproducts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CartproductsRepository extends JpaRepository<Cartproducts, Long> {

}
