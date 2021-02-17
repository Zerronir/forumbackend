package com.tokendemo.tokendemo.Repository;

import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmailEqualsAndPasswordEquals(String email, String pass);
}
