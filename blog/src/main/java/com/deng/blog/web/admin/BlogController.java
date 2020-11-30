package com.deng.blog.web.admin;

import com.deng.blog.po.Blog;
import com.deng.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by deng on 2020-11-24
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog")
    public String list(@PageableDefault(size = 2, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable,
                       Blog blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blog";
    }
}
