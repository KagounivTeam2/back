#!/bin/bash

# 이미지 풀
docker pull 313706543169.dkr.ecr.us-east-1.amazonaws.com/kagouniv/back

# 도커 실행
docker-compose up -f docker-compose-prod.yml -d