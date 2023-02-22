# Day 3

## Lab - Using external volume/directory to store the mysql database (Docker Volume Mounting)

First create a mysql db container that uses the container storage to save the database and tables
```
docker run -d --name mysql --hostname mysql -e MYSQL_ROOT_PASSWORD=root@123 bitnami/mysql:latest
docker ps
docker exec -it mysql sh
mysql -u root -p 
CREATE DATABASE tektutor;
USE tektutor;
CREATE TABLE training ( id INT NOT NULL, name VARCHAR(50), duration VARCHAR(50), PRIMARY KEY(id) );
INSERT INTO training VALUES ( 1, "DevOps", "5 Days" );
INSERT INTO training VALUES ( 2, "Microservices", "5 Days" );
INSERT INTO training VALUES ( 3, "OpenShift", "5 Days" );
SELECT * FROM training;
exit
exit
```
When it prompts for password, you can type 'root@123' without the quotes.

Now let's delete the mysql container, when we delete the container, the tektutor database, trainig table and all records will be lost.
```
docker rm -f mysql
docker ps -a
```

Now let's create another new mysql container
```
docker run -d --name mysql --hostname mysql -e MYSQL_ROOT_PASSWORD=root@123 bitnami/mysql:latest
docker ps
docker exec -it mysql sh
mysql -u root -p 

SHOW DATABASES;
```

Now, you can observe that the tektutor database is missing as we used the container storage and data is lost.

This is the reason, external volumes are used to persist the data permanently.

Let's create a new mysql container that uses external persistent volume.
```
docker rm -f mysql
mkdir -p /tmp/mysql
chmod 777 /tmp/mysql
docker run -d --name mysql --hostname mysql -e MYSQL_ROOT_PASSWORD=root@123 -v tmp/mysql:/bitnami/mysql/data bitnami/mysql:latest
docker ps
docker exec -it mysql sh

mysql -u root -p
CREATE DATABASE tektutor;
USE tektutor;
CREATE TABLE training ( id INT NOT NULL, name VARCHAR(50), duration VARCHAR(50), PRIMARY KEY(id) );
INSERT INTO training VALUES ( 1, "DevOps", "5 Days" );
INSERT INTO training VALUES ( 2, "Microservices", "5 Days" );
INSERT INTO training VALUES ( 3, "OpenShift", "5 Days" );
SELECT * FROM training;
exit
exit
docker rm -f mysql
```

Let's recreate another new mysql container mounting the exact same external volume path
```
docker run -d --name mysql --hostname mysql -e MYSQL_ROOT_PASSWORD=root@123 -v tmp/mysql:/bitnami/mysql/data bitnami/mysql:latest
docker ps
docker exec -it mysql sh
mysql -u root -p

SHOW DATABASES;
USE tektutor;
SHOW TABLES;
SELECT * FROM training;
exit
exit
```

In the above exercise, you would have learned that storing the data to an external volume retains the data permanently and it is accessible from other containers too.





