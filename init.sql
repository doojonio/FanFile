BEGIN;
    CREATE TABLE users (
        id       BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        username TEXT   NOT NULL UNIQUE,
        password TEXT   NOT NULL,
        salt     TEXT   NOT NULL
    );

    CREATE TABLE snippets (
        id            BIGINT      PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        user_id       BIGINT      REFERENCES users(id),
        title         TEXT        NOT NULL,
        is_hide       BOOLEAN     NOT NULL,
        creating_date TIMESTAMPTZ NOT NULL
    );

    CREATE TABLE languages (
        id   SMALLINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        name TEXT     NOT NULL UNIQUE
    );

    CREATE TABLE files (
        id          BIGINT   PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        snippet_id  BIGINT   REFERENCES snippets(id) ON DELETE CASCADE,
        language_id SMALLINT REFERENCES languages(id) ON DELETE CASCADE,
        title       TEXT     NOT NULL,
        content     TEXT     NOT NULL,
        queue_num   SMALLINT NOT NULL
    );

    CREATE TABLE encrypted_keys (
        snippet_id BIGINT PRIMARY KEY REFERENCES snippets(id) ON DELETE CASCADE,
        key        TEXT   NOT NULL
    );
END;
