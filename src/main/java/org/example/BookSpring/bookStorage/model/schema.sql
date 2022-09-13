
DROP TABLE IF EXISTS public."User" CASCADE;

CREATE TABLE IF NOT EXISTS public."User"
(
    id_User serial NOT NULL,
    userName text NOT NULL,
    dateBirth date NOT NULL,
    dateTakeItem date NOT NULL,

    CONSTRAINT "pk_idName_User" PRIMARY KEY (id_User)
);


DROP TABLE IF EXISTS public."Book";

CREATE TABLE IF NOT EXISTS public."Book"
(
    id_Book serial NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    pages integer NOT NULL,
    dateBook date NOT NULL,

    nsbn text NOT NULL,
    subject text NOT NULL,
    status text NOT NULL,
    editorial text NOT NULL,

    userTaken integer ,


    CONSTRAINT "pk_id_Book" PRIMARY KEY (id_Book),

    CONSTRAINT "fk_user_Book" FOREIGN KEY (userTaken)
        REFERENCES "public"."User" (id_User) MATCH SIMPLE


);

DROP TABLE IF EXISTS public."Letter";

CREATE TABLE IF NOT EXISTS public."Letter"
(
    id_Letter serial NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    pages integer NOT NULL,
    dateLetter date NOT NULL,

    subject text NOT NULL,
    place text NOT NULL,

    userTaken integer ,


    CONSTRAINT "pk_id_Letter" PRIMARY KEY (id_Letter),

    CONSTRAINT "fk_user_Letter" FOREIGN KEY (userTaken)
        REFERENCES "public"."User" (id_User) MATCH SIMPLE

);

DROP TABLE IF EXISTS public."Magazine";

CREATE TABLE IF NOT EXISTS public."Magazine"
(
    id_Magazine serial NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    pages integer NOT NULL,
    dateMagazine date NOT NULL,

    editorial text NOT NULL,
    volume integer NOT NULL,
    subject text NOT NULL,

    userTaken integer ,


    CONSTRAINT "pk_id_Magazine" PRIMARY KEY (id_Magazine),

    CONSTRAINT "fk_user_Magazine" FOREIGN KEY (userTaken)
        REFERENCES "public"."User" (id_User) MATCH SIMPLE

);

