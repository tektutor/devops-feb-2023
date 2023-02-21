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
- Two types
  1. Type 1 - Bare Metal Hypervisor - This can be installed directly on the bare metal with no OS
     - used in Workstations/Servers
  2. Type 2 - This can be installed on top of a Host OS (Windows,Unix,Linux or Mac OS-X)
     - used in Laptops/Desktops
  
- Examples
  Type 1 - VMWare vCenter/vSphere
  Type 2
  - VMWare Workstation ( Linux & Windows )
  - VMWare Fusion ( Mac OS-X)
  - Oracle VirtualBox
  - Microsoft Hyper-V
  - Parallels ( Mac OS-X )
  - KVM (Kernel Virtual Manager )

- Virtualization helps consoliating many Physical Server into one or few number of Physical Server
- Cost Savings
  - With few servers, many OS can be installed on Virtual Machines
  - Cost of real estate renting/lease reduces as the size of server room reduces
  - Cost of Airconditioning, sound proofing, electricity bill reduces drastically
  - each Virtual Machine(VM) represents one fully functional Operating System
  - each Virtual Machine(VM) get its own dedicated hardware resources
    - CPU
    - RAM
    - Storage (HDD)
  - heavy-weight Virtualization

## How to find out the maximum number of Virtual Machines a laptop/desktop/workstation/server can support?
- Processor ( how many CPU cores the Processor supports )
- RAM
- Storage
- Processors (x86_64)
  AMD
  - AMD-V - Virtualization feature set supported in AMD Processors
  
  Intel
  - VT-X - Virtualization feature set supported in Intel Processors
Processors
- comes in different form factor
- SCM ( Single Chip Module - One Physical IC will contain only 1 Processor with many CPU Cores )
- MCM ( Multi Chip Modules - One Physial IC with contain many Processor each with many CPU Cores )
- If I need let's say 2000 Virtual Machines, how many minimum Physical servers can support 2000 VMs
  - 1 or 2 Physical Servers
  - 8 Socket Server Motherboards
  - 8 MCM Processors on 8 different Sockets on the Server Motherboard
  - Assume each MCM supports let's say 4 Processors, each Processor supports 128 Cores
  - 4 x 128 = in MCM
  - 4 x 128 x 8 = 4096 Physical Cores
  - each Physical is seen as 2 or 4 virtual cores by the Hypervisor software
  - 4096 x 2 = 8192 virtual cores
 

## What is Application Virtualization?
- Container Technology aka Application Virtualization Technology
- each Container represents only single application process
- Container though it supports many OS like features, it is still an application process not a OS
- Container's however has
  - a separate Network stack ( 7 OSI Layers )
  - a virtual network card
  - an IP address
  - file system
  - ports ( 0 to 65535 )
- each containers runs in its own namespace
- containers don't get their own dedicated hardware resource, all the containers and normal applications running on the HOST OS shares the hardware resources available to the HOST OS. 
- containers depends on the OS kernel on the HOST OS

## Hypervisor vs Container Technology
- Hypervisor represents a fully function OS, while container represents one single application
- Within a Virtual Machine, you could install Container software and run many containers
- Hence, container's will never be able to replace Virtual Machines or Operating Systems
- Containers depends on the HOST OS Kernel for OS level functionalities

## Docker Overview
- is one of the popular Container Software out there
- comes in 2 flavours
  - Community Edition (OpenSource ) - Docker CE
  - Enterprise Edition (Paid) - Docker EE
- follows client/server architecture
  client tool - docker
  server tool - dockerd ( d stands for daemon - runs as a service in the background )
- if client and server are running on the same system, they use unix socket for communication
- if client and server are running on different system, they could use REST API for communication

## Docker Alternatives
- Podman
- LXC

## What is Container Runtime ?
- are not used by end-users directly
- This software know how many manage containers
  - create a container using Container Image
  - start the container
  - stop the container
  - restart
  - kill/abort
  - delete
  - rename
- Examples
  - runC
  - CRI-O

## Tools that manages Container Images
- Kaniko
- Buildah

## What is a Container Engine ?
- high-level software used by End-users
- provides user-friendly commands to manage containers and images
- depends on Container Runtime to manage Containers
- depends on other tools to manage Container Images
- Examples
  - Docker depends on containerd which in turn depends on runC container Runtime
  - Podman
  
## Linux Kernel Feature that enables Container Technology
- Namespace
  - to isolate one container from the other containers
- Control Groups (CGroups)
  - are used to apply resource quota restrictions to containers
  - For example,
    - you could restrict how much CPU a container can use at the max ( 25%, 50% )
    - you could restrict how much RAM a container can use at the max
    - you could restrict how much Storage a container can use at the max

## What is Container Orchestration Platforms
- they manage containerized applications
- they manage containerized Microservices, Web Servers, App Server, DB Servers, etc., 
- they support High Availability(HA) to your containerized applications
- supports inbuilt monitoring and self-healing
- scaling up/down when the traffic to your containerized application increases/decreases
- rolling update
  - upgrading your application/microservice from one version to other in live environment without any downtime
  - also supports rolling back to older stable versions when new deployed version is found to unstable
- supports services to expose your application/microservice instances within the container orchestration cluster or to the external world

- Examples
  - Google Kubernetes 
    - Supports many Container Runtimes and Container Engines ( opensource )
    - Production grade
    
  - Red Hat OpenShift 
    - it is developed on top Google Kubernetes
    - supports many additional features on top of features supported by Kubernetes
    - Supports CRI-O Container Runtime and Podman Container Engine - Paid
    - production grade
    
  - Docker SWARM 
     - Docker's native Container Orchestration Platforms ( supports only Docker ) - opensource
     - not production grade

## What is a Docker Image
- a specification of a Docker container
- whatever software tools you need in a container can be installed on Docker image
- containers are created from a Docker Image

## What is a Docker Container
- a running instance of a Docker Image
- each running container gets a unique name, id and IP address

# Docker Commands

## Finding the docker version
```
docker --version
```

Expected output
<pre>
[jegan@tektutor ~]$ docker --version
Docker version 23.0.1, build a5ee5b1
</pre>

## Lab - Finding more information about your docker installation
```
docker info
```

Expected output
<pre>
 jegan@tektutor  ~  docker info
Client:
 Context:    default
 Debug Mode: false
 Plugins:
  buildx: Docker Buildx (Docker Inc.)
    Version:  v0.10.2
    Path:     /usr/libexec/docker/cli-plugins/docker-buildx
  compose: Docker Compose (Docker Inc.)
    Version:  v2.16.0
    Path:     /usr/libexec/docker/cli-plugins/docker-compose
  scan: Docker Scan (Docker Inc.)
    Version:  v0.23.0
    Path:     /usr/libexec/docker/cli-plugins/docker-scan

Server:
 Containers: 1
  Running: 1
  Paused: 0
  Stopped: 0
 Images: 1
 Server Version: 23.0.1
 Storage Driver: overlay2
  Backing Filesystem: btrfs
  Supports d_type: true
  Using metacopy: false
  Native Overlay Diff: true
  userxattr: false
 Logging Driver: json-file
 Cgroup Driver: systemd
 Cgroup Version: 2
 Plugins:
  Volume: local
  Network: bridge host ipvlan macvlan null overlay
  Log: awslogs fluentd gcplogs gelf journald json-file local logentries splunk syslog
 Swarm: inactive
 Runtimes: io.containerd.runc.v2 runc
 Default Runtime: runc
 Init Binary: docker-init
 containerd version: 2456e983eb9e37e47538f59ea18f2043c9a73640
 runc version: v1.1.4-0-g5fd4c4d
 init version: de40ad0
 Security Options:
  seccomp
   Profile: builtin
  cgroupns
 Kernel Version: 6.0.7-301.fc37.x86_64
 Operating System: Fedora Linux 37 (Workstation Edition)
 OSType: linux
 Architecture: x86_64
 CPUs: 48
 Total Memory: 125.5GiB
 Name: tektutor.org
 ID: e59f4066-3c58-41ca-aec6-0a6f04e4a4ca
 Docker Root Dir: /var/lib/docker
 Debug Mode: false
 Registry: https://index.docker.io/v1/
 Experimental: false
 Insecure Registries:
  127.0.0.0/8
 Live Restore Enabled: false
</pre>

## Lab - Listing Docker Images on your Docker Local Registry
```
docker images
```

## Lab - Creating your first Docker container 
```
docker run hello-world
```

Expected output
<pre>
 jegan@tektutor  ~  docker run hello-world
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
2db29710123e: Pull complete 
Digest: sha256:6e8b6f026e0b9c419ea0fd02d3905dd0952ad1feea67543f525c73a0a790fefb
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
</pre>

## Lab - Listing the currently running containers
```
docker ps
```

Expected output
<pre>
 jegan@tektutor  ~  docker ps
CONTAINER ID   IMAGE                                            COMMAND                  CREATED       STATUS       PORTS                                                           NAMES
0c14e8de60da   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   4 hours ago   Up 4 hours   0.0.0.0:8081-8082->8081-8082/tcp, :::8081-8082->8081-8082/tcp   artifactory
</pre>


## Lab - Listing all containers irrespective of their running status
```
docker ps -a
```

Expected output
<pre>
docker ps -a

CONTAINER ID   IMAGE                                            COMMAND                  CREATED         STATUS                     PORTS                                                           NAMES
6c569adc718c   hello-world                                      "/hello"                 2 minutes ago   Exited (0) 2 minutes ago                                                                   frosty_hermann
0c14e8de60da   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   4 hours ago     Up 4 hours                 0.0.0.0:8081-8082->8081-8082/tcp, :::8081-8082->8081-8082/tcp   artifactory
</pre>

## Lab - Renaming a container
```
docker rename <current-name> <new-name>
```

Expected output
<pre>
docker rename frosty_hermann hello

 jegan@tektutor  ~  docker ps
CONTAINER ID   IMAGE                                            COMMAND                  CREATED       STATUS       PORTS                                                           NAMES
0c14e8de60da   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   4 hours ago   Up 4 hours   0.0.0.0:8081-8082->8081-8082/tcp, :::8081-8082->8081-8082/tcp   artifactory
 jegan@tektutor  ~  docker ps -a
CONTAINER ID   IMAGE                                            COMMAND                  CREATED         STATUS                     PORTS                                                           NAMES
6c569adc718c   hello-world                                      "/hello"                 5 minutes ago   Exited (0) 5 minutes ago                                                                   hello
0c14e8de60da   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   4 hours ago     Up 4 hours                 0.0.0.0:8081-8082->8081-8082/tcp, :::8081-8082->8081-8082/tcp   artifactory
</pre>
