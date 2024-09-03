CREATE TABLE IF NOT EXISTS article (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content VARCHAR(255)
);


-- comment 테이블 정의 (article_id 컬럼 추가)
