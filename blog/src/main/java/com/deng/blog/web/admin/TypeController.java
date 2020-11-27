package com.deng.blog.web.admin;

import com.deng.blog.po.Type;
import com.deng.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


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
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    @GetMapping("/type/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/type-input";
    }

    @PostMapping("/type")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {

        Type fType = typeService.getTypeByName(type.getName());
        if (fType != null) {
            result.rejectValue("name", "nameExist", "分类已经存在");
        }

        if (result.hasErrors()) {
            return "admin/type-input";
        }

        Type sType = typeService.saveType(type);
        if (sType != null) {
            attributes.addFlashAttribute("errMessage", "操作失败");
        } else {
            attributes.addFlashAttribute("succMessage", "操作成功");
        }
        return "redirect:/admin/type";
    }

}
