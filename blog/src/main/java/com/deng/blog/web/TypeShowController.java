package com.deng.blog.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * created by deng on 2021-01-03
 **/
@Controller
public class TypeShowController {

    @GetMapping("/type/{id}")

    public String type(@PageableDefault(size = 8 , sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @PathVariable Long id, Model model) {

        return "type";
    }
}
