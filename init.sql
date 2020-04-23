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
        title         TEXT        DEFAULT 'Unnamed',
        is_hide       BOOLEAN     DEFAULT 'f',
        encrypted_key TEXT,
        creating_date TIMESTAMPTZ DEFAULT now()
    );

    CREATE TABLE languages (
        id   SMALLINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        name TEXT     NOT NULL UNIQUE
    );

    CREATE TABLE files (
        id          BIGINT   PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
        snippet_id  BIGINT   REFERENCES snippets(id) ON DELETE CASCADE,
        language_id SMALLINT REFERENCES languages(id) ON DELETE CASCADE,
        title       TEXT     DEFAULT 'Unnamed',
        content     TEXT     DEFAULT 'Empty content',
        queue_num   SMALLINT DEFAULT 0
    );
END;
