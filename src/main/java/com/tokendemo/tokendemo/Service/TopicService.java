package com.tokendemo.tokendemo.Service;

import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Entities.Topic;
import com.tokendemo.tokendemo.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TopicService implements TopicRepository {
    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public <S extends Topic> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Topic> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Topic> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Topic> findAll() {
        return null;
    }

    @Override
    public Iterable<Topic> findAllById(Iterable<Integer> integers) {
        return null;
    }

    public Iterable<Topic> findTopicsByCategoryId(int catId) {
        List<Topic> categoryList = new ArrayList<>();

        List<Map<String, Object>> mapRow = jdbcTemplate.queryForList("SELECT * FROM topics WHERE categoryId = ?", catId);

        for (Map row : mapRow) {
            Topic topic = new Topic();

            topic.set_id((int) ((Integer) row.get("_id")).longValue());
            topic.setTitle((String) row.get("title"));
            topic.setContent((String) row.get("content"));
            topic.setCreatedAt((Timestamp) row.get("created_at"));
            topic.setUpdatedAt((Timestamp) row.get("updated_at"));
            topic.setViews((Integer) row.get("views"));
            topic.setUserId((Integer) row.get("userId"));

            categoryList.add(topic);

        }

        return categoryList;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Topic entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Topic> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
