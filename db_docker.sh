#!/usr/bin/env bash

docker run -d -e POSTGRES_DB="fanfile" -e POSTGRES_USER="fanfile" -e POSTGRES_PASSWORD="qwe123" \
-p "1337:5432" -v "/home/dockeroid/postgresql/fanfile_data:/var/lib/postgresql/data" \
-v "init.sql:/docker-entrypoint-initdb.d/init.sql" --name "fanfile_db" postgres:12
