#!/bin/bash

# 이미지 pull
docker pull "${ECR_REGISTRY}/${ECR_REPOSITORY}:${IMAGE_TAG}"

docker compose -f /home/ec2-user/back/docker-compose-prod.yml up -d