package com.tokendemo.tokendemo.Repository;

import com.tokendemo.tokendemo.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    User registerUser(User user) throws Exception;

    User getUserByEmailAndPassword(String email, String password);

    boolean deleteUser(User user) throws Exception;

    boolean updateUser(User user) throws Exception;

    User findByEmailEqualsAndPasswordEquals(String email, String Password);

}
