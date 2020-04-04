package com.myboot.controllers.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Nansen
 * @create 2020/4/4 21:27
 */
@RestController
public class UserController {

    @PostMapping("getUser")
    public User getUser(@RequestBody @Valid User user){
        user.setAge("12");
        user.setSex("male");
        return  user;
    }
}
