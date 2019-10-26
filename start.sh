#!/bin/bash

mvn clean install
#docker-compose down -v --rmi all --remove-orphans
docker rmi -f admin-service occupant-service manager-service auth-center gateway-service registry-service config-server
docker-compose build
./run.sh
