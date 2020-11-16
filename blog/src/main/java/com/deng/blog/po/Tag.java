package com.deng.blog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created by deng on 2020-11-16
 **/

@Entity
@Table(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tagLst")
    private List<Blog> blogLst = new ArrayList<>();

    public Tag() {
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
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
