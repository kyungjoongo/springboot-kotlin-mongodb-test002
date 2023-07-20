package com.kyungjoon.login;

import com.kyungjoon.user.User;
import com.kyungjoon.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) throws Exception {

        User userOne = this.loginService.getUserOne(user);

        this.userService.updateOne(userOne);

        return ResponseEntity.ok().body(userOne);
    }


}
