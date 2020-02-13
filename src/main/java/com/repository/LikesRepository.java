package com.repository;

import com.domain.Likes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Likes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

}
