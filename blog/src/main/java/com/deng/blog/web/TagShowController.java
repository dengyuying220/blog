package com.deng.blog.web;

import com.deng.blog.po.Blog;
import com.deng.blog.po.Tag;
import com.deng.blog.po.Type;
import com.deng.blog.service.BlogService;
import com.deng.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * created by deng on 2021-01-07
 **/
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tag/{id}")
    public String type(@PageableDefault(size = 2 , sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @PathVariable Long id, Model model) {
        List<Tag> tagList = tagService.listTagTop(10000);
        if (id == -1) {
            id = tagList.get(0).getId();
        }
        model.addAttribute("tags", tagList);
        model.addAttribute("page", blogService.listBlogByTagId(pageable, id));
        model.addAttribute("activeTagId", id);
        return "tag";
    }
}
