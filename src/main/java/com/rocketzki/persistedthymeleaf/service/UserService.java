package com.rocketzki.persistedthymeleaf.service;

import com.rocketzki.persistedthymeleaf.model.User;
import com.rocketzki.persistedthymeleaf.repository.AuthorityRepo;
import com.rocketzki.persistedthymeleaf.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;
    private AuthorityRepo authorityRepo;
    private PasswordEncoder passEncoder;


    @Autowired
    public UserService(AuthorityRepo authorityRepo, UserRepo userRepo, PasswordEncoder passEncoder) {
        this.userRepo = userRepo;
        this.authorityRepo = authorityRepo;
        this.passEncoder = passEncoder;
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User saveUser(User user) {
        user.setEnabled(true);
        user.setPassword(passEncoder.encode(user.getPassword()));
        setUserAuthority(user, "ADMIN");
        return userRepo.save(user);
    }

    private void setUserAuthority(User user, String authority){
        authorityRepo.setUserAuthority(user.getUsername(), authority);
    }


}
