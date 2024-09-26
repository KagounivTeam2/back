#!/bin/bash

# 이미지 풀
docker pull 313706543169.dkr.ecr.us-east-1.amazonaws.com/kagouniv/back:prod

cd /home/ec2-user/back

# 도커 실행
docker-compose -f docker-compose-prod.yml --env-file .env up -d