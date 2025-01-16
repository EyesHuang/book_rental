CREATE TABLE IF NOT exists app_user
(
    id         uuid NOT NULL,
    "password" varchar(255) NULL,
    "role"     varchar(255) NULL,
    username   varchar(255) NULL,
    CONSTRAINT app_user_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT exists book
(
    id     uuid NOT NULL,
    author varchar(255) NULL,
    image  varchar(255) NULL,
    title  varchar(255) NULL,
    CONSTRAINT book_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT exists inventory
(
    id        uuid NOT NULL,
    book_id   uuid NULL,
    user_id   uuid NULL,
    loan_date timestamp without time zone NULL,
    CONSTRAINT borrowed_book_pkey PRIMARY KEY (id),
    CONSTRAINT book_id_key FOREIGN KEY (book_id) REFERENCES book (id),
    CONSTRAINT user_id_key FOREIGN KEY (user_id) REFERENCES app_user (id)
);
