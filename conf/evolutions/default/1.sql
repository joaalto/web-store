# Products schema

# --- !Ups

CREATE SEQUENCE PRODUCT_ID_SEQ;
CREATE TABLE PRODUCT (
    ID INTEGER NOT NULL DEFAULT nextval('PRODUCT_ID_SEQ'),
    NAME VARCHAR(64),
    COLOUR VARCHAR(24),
    SIZE VARCHAR(12)
);

# --- !Downs

DROP TABLE PRODUCT;
DROP SEQUENCE PRODUCT_ID_SEQ;
