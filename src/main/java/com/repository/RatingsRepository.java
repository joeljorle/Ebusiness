package com.repository;

import com.domain.Ratings;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ratings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Long> {

}
