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
 
 
