package com.deng.blog.dao;

import com.deng.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by deng on 2020-11-25
 **/
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);
}
