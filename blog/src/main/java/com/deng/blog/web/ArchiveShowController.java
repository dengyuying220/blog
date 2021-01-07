package com.deng.blog.web;

import com.deng.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * created by deng on 2021-01-07
 **/
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archive")
    public String archive(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        return "archive";
    }
}
