package com.dbcargo.notesboard.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "release", nullable = false)
    private String release;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name="application_id")
    private ApplicationEntity application;
}