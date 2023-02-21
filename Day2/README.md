# Day2

## Creating JFrog Artifactory as a Docker Container
```
docker pull docker.bintray.io/jfrog/artifactory-oss:latest
docker run --name artifactory --hostname artifactory -d -p 8081-8082:8081-8082 docker.bintray.io/jfrog/artifactory-oss:latest
```
