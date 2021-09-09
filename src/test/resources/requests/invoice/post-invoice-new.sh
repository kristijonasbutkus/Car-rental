#!/bin/bash
curl -X POST -v -# -H "Content-Type: application/json" -d@./POST-employee-new.http http://localhost:8080/employee/api/employee/new --user admin:pa$$w0rd -v

curl -X PUT -v -# -H "Content-Type: application/json" -d@./POST-employee.http http://localhost:8080/employee/api/employee/5 --user admin:pa$$w0rd -v
