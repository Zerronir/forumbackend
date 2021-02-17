package com.tokendemo.tokendemo.Controllers;

import com.google.gson.Gson;
import com.tokendemo.tokendemo.Entities.Category;
import com.tokendemo.tokendemo.Entities.User;
import com.tokendemo.tokendemo.Repository.UserRepoAcces;
import com.tokendemo.tokendemo.Repository.UserRepository;
import com.tokendemo.tokendemo.Service.LoginService;
import com.tokendemo.tokendemo.Service.TokenService;
import com.tokendemo.tokendemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class LoginController {
    // Creamos el template para las querys
    JdbcTemplate jdbcTemplate;

    Gson gson = new Gson();

    @Autowired
    UserRepoAcces userRepoAcces;

    UserRepository userRep;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String payload) throws Exception {
        Map<String, String> map = gson.fromJson(payload, HashMap.class);

        String username = map.get("username");
        String password = map.get("password");

        if(userRep.findByEmailEqualsAndPasswordEquals(username, password) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {

            User user = userRep.findByEmailEqualsAndPasswordEquals(username, password);

            String token = tokenService.newToken(user.getEmail());

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("token", token);

            return new ResponseEntity<>(gson.toJson(responseMap), HttpStatus.UNAUTHORIZED);
        }

    }

}
