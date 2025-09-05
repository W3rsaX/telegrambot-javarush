#!/bin/bash

set -e

if [ ! -f .env.prod ]; then
    echo "❌ Error: .env file not found"
    echo "Please create .env file with required environment variables"
    exit 1
fi

export $(grep -v '^#' .env.prod | xargs)

if [ -z "$BOT_TOKEN" ]; then
    echo "❌ Error: BOT_TOKEN is not set in .env file"
    exit 1
fi

if [ -z "$DB_USERNAME" ]; then
    echo "❌ Error: DB_USERNAME is not set in .env file"
    exit 1
fi

if [ -z "$DB_PASSWORD" ]; then
    echo "❌ Error: DB_PASSWORD is not set in .env file"
    exit 1
fi

if [ -z "$DB_URL" ]; then
    echo "❌ Error: DB_URL is not set in .env file"
    exit 1
fi

if [ -z "$DB_NAME" ]; then
    echo "❌ Error: DB_NAME is not set in .env file"
    exit 1
fi

echo "Starting deployment with token: ${BOT_TOKEN:0:10}..."



echo "Building project..."
mvn clean package

echo "Stopping existing containers..."
docker-compose stop

echo "Starting new deployment..."
docker-compose up --build -d

echo "✅ Deployment completed successfully!"