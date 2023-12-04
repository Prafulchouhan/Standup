package com.standup.repo;

import com.standup.model.TeamSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamSpaceRepository extends JpaRepository<TeamSpace, Long> {
    Optional<TeamSpace> findByTitle(String title);

    void deleteByTitle(String title);
}