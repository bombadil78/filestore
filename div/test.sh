#!/bin/bash

curl -X POST -F "file=@data.txt" http://localhost:9090/api/files
