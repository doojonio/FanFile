#!/usr/bin/env bash

docker run -d -v "/home/dockeroid/redis/data/:/data" --name fanfile_redis -p "1488:6379" redis:5
