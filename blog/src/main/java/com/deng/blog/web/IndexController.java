package com.deng.blog.web;

import com.deng.blog.NotFoundException;
import com.deng.blog.service.BlogService;
import com.deng.blog.service.TagService;
import com.deng.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * created by deng on 2020-11-11
 **/
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listType(6));
        model.addAttribute("tags",tagService.listTag(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlog(8));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(/*@PathVariable Integer id, @PathVariable String name*/) {
        System.out.println("--------------blog--------------");
        return "blog";
    }
}
