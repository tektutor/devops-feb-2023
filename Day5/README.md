# Day 5

## Lab - Using Prometheus to collect Jenkins Performance metrics and plot the metrics using Grafana Dashboard

Create Prometheus as a Docker container
```
docker run -d --name prometheus-node1 --network host bitnami/prometheus:latest
docker ps
```

Expected output
<pre>
jegan@tektutor.org $ <b>docker run -d --name prometheus-node1 --network host bitnami/prometheus:latest</b>

Unable to find image 'bitnami/prometheus:latest' locally
latest: Pulling from bitnami/prometheus
edaa68e8bf3e: Pull complete 
Digest: sha256:12ab153b4c8330abb0dfb77f7dccf81056ac6953581b9bfde9b95f50af3a8edf
Status: Downloaded newer image for bitnami/prometheus:latest
3cbd4d6721213b152245599859ba7f4876563c8cf064572c4c09437907840425

jegan@tektutor.org $ <b>docker ps</b>
 
CONTAINER ID   IMAGE                       COMMAND                  CREATED         STATUS         PORTS                                   NAMES
3cbd4d672121   bitnami/prometheus:latest   "/opt/bitnami/promet…"   4 seconds ago   Up 2 seconds                                           prometheus-node1
</pre>

Accessing the Prometheus Dashboard on your RPS Ubuntu machine chrome web browsers
```
http://localhost:9090
```

![Prometheus Dashboard](prometheus-dashboard.png)


## Lab - Starting Grafana as a Docker container
```
docker run -d --name grafana --network host bitnami/grafana:latest
docker ps

```

Expected output
<pre>
jegan@tektutor.org $ <b>docker run -d --name grafana --network host bitnami/grafana:latest</b>

9ddd5dd0307c26ad7f102c281a53f407926d614be62a9d8eab8c7976b46afcbc

jegan@tektutor.org $ <b>docker ps</b>
CONTAINER ID   IMAGE                       COMMAND                  CREATED         STATUS         PORTS                                   NAMES
9ddd5dd0307c   bitnami/grafana:latest      "/opt/bitnami/script…"   2 seconds ago   Up 2 seconds                                           grafana
3cbd4d672121   bitnami/prometheus:latest   "/opt/bitnami/promet…"   7 minutes ago   Up 7 minutes                                           prometheus-node1
</pre>

Accessing the Grafana Dashboard from your Lab machine chrome browser
```
http://localhost:3000
```

When it prompts for login credentials, you may type the below credentials
<pre>
username - admin
password - admin
</pre>

After first login, I changed the password to below credentials
<pre>
username - admin
password - Admin@123
</pre>
