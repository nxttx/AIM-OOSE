@echo off
docker build -t spotitube_intergrationtestdb .
docker run --name spotitube_intergrationtestdb -dp 3306:3306 spotitube_intergrationtestdb
Echo Waiting for mysql to start.
PING -n 30 127.0.0.1>nul
Echo Now we are ready
