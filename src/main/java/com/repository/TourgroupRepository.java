package com.repository;

import com.domain.Tourgroup;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Tourgroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TourgroupRepository extends JpaRepository<Tourgroup, Long> {

}
