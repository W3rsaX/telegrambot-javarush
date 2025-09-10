#!/bin/bash

echo "Stopping container"
docker-compose -f docker-compose-prod.yml stop
echo "✅ Stopping completed successfully!"