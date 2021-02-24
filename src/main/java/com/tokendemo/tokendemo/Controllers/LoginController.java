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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String payload) throws Exception {
        Map<String, String> map = gson.fromJson(payload, HashMap.class);

        String username = map.get("email");
        String password = map.get("password");

        if(userRepoAcces.findByEmailEqualsAndPasswordEquals(username, hashPass(password)) == null) {
            return new ResponseEntity<>(gson.toJson("Error, no user found"), HttpStatus.UNAUTHORIZED);
        } else {

            User user = userRepoAcces.findByEmailEqualsAndPasswordEquals(username, hashPass(password));

            String token = tokenService.newToken(user.getEmail());

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("token", token);

            Map<String, User> userMap = new HashMap<>();
            userMap.put("user", user);

            return new ResponseEntity<>(gson.toJson(responseMap) + " " + gson.toJson(userMap), HttpStatus.OK);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody String payload) throws SQLException {

        Map<String, String> formMap = gson.fromJson(payload, HashMap.class);

        String name = formMap.get("name");
        String email = formMap.get("email");
        String pw = formMap.get("password");
        String role = formMap.get("role");

        try {
            // Create the user to save in database
            User u = new User();
            u.setRole(role);
            u.setPassword(hashPass(pw));
            u.setEmail(email);
            u.setName(name);

            userRepoAcces.save(u);

            return new ResponseEntity<>(gson.toJson("done"), HttpStatus.CREATED);

        }catch (NoSuchAlgorithmException ex) {
            return new ResponseEntity<>(gson.toJson(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }


    }

    private String hashPass(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(password.getBytes());

        byte[] digest = md.digest();

        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < digest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        return hexString.toString();

    }

}
