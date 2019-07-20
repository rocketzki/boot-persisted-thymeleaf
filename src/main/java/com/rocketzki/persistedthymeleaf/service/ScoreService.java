package com.rocketzki.persistedthymeleaf.service;

import com.rocketzki.persistedthymeleaf.model.Score;
import com.rocketzki.persistedthymeleaf.repository.ScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScoreService {

    private ScoreRepo repo;

    @Autowired
    public ScoreService(ScoreRepo repo) {
        this.repo = repo;
    }

    public List<Score> getAllScores() {
        return repo.getAllScores();
    }

    public Score saveScore(Integer score) {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return repo.save(new Score(loggedInUser, score, LocalDateTime.now()));
    }


}
