package com.tokendemo.tokendemo.Service;

import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService implements CategoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    CategoryRepository catRepo;

    @Override
    public <S extends Category> S save(S entity) {

        try {
            jdbcTemplate.update(
                    "INSERT INTO category (color, title, slug, description) VALUES (?, ?, ?, ?)",
                    entity.getColor(),
                    entity.getTitle(),
                    entity.getSlug(),
                    entity.getDescription());
            return entity;
        } catch (Exception exception) {
            return null;
        }
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

        Category cat = new Category();

        List<Map<String, Object>> catQuery = jdbcTemplate.queryForList("SELECT * FROM category WHERE slug = ?", slug);

        for (Map row : catQuery) {
            cat.set_id((Integer) row.get("_id"));
            return cat.get_id();
        }


        return cat.get_id();
    }

    public Category getBySlug(String slug) {
        Category cat = new Category();

        List<Map<String, Object>> catQuery = jdbcTemplate.queryForList("SELECT * FROM category WHERE slug = ?", slug);

        for (Map row : catQuery) {
            cat.set_id((Integer) row.get("_id"));
            cat.setTitle("title");
            cat.setSlug(slug);
            cat.setDescription("description");
            cat.setColor("color");
            return cat;
        }


        return null;
    }

    // Delete category
    public boolean deleteBySlugEquals(String slug) {

        try {
            jdbcTemplate.update("DELETE FROM category WHERE slug = ?", slug);
            return true;
        }catch (Exception exception) {
            return false;
        }
    }

}
