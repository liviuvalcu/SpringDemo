drop table IF EXISTS POST_COMMENT;
CREATE TABLE post_comment
(
    id         BIGINT       NOT NULL,
    parent_id  BIGINT       NULL,
    review     VARCHAR(255) NULL,
    status     VARCHAR(20)          NULL,
    creation_date datetime     NULL,
    votes      INT          NOT NULL,
    CONSTRAINT pk_post_comment PRIMARY KEY (id)
);

ALTER TABLE post_comment
    ADD CONSTRAINT FK_POST_COMMENT_ON_PARENT FOREIGN KEY (parent_id) REFERENCES post_comment (id);

drop table IF EXISTS authorities;
drop table IF EXISTS users;

create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);