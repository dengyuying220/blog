package com.deng.blog.service;

import com.deng.blog.dao.CommentRepository;
import com.deng.blog.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by deng on 2020-12-27
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> tmpReplys = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComments(comments);
    }

    /*
    * 循环顶级评论节点
    * */
    public List<Comment> eachComments(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment sComment : comments) {
            Comment tComment = new Comment();
            BeanUtils.copyProperties(sComment, tComment);
            commentsView.add(tComment);
        }
//        合并顶级评论的子评论
        combineChildren(commentsView);
        return commentsView;
    }

    public void combineChildren(List<Comment> commentsView) {
        for (Comment comment : commentsView) {
            List<Comment> replys = new ArrayList<>();
            replys = comment.getReplyComments();
            for (Comment reply : replys) {
//                递归查询子节点,获取全部子孙节点
                recursively(reply);
            }
            comment.setReplyComments(tmpReplys);
            tmpReplys = new ArrayList<>();
        }
    }

    /*
    * 递归查询评论的子评论,获取全部子孙评论
    * */
    public void  recursively(Comment comment) {
        tmpReplys.add(comment);
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tmpReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.findById(parentCommentId).get());
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
