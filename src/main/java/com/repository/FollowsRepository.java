package com.repository;

import com.domain.Follows;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Follows entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FollowsRepository extends JpaRepository<Follows, Long> {

}
