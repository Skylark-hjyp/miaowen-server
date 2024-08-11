-- 创建数据库miaowen，使用utf8mb4字符集，支持存储表情
# CREATE DATABASE `miaowen` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用miaowen数据库
USE `miaowen`;

-- 创建author表
DROP TABLE IF EXISTS `author_table`;
CREATE TABLE `author_table` (
        `id` INT AUTO_INCREMENT PRIMARY KEY,
        `author_name` VARCHAR(255) DEFAULT NULL,
        `author_avatar` VARCHAR(255) DEFAULT NULL,
        `subscriber_count` INT DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `author_table`(id, author_name, author_avatar, subscriber_count) VALUES (1, '阿里云开发者', 'https://ts4.cn.mm.bing.net/th?id=ODLS.ec018776-f8de-4e96-b7c3-f27332c63d0a&w=18&h=18&o=6&pid=AdsPlus', 13);
INSERT INTO `author_table`(id, author_name, author_avatar, subscriber_count) VALUES (2, '架构文摘', 'http://mmbiz.qpic.cn/mmbiz_png/XnOam47fJujnrjVvXpgPKJA6ib3qlPr6iccLUn87vtEibetwxjdxuLf8gr1cP15nVUcHygFAWvpyq6icfFwbJxoeTQ/300?wx_fmt=png&wxfrom=19', 245);
INSERT INTO `author_table`(id, author_name, author_avatar, subscriber_count) VALUES (3, '京东技术', 'https://ts1.cn.mm.bing.net/th?id=ODLS.59611976-29c7-4d14-8200-407171d637fb&w=32&h=32&qlt=91&pcl=fffffa&o=6&pid=1.2', 57);
INSERT INTO `author_table`(id, author_name, author_avatar, subscriber_count) VALUES (4, '夕小瑶科技说', 'http://mmbiz.qpic.cn/mmbiz_png/5fknb41ib9qGErgbAAhiaht2FBwPtIicZ71VGcCM5KYywFmHzAV96ppvJ8ntuiaUx4icePOwoxz5mhPwmup3yibyibqQQ/300?wx_fmt=png&wxfrom=19', 3);



-- 以下是一些可能的索引，根据实际需要创建
-- 创建索引，优化作者名称搜索
-- CREATE INDEX idx_author_name ON `author` (`authorName`);


DROP TABLE IF EXISTS `article_table`;
-- 创建article表
CREATE TABLE `article_table` (
       `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
       `article_id` VARCHAR(255) NOT NULL COMMENT '文章ID',
       `article_title` VARCHAR(255) NOT NULL COMMENT '文章名称',
       `article_author_id` INT COMMENT '文章作者ID',
       `article_author_name` VARCHAR(255) NOT NULL COMMENT '文章作者名称',
       `article_type` INT DEFAULT NULL COMMENT '文章类型 0代表外部文章分享 1代表本网站内部文章',
       `article_link` VARCHAR(512) NOT NULL COMMENT '文章链接',
       `article_publish_time` DATETIME DEFAULT NOW() COMMENT '文章发布时间',
       `article_receive_time` DATETIME DEFAULT NOW() COMMENT '文章收录时间',
       `article_tag` VARCHAR(255) DEFAULT NULL COMMENT '文章标签，使用|分隔',
       `article_browse_count` INT DEFAULT 0 COMMENT '文章浏览量',
       `article_comment_count` INT DEFAULT 0 COMMENT '文章评论量',
       `article_like_count` INT DEFAULT 0 COMMENT '文章点赞量',
       `article_dislike_count` INT DEFAULT 0 COMMENT '文章点踩量',
       `article_mark_count` INT DEFAULT 0 COMMENT '文章收藏量',
       `article_share_count` INT DEFAULT 0 COMMENT '文章转发量',
       FOREIGN KEY (article_author_id) REFERENCES author_table(id)
       ON DELETE SET NULL
       ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `article_table`(article_id, article_title, article_author_id, article_author_name, article_type, article_link, article_tag) values (
    '7227316551681089536', '一文详解容器技术简介和基本原理', 1, '阿里云开发者', 0, 'https://mp.weixin.qq.com/s/qF1IQjy-A4xijW9YRf8Rvg', '容器|云原生'
);
INSERT INTO `article_table`(article_id, article_title, article_author_id, article_author_name, article_type, article_link, article_tag) values (
    '7227317975689240576', '阿里大佬写的Controller太优雅了！', 2, '架构文摘', 0, 'https://mp.weixin.qq.com/s/vxtwj5caw6pjDlx1SQQ-fQ', '后端|SpringBoot'
);
INSERT INTO `article_table`(article_id, article_title, article_author_id, article_author_name, article_type, article_link, article_tag) values (
    '7227318775387824128', '从C端到B端：我的前端技术进阶之路', 3, '京东技术', 0, 'https://mp.weixin.qq.com/s/b8qB6mml_KufnLX1ijxYbg', '前端|架构'
);
INSERT INTO `article_table`(article_id, article_title, article_author_id, article_author_name, article_type, article_link, article_tag) values (
    '7227319175222435840', '程序员窃喜！卡了大模型脖子的Json输出，OpenAI终于做到了100%正确', 4, '夕小瑶科技说', 0, 'https://mp.weixin.qq.com/s/E0z9wTy2G6NWd7fB8hikvw', 'AI'
);

-- 以下是一些可能的索引，根据实际需要创建
-- 创建索引，优化文章标题搜索
-- CREATE INDEX idx_article_title ON `article` (`articleTitle`);

-- 创建索引，优化文章作者名称搜索
-- CREATE INDEX idx_article_author_name ON `article` (`articleAuthorName`);

-- 创建索引，优化文章标签搜索
-- CREATE INDEX idx_article_tag ON `article` (`articleTag`);

-- 创建browse_record表
DROP TABLE IF EXISTS `browse_record_table`;
CREATE TABLE `browse_record_table` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户ID',
    `article_id` VARCHAR(255) NOT NULL COMMENT '文章ID',
    `is_deleted` TINYINT(1) DEFAULT TRUE COMMENT '是否删除，默认不删除',
    `create_time` DATETIME DEFAULT NOW() COMMENT '操作时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建评论表
DROP TABLE IF EXISTS `comment_table`;
CREATE TABLE `comment_table` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户ID',
    `article_id` VARCHAR(255) NOT NULL COMMENT '文章ID',
    `parent_comment_id` INT DEFAULT NULL COMMENT '父评论ID',
    `comment_content` VARCHAR(255) DEFAULT NULL COMMENT '评论内容',
    `reply_user_id` VARCHAR(255) DEFAULT NULL COMMENT '子评论分为两种，一种是回复父评论，此时回复用户为null；一种是回复其他子评论，此时回复用户ID不为空.',
    `is_deleted` TINYINT(1) DEFAULT TRUE COMMENT '是否删除，默认不删除',
    `create_time` DATETIME DEFAULT NOW() COMMENT '操作时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建dislike_record表
DROP TABLE IF EXISTS `dislike_record_table`;
CREATE TABLE `dislike_record_table` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户ID',
    `article_id` VARCHAR(255) NOT NULL COMMENT '文章ID',
    `is_deleted` TINYINT(1) DEFAULT TRUE COMMENT '是否删除，默认不删除',
    `create_time` DATETIME DEFAULT NOW() COMMENT '操作时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建like_record表
DROP TABLE IF EXISTS `like_record_table`;
CREATE TABLE `like_record_table` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户ID',
    `article_id` VARCHAR(255) NOT NULL COMMENT '文章ID',
    `is_deleted` TINYINT(1) DEFAULT TRUE COMMENT '是否删除，默认不删除',
    `create_time` DATETIME DEFAULT NOW() COMMENT '操作时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建mark_record表
DROP TABLE IF EXISTS `mark_record_table`;
CREATE TABLE `mark_record_table` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户ID',
    `article_id` VARCHAR(255) NOT NULL COMMENT '文章ID',
    `is_deleted` TINYINT(1) DEFAULT TRUE COMMENT '是否删除，默认不删除',
    `create_time` DATETIME DEFAULT NOW() COMMENT '操作时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建share_record表
DROP TABLE IF EXISTS `share_record_table`;
CREATE TABLE `share_record_table` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(255) NOT NULL COMMENT '用户ID',
    `article_id` VARCHAR(255) NOT NULL COMMENT '文章ID',
    `is_deleted` TINYINT(1) DEFAULT TRUE COMMENT '是否删除，默认不删除',
    `create_time` DATETIME DEFAULT NOW() COMMENT '操作时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;