#docker-compose down -v --rmi all --remove-orphans
docker container rm -f config-server-cr registry-service-cr gateway-service-cr auth-center-cr admin-service-cr occupant-service-cr manager-service-cr
docker rmi -f admin-service occupant-service manager-service auth-center gateway-service registry-service config-server