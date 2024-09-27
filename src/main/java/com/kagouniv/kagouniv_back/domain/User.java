package com.kagouniv.kagouniv_back.domain;


import com.kagouniv.kagouniv_back.domain.enums.Role;
import com.kagouniv.kagouniv_back.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 30, unique = true)
    private String loginId; //아이디

    @Column(nullable = false)
    private String password; //비밀번호

    /**
     * 상황이 어떻게 될지 몰라,
     * 권한을 일단 나누기는 했습니다.
     */
    @Enumerated(EnumType.STRING)
    private Role role; //권한 -> USER, ADMIN

    @Column(length = 1000)
    private String refreshToken;

    //--------------------------------------------------

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Habit> habits;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteHabit> favoriteHabits;


    //---------------------------------------------------

    public void encdoePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void destroyRefreshToken() {
        this.refreshToken = null;
    }

    //---------------------------------------------------

    @Builder
    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
