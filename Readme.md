## Setup MySql server
1. Create your volumes. You’ll create one for the data and one for configuration of MySQL.

   docker volume create mysql_data
   docker volume create mysql_config

2. Create a network that your application and database will use to talk to each other.

   docker network create mysqlnet

3. Run MySQL in a container and attach to the volumes and network you created

   docker run -it --rm \
   -e MYSQL_ROOT_PASSWORD=<password> \
   -v mysql_data:/var/lib/mysql \
   -v mysql_config:/etc/mysql/conf.d \
   --network mysqlnet \
   --name mysqlserver \
   -p 3306:3306 mysql:8.0

4. Login into MySql using
   docker exec -it <containerId> sh
   mysql -u root -p

5. Add user

   CREATE USER '<username>'@'localhost' IDENTIFIED BY 'password';

6. Create db

   CREATE DATABASE <NAME>;

7. Grant privileges

   GRANT ALL ON <db>.* TO '<username>'@'localhost';


## Run a project
1. Change your user, password, dbname in application-mysql.properties and docker-compose files.
2. Run for dev

   docker compose -f docker-compose.dev.yml up --build -d

3. Run for prod

   docker compose -f docker-compose.prod.yml up --build -d
