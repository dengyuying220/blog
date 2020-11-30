package com.deng.blog.dao;

import com.deng.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by deng on 2020-11-30
 **/
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String name);
}
