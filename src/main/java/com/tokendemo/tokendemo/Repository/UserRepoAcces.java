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

        query = "SELECT * FROM users WHERE email = ? AND password = ?";

        User user = new User();

        User finalUser = user;
        user = jdbcTemplate.queryForObject(query, new String[]{email, password},
                (resultSet, i) -> {
                    int userId = resultSet.getInt("userId");
                    String userName = resultSet.getString("userName");
                    String userEmail = resultSet.getString("userEmail");
                    String userPassword = resultSet.getString("userPassword");
                    String avatar = resultSet.getString("avatar");
                    String role = resultSet.getString("userrole");

                    finalUser.set_id(userId);
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

    @Override
    public User findByEmailEqualsAndPasswordEquals(String email, String password) {
        User u = new User();

        jdbcTemplate.query("SELECT * FROM users WHERE email = ? AND password = ?",
                    new Object[]{email, password},
                    resultSet -> {
                        u.set_id(resultSet.getInt("_id"));
                        u.setName(resultSet.getString("name"));
                        u.setEmail(resultSet.getString("email"));
                        u.setPassword(resultSet.getString("password"));
                        u.setRole(resultSet.getString("role"));
                    }
                );

        return u;
    }

    @Override
    public <S extends User> S save(S entity) {

        jdbcTemplate.update("INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)",
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole());

        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
