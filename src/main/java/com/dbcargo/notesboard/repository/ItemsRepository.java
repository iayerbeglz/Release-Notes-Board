package com.dbcargo.notesboard.repository;

import com.dbcargo.notesboard.domain.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<ItemEntity, Long> {

    ItemEntity findByRelease(String release);
}