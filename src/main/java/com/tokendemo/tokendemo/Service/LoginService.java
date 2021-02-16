package com.tokendemo.tokendemo.Service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {


    public boolean checkUserAndPassword(String username, String password) {

        if(username.equals("bill") && password.equals("gates")) return true;

        return false;

    }





}
