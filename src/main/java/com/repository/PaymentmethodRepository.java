package com.repository;

import com.domain.Paymentmethod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Paymentmethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentmethodRepository extends JpaRepository<Paymentmethod, Long> {

}
