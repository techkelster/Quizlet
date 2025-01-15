package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String noteText;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
