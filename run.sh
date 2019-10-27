#!/bin/bash

#YOU NEED CHECK ADDRESSES BY DOCKER INSPECT
eurka_uri_env="http://172.17.0.4:8761/eureka"
config_uri="http://172.17.0.3:8888"
db_uri="jdbc:postgresql://172.17.0.2:5432/housingdb"
auth_center_uri="http://172.17.0.6:8085"
admin_service_uri="http://172.17.0.7:8092"
occupant_service_uri="http://172.17.0.8:8091"
manager_service_uri="http://172.17.0.9:8090"

echo "ENV variables:"
echo "EUREKA URI: $eurka_uri_env"
echo "CONFIG URI: $config_uri"
echo "DB URI: $db_uri"

echo "Database starting..."
docker run -d -it  --name postgres-database -e POSTGRES_USER=dmcs -e POSTGRES_PASSWORD=docker -e POSTGRES_DB=housingdb postgres
sleep 15
echo "Config Server starting..."
docker run -d -it --name config-server-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri config-server
sleep 15
echo "Registry Service starting..."
docker run -d -it --name registry-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri registry-service
sleep 15
echo "Gateway Service starting..."
docker run -d -it --name gateway-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri -e AUTH_CENTER_URL=$auth_center_uri -e MANGER_SERVICE_URL=$manager_service_uri -e OCCUPANT_SERVICE_URL=$occupant_service_uri -e ADMIN_SERVICE_URL=$admin_service_uri gateway-service
sleep 15
echo "Other Services starting..."
docker run -d -it --name auth-center-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri -e DB=$db_uri auth-center
docker run -d -it --name admin-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri -e DB=$db_uri admin-service
docker run -d -it --name occupant-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri -e DB=$db_uri occupant-service
docker run -d -it --name manager-service-cr -e EUREKA_URI=$eurka_uri_env -e URI=$config_uri -e DB=$db_uri manager-service
echo "DONE"

