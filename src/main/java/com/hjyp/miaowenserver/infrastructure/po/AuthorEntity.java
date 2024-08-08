package com.hjyp.miaowenserver.infrastructure.po;

import lombok.Data;

@Data
public class AuthorEntity {
    /**
     * 数据库主键ID, 作者ID
     */
    private Integer id;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 作者头像链接
     */
    private String authorAvatar;

    /**
     * 订阅者数量
     */
    private Integer subscriberCount;
}
