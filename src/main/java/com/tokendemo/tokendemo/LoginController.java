package com.tokendemo.tokendemo;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    Gson gson = new Gson();

    @Autowired
    LoginService loginService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String payload) {
        Map<String, String> map = gson.fromJson(payload, HashMap.class);

        String username = map.get("username");
        String password = map.get("password");

        if(!loginService.checkUserAndPassword(username, password)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = tokenService.newToken(username);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("token", token);

        return new ResponseEntity<>(gson.toJson(responseMap), HttpStatus.ACCEPTED);

    }

}
