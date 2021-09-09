#!/bin/bash

curl -X PUT -v -# -H "Content-Type: application/json" -d@./POST-employee.http http://localhost:8080/employee/api/employee/3 --user admin:pa$$w0rd -v
