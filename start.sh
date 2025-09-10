#!/bin/bash

set -e

if [ ! -f .env.prod ]; then
    echo "❌ Error: .env.prod file not found"
    echo "Please create .env.prod file with required environment variables"
    exit 1
fi

export $(grep -v '^#' .env.prod | xargs)

if [ -z "$DB_USERNAME" ]; then
    echo "❌ Error: DB_USERNAME is not set in .env.prod file"
    exit 1
fi

if [ -z "$DB_PASSWORD" ]; then
    echo "❌ Error: DB_PASSWORD is not set in .env.prod file"
    exit 1
fi

if [ -z "$DB_NAME" ]; then
    echo "❌ Error: DB_NAME is not set in .env.prod file"
    exit 1
fi

if [ -z "$BOT_TOKEN" ]; then
    echo "❌ Error: BOT_TOKEN is not set in .env.prod file"
    exit 1
fi

echo "Launching an application using a token: ${BOT_TOKEN:0:10}..."

echo "Launching a Docker Container"
docker-compose -f docker-compose-prod.yml --env-file .env.prod  up -d

echo "✅ Starting completed successfully!"