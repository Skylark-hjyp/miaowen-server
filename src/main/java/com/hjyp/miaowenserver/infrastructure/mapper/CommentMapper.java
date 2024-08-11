package com.hjyp.miaowenserver.infrastructure.mapper;

import com.hjyp.miaowenserver.infrastructure.po.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    // 新增评论
    Integer insertComment(CommentEntity commentEntity);

    // 删除评论
    Integer deleteComment(Integer id);
}
