#!/bin/bash

eurka_uri_env="http://172.17.0.3:8761/eureka"
config_uri="http://172.17.0.2:8888"
db_uri="jdbc:postgresql://172.25.0.2:5432/housingdb"

echo "ENV variables:"
echo "EUREKA URI: $eurka_uri_env"
echo "CONFIG URI: $config_uri"
echo "DB URI: $db_uri"

echo "Database starting..."
docker-compose up -d database-service
sleep 15
echo "Config Server starting..."
docker run -d -it --name config-server-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri config-server
sleep 15
echo "Registry Service starting..."
docker run -d -it --name registry-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri registry-service
sleep 15
echo "Gateway Service starting..."
docker run -d -it --name gateway-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri gateway-service
sleep 15
echo "Other Services starting..."
docker run -d -it --name auth-center-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri DB=$db_uri auth-center
docker run -d -it --name admin-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri -e DB=$db_uri admin-service
docker run -d -it --name occupant-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri -e DB=$db_uri occupant-service
docker run -d -it --name manager-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri -e DB=$db_uri manager-service
echo "DONE"

