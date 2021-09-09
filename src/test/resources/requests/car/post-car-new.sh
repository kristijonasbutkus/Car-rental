#!/bin/bash
curl -X POST -v -# -H "Content-Type: application/json" -d@./POST-car-new.http http://localhost:8080/car/api/car/new --user admin:pa$$w0rd -v

curl -X PUT -v -# -H "Content-Type: application/json" -d@./PUT-car.http http://localhost:8080/car/api/car/save/11
