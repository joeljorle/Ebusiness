package com.repository;

import com.domain.Bookingview;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Bookingview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingviewRepository extends JpaRepository<Bookingview, Long> {

}
