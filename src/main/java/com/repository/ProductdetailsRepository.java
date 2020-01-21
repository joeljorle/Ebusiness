package com.repository;

import com.domain.Productdetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Productdetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductdetailsRepository extends JpaRepository<Productdetails, Long> {

}
