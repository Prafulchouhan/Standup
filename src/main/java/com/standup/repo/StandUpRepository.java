package com.standup.repo;

import com.standup.model.StandUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandUpRepository extends JpaRepository<StandUp, Long> {
}