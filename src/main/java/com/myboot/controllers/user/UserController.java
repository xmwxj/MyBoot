package com.myboot.controllers.user;

import com.myboot.validation.groups.Delete;
import com.myboot.validation.groups.SeqValidator;
import com.myboot.validation.groups.Update;
import com.myboot.vo.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

/**
 * @author Nansen
 * @create 2020/4/4 21:27
 */

/**
 * - 如果不使用Groups功能，@Valid和@Validated效果一样
 * - 关于分组接口：Default接口
 *     - 验证器如果没有写明Groups,默认分组是Default.class
 *     - @Validated(value = {Update.class} 会去验证所有的Update分组和default分组，因为update接口有继承default接口
 *     - @Validated(value = {Delete.class} 会去验证所有的delete分组，不会验证default分组，因为Delete接口没有继承default接口
 */
@RestController
public class UserController {

    /**
     * @param user
     * @return
     * 这里会去验证所有default的分组
     */
    @PostMapping("getUser")
    public User getUser(@RequestBody @Validated User user){
        user.setAge(12);
        user.setSex("male");
        return  user;
    }
    /**
     * @param user
     * @return
     * 这里会去验证所有default的分组
     */
    @PostMapping("createUser")
    public User createUser(@RequestBody @Validated(value = {Update.class}) User user){
        user.setId(1);
        return user;
    }

    /**
     * @param user
     * @return
     * 这里会去验证所有Update分组和dafault分组，不会验证delete分组
     */
    @PostMapping("updateUser")
    public int updateUser(@RequestBody @Validated(value = {SeqValidator.class}) User user){
        return 1;
    }

    /**
     *
     * @param user
     * @return
     * 这里指挥去验证delete分组
     */
    @PostMapping("deleteUser")
    public int deleteUser(@RequestBody @Validated(value = {Delete.class}) User user){
        return 1;
    }
}
