package com.kagouniv.kagouniv_back.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    //--------------------------------------------------

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Habit> habits;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteHabit> favoriteHabits;


    //---------------------------------------------------

    @Builder
    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
