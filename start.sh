#!/bin/bash

set -e

if [ -z "$1" ]; then
    echo "❌ Error: No bot token provided"
    echo "Usage: $0 <bot_token>"
    exit 1
fi

TOKEN=$1
echo "Starting deployment with token: ${TOKEN:0:10}..."

echo "Pulling latest changes..."
git pull

echo "Building project..."
mvn clean package

echo "Stopping existing containers..."
docker-compose stop

echo "Starting new deployment..."
export BOT_TOKEN=$TOKEN
docker-compose up --build -d

echo "✅ Deployment completed successfully!"