package com.tokendemo.tokendemo.Repository;

import com.tokendemo.tokendemo.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepoAccess")
public class UserRepoAcces implements UserRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    String query;


    @Override
    public User registerUser(User user) throws Exception {
        return null;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {

        query = "SELECT * FROM users WHERE userEmail = ? AND userPassword = ?";

        User user = new User();

        User finalUser = user;
        user = jdbcTemplate.queryForObject(query, new Object[]{email, password},
                (resultSet, i) -> {
                    int userId = resultSet.getInt("userId");
                    String userName = resultSet.getString("userName");
                    String userEmail = resultSet.getString("userEmail");
                    String userPassword = resultSet.getString("userPassword");
                    String avatar = resultSet.getString("avatar");
                    String role = resultSet.getString("userrole");

                    finalUser.setUserId(userId);
                    finalUser.setEmail(userEmail);
                    finalUser.setName(userName);
                    finalUser.setPassword(userPassword);
                    finalUser.setAvatar(avatar);
                    finalUser.setRole(role);
                    return finalUser;
                });

        // Devolvemos un Optional nulo donde le pasamos el objeto de tipo Person
        return null;
    }

    @Override
    public boolean deleteUser(User user) throws Exception {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        return false;
    }
}
