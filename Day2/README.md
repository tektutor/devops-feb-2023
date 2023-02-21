# Day2

## Launching JFrog Artifactory as a Docker container
```
docker run --name artifactory --hostname -d -p 8081-8082:8081-8082 docker.bintray.io/jfrog/artifactory-oss:latest

docker ps
```

Launch your JFrog Artifactory page from RPS Ubuntu chrome browser
```
http://localhost:8081
```

## You need to edit your maven settings.xml file
