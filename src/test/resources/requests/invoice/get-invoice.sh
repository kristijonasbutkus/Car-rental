#!/bin/bash

curl http://localhost:8080/invoice/api/invoice/$1 --user admin:pa$$w0rd -v
