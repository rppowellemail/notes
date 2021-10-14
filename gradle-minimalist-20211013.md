# Gradle - minimumalist

Minimalist gralde to build/run java program

`MainClassName.java`
```java
class MainClassName {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
```

`build.gradle`
```gradle
apply plugin: "java"
apply plugin: "application"
mainClassName="MainClassName"
sourceSets.main.java.srcDirs=['.']
```

Commands:

```
gradle build
gradle run
gradle clean
```

---

Details

`build.gradle`

```
apply plugin: "java"
apply plugin: "application"
mainClassName="MainClassName"

repositories { mavenCentral() }

//dependencies {
//    implementation 'org.hibernate:hibernate-core:3.6.7.Final'
//}

sourceSets.main.java.srcDirs=['.']

//sourceSets {
//    main {
//         java {
//            srcDirs = ['.']
//         }
//    }
//}
```
