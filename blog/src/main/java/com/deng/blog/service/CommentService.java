package com.deng.blog.service;

import com.deng.blog.po.Comment;

import java.util.List;

/**
 * created by deng on 2020-12-27
 **/
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
