package com.deng.blog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created by deng on 2020-11-16
 **/
@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogLst = new ArrayList<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogLst() {
        return blogLst;
    }

    public void setBlogLst(List<Blog> blogLst) {
        this.blogLst = blogLst;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
