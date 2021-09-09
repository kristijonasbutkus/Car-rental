#!/bin/bash

#curl -v -# -H "Content-Type: application/json" -d@./POST-branch.http http://localhost:8080/branch/new
curl -v -# -H "Content-Type: application/json" -d@./POST-branch.http http://localhost:8080/branch/delete/11
