package com.rocketzki.persistedthymeleaf.repository;

import com.rocketzki.persistedthymeleaf.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepo extends JpaRepository<Score, Integer> {

    @Query(value = "select * from score", nativeQuery = true)
    List<Score> getAllScores();



}
