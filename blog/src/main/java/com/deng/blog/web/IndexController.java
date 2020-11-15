package com.deng.blog.web;

import com.deng.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * created by deng on 2020-11-11
 **/
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(/*@PathVariable Integer id, @PathVariable String name*/) {
//        int i = 9/0;
//        String blog = null;
//        if (blog == null) {
//            throw  new NotFoundException("博客不存在");
//        }
        System.out.println("--------------index--------------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog(/*@PathVariable Integer id, @PathVariable String name*/) {
        System.out.println("--------------blog--------------");
        return "blog";
    }
}
