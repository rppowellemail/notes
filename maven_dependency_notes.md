Can use the following to download just dependencies:

    <?xml version="1.0" encoding="UTF-8"?>
    <!-- mvn dependency:resolve -->
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>example.maven.apache.org</groupId>
        <artifactId>mavenproject</artifactId>
        <version>1.0-SNAPSHOT</version>
     <dependencies>
     <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
      <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.3</version>
      </dependency>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <type>jar</type>
        <scope>test</scope>
        <optional>true</optional>
       </dependency>
      </dependencies>
    </project>

Use as:
    mvn dependency:resolve

