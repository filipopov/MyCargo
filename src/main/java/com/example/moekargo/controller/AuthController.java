package com.example.moekargo.controller;

import com.example.moekargo.model.User;
import com.example.moekargo.service.AuthService;
import com.example.moekargo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage(){ return "login"; }

    @PostMapping("/login")
    public String getLoginInfo(Model model, HttpServletRequest request){
        User user = null;
        try{
            user = this.authService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:/home";
        }
        catch (IllegalArgumentException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String repeatPassword,
            @RequestParam String fullName,
            @RequestParam String email){

        try {
            this.userService.register(username, password, repeatPassword, fullName, email);
            return "thank-you";
        }
        catch (IllegalArgumentException exception){
            return "redirect:/register?error="+exception.getMessage();
        }
    }
}
