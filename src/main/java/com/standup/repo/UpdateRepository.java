package com.standup.repo;

import com.standup.model.Update;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateRepository extends JpaRepository<Update, Long> {
}