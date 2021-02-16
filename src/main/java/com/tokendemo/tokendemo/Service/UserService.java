package com.tokendemo.tokendemo.Service;

import com.tokendemo.tokendemo.Entities.User;
import com.tokendemo.tokendemo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepo userRepo;

    public Optional<User> doLogin(String email, String password) throws Exception {
        return userRepo.getUserByEmailAndPassword(email, password);
    }

}
