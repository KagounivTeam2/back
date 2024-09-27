package com.kagouniv.kagouniv_back.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "recommend")
@Entity
@Getter
@NoArgsConstructor
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID recommendHabitName;

}