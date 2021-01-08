package com.deng.blog.service;

import com.deng.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * created by deng on 2020-11-30
 **/
public interface BlogService {

    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);

    Blog getMarkdownToHtmlBlog(Long id);

    int updateViews(Long id);

//    条件查询
    Page<Blog> listBlog(Pageable pageable, Blog blog);

//    关键字查询
    Page<Blog> listBlog(Pageable pageable, String key);

    Page<Blog> listBlogByTagId(Pageable pageable, Long tagId);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();

    Page<Blog> listBlog(Pageable pageable);

    List<Blog> listRecommendBlog(Integer size);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
