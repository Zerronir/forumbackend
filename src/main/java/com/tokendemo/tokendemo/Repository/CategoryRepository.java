package com.tokendemo.tokendemo.Repository;

import com.tokendemo.tokendemo.Entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
