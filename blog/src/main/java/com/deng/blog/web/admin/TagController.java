package com.deng.blog.web.admin;

import com.deng.blog.po.Tag;
import com.deng.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * created by deng on 2020-11-30
 **/
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tag")
    public String list(@PageableDefault(size = 2, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "/admin/tag";
    }

    @GetMapping("/tag/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }

    @GetMapping("/tag/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tag-input";
    }

    @PostMapping("/tag")
    public String post(@Valid  Tag tag, BindingResult result, RedirectAttributes attributes) {

        Tag fTag = tagService.getTagByName(tag.getName());
        if (fTag != null) {
            result.rejectValue("name", "nameExist", "标签已经存在");
        }

        if (result.hasErrors()) {
            return "admin/tag-input";
        }

        Tag sTag = tagService.saveTag(tag);
        if (sTag == null) {
            attributes.addFlashAttribute("errMessage", "新增失败");
        } else {
            attributes.addFlashAttribute("succMessage", "新增成功");
        }
        return "redirect:/admin/tag";
    }

    @PostMapping("/tag/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {

        Tag fTag = tagService.getTagByName(tag.getName());
        if (fTag != null) {
            result.rejectValue("name", "nameExist", "标签已经存在");
        }

        if (result.hasErrors()) {
            return "admin/tag-input";
        }

        Tag sTag = tagService.updateTag(id, tag);
        if (sTag == null) {
            attributes.addFlashAttribute("errMessage", "更新失败");
        } else {
            attributes.addFlashAttribute("succMessage", "更新成功");
        }
        return "redirect:/admin/tag";
    }

    @GetMapping("/tag/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("succMessage", "删除成功");
        return "redirect:/admin/tag";
    }
}
