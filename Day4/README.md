# Day4

## Configuring Windows Ansible Node

1. You may create a T2 - Medium ec2 instance with Windows 2019 base AMI

2. Windows Node Ansible Requirments	
     - PowerShell 3.0 or latest
     - .Net Framework 4.5 or latest

### Finding PowerShell version

$PSVersionTable

### Finding .Net Framework Version

1. Open regedit

2. HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\NET Framework Setup\NDP\v4\Full

### Configuring WinRM on Windows machine

$url = "https://raw.githubusercontent.com/ansible/ansible/devel/examples/scripts/ConfigureRemotingForAnsible.ps1"

$file = "$env:temp\ConfigureRemotingForAnsible.ps1"

(New-Object -TypeName System.Net.WebClient).DownloadFile($url, $file)

powershell.exe -ExecutionPolicy ByPass -File $file

### Configuring Windows node with Basic authentication

Set-Item -Path WSMan:\localhost\Service\Auth\Basic -Value $true

### Verify if WinRM Listeners are running ( 2 listerners one for Http and other for Https expected )

winrm enumerate winrm/config/Listener

### On the Ansible Controller machine, make sure pywinrm is installed
pip install "pywinrm>=0.3.0"

### Troubleshooting Fedora boxes VM not pinging
```
sudo dnf install libvirt-daemon-config-network
sudo systemctl enable virtnetworkd.service
sudo systemctl start virtnetworkd.service
sudo systemctl status virtnetworkd.service
```

## What is Agile?
- fail-fast approach
- we need frequent feedbacks from customer
- 

## What is DevOps?
- a combination of tools and process that helps the team frequently deliver releases to customer with confidence

## What is Continuous Integration (CI) ?
- integrating your code changes several times a day to your dev branches is critical
- let's say you are using GitHub, when code is committed to your GitHub Project repo, Jenkins job which monitors your Code Repo will trigger an automated build
- the automated will compile and run all the automated test cases and sends the build and test report to the team via email
- the build and test report we receive from Jenkins is the feedback

## What is Continuous Delivery (CD) ?
- an extension of Continuous Integration
- it deploys the tested binaries automatically to testing/staging/pre-production environments for further automated testing

## What is Continuous Deployment (CD) ?
- an extension of Continuous Delivery (CD)
- if your product binary passes all stages of your production pipeline and it is automatically released to customers or it make the binary live in production

## What is TDD?
- Test Driven Development
- Before writing application code, we need to write an automated test that verifies a single scenario
- Initially the test always fails as there is no application code implemented
- Then whatever code is required to make the test pass, only so much code should be written
- Then see the test passing
- Write another test case to verify yet another scenario and continue this process
- TDD test cases usually only target unit and integration testing
- TDD Test cases are more technical,in the sense the business folks may not understand these test case 

## What is BDD?
- Behavior Driven Development
- The test cases are written spoken english like language
- The BDD scenarios can be written in many spoken language
- Gerkin language

## Solution to the Docker daemon vulnerabilities
<pre>
https://docs.docker.com/engine/security/rootless/
</pre>

## Continuous Integration Build Servers
- Jenkins is most popular CI/CD Build Server
- Hudson was first CI/CD tool developed by Koshuge Kawaguchi (Sun Microsystems employee)
- Jenkins is fork(branch) from Hudson
- Jenkins and Hudson both are opensource CI/CD Build Server
- Jenkins is maintained by Koshuge Kawaguchi, founder of Cloudbees Organization
- Cloudbees is Enterprise version of Jenkins
- Alternates to Jenkins/Cloudbees
  - TeamCity
  - Bamboo
  - Microsoft Team Foundation Server (TFS)
 
## Launching Jenkins
```
cd ~/Downloads
java -jar ./jenkins.war
```
Expected output
<pre>
jegan@tektutor.org  ~/Downloads  java -jar ./jenkins.war 
Running from: /home/jegan/Downloads/jenkins.war
webroot: $user.home/.jenkins
2023-02-23 08:23:15.578+0000 [id=1]	INFO	winstone.Logger#logInternal: Beginning extraction from war file
2023-02-23 08:23:16.509+0000 [id=1]	WARNING	o.e.j.s.handler.ContextHandler#setContextPath: Empty contextPath
2023-02-23 08:23:16.564+0000 [id=1]	INFO	org.eclipse.jetty.server.Server#doStart: jetty-10.0.12; built: 2022-09-14T01:54:40.076Z; git: 408d0139887e27a57b54ed52e2d92a36731a7e88; jvm 17.0.6+10
2023-02-23 08:23:16.804+0000 [id=1]	INFO	o.e.j.w.StandardDescriptorProcessor#visitServlet: NO JSP Support for /, did not find org.eclipse.jetty.jsp.JettyJspServlet
2023-02-23 08:23:16.856+0000 [id=1]	INFO	o.e.j.s.s.DefaultSessionIdManager#doStart: Session workerName=node0
2023-02-23 08:23:17.226+0000 [id=1]	INFO	hudson.WebAppMain#contextInitialized: Jenkins home directory: /home/jegan/.jenkins found at: $user.home/.jenkins
2023-02-23 08:23:17.367+0000 [id=1]	INFO	o.e.j.s.handler.ContextHandler#doStart: Started w.@73ad4ecc{Jenkins v2.375.3,/,file:///home/jegan/.jenkins/war/,AVAILABLE}{/home/jegan/.jenkins/war}
2023-02-23 08:23:17.390+0000 [id=1]	INFO	o.e.j.server.AbstractConnector#doStart: Started ServerConnector@1d483de4{HTTP/1.1, (http/1.1)}{0.0.0.0:8080}
2023-02-23 08:23:17.404+0000 [id=1]	INFO	org.eclipse.jetty.server.Server#doStart: Started Server@48d61b48{STARTING}[10.0.12,sto=0] @2222ms
2023-02-23 08:23:17.405+0000 [id=47]	INFO	winstone.Logger#logInternal: Winstone Servlet Engine running: controlPort=disabled
2023-02-23 08:23:17.552+0000 [id=53]	INFO	jenkins.InitReactorRunner$1#onAttained: Started initialization
2023-02-23 08:23:17.558+0000 [id=72]	INFO	jenkins.InitReactorRunner$1#onAttained: Listed all plugins
2023-02-23 08:23:18.175+0000 [id=81]	INFO	jenkins.InitReactorRunner$1#onAttained: Prepared all plugins
2023-02-23 08:23:18.181+0000 [id=90]	INFO	jenkins.InitReactorRunner$1#onAttained: Started all plugins
2023-02-23 08:23:18.189+0000 [id=98]	INFO	jenkins.InitReactorRunner$1#onAttained: Augmented all extensions
2023-02-23 08:23:18.355+0000 [id=118]	INFO	jenkins.InitReactorRunner$1#onAttained: System config loaded
2023-02-23 08:23:18.358+0000 [id=122]	INFO	jenkins.InitReactorRunner$1#onAttained: System config adapted
2023-02-23 08:23:18.359+0000 [id=127]	INFO	jenkins.InitReactorRunner$1#onAttained: Loaded all jobs
2023-02-23 08:23:18.362+0000 [id=134]	INFO	jenkins.InitReactorRunner$1#onAttained: Configuration for all jobs updated
2023-02-23 08:23:18.387+0000 [id=153]	INFO	hudson.util.Retrier#start: Attempt #1 to do the action check updates server
2023-02-23 08:23:18.689+0000 [id=138]	INFO	jenkins.install.SetupWizard#init: 

*************************************************************
*************************************************************
*************************************************************

Jenkins initial setup is required. An admin user has been created and a password generated.
Please use the following password to proceed to installation:

a5eaf1d12b06470392c659c5fa96568c

This may also be found at: /home/jegan/.jenkins/secrets/initialAdminPassword

*************************************************************
*************************************************************
*************************************************************

2023-02-23 08:23:30.922+0000 [id=158]	INFO	jenkins.InitReactorRunner$1#onAttained: Completed initialization
2023-02-23 08:23:30.938+0000 [id=37]	INFO	hudson.lifecycle.Lifecycle#onReady: <b>Jenkins is fully up and running</b>
2023-02-23 08:23:32.019+0000 [id=153]	INFO	h.m.DownloadService$Downloadable#load: Obtained the updated data file for hudson.tasks.Maven.MavenInstaller
2023-02-23 08:23:32.020+0000 [id=153]	INFO	hudson.util.Retrier#start: Performed the action check updates server successfully at the attempt #1
</pre>

 ### Accessing Jenkins webpage from chrome web browser on RPS Ubuntu machine
 ```
 http://localhost:8080
 ```

### Additional Plugins that needs to be installed
You may check my medium blog to get step by step instructions here https://medium.com/tektutor/ci-cd-with-maven-github-docker-jenkins-aca28c252fec

Navigate to Manage Jenkins ==> Manage Plugins ==> Available 

<pre>
1. Docker
2. Maven Integration
3. Build Pipeline
4. Ansible
5. Ansible Tower (optional)
</pre>
