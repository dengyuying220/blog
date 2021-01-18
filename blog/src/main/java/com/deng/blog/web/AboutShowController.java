package com.deng.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * created by deng on 2021-01-10
 **/
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about()  {
        return "about";
    }
}
