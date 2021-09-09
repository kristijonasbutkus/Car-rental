#!/bin/bash

curl -X PUT -v -# -H "Content-Type: application/json" -d@./PUT-invoice.http http://localhost:8080/invoice/api/invoice/print/1 --user admin:pa$$w0rd -v
