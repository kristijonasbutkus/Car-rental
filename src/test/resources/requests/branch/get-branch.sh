#!/bin/bash

curl http://localhost:8080/car/api/car-list --user admin:pa$$w0rd -v
curl http://localhost:8080/car/api/car/delete/1 --user admin:pa$$w0rd -v
