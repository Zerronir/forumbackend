package com.tokendemo.tokendemo.Controllers;

import com.google.gson.Gson;
import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Entities.Topic;
import com.tokendemo.tokendemo.Repository.CategoryRepository;
import com.tokendemo.tokendemo.Service.CategoryService;
import com.tokendemo.tokendemo.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    Gson gson = new Gson();

    @Autowired
    CategoryService categoryService;

    @Autowired
    TopicService topicService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/categories")
    public ResponseEntity<String> getCategories() {
        List<Category> list = new ArrayList<>();
        categoryService
                .findAll()
                .forEach(c -> list.add(c));
        return new ResponseEntity<>(gson.toJson(list), HttpStatus.OK);
    }

    @GetMapping("/categories/{slug}")
    public ResponseEntity<String> getCategory(@PathVariable String slug) {

        List<Topic> topics = new ArrayList<>();

        int catId = 1;

        topicService.findTopicsByCategoryId(catId).forEach(topics::add);

        return new ResponseEntity<>(gson.toJson(topics), HttpStatus.OK);
    }

}
