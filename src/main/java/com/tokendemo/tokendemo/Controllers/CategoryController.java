package com.tokendemo.tokendemo.Controllers;

import com.google.gson.Gson;
import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    Gson gson = new Gson();

    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public ResponseEntity<String> getCategories() {
        List<Category> list = new ArrayList<>();
        categoryRepository.findAll().forEach(c -> list.add(c));
        return new ResponseEntity<>(gson.toJson(list), HttpStatus.OK);
    }

    @GetMapping("/categories/{slug}")
    public ResponseEntity<String> getCategory(@PathVariable String slug) {
        Category category = categoryRepository.getCategoryBySlugEquals(slug);
        return new ResponseEntity<>(gson.toJson(category), HttpStatus.OK);
    }

}
