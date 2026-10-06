package com.dbcargo.notesboard.repository;

import com.dbcargo.notesboard.domain.entities.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationsRepository extends JpaRepository<ApplicationEntity, Long> {
}