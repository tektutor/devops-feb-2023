# Day 2

Creating JFrog Artifactory as a Docker Container
For detailed instructions, you may refer the official documentation here https://www.jfrog.com/confluence/display/RTF6X/Installing+with+Docker

```
docker pull docker.bintray.io/jfrog/artifactory-oss:latest
docker run --name artifactory --hostname artifactory -d -p 8081-8082:8081-8082 docker.bintray.io/jfrog/artifactory-oss:latest
docker ps
```
Expected output
<pre>
 jegan@tektutor  ~/devops-feb-2023/Day2/multi-module-project/main$ docker pull docker.bintray.io/jfrog/artifactory-oss:latest

latest: Pulling from jfrog/artifactory-oss
fdb82fb306d5: Pull complete 
1ed462c7eefc: Pull complete 
a135bf91fcb7: Pull complete 
7205ca6d16f2: Pull complete 
0d54d3f6bbca: Pull complete 
4f4fb700ef54: Pull complete 
a728be71ea48: Pull complete 
e70fd8c445c8: Pull complete 
b9e293dc4ac3: Pull complete 
2a5eb3a9f3fa: Pull complete 
Digest: sha256:570624299b92ab43ec70a0a38617ed5e8452e23f9aeaeb8f9c32b9d9c1e35418
Status: Downloaded newer image for docker.bintray.io/jfrog/artifactory-oss:latest
docker.bintray.io/jfrog/artifactory-oss:latest

 jegan@tektutor  ~/devops-feb-2023/Day2/multi-module-project   main  docker run --name artifactory --hostname artifactory -d -p 8081-8082:8081-8082 docker.bintray.io/jfrog/artifactory-oss:latest

0c14e8de60da62819561aedbcc12d2b09cb18e3c0fd67bbf78a3deebce4be9af

 jegan@tektutor  ~/devops-feb-2023/Day2/multi-module-project   main  docker ps
CONTAINER ID   IMAGE                                            COMMAND                  CREATED         STATUS         PORTS                                                           NAMES
0c14e8de60da   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   4 seconds ago   Up 3 seconds   0.0.0.0:8081-8082->8081-8082/tcp, :::8081-8082->8081-8082/tcp   artifactory
</pre>

You may then access the JFrog Artifactory web page from your RPS Ubuntu Chrome Web browser
<pre>
http://localhost:8081
</pre>

When it prompts for login credentials, please use the below default credentials to login
<pre>
username - admin
password - password
</pre>

In case it prompts to change the password, maybe change it to 'Admin@123' as I would use this password in my code.

You need to find your maven settings.xml file and edit the file.
```
mvn --version
```

In my case the path is
<pre>
/home/jegan/Downloads/apache-maven-3.9.0/conf/settings.xml
</pre>

You need to modify the settings.xml as shown below
````
 <servers>
    <server>
      <id>artifactory</id>
      <username>admin</username>
      <password>Admin@123</password>
    </server>
</servers>
```
In the above file, the servers tag already exists at line number 112, you need to add the server tag entry just below the servers tag entry as shown above.  The rest of the file content should be retained as it is.

You may now go to your maven project
```
cd ~/devops-feb-2023
git pull origin main

cd Day2/multi-module-project
mvn deploy
```

# Docker

## What is dual booting or multi-booting?
- Boot Loader utilities are used to boot into different OS on a laptop/desktop
- Boot Loader Examples
  - LILO (Linux Loaders)
  - GRUB1 
  - GRUB2
- Master Boot Record (MBR)
  - in your hard disk, sector 0 and byte 0 - typically would be size 512 bytes

## What is Hypervisor?
- aka virtualization 
- this technology allows running multiple OS on a Desktop/Laptop/Workstation/Server at the same time
- i.e many OS can be actively running at the same on a Desktop/Laptop/Workstation/Server


## What is Application Virtualization?

## Hypervisor vs Container Technology

## Docker Overview

## Docker Alternatives

