package com.tokendemo.tokendemo.Repository;

import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category getCategoryBySlugEquals(String slug);

}
