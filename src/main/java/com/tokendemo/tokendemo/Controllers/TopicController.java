package com.tokendemo.tokendemo.Controllers;

import com.google.gson.Gson;
import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Entities.Topic;
import com.tokendemo.tokendemo.Entities.User;
import com.tokendemo.tokendemo.Service.CategoryService;
import com.tokendemo.tokendemo.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TopicController {

    Gson gson = new Gson();

    @Autowired
    TopicService topicService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HttpSession session;

    @GetMapping("/categories/{slug}/topics")
    public ResponseEntity<String> getTopics(@PathVariable(value = "slug") String slug) {

        int catId = categoryService.getCategoryBySlugEquals(slug);

        List<Topic> topics = new ArrayList<>();

        topicService.findTopicsByCategoryId(catId).forEach(t -> topics.add(t));

        return new ResponseEntity<>(gson.toJson(topics), HttpStatus.OK);
    }

    @PostMapping("/topics")
    public ResponseEntity<String> newTopic(@RequestBody String payload) {

        try {
            Map<String, String> form = gson.fromJson(payload, HashMap.class);

            User u = (User) session.getAttribute("user");

            Topic topic = new Topic();
            topic.setViews(0);
            topic.setTitle(form.get("title"));
            topic.setUserId(u.get_id());
            topic.setCategoryId((Integer) categoryService.getCategoryBySlugEquals(form.get("category")));
            topic.setContent(form.get("content"));

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(gson.toJson(session.getAttribute("user")), HttpStatus.BAD_REQUEST);
        }

    }

}
