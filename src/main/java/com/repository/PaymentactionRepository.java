package com.repository;

import com.domain.Paymentaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Paymentaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentactionRepository extends JpaRepository<Paymentaction, Long> {

}
