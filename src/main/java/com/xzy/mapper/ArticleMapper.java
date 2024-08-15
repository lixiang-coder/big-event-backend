package com.xzy.mapper;

import com.xzy.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    /**
     * 新增文章
     */
    @Insert("insert into article (title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            " values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    /**
     * 文章列表(条件分页)
     */
    List<Article> list(Integer userId, String categoryId, String state);
}
