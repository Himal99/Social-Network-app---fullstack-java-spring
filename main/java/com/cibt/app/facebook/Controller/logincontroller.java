package com.cibt.app.facebook.Controller;

import javax.servlet.http.HttpSession;

import com.cibt.app.facebook.Entity.User;
import com.cibt.app.facebook.Repository.userRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class logincontroller {

    @Autowired
    private userRepository userRepository;

    @GetMapping
    public String index() {
        return "login/index";
    }

    // using httpsession to store the user detail in memory
    @PostMapping
    public String login(HttpSession session, @RequestParam("username") String username,
            @RequestParam("password") String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            /*
             * when user get access nd enter into a profile then his/her detail will save in
             * a memory through a session
             */

            session.setAttribute("user", user);
            return "redirect:/account";
        } else {
            return "redirect:/login?error";
        }

    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}