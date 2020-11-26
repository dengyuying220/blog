package com.deng.blog.web.admin;

import com.deng.blog.po.Type;
import com.deng.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * created by deng on 2020-11-25
 **/
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/type")
    public String list(@PageableDefault(size = 2, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "/admin/type";
    }

    @GetMapping("/type/input")
    public String input() {
        return "admin/type-input";
    }

    @PostMapping("/type")
    public String post(Type type) {
        Type t = typeService.saveType(type);
        if (t == null) {
//
        }
        return "redirect:/admin/type";
    }

}
