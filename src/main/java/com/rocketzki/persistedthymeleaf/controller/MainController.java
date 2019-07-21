package com.rocketzki.persistedthymeleaf.controller;

import com.rocketzki.persistedthymeleaf.model.User;
import com.rocketzki.persistedthymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/game"})
    public ModelAndView getHomePage() {
        return new ModelAndView("game");
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(@RequestParam(required = false) String error,
                                      ModelAndView mav) {
        mav.setViewName("login");
        if ("true".equals(error)) {
            mav.addObject("error", "Login error - bad username or password.");
        }
        return mav;
    }


    @GetMapping("/register")
    public ModelAndView showRegistration() {
        return new ModelAndView("register");
    }


    @PostMapping(value = "/register")
    public ModelAndView registerUser(ModelAndView mav,
                                     @Valid User user,
                                     BindingResult bindingResult) {
        mav.setViewName("register");

        if (userService.findByUsername(user.getUsername()) != null) {
            mav.addObject("error", "That username is in use!");
            return mav;
        }

        if (bindingResult.hasErrors()) {
            mav.addObject("error", "Fill in all fields!");
        } else {
            userService.saveUser(user);
            mav.addObject("success", "User has been registered!");
        }
        return mav;
    }


}
