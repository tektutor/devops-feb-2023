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

## Lab - Deleting an exited container 
```
docker rm hello
```
Expected output
<pre>
 jegan@tektutor  ~  docker rm hello
hello
 jegan@tektutor  ~  docker ps -a
CONTAINER ID   IMAGE                                            COMMAND                  CREATED       STATUS       PORTS                                                           NAMES
0c14e8de60da   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   4 hours ago   Up 4 hours   0.0.0.0:8081-8082->8081-8082/tcp, :::8081-8082->8081-8082/tcp   artifactory
</pre>

## Deleting a running container
```

```

Expected output
<pre>
jegan@tektutor  ~  docker ps
CONTAINER ID   IMAGE                                            COMMAND                  CREATED       STATUS       PORTS                                                           NAMES
0c14e8de60da   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   4 hours ago   Up 4 hours   0.0.0.0:8081-8082->8081-8082/tcp, :::8081-8082->8081-8082/tcp   artifactory
 jegan@tektutor  ~  docker rm artifactory
Error response from daemon: You cannot remove a running container 0c14e8de60da62819561aedbcc12d2b09cb18e3c0fd67bbf78a3deebce4be9af. Stop the container before attempting removal or force remove
 ✘ jegan@tektutor  ~  docker stop artifactory
artifactory
 jegan@tektutor  ~  docker rm artifactory
artifactory
 jegan@tektutor  ~  docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
 jegan@tektutor  ~  docker ps -a
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
</pre>

## Creating ubuntu containers in background
```
docker run -dit --name ubuntu1 --hostname ubuntu1 ubuntu:16.04 /bin/bash
docker run -dit --name ubuntu2 --hostname ubuntu2 ubuntu:16.04 /bin/bash
docker run -dit --name ubuntu3 --hostname ubuntu3 ubuntu:16.04 /bin/bash
```

Expected output
<pre>
 jegan@tektutor  ~  docker run -dit --name ubuntu1 --hostname ubuntu1 ubuntu:16.04 /bin/bash
Unable to find image 'ubuntu:16.04' locally
16.04: Pulling from library/ubuntu
58690f9b18fc: Pull complete 
b51569e7c507: Pull complete 
da8ef40b9eca: Pull complete 
fb15d46c38dc: Pull complete 
Digest: sha256:1f1a2d56de1d604801a9671f301190704c25d604a416f59e03c04f5c6ffee0d6
Status: Downloaded newer image for ubuntu:16.04
ab64f512f5e86f70f206a9e489c8749598fe1d57be967816346ad4eb5c995d8f

 jegan@tektutor  ~  docker ps
CONTAINER ID   IMAGE          COMMAND       CREATED         STATUS         PORTS     NAMES
ab64f512f5e8   ubuntu:16.04   "/bin/bash"   3 seconds ago   Up 2 seconds             ubuntu1

 jegan@tektutor  ~  docker run -dit --name ubuntu2 --hostname ubuntu2 ubuntu:16.04 /bin/bash
7a0a3f8b9e0a13ce3b40d9332e060b5bbe122c59d819dccebe39289f232f6f66

 jegan@tektutor  ~  docker run -dit --name ubuntu3 --hostname ubuntu3 ubuntu:16.04 /bin/bash
9cfa1cd2e345dffbf16535366a70c4014a09219c189428cadfc43ce69da797cb

 jegan@tektutor  ~  docker ps
CONTAINER ID   IMAGE          COMMAND       CREATED          STATUS          PORTS     NAMES
9cfa1cd2e345   ubuntu:16.04   "/bin/bash"   2 seconds ago    Up 1 second               ubuntu3
7a0a3f8b9e0a   ubuntu:16.04   "/bin/bash"   8 seconds ago    Up 7 seconds              ubuntu2
ab64f512f5e8   ubuntu:16.04   "/bin/bash"   20 seconds ago   Up 19 seconds             ubuntu1
</pre>

## Lab - Stopping and Starting multiple containers
```
docker stop ubuntu1
docker stop ubuntu2 ubuntu3
docker ps
docker ps -a
docker start ubuntu1 ubuntu2 ubuntu3
docker ps
```

Expected output
<pre>
 jegan@tektutor  ~  docker stop ubuntu1
ubuntu1

 jegan@tektutor  ~  docker stop ubuntu2 ubuntu3
ubuntu2
ubuntu3

 jegan@tektutor  ~  docker ps 
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES

 jegan@tektutor  ~  docker ps -a
CONTAINER ID   IMAGE          COMMAND       CREATED         STATUS                      PORTS     NAMES
9cfa1cd2e345   ubuntu:16.04   "/bin/bash"   2 minutes ago   Exited (0) 5 seconds ago              ubuntu3
7a0a3f8b9e0a   ubuntu:16.04   "/bin/bash"   2 minutes ago   Exited (0) 5 seconds ago              ubuntu2
ab64f512f5e8   ubuntu:16.04   "/bin/bash"   2 minutes ago   Exited (0) 11 seconds ago             ubuntu1

 jegan@tektutor  ~  docker start ubuntu1 ubuntu2 ubuntu3
ubuntu1
ubuntu2
ubuntu3

 jegan@tektutor  ~  docker ps
CONTAINER ID   IMAGE          COMMAND       CREATED         STATUS         PORTS     NAMES
9cfa1cd2e345   ubuntu:16.04   "/bin/bash"   2 minutes ago   Up 3 seconds             ubuntu3
7a0a3f8b9e0a   ubuntu:16.04   "/bin/bash"   2 minutes ago   Up 3 seconds             ubuntu2
ab64f512f5e8   ubuntu:16.04   "/bin/bash"   3 minutes ago   Up 4 seconds             ubuntu1
</pre>

## Lab - Finding the IP address of a container
```
docker inspect ubuntu1
docker inspect ubuntu1 | grep IPA
docker inspect {{.NetworkSettings.IPAddress}} ubuntu1
```

Expected output
<pre>
 jegan@tektutor  ~/devops-feb-2023/Day2/container-with-gui  ⇅ main  docker ps
CONTAINER ID   IMAGE          COMMAND       CREATED         STATUS         PORTS     NAMES
9cfa1cd2e345   ubuntu:16.04   "/bin/bash"   7 minutes ago   Up 5 minutes             ubuntu3
7a0a3f8b9e0a   ubuntu:16.04   "/bin/bash"   8 minutes ago   Up 5 minutes             ubuntu2
ab64f512f5e8   ubuntu:16.04   "/bin/bash"   8 minutes ago   Up 5 minutes             ubuntu1
 jegan@tektutor  ~/devops-feb-2023/Day2/container-with-gui  ⇅ main  docker inspect ubuntu1
[
    {
        "Id": "ab64f512f5e86f70f206a9e489c8749598fe1d57be967816346ad4eb5c995d8f",
        "Created": "2023-02-21T09:40:35.581916928Z",
        "Path": "/bin/bash",
        "Args": [],
        "State": {
            "Status": "running",
            "Running": true,
            "Paused": false,
            "Restarting": false,
            "OOMKilled": false,
            "Dead": false,
            "Pid": 73993,
            "ExitCode": 0,
            "Error": "",
            "StartedAt": "2023-02-21T09:43:39.186027832Z",
            "FinishedAt": "2023-02-21T09:43:16.890883046Z"
        },
        "Image": "sha256:b6f50765242581c887ff1acc2511fa2d885c52d8fb3ac8c4bba131fd86567f2e",
        "ResolvConfPath": "/var/lib/docker/containers/ab64f512f5e86f70f206a9e489c8749598fe1d57be967816346ad4eb5c995d8f/resolv.conf",
        "HostnamePath": "/var/lib/docker/containers/ab64f512f5e86f70f206a9e489c8749598fe1d57be967816346ad4eb5c995d8f/hostname",
        "HostsPath": "/var/lib/docker/containers/ab64f512f5e86f70f206a9e489c8749598fe1d57be967816346ad4eb5c995d8f/hosts",
        "LogPath": "/var/lib/docker/containers/ab64f512f5e86f70f206a9e489c8749598fe1d57be967816346ad4eb5c995d8f/ab64f512f5e86f70f206a9e489c8749598fe1d57be967816346ad4eb5c995d8f-json.log",
        "Name": "/ubuntu1",
        "RestartCount": 0,
        "Driver": "overlay2",
        "Platform": "linux",
        "MountLabel": "",
        "ProcessLabel": "",
        "AppArmorProfile": "",
        "ExecIDs": null,
        "HostConfig": {
            "Binds": null,
            "ContainerIDFile": "",
            "LogConfig": {
                "Type": "json-file",
                "Config": {}
            },
            "NetworkMode": "default",
            "PortBindings": {},
            "RestartPolicy": {
                "Name": "no",
                "MaximumRetryCount": 0
            },
            "AutoRemove": false,
            "VolumeDriver": "",
            "VolumesFrom": null,
            "ConsoleSize": [
                32,
                136
            ],
            "CapAdd": null,
            "CapDrop": null,
            "CgroupnsMode": "private",
            "Dns": [],
            "DnsOptions": [],
            "DnsSearch": [],
            "ExtraHosts": null,
            "GroupAdd": null,
            "IpcMode": "private",
            "Cgroup": "",
            "Links": null,
            "OomScoreAdj": 0,
            "PidMode": "",
            "Privileged": false,
            "PublishAllPorts": false,
            "ReadonlyRootfs": false,
            "SecurityOpt": null,
            "UTSMode": "",
            "UsernsMode": "",
            "ShmSize": 67108864,
            "Runtime": "runc",
            "Isolation": "",
            "CpuShares": 0,
            "Memory": 0,
            "NanoCpus": 0,
            "CgroupParent": "",
            "BlkioWeight": 0,
            "BlkioWeightDevice": [],
            "BlkioDeviceReadBps": [],
            "BlkioDeviceWriteBps": [],
            "BlkioDeviceReadIOps": [],
            "BlkioDeviceWriteIOps": [],
            "CpuPeriod": 0,
            "CpuQuota": 0,
            "CpuRealtimePeriod": 0,
            "CpuRealtimeRuntime": 0,
            "CpusetCpus": "",
            "CpusetMems": "",
            "Devices": [],
            "DeviceCgroupRules": null,
            "DeviceRequests": null,
            "MemoryReservation": 0,
            "MemorySwap": 0,
            "MemorySwappiness": null,
            "OomKillDisable": null,
            "PidsLimit": null,
            "Ulimits": null,
            "CpuCount": 0,
            "CpuPercent": 0,
            "IOMaximumIOps": 0,
            "IOMaximumBandwidth": 0,
            "MaskedPaths": [
                "/proc/asound",
                "/proc/acpi",
                "/proc/kcore",
                "/proc/keys",
                "/proc/latency_stats",
                "/proc/timer_list",
                "/proc/timer_stats",
                "/proc/sched_debug",
                "/proc/scsi",
                "/sys/firmware"
            ],
            "ReadonlyPaths": [
                "/proc/bus",
                "/proc/fs",
                "/proc/irq",
                "/proc/sys",
                "/proc/sysrq-trigger"
            ]
        },
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/77b81ae609f70a84ca01efaccb4720005c0bc65722cf6a8dd52b2ba79a02f903-init/diff:/var/lib/docker/overlay2/43bb6e164b5f269dacbc868348c858b3d6e903e2a67f95ca2f3936e9d1492626/diff:/var/lib/docker/overlay2/1fb312a45e123dfb0c4098a4e1747db2b77637861fd13609319149cdf3d85800/diff:/var/lib/docker/overlay2/942ee114bb00a7b1ec57ada39f931a8d3efa6029644f3df334aa9d921c4e9068/diff:/var/lib/docker/overlay2/f649278f5cecd29d51d4abde0e2da8f34b2c79e85b168cec05a6c03fbee4506e/diff",
                "MergedDir": "/var/lib/docker/overlay2/77b81ae609f70a84ca01efaccb4720005c0bc65722cf6a8dd52b2ba79a02f903/merged",
                "UpperDir": "/var/lib/docker/overlay2/77b81ae609f70a84ca01efaccb4720005c0bc65722cf6a8dd52b2ba79a02f903/diff",
                "WorkDir": "/var/lib/docker/overlay2/77b81ae609f70a84ca01efaccb4720005c0bc65722cf6a8dd52b2ba79a02f903/work"
            },
            "Name": "overlay2"
        },
        "Mounts": [],
        "Config": {
            "Hostname": "ubuntu1",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "Tty": true,
            "OpenStdin": true,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
            ],
            "Cmd": [
                "/bin/bash"
            ],
            "Image": "ubuntu:16.04",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": {}
        },
        "NetworkSettings": {
            "Bridge": "",
            "SandboxID": "fe4d4d4dea1425c2ffcbec43fef313b43661c929f98cf7d3a62bdb12705fb74f",
            "HairpinMode": false,
            "LinkLocalIPv6Address": "",
            "LinkLocalIPv6PrefixLen": 0,
            "Ports": {},
            "SandboxKey": "/var/run/docker/netns/fe4d4d4dea14",
            "SecondaryIPAddresses": null,
            "SecondaryIPv6Addresses": null,
            "EndpointID": "00c9156a3531a31141c2d89c216bb9c2387ce3ce167eb9a9ba0f5be120a2be42",
            "Gateway": "172.17.0.1",
            "GlobalIPv6Address": "",
            "GlobalIPv6PrefixLen": 0,
            "IPAddress": "172.17.0.2",
            "IPPrefixLen": 16,
            "IPv6Gateway": "",
            "MacAddress": "02:42:ac:11:00:02",
            "Networks": {
                "bridge": {
                    "IPAMConfig": null,
                    "Links": null,
                    "Aliases": null,
                    "NetworkID": "d526b383200aa1000d0e9ffcd4e235bc5dda097e8cd22bc40f05b9d63ce6796a",
                    "EndpointID": "00c9156a3531a31141c2d89c216bb9c2387ce3ce167eb9a9ba0f5be120a2be42",
                    "Gateway": "172.17.0.1",
                    "IPAddress": "172.17.0.2",
                    "IPPrefixLen": 16,
                    "IPv6Gateway": "",
                    "GlobalIPv6Address": "",
                    "GlobalIPv6PrefixLen": 0,
                    "MacAddress": "02:42:ac:11:00:02",
                    "DriverOpts": null
                }
            }
        }
    }
]

 jegan@tektutor  ~/devops-feb-2023/Day2/container-with-gui  ⇅ main  docker inspect ubuntu1 | grep IPA
            "SecondaryIPAddresses": null,
            "IPAddress": "172.17.0.2",
                    "IPAMConfig": null,
                    "IPAddress": "172.17.0.2",
                    
 jegan@tektutor  ~/devops-feb-2023/Day2/container-with-gui  ⇅ main  docker inspect -f {{.NetworkSettings.IPAddress}} ubuntu1
172.17.0.2

 jegan@tektutor  ~/devops-feb-2023/Day2/container-with-gui  ⇅ main  ping 172.17.0.2
PING 172.17.0.2 (172.17.0.2) 56(84) bytes of data.
64 bytes from 172.17.0.2: icmp_seq=1 ttl=64 time=0.122 ms
64 bytes from 172.17.0.2: icmp_seq=2 ttl=64 time=0.059 ms
64 bytes from 172.17.0.2: icmp_seq=3 ttl=64 time=0.065 ms
^C
--- 172.17.0.2 ping statistics ---
3 packets transmitted, 3 received, 0% packet loss, time 2032ms
rtt min/avg/max/mdev = 0.059/0.082/0.122/0.028 ms
</pre>

## Lab - Building a custom docker image
```
cd ~/devops-feb-2023
git pull

cd Day2/CustomDockerImage
docker build -t tektutor/ubuntu .
docker images
docker run -dit --name c1 --hostname c1 tektutor/ubuntu /bin/bash
docker ps
docker exec -it c1 bash
vim
tree
ifconfig
ping 
```

## Lab - Running GUI application within a container

Let's build image and install gedit GUI editor
```
cd ~/devops-feb-2023
git pull origin main

cd Day2/container-with-gui
docker build -t tektutor/gedit .
docker images
xhost local:root
docker run --env="DISPLAY" --net=host --name=gedit tektutor/gedit:latest
```

You should see gedit UI on your local machine.
