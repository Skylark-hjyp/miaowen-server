package com.hjyp.miaowenserver.infrastructure.dao;

import com.hjyp.miaowenserver.infrastructure.po.AuthorEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorDao {
    Integer insert(AuthorEntity author);
    Integer update(AuthorEntity author);
    Integer delete(Integer id);
    AuthorEntity selectById(Integer id);
    List<AuthorEntity> selectAll();
    List<AuthorEntity> selectByName(String name);
    List<AuthorEntity> selectByAuthorName(String authorName);
    List<AuthorEntity> selectAuthorByBatch(List<Integer> authorIds);
}
