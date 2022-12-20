package com.olusegun.repository;

import com.olusegun.data.model.Cd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CdRepository extends JpaRepository<Cd, Long> {
    Optional<Cd> findByTitle(String cdTitle);
    Optional<Cd> findByArtist(String cdArtist);
}
