# one

https://stackoverflow.com/questions/34865883/creating-an-executable-jar-with-groovy-script

## files

`HelloWorld.groovy`

```
println "Hello World!"
```

`manifest.txt`

```
Main-class: HelloWorld
Class-path: jar/groovy-all-2.4.6.jar
```

## build steps

```
rm *.class
groovyc HelloWorld.groovy
jar cvfm HelloWorld.jar manifest.txt HelloWorld.class
```

```
mkdir jar
cp $GROOVY_HOME/embeddable/groovy-all-2.4.6.jar jar
```

```
java -jar HelloWorld.jar
```

# gradle-one

https://stackoverflow.com/questions/35110582/gradle-groovy-shadow-project-to-create-executable-jar-file

## files

`src/main/groovy/HelloWorld.groovy`

```
println "Hello World!"
```

`build.gradle`

```
plugins {
   id 'groovy'
   id 'application'
   id 'com.github.johnrengelman.shadow' version '1.2.2'
}

repositories {
    mavenCentral()
}

dependencies {
    compile 'org.codehaus.groovy:groovy-all:2.4.4'
}

version = '0.1'
mainClassName = 'HelloWorld'
shadowJar {
    mergeServiceFiles()
}
```

## build steps

```
gradle run
```

# gradle-two

## files

`src/main/groovy/HelloWorld.groovy`

```
println "Hello World!"
```

`build.gradle`

```
plugins {
    id 'groovy'
}

version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}
configurations {
    groovyScript
}

dependencies {
    compile 'org.codehaus.groovy:groovy-all:2.5.2'
}

task runScript (dependsOn: 'classes', type: JavaExec) {
    main = 'HelloWorld'
    classpath = sourceSets.main.runtimeClasspath
}
```

## build steps

```
gradle run
```

# gradle-three

https://github.com/objectified/groovy-gradle-fatjar-example

## files

`src/main/groovy/HelloWorld.groovy`

```
println "Hello World!"
```

`build.gradle`

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.github.jengelman.gradle.plugins:shadow:5.0.0'
    }
}

apply plugin: 'com.github.johnrengelman.shadow'
apply plugin: 'groovy'

version '1.0-SNAPSHOT'

repositories {
    jcenter()
}

dependencies {
    compile 'org.codehaus.groovy:groovy-all:2.5.7'
}

task runScript (dependsOn: 'classes', type: JavaExec) {
    main = 'HelloWorld'
    classpath = sourceSets.main.runtimeClasspath
}

jar {
    manifest {
        attributes 'Main-Class': 'HelloWorld'
    }
}
```

## build steps

```
groovy src/main/groovy/HelloWorld.groovy
```

```
gradle run
```

```
gradle shadowJar
java -jar build/libs/gradle-three-1.0-SNAPSHOT-all.jar
```
