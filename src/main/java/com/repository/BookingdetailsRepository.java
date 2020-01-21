package com.repository;

import com.domain.Bookingdetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Bookingdetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingdetailsRepository extends JpaRepository<Bookingdetails, Long> {

}
