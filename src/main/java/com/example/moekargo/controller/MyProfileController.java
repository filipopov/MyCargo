package com.example.moekargo.controller;

import com.example.moekargo.model.User;
import com.example.moekargo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/myProfile")
@AllArgsConstructor
public class MyProfileController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String getMyProfilePage(Model model, HttpServletRequest request){
        model.addAttribute("username", request.getRemoteUser());
        return "my-profile";
    }


    @PostMapping
    public String editProfilePage(Model model, HttpServletRequest request){
        User user = this.userService.findByUsername(request.getRemoteUser()).get();
        if(request.getParameter("password").equals(request.getParameter("repeat_password"))) {
            user.setFullName(request.getParameter("full_name"));
            user.setPassword(passwordEncoder.encode(request.getParameter("password")));
            this.userService.save(user);
        }
        else
            throw new IllegalArgumentException();

        model.addAttribute("username", request.getRemoteUser());
        return "my-profile";
    }
}
