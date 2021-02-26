package com.tokendemo.tokendemo.Controllers;

import com.google.gson.Gson;
import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Entities.Topic;
import com.tokendemo.tokendemo.Repository.CategoryRepository;
import com.tokendemo.tokendemo.Service.CategoryService;
import com.tokendemo.tokendemo.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

    @PostMapping("/categories")
    public ResponseEntity<String> newCategory(@RequestBody String form) {

        /*// Encapsulate the insert in a try catch
        try {
            // Map the form payload
            HashMap<String, String> formPayload = gson.fromJson(form, HashMap.class);

            Category cat = new Category();
            cat.setColor("green");
            cat.setDescription(formPayload.get("description"));
            cat.setSlug(formPayload.get("slug"));
            cat.setTitle(formPayload.get("title"));

            categoryService.save(cat);

            // If everything is correct then return an OK status
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception exception) {
            // In case we get an error then return a 400 (Bad Request) status
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/

        // Map the form payload
        HashMap<String, String> formPayload = gson.fromJson(form, HashMap.class);

        Category cat = new Category();
        cat.setColor("green");
        cat.setDescription(formPayload.get("description"));
        cat.setSlug(formPayload.get("title").toLowerCase().replace(" ", "-"));
        cat.setTitle(formPayload.get("title"));

        categoryService.save(cat);

        // If everything is correct then return an OK status
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/categories/{slug}")
    public ResponseEntity<String> getCategory(@PathVariable String slug) {

        List<Topic> topics = new ArrayList<>();

        int catId = 1;

        topicService.findTopicsByCategoryId(catId).forEach(topics::add);

        return new ResponseEntity<>(gson.toJson(topics), HttpStatus.OK);
    }

}
