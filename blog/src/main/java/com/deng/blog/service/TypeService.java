package com.deng.blog.service;

import com.deng.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * created by deng on 2020-11-25
 **/
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listType(Integer size);

    Type updateType(Long id, Type type);

    void deleteType(Long id);
}
