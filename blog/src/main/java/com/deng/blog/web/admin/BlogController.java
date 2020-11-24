package com.deng.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by deng on 2020-11-24
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/blog")
    public String list() {
        return "admin/blog";
    }
}
