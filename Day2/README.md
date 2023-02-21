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

## Hypervisor vs Container Technology

## Docker Overview

## Docker Alternatives

