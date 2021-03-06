package com.cloudy.controller;

import com.cloudy.entity.User;
//import com.cloudy.security.social.AppSignUpUtils;
import com.cloudy.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.annotation.JsonView;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ljy_cloudy on 2018/10/5.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private SecurityProperties securityProperties;

//    @Autowired
//    private AppSignUpUtils appSignUpUtils;

    @PostMapping("/regist")
    public void registUser(User user , HttpServletRequest request){
        String userId = user.getUsername();
//        appSignUpUtils.doPostSignUp(userId, new ServletWebRequest(request));
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @GetMapping("/me")
    public Object getCurrentUser( Authentication user,HttpServletRequest request)throws Exception {
        String token = StringUtils.substringAfter(request.getHeader("Authorization"), "Bearer ");

        Claims claims = Jwts.parser().setSigningKey(
                securityProperties.getOauth2().getSigningKey().getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();
        // 拿到自定义增强的参数
        String company = (String) claims.get("company");

        System.out.println(company);

        return user;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "查询用户信息")
    public List<User> query(@RequestParam(value = "username", required = false) String username,
                            Pageable pageable) {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        return userList;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation(value = "获取用户信息")
    public User getInfo(@PathVariable String id) {

//        throw new RuntimeException("usr not exist !");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    @ApiOperation(value = "创建用户信息")
    public User create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        System.out.println(user.getUsername());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("更新用户信息")
    public User update(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(
                    objectError -> {
                        System.out.println(objectError.getDefaultMessage());
                    }
            );
        }

        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@ApiParam(value = "用户ID") @PathVariable String id) {
        System.out.println(id);
    }
}
