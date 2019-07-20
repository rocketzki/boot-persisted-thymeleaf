package com.rocketzki.persistedthymeleaf.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AuthorityRepo {

    private static final String INSERT_SCORE_STATEMENT = "insert into authority (username, type) values (?,?)";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void setUserAuthority(String username, String type) {
        entityManager.createNativeQuery(INSERT_SCORE_STATEMENT)
                .setParameter(1, username)
                .setParameter(2, type)
                .executeUpdate();
    }

}
