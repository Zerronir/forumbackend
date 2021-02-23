package com.tokendemo.tokendemo.Service;

import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService implements CategoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public <S extends Category> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Category> findAll() {

        List<Category> categoryList = new ArrayList<>();

        List<Map<String, Object>> mapRow = jdbcTemplate.queryForList("SELECT * FROM category");

        for (Map row : mapRow) {
            Category cat = new Category();

            cat.set_id((int) ((Integer) row.get("_id")).longValue());
            cat.setTitle((String) row.get("title"));
            cat.setSlug((String) row.get("slug"));
            cat.setDescription((String) row.get("description"));
            cat.setColor((String) row.get("color"));

            categoryList.add(cat);

        }

        return categoryList;
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Integer> integers) {
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
    public void delete(Category entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> entities) {

    }

    @Override
    public void deleteAll() {

    }

    // CUSTOM METHODS
    public int getCategoryBySlugEquals(String slug)  {

        int catId = 0;

        List<Map<String, Object>> cat = jdbcTemplate.queryForList("SELECT _id FROM category WHERE slug = ?", slug);

        for (Map row : cat) {
            catId = (Integer) row.get("_id");
            return catId;
        }


        return catId;
    }



}
