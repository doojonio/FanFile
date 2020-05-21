CREATE TABLE users(
    id       BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    login    TEXT   NOT NULL UNIQUE,
    password TEXT   NOT NULL
);
CREATE TABLE snippets(
    id          BIGINT      PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id     BIGINT      NOT NULL REFERENCES users(id) DEFAULT 1,
    public      BOOLEAN     NOT NULL DEFAULT 'true',
    title       TEXT        NOT NULL DEFAULT 'Unnamed',
    h_link      TEXT        UNIQUE,
    create_time TIMESTAMP   NOT NULL DEFAULT NOW()
);
CREATE TABLE languages(
    id   SMALLINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name TEXT     NOT NULL UNIQUE
);
CREATE TABLE files(
    id          BIGINT    PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    snippet_id  BIGINT    NOT NULL REFERENCES snippets(id),
    language_id SMALLINT  NOT NULL REFERENCES languages(id) DEFAULT 1,
    title       TEXT      NOT NULL DEFAULT 'Unnamed',
    content     TEXT      NOT NULL,
    change_time TIMESTAMP NOT NULL DEFAULT NOW()
);
INSERT INTO users(login, password) VALUES
    ('Anonymous', 'perl->kill_java');
INSERT INTO languages(name) VALUES
    ('Text'), ('Perl'), ('Raku'), ('JavaScript'), ('C++');
