package com.kagouniv.kagouniv_back.repository;

import com.kagouniv.kagouniv_back.domain.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, UUID> {
}
