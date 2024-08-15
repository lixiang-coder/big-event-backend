package com.xzy.mapper;

import com.xzy.pojo.Article;
import org.apache.ibatis.annotations.*;

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

    /**
     * 获取文章详情
     */
    @Select("select * from article where id = #{id}")
    Article detail(Integer id);

    /**
     * 更新文章
     */
    @Update("update article set content = #{content},title = #{title},cover_img = #{coverImg}," +
            "state = #{state} ,category_id = #{categoryId},update_time = #{updateTime} where id = #{id}")
    void update(Article article);


    /**
     * 删除文章
     */
    @Delete("delete from article where id = #{id}")
    void delete(Integer id);
}
