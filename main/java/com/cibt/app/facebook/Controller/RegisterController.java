package com.cibt.app.facebook.Controller;

import com.cibt.app.facebook.Entity.User;
import com.cibt.app.facebook.Entity.UserProfile;

import com.cibt.app.facebook.Repository.userProfileRepository;
import com.cibt.app.facebook.Repository.userRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private userRepository userRepository;
    @Autowired
    private userProfileRepository profileRepository;

    @GetMapping
    public String index() {

        return "register/index";
    }

    // check user form database
    @PostMapping(value = "/checkuser")
    @ResponseBody
    public boolean checkUser(@RequestParam("value") String value) {

        if (userRepository.findByUsername(value) == null) {
            return false;
        } else
            return true;
    }

    // check email from database
    @PostMapping(value = "/checkemail")
    @ResponseBody
    public boolean checkEmail(@RequestParam("value") String value) {

        if (userRepository.findByemail(value) == null) {
            return false;
        } else
            return true;
    }

    // save a registered user
    @PostMapping()

    public String save(@ModelAttribute("User") User user) {
        user.setStatus(true);

        userRepository.save(user);
        UserProfile Profile = new UserProfile();
        Profile.setUser(user);
        profileRepository.save(Profile);

        return "redirect:/login";
    }
}