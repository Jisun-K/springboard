-- Post 테이블 생성
CREATE TABLE post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 게시글 ID
    title VARCHAR(200) NOT NULL,           -- 제목
    content TEXT,                          -- 내용
    writer VARCHAR(50),                    -- 작성자 이름
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 작성일시
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 수정일시
    user_id BIGINT                         -- 작성자 (User FK)
);

-- 추가적으로, 외래 키 제약조건을 설정하려면 아래와 같은 SQL을 추가할 수 있습니다
-- (예시: user_id는 실제로 'users' 테이블에 존재하는 ID여야 함)
-- ALTER TABLE post ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id);
