package com.repository;

import com.domain.Tourcategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tourcategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TourcategoryRepository extends JpaRepository<Tourcategory, Long> {

}
