package com.repository;

import com.domain.Paymentcategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Paymentcategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentcategoryRepository extends JpaRepository<Paymentcategory, Long> {

}
