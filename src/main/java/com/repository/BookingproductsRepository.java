package com.repository;

import com.domain.Bookingproducts;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bookingproducts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingproductsRepository extends JpaRepository<Bookingproducts, Long> {

}
