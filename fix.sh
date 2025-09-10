#!/bin/bash

echo "================================================"
echo "⚠️  ATTENTION! THIS ACTION WILL DELETE ALL DATA IN DATABASE!"
echo "================================================"
echo "The following actions will be performed:"
echo "1. Complete stop of all containers"
echo "2. Deleting all volumes (DB data will be lost!)"
echo "3. Removing all images"
echo "================================================"

read -p "Are you sure you want to continue?? (y/N): " -n 1 -r
echo

if [[ ! $REPLY =~ ^[Yy]$ ]]
then
    echo "❌ Operation canceled"
    exit 1
fi

echo "Starting fix Docker Container"
docker-compose -f docker-compose-prod.yml down --volumes --rmi all --remove-orphans
bash start.sh