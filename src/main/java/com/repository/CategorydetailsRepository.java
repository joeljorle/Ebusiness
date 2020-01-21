package com.repository;

import com.domain.Categorydetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Categorydetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorydetailsRepository extends JpaRepository<Categorydetails, Long> {

}
