package com.kyungjoon.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Object getAllUser() {
        List<User> users = userService.getAll();
        try {
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().toString();
        }


    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(userService.getOneByID(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        System.out.println("sdlfksdlkflsdkfksdfk====>>>>>");

        return ResponseEntity.ok().body(this.userService.createProduct(user));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) throws Exception {
        user.setId(userId);
        return ResponseEntity.ok().body(this.userService.updateOne(user));
    }

    @DeleteMapping("/users/{id}")
    public HttpStatus deleteUser(@PathVariable String id) throws Exception {
        this.userService.deleteOne(id);
        return HttpStatus.OK;
    }
}
