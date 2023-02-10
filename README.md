# SpringBoot-2-StudentCRUDApp

Followed - Spring Boot Tutorial - AmigosCode - https://www.youtube.com/watch?v=9SGDpanrc8U

Use Spring Initializr to get set up
Dependencies:
Spring Web
Spring Data JPA
PostgreSQL Driver

Last Steps to get SpringBoot app up and running
Maven clean - removes target folder
maven install - rebuilds target folder - clean, validate, compile, test, package, verify
Make sure DB is up and running (psql?)
Then, in target folder, jar file to run manually
in terminal - navigate to target folder (within project root)
run project from here
--> java -jar demo-0.0.1-SNAPSHOT.jar
ctrl + C to stop app
and/Or to run another instance (on another server)
--> java -jar demo-0.0.1-SNAPSHOT.jar --server.port=8081
from jar - spin up instances
deploy to server
dockerize it
do whatever you want with jar file to deploy it for real users
