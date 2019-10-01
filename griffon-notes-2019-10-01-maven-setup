Griffon Tutorial Part 3

Alternative way to create project

* http://andresalmiray.com/griffon-mythbusters/

Using maven archetypes

    griffon-javafx-groovy-archetype
    griffon-javafx-java-archetype
    griffon-lanterna-groovy-archetype
    griffon-lanterna-java-archetype
    griffon-pivot-groovy-archetype
    griffon-pivot-java-archetype
    griffon-swing-groovy-archetype
    griffon-swing-java-archetype

Select a starting archetype from the list and invoke the archetype:generate goal

```
$ mvn archetype:generate
    -DarchetypeGroupId=org.codehaus.griffon.maven
    -DarchetypeArtifactId=griffon-javafx-java-archetype
    -DarchetypeVersion=<archetype-version>
    -DgroupId=org.example
    -DartifactId=app
    -Dversion=1.0.0-SNAPSHOT
```

Example command line/interactive:

```
$ mvn archetype:generate
...
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 1426: griffon-lanterna-java-archetype
Choose archetype:
1: remote -> org.codehaus.griffon.maven:griffon-lanterna-java-archetype (Griffon Lanterna Java Archetype)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 1
Choose org.codehaus.griffon.maven:griffon-lanterna-java-archetype version: 
1: 2.15.0
2: 2.15.1
Choose a number: 2: 2
Define value for property 'groupId': org.loscon.registration
Define value for property 'artifactId': loscon-registration-ui-griffon-lanterna-java
Define value for property 'version' 1.0-SNAPSHOT: : 
Define value for property 'package' org.loscon.registration: : 
Confirm properties configuration:
groupId: org.loscon.registration
artifactId: loscon-registration-ui-griffon-lanterna-java
version: 1.0-SNAPSHOT
package: org.loscon.registration
...
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: griffon-lanterna-java-archetype:2.15.1
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: org.loscon.registration
[INFO] Parameter: artifactId, Value: loscon-registration-ui-griffon-lanterna-java
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Parameter: package, Value: org.loscon.registration
[INFO] Parameter: packageInPathFormat, Value: org/loscon/registration
[INFO] Parameter: package, Value: org.loscon.registration
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Parameter: groupId, Value: org.loscon.registration
[INFO] Parameter: artifactId, Value: loscon-registration-ui-griffon-lanterna-java
[WARNING] The directory /Users/rpowell/IdeaProjects/loscon-registration/jvm/loscon-registration-ui-griffon-lanterna-java already exists.
[INFO] Project created from Archetype in dir: /Users/rpowell/IdeaProjects/loscon-registration/jvm/loscon-registration-ui-griffon-lanterna-java
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  06:06 min
[INFO] Finished at: 2019-10-01T16:12:39-07:00
[INFO] ------------------------------------------------------------------------
```

```
mvn archetype:generate \
   -DarchetypeGroupId=org.codehaus.griffon.maven \
    -DarchetypeArtifactId=griffon-javafx-java-archetype \
    -DarchetypeVersion=2.15.1 \
    -DgroupId=org.loscon.registration \
    -DartifactId=loscon-registration-ui-griffon-lanterna-java \
    -Dversion=1.0.0-SNAPSHOT
```
