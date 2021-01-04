package com.deng.blog.web;

import com.deng.blog.po.Blog;
import com.deng.blog.po.Type;
import com.deng.blog.service.BlogService;
import com.deng.blog.service.TypeService;
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
 * created by deng on 2021-01-03
 **/
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/type/{id}")

    public String type(@PageableDefault(size = 8 , sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @PathVariable Long id, Model model) {
        List<Type> typeList = typeService.listTypeTop(10000);
        if (id == -1) {
            id = typeList.get(0).getId();
            Blog blog = new Blog();
            blog.setType(typeService.getType(id));
            model.addAttribute("types", typeList);
            model.addAttribute("page", blogService.listBlog(pageable, blog));
            model.addAttribute("activeTypeId", id);
        }
        return "type";
    }
}
