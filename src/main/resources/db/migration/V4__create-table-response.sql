CREATE TABLE response (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    moment TIMESTAMP,
    author_id BIGINT,
    topic_id BIGINT,
    message TEXT,
    solved BOOLEAN,
    FOREIGN KEY (author_id) REFERENCES user(id),
    FOREIGN KEY (topic_id) REFERENCES topic(id)
);