# Day1

## Cloning TekTutor Training Repository
```
cd ~
git clone https://github.com/tektutor/devops-feb-2023.git
```

## Pulling the delta changes
```
cd ~/devops-feb-2023
git checkout -b main
git pull origin main
ls
```

## What is Ant ?
- build tool used by Java projects but also support other languages
- it uses a build.xml
- the build instructions must be written in the build.xml
- no built-in support for dependency management
- there is no standard enforcement for project directory structure
- an opensource developed by Apache Foundation
- they themselves found it very difficult to use in a complex project called Jakarta
- they wanted a better build tool which fixes all the issues they found in Ant and that's how Maven opensource project started

## What is Maven
- a build tool used predominantly by Java projects
- it is a language agnostnic(independent) build tool
- it was developed by Apache Foundation as an alternate to Apache Ant build tool
- it is an opensource tool
- it has inbuilt dependency management
- it uses pom.xml for capturing project name and its dependencies
- unlike the Ant tool, pom.xml has only dependencies, project name, etc, you don't need write the actual build instruction, hence it is a good use of xml
- it follows a plugin based architecture
- Maven depends on plugins to achieve any build related activity
- For example
  - it depends on maven-compiler-plugin to perform Java based compilation
  - it depends on maven-jar-plugin to package a Java jar based application
  - it depends on maven-war-plugin to java web applications
  - it used maven-clean-plugin to clean up the target binary folder
- Maven follows Convention over Configuration
  - Maven is strict when it comes to standards
  - Maven has project folder structure for every type of project
  - It will insist where to keep the source code, where to keep the test cases, where it would by default creat the compiled binary files
  - if we the Maven convention, then we don't need to configure Maven pom.xml as we are folowing the standard
  - in case you need to override the default maven behaviour that is the time you need to configure pom.xml
  - when you name your project we need use Maven co-ordinates


## What are Maven co-ordinates?
- Just like GPS has co-ordinates ( longiture, latitue and altitude )
  - a particular combination of GPS coordinates will also give a unique location on earth
- groupId
- artifactId
- version
- Maven coordinates
  - the combination of groupId, artifactId and version should also point to or should refer a single artifactId
  - groupId
    - it is organization's reverse domain name
    - For example, tektutor.org is my startup domain, the reverse domain would be org.tektutor
    - this is the standard recommended for groupId
    - the naming convention is similar to the convention followed in Java package naming convention
  - artifactId
    - name of the jar,war,zip, ear file etc
  - version ( 1.2.3 )
    - major
    - minor
    - incremental version


## What is Maven Life Cycle
- Maven Plugin has one or more goals, each goal supports one functionality
- supports 3 built-in life cycles
  1. default
  2. clean
  3. site
- Maven Life Cycle has one or more phases
- Each Phase can invoke one or more Plugin goals
- it is a sequence of commands, executed from top to bottom order

## Optional Lab - In case you prefer hand-coding yourself
```
mkdir -p ~/training/day1

mkdir hello

cd hello
touch pom.xml
mkdir -p src/main/java/org/tektutor
mkdir -p src/test/java/org/tektutor
touch src/main/java/org/tektutor/App.java
touch src/test/java/org/tektutor/AppTest.java

tree
```

Then you may edit the pom.xml and type the below code
```
<project>
  <modelVersion>4.0.0</modelVersion>
  
  <groupId>org.tektutor</groupId>
  <artifactId>hello</artifactId>
  <version>1.0.0</version>
  
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
    </dependency>
  </dependencies>
</project>
```

Then you may edit the src/main/java/org/tektutor/App.java and type the below code

```
package org.tektutor;

public class App {
  public String sayHello() {
    return "Hello Java - Maven project!";
  }
  
  public static void main ( String[] args ) {
    App hello = new App();
    
    System.out.println ( hello.sayHello() );
  }
}
```

Finally, you may edit the src/test/java/org/tektutor/AppTest.java and type the below code
```
package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
  
  @Test
  public void testSayHello() {
    App hello = new App();
    
    String expectedResponse = "Hello Java - Maven project!";
    String actualResponse = hello.sayHello();
    
    assertEquals ( expectedResponse, actualResponse );
  }
}
```


## Lab - Listing the Maven default life-cycle phases
```
cd ~/devops-feb-2023
git pull

cd Day1/hello
mvn help:describe -Dcmd=compile
```

## Lab - Listing the Maven clean life-cycle phases
```
cd ~/devops-feb-2023
git pull

cd Day1/hello
mvn help:describe -Dcmd=clean
```

## Lab - Listing the Maven site life-cycle phases
```
cd ~/devops-feb-2023
git pull

cd Day1/hello
mvn help:describe -Dcmd=site
```

## Lab - Printing the Maven effective pom
```
cd ~/devops-feb-2023
git pull

cd Day1/hello
mvn help:effective-pom
```

## Lab - Compiling the hello java project using Maven
```
cd ~/devops-feb-2023
git pull

cd Day1/hello
mvn compile
```

## Lab - Running automated test cases as part of Maven build
```
cd ~/devops-feb-2023
git pull

cd Day1/hello
mvn clean test
```

## Lab - Getting plugin goal help
```
cd ~/devops-feb-2023
git pull

cd Day1/hello
mvn help:describe -Dplugin=org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M8 -Ddetail > out.yml 2>&1
cat out.yml
```

## Lab - Configuring Maven not to fail the build when test cases fail

You need to configure the maven-surefire-plugin in the pom.xml. You need to add the below build section to your pom.xml within the project root tag.
```
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.0.0-M8</version>
      <configuration>
        <testFailureIgnore>true</testFailureIgnore>
      </configuration>
    </plugin>
  </plugins>
</build>
```

Now you may try running the test cases, the expectation is even though the test case(s) fail, the build will not fail.

```
cd ~/devops-feb-2023
git pull

cd Day1/hello
mvn test
```
