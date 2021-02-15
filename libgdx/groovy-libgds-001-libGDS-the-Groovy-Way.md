

References:

* https://impetus-games.com/blog/libGDX-the-Groovy-Way (obsolete)

----

Adding `groovy` to LibGDX starter project

Reference:

* https://stackoverflow.com/questions/38285929/errors-while-configuring-groovy-in-libgdx-game

## Create and Import the libGDX project (gdx-start)

Create stub project using gdx-start

    java -jar gdx-setup.jar

Using the following:

Name        | Value
------------|------
Name        |`libGDX202102141650`
Package     |`com.mygdx.game`
Game class  |`MyGdxGame`
Destination |`C:\Users\rppowell\Desktop\libgdx\libGDX202102141650`
Android SDK |`C:\Path\To\Your\Sdk`

Sub Projects:
* `Desktop` checked

Extensions:
* None checked

Click on `Generate` to generate the project.

Import into IntelliJ:
* `File` -> `New` -> `Project from Existing Sources...`

Select the project `build.gradle` file:
* `C:\Users\rppowell\Desktop\Learning\libGDX202102141650\build.gradle`

---

## Add `groovy` to project `build.gradle`

Edit `C:\Users\rppowell\Desktop\Learning\libGDX202102141650\build.gradle`

Before:

    ...
    project(":core") {
        apply plugin: "java-library"
    
    
        dependencies {
            api "com.badlogicgames.gdx:gdx:$gdxVersion"
            api "com.badlogicgames.gdx:gdx-box2d:$gdxVersion"
            
        }
    }
    ...

After:

    ...
    project(":core") {
        apply plugin: "groovy"
        apply plugin: "java-library"
    
    
        dependencies {
            api "com.badlogicgames.gdx:gdx:$gdxVersion"
            api "com.badlogicgames.gdx:gdx-box2d:$gdxVersion"
            compile 'org.codehaus.groovy:groovy-all:2.3.11'
    
        }
    }
    ...

---

Edit `C:\Users\rppowell\Desktop\Learning\libGDX202102141650\core\build.gradle`

Change:

    sourceSets.main.java.srcDirs = [ "src/" ]

To:

    sourceSets {
        main {
            java { srcDirs = [] }
            groovy { srcDir "src" }
        }
    }

Create `java`/`groovy` code directories

* `C:\Users\rppowell\Desktop\Learning\libGDX202102141650\core\src\java`
* `C:\Users\rppowell\Desktop\Learning\libGDX202102141650\core\src\groovy`

Move `C:\Users\rppowell\Desktop\Learning\libGDX202102141650\core\src\com` to `C:\Users\rppowell\Desktop\Learning\libGDX202102141650\core\src\java`

Change `Program Structure` by going to:

* `File` -> `Program Structure...`

Or:

* Ctrl + Alt + Shift + S

Under `Project Settings`, select `Modules`.

Under Modules, select:

    libGDX202102141650
      core
        main

Unmark `Sources` for `C:\Users\rppowell\Desktop\Learning\libGDX202102141650\core\src`
Mark `Sources` for:
* `groovy`
* `java`

Under `Project`, Right-Click `core` and select `Add Framework Support...`

In `Add Frameworks Support`, check `Groovy`, and under `Use library` select `Gradle: org.codehaus.groovy:groovy-all:2.3.11`

----

First `groovy` class

Under `core` -> `src`, Right-Click on `groovy` and select `New` > `Groovy Class`

Create as `groovy\com.mygdx.game.GroovyGdxGame`:

    class GroovyGdxGame extends ApplicationAdapter {
        @Override
        public void render () {
            Gdx.gl.glClearColor(0, 0, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
    }

Under `C:\Users\rppowell\Desktop\Learning\libGDX202102141650\desktop\src\com\mygdx\game\desktop\DesktopLauncher.java`

Change:

	public class DesktopLauncher {
		public static void main (String[] arg) {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			new LwjglApplication(new MyGdxGame(), config);
		}
	}

To:

	public class DesktopLauncher {
		public static void main (String[] arg) {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			new LwjglApplication(new GroovyGdxGame(), config);
		}
	}

To run go to `Gradle`:

* `libGDX202102141650` -> `Tasks` -> `other` -> `run`
