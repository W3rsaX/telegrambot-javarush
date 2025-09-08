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

echo "Starting test-db PostgreSQL"
docker-compose -f docker-compose-test.yml up -d

echo "Building project..."
mvn clean package

echo "Stopping existing containers..."
docker-compose -f docker-compose-test.yml down --rmi all --volumes --remove-orphans
docker-compose -f docker-compose.yml down --rmi all --volumes --remove-orphans

echo "Starting new deployment..."
docker-compose -f docker-compose.yml --env-file .env.prod  up -d

echo "✅ Deployment completed successfully!"