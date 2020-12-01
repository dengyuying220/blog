package com.deng.blog.service;

import com.deng.blog.NotFoundException;
import com.deng.blog.dao.BlogRepository;
import com.deng.blog.po.Blog;
import com.deng.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * created by deng on 2020-11-30
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return blogRepository.findAll(new Specification<Blog>() {
                                          @Override
                                          public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                                              List<Predicate> predicateList = new ArrayList<>();
                                              if(!("".equals(blog.getTitle()) && blog.getTitle() != null)) {
                                                  predicateList.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                                              }
                                              if (!(blog.getType().getId() != null)) {
                                                  predicateList.add(cb.equal(root.<Type>get("type"), blog.getType().getId()));
                                              }
                                              if (blog.isRecommend()) {
                                                  predicateList.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                                              }
                                              cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

                                              return null;
                                          }
                                      }, pageable);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog t =blogRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该博客");
        }
        BeanUtils.copyProperties(blog, t);
        return blogRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
