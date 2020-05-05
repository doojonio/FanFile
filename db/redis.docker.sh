#!/usr/bin/env bash

docker run -d --name 'fanfile_sessions' -p '6379:6379' redis:latest
