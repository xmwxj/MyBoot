package com.myboot.controllers.user;

import com.myboot.vo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Nansen
 * @create 2020/4/4 21:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void testGetUser(){
        User user = new User();
        user.setName("test");
        User user2 =  userController.getUser(user);
        System.out.println("user2 = " + user2.getName());
        Assert.assertEquals(user2.getName(),"test");
    }
}
