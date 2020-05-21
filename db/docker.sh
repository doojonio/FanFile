#!/usr/bin/env bash

docker run -d -p '5432:5432' -e POSTGRES_USER='fanfile' -e POSTGRES_PASSWORD='qwe123' -e POSTGRES_DATABASE='fanfile' \
    --name fanfile postgres:11
