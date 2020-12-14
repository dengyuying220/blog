package com.deng.blog.service;

import com.deng.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * created by deng on 2020-11-30
 **/
public interface BlogService {

    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, Blog blog);

    Page<Blog> listBlog(Pageable pageable);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
