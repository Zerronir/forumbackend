package com.tokendemo.tokendemo.Controllers;

import com.google.gson.Gson;
import com.tokendemo.tokendemo.Entities.Topic;
import com.tokendemo.tokendemo.Service.CategoryService;
import com.tokendemo.tokendemo.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TopicController {

    Gson gson = new Gson();

    @Autowired
    TopicService topicService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping("/categories/{slug}/topics")
    public ResponseEntity<String> getTopics(@PathVariable(value = "slug") String slug) {

        int catId = categoryService.getCategoryBySlugEquals(slug);

        List<Topic> topics = new ArrayList<>();

        topicService.findTopicsByCategoryId(catId).forEach(t -> topics.add(t));

        return new ResponseEntity<>(gson.toJson(topics), HttpStatus.OK);
    }

}
