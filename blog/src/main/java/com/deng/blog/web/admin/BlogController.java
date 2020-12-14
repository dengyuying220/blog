package com.deng.blog.web.admin;

import com.deng.blog.po.Blog;
import com.deng.blog.po.Type;
import com.deng.blog.po.User;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * created by deng on 2020-11-24
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blog-input";
    private static final String LIST = "admin/blog";
    private static final String REDIRECT_LIST = "redirect:/admin/blog";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blog")
    public String list(@PageableDefault(sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable,
                       Blog blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        model.addAttribute("types", typeService.listType());
        return LIST;
    }

    @PostMapping("/blog/search")
    public String search(@PageableDefault(sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable,
                       Blog blog, Model model) {
        if (blog.getTypeId()!= null && !blog.getTypeId().isEmpty()) {
            blog.setType(typeService.getType(Long.valueOf(blog.getTypeId())));
        }
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blog :: blogList";
    }

    @GetMapping("/blog/input")
    public String input(Model model) {
        Blog blog = new Blog();
        blog.setType(new Type());
        model.addAttribute("blog", blog);
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        return INPUT;
    }

    @GetMapping("/blog/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        return INPUT;
    }

    @PostMapping("/blog")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTagLst(tagService.listTag(blog.getTagIds()));
        Blog blog1 = blogService.saveBlog(blog);
        if (blog1 == null) {
            attributes.addFlashAttribute("errMessage", "操作失败");
        } else {
            attributes.addFlashAttribute("succMessage", "操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blog/{id}/delete")
    public  String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("succMessage", "删除成功");
        return REDIRECT_LIST;
    }
}
