-- 생성자 Oracle SQL Developer Data Modeler 21.4.2.059.0838
--   위치:        2023-12-13 12:41:19 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE


CREATE TABLE admin (
    admin_num NUMBER(20) NOT NULL,
    admin_id  VARCHAR2(40),
    admin_pw  VARCHAR2(40)
);

ALTER TABLE admin ADD CONSTRAINT admin_pk PRIMARY KEY ( admin_num );

CREATE TABLE album (
    album_id        NUMBER(20) NOT NULL,
    album_name      VARCHAR2(300) NOT NULL,
    release_date    VARCHAR2(40),
    album_genre     VARCHAR2(100),
    album_publisher VARCHAR2(100),
    album_agency    VARCHAR2(100),
    album_thumbnail VARCHAR2(200)
);

ALTER TABLE album ADD CONSTRAINT album_pk PRIMARY KEY ( album_id );

CREATE TABLE album_artist (
    album_id  NUMBER(20) NOT NULL,
    artist_id NUMBER(20) NOT NULL
);

CREATE TABLE announcement (
    ann_num     NUMBER(20) NOT NULL,
    ann_title   VARCHAR2(100),
    ann_date    DATE,
    ann_content VARCHAR2(2000),
    admin_num   NUMBER(20) NOT NULL
);

ALTER TABLE announcement ADD CONSTRAINT announcement_pk PRIMARY KEY ( ann_num );

CREATE TABLE artist (
    artist_id        NUMBER(20) NOT NULL,
    artist_name      VARCHAR2(200) NOT NULL,
    artist_date      VARCHAR2(40),
    artist_type      VARCHAR2(20),
    artist_thumbnail VARCHAR2(200),
    artist_agency    VARCHAR2(100),
    artist_nation    VARCHAR2(100)
);

ALTER TABLE artist ADD CONSTRAINT artist_pk PRIMARY KEY ( artist_id );

CREATE TABLE member (
    member_name VARCHAR2(200),
    artist_id   NUMBER(20) NOT NULL
);

CREATE TABLE notacceptuser (
    user_num   NUMBER(20),
    admin_num  NUMBER(20),
    start_date DATE,
    end_date   DATE
);

create table banedLog_table(
user_num number(20)
);

CREATE TABLE review (
    review_num     NUMBER(20) NOT NULL,
    user_num       NUMBER(20) NOT NULL,
    type_id        NUMBER(20) NOT NULL,
    review_point   NUMBER(2, 1) NOT NULL,
    review_content VARCHAR2(1500),
    review_date    DATE,
    suggestion     NUMBER(20),
    flag           VARCHAR2(10)
);

ALTER TABLE review ADD CONSTRAINT review_pk PRIMARY KEY ( review_num );

CREATE TABLE review_report (
    report_num      NUMBER(20) NOT NULL,
    review_num      NUMBER(20),
    report_user_num NUMBER(20),
    user_num        NUMBER(20),
    report_date     DATE
);

ALTER TABLE review_report ADD CONSTRAINT review_report_pk PRIMARY KEY ( report_num );

CREATE TABLE song (
    song_id        NUMBER(20) NOT NULL,
    song_name      VARCHAR2(300) NOT NULL,
    song_genre     VARCHAR2(100),
    release_date   VARCHAR2(40),
    lyrics         VARCHAR2(200),
    views          NUMBER(20),
    song_thumbnail VARCHAR2(200),
    album_id       NUMBER(20) NOT NULL,
    song_nation    VARCHAR2(12)
);

ALTER TABLE song ADD CONSTRAINT song_pk PRIMARY KEY ( song_id );

CREATE TABLE song_artist (
    song_id   NUMBER(20) NOT NULL,
    artist_id NUMBER(20) NOT NULL
);

CREATE TABLE thumbup_artist (
    user_num     NUMBER(20) NOT NULL,
    artist_id    NUMBER(20),
    thumbup_date DATE
);

CREATE TABLE thumbup_consert (
    user_num     NUMBER(20) NOT NULL,
    consert_num  NUMBER(20),
    thumbup_date DATE
);

CREATE TABLE thumbup_song (
    user_num     NUMBER(20) NOT NULL,
    song_id      NUMBER(20) NOT NULL,
    thumbup_date DATE
);

CREATE TABLE user_info (
    user_num        NUMBER(20) NOT NULL,
    user_id         VARCHAR2(100),
    user_pw         VARCHAR2(100),
    user_email      VARCHAR2(100),
    user_tel        VARCHAR2(100),
    user_address    VARCHAR2(100),
    user_name       VARCHAR2(50),
    user_birthday   DATE,
    user_registdate DATE,
    user_logintype  VARCHAR2(30) DEFAULT 'Mua 회원'
);
ALTER TABLE user_info ADD CONSTRAINT user_info_pk PRIMARY KEY ( user_num );

CREATE TABLE viewed_song (
    user_num    NUMBER(20) NOT NULL,
    song_id     NUMBER(20) NOT NULL,
    viewed_date DATE
);

ALTER TABLE album_artist
    ADD CONSTRAINT album_artist_album_fk FOREIGN KEY ( album_id )
        REFERENCES album ( album_id );

ALTER TABLE album_artist
    ADD CONSTRAINT album_artist_artist_fk FOREIGN KEY ( artist_id )
        REFERENCES artist ( artist_id );

ALTER TABLE announcement
    ADD CONSTRAINT announcement_admin_fk FOREIGN KEY ( admin_num )
        REFERENCES admin ( admin_num );

ALTER TABLE member
    ADD CONSTRAINT member_artist_fk FOREIGN KEY ( artist_id )
        REFERENCES artist ( artist_id );

ALTER TABLE review
    ADD CONSTRAINT review_user_info_fk FOREIGN KEY ( user_num )
        REFERENCES user_info ( user_num );

ALTER TABLE song
    ADD CONSTRAINT song_album_fk FOREIGN KEY ( album_id )
        REFERENCES album ( album_id );

ALTER TABLE song_artist
    ADD CONSTRAINT song_artist_artist_fk FOREIGN KEY ( artist_id )
        REFERENCES artist ( artist_id );

ALTER TABLE song_artist
    ADD CONSTRAINT song_artist_song_fk FOREIGN KEY ( song_id )
        REFERENCES song ( song_id );

ALTER TABLE thumbup_artist
    ADD CONSTRAINT thumbup_artist_user_info_fk FOREIGN KEY ( user_num )
        REFERENCES user_info ( user_num );

ALTER TABLE thumbup_consert
    ADD CONSTRAINT thumbup_consert_user_info_fk FOREIGN KEY ( user_num )
        REFERENCES user_info ( user_num );

ALTER TABLE thumbup_song
    ADD CONSTRAINT thumbup_user_info_fk FOREIGN KEY ( user_num )
        REFERENCES user_info ( user_num );

ALTER TABLE viewed_song
    ADD CONSTRAINT viewed_song_song_fk FOREIGN KEY ( song_id )
        REFERENCES song ( song_id );

ALTER TABLE viewed_song
    ADD CONSTRAINT viewed_song_user_info_fk FOREIGN KEY ( user_num )
        REFERENCES user_info ( user_num );


drop sequence song_seq;
drop sequence user_seq;
drop sequence artist_seq;
drop sequence album_seq;
drop sequence review_seq;
drop sequence report_seq;

create sequence song_seq start with 1 increment by 1 minvalue 1;
create sequence artist_seq start with 1 increment by 1 minvalue 1;
create sequence album_seq start with 1 increment by 1 minvalue 1;
create sequence user_seq start with 1 increment by 1 minvalue 1;
create sequence review_seq start with 1 increment by 1 minvalue 1;
create sequence report_seq start with 1 increment by 1 minvalue 1;

CREATE OR REPLACE TRIGGER log_table
AFTER INSERT ON notacceptuser
FOR EACH ROW
DECLARE
BEGIN
    INSERT INTO banedLog_table (user_num) VALUES (:NEW.user_num);
END;
/