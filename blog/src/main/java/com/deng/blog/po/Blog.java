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

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    private String description;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation; //打赏
    private boolean share;    //转载
    private boolean commentable;    //评论
    private boolean publish;  //发布
    private boolean recommend;  //推荐
    private Date createTime;
    private Date updateTime;

    @Transient
    private String tagIds;
    @Transient
    private String typeId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void init() {
        this.tagIds = tagsToIds(this.getTagLst());
    }
//    遍历集合取对象的属性，拼接一个字符串
    private  String tagsToIds(List<Tag> tagLst) {
        if (!tagLst.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tagLst) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
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
                ", appreciation=" + appreciation +
                ", share=" + share +
                ", commentable=" + commentable +
                ", publish=" + publish +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tagIds='" + tagIds + '\'' +
                ", type=" + type +
                ", tagLst=" + tagLst +
                ", user=" + user +
                ", commentLst=" + commentLst +
                '}';
    }
}
