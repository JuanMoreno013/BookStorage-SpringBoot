
--DROP TABLE IF EXISTS Users CASCADE;

CREATE TABLE IF NOT EXISTS Users
(
    id serial ,
    user_name text NOT NULL,
    date_birth date NOT NULL,
    date_take_item date NOT NULL,

    CONSTRAINT "pk_idName_User" PRIMARY KEY (id)
);


--DROP TABLE IF EXISTS Books;

CREATE TABLE IF NOT EXISTS Books
(
    id serial ,
    title text NOT NULL,
    author text NOT NULL,
    pages integer NOT NULL,
    date_write date NOT NULL,

    nsbn text NOT NULL,
    subject text NOT NULL,
    status text NOT NULL,
    editorial text NOT NULL,

    user_taken integer ,


    CONSTRAINT "pk_id_Book" PRIMARY KEY (id),

    CONSTRAINT "fk_user_Book" FOREIGN KEY (user_taken)
        REFERENCES Users (id) MATCH SIMPLE

);

--DROP TABLE IF EXISTS Letters;

CREATE TABLE IF NOT EXISTS Letters
(
    id serial NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    pages integer NOT NULL,
    date_write date NOT NULL,

    subject text NOT NULL,
    place text NOT NULL,

    user_taken integer ,


    CONSTRAINT "pk_id_Letter" PRIMARY KEY (id),

    CONSTRAINT "fk_user_Letter" FOREIGN KEY (user_taken)
       REFERENCES Users (id) MATCH SIMPLE

);

--DROP TABLE IF EXISTS Magazines;

CREATE TABLE IF NOT EXISTS Magazines
(
    id serial NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    pages integer NOT NULL,
    date_write date NOT NULL,

    editorial text NOT NULL,
    volume integer NOT NULL,
    subject text NOT NULL,

    user_taken integer ,


    CONSTRAINT "pk_id_Magazine" PRIMARY KEY (id),

    CONSTRAINT "fk_user_Magazine" FOREIGN KEY (user_taken)
        REFERENCES Users (id) MATCH SIMPLE

);

