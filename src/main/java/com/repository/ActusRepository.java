package com.repository;

import com.domain.Actus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Actus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActusRepository extends JpaRepository<Actus, Long> {

}
