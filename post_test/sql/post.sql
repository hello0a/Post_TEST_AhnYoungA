-- Active: 1770210803187@@127.0.0.1@3306@aloha
DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
    `no` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'PK',   -- 고유키 (자동 증가)
    `id` VARCHAR(64) NOT NULL COMMENT 'UK',     -- 외래키
    `title` VARCHAR(100) NOT NULL COMMENT '제목',
    `writer` VARCHAR(100) NOT NULL COMMENT '작성자',
    `content` TEXT NOT NULL COMMENT '내용',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT '게시판';

INSERT INTO post (id, title, writer, content)
SELECT 
  UUID(),
  CONCAT('게시판 샘플 데이터 ', t.num),
  CONCAT('작성자 ', t.num),
  CONCAT('내용 샘플 데이터 ', t.num)
FROM (
  SELECT @row := @row + 1 AS num
  FROM information_schema.tables, (SELECT @row := 0) r
  LIMIT 100
) t;
