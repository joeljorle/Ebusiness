package com.repository;

import com.domain.Cherche;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Cherche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChercheRepository extends JpaRepository<Cherche, Long> {

}
