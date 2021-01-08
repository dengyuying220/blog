package com.deng.blog.dao;

import com.deng.blog.po.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * created by deng on 2020-11-30
 **/
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1 or b.description like ?1")
    List<Blog> findByKeyWD(Pageable pageable, String keyWD);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views + 1 where b.id = ?1")
    int updateViews(Long id);

    @Query(value = "select date_format(b.update_time, '%Y') as year from tb_blog b GROUP by year ORDER by year DESC",
        nativeQuery = true)
    List<String> findGroupYear();

    @Query(value = "select * from tb_blog b where  date_format(b.update_time, '%Y') = ?1 ORDER by b.update_time DESC",
            nativeQuery = true)
    List<Blog> findBlogByGroupYear(String year);

}
