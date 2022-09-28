
--DROP TABLE IF EXISTS User CASCADE;

create TABLE Users
(
    id_User serial PRIMARY KEY NOT NULL,
    userName text NOT NULL,
    dateBirth date NOT NULL,
    dateTakeItem date NOT NULL,

);


--DROP TABLE IF EXISTS Book;

create TABLE Book
(
    id_Book serial PRIMARY KEY NOT NULL ,
    title text NOT NULL,
    author text NOT NULL,
    pages integer NOT NULL,
    dateBook date NOT NULL,

    nsbn text NOT NULL,
    subject text NOT NULL,
    status text NOT NULL,
    editorial text NOT NULL,

    userTaken integer ,

    FOREIGN KEY (userTaken) REFERENCES Users

);

create TABLE Letter
(
    id_Letter serial PRIMARY KEY NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    pages integer NOT NULL,
    dateLetter date NOT NULL,

    subject text NOT NULL,
    place text NOT NULL,

    userTaken integer ,

    FOREIGN KEY (userTaken) REFERENCES Users

);

create TABLE Magazine
(
    id_Magazine serial PRIMARY KEY NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    pages integer NOT NULL,
    dateMagazine date NOT NULL,

    subject text NOT NULL,
    volume integer NOT NULL,
    editorial text NOT NULL,

    userTaken integer ,

    FOREIGN KEY (userTaken) REFERENCES Users

);

