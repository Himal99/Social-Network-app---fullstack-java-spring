package com.cibt.app.facebook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "postdetail")
public class PostDetailController {
    
    @GetMapping
    public String index(){
        return "profile/shared/postdetail";
    }
}