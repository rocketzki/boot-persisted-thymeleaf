package com.rocketzki.persistedthymeleaf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private Integer points;
    private LocalDateTime date;


    public Score() {

    }

    public Score(String username, Integer points, LocalDateTime date) {
        this.username = username;
        this.points = points;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getPoints() {
        return points;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
