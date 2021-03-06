package com.deng.blog.web;

import com.deng.blog.po.Comment;
import com.deng.blog.po.User;
import com.deng.blog.service.BlogService;
import com.deng.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * created by deng on 2020-12-27
 **/
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    BlogService blogService;

    @GetMapping("/comment/{blogId}")
    public String comment(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    @PostMapping("/comment")
    public String post(Comment comment, HttpSession session) {
        comment.setBlog(blogService.getBlog(comment.getBlog().getId()));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
//            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(false);
        }
        commentService.saveComment(comment);
        return "redirect:/comment/" + comment.getBlog().getId();
    }
}
