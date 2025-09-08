#!/bin/bash

echo "Starting container"
docker-compose -f docker-compose.yml --env-file .env.prod  up -d
echo "✅ Starting completed successfully!"
