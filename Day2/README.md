# Day2

## Creating JFrog Artifactory as a Docker Container
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

 jegan@tektutor  ~/devops-feb-2023/Day2/multi-module-project   main  <b>docker run --name artifactory --hostname artifactory -d -p 8081-8082:8081-8082 docker.bintray.io/jfrog/artifactory-oss:latest</b>

0c14e8de60da62819561aedbcc12d2b09cb18e3c0fd67bbf78a3deebce4be9af

 jegan@tektutor  ~/devops-feb-2023/Day2/multi-module-project   main  <b>docker ps</b>
CONTAINER ID   IMAGE                                            COMMAND                  CREATED         STATUS         PORTS                                                           NAMES
0c14e8de60da   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   4 seconds ago   Up 3 seconds   0.0.0.0:8081-8082->8081-8082/tcp, :::8081-8082->8081-8082/tcp   artifactory
</pre>

You may then access the JFrog Artifactory web page from your RPS Ubuntu Chrome Web browser
```
http://localhost:8081
```

When it prompts for login credentials, please use the below default credentials to login
<pre>
username - admin
password - password
</pre>

In case it prompts to change the password, maybe change it to 'Admin@123' as I would use this password in my code.
