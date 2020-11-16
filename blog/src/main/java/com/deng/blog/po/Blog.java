package com.deng.blog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by deng on 2020-11-16
 **/
@Entity
@Table(name = "tb_blog")
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean isAppreciation;
    private boolean isShare;
    private boolean commentable;
    private boolean isPublish;
    private boolean recomend;
    private Date createTime;
    private Date updateTime;

    @ManyToOne
    private Type type;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tagLst = new ArrayList<>();
    @ManyToOne
    private User user ;
    @OneToMany(mappedBy = "blog")
    private List<Comment> commentLst = new ArrayList<>();

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return isAppreciation;
    }

    public void setAppreciation(boolean appreciation) {
        isAppreciation = appreciation;
    }

    public boolean isShare() {
        return isShare;
    }

    public void setShare(boolean share) {
        isShare = share;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public boolean isPublish() {
        return isPublish;
    }

    public void setPublish(boolean publish) {
        isPublish = publish;
    }

    public boolean isRecomend() {
        return recomend;
    }

    public void setRecomend(boolean recomend) {
        this.recomend = recomend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTagLst() {
        return tagLst;
    }

    public void setTagLst(List<Tag> tagLst) {
        this.tagLst = tagLst;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentLst() {
        return commentLst;
    }

    public void setCommentLst(List<Comment> commentLst) {
        this.commentLst = commentLst;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", isAppreciation=" + isAppreciation +
                ", isShare=" + isShare +
                ", commentable=" + commentable +
                ", isPublish=" + isPublish +
                ", recomend=" + recomend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}