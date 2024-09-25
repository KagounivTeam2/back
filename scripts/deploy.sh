#!/bin/bash

docker pull 313706543169.dkr.ecr.us-east-1.amazonaws.com/kagouniv/back:latest

docker compose up -f docker-compose-prod.yml up -d