drop table POST_COMMENT;
CREATE TABLE post_comment
(
    id         BIGINT       NOT NULL,
    parent_id  BIGINT       NULL,
    review     VARCHAR(255) NULL,
    status     VARCHAR(20)          NULL,
    created_on datetime     NULL,
    votes      INT          NOT NULL,
    CONSTRAINT pk_post_comment PRIMARY KEY (id)
);

ALTER TABLE post_comment
    ADD CONSTRAINT FK_POST_COMMENT_ON_PARENT FOREIGN KEY (parent_id) REFERENCES post_comment (id);