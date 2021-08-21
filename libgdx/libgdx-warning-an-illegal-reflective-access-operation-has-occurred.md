
The following message when running:

    > Task :desktop:DesktopLauncher.main()
    WARNING: An illegal reflective access operation has occurred
    An illegal reflective access operation has occurred
    
    WARNING: Illegal reflective access by org.lwjgl.LWJGLUtil$3 (file:/C:/Users/rppowell/.gradle/caches/modules-2/files-2.1/org.lwjgl.lwjgl/lwjgl/2.9.3/3df168ac74e4a8c96562cdff24ad352e255bf89c/lwjgl-2.9.3.jar) to method java.lang.ClassLoader.findLibrary(java.lang.String)
    Illegal reflective access by org.lwjgl.LWJGLUtil$3 (file:/C:/Users/rppowell/.gradle/caches/modules-2/files-2.1/org.lwjgl.lwjgl/lwjgl/2.9.3/3df168ac74e4a8c96562cdff24ad352e255bf89c/lwjgl-2.9.3.jar) to method java.lang.ClassLoader.findLibrary(java.lang.String)
    
    WARNING: Please consider reporting this to the maintainers of org.lwjgl.LWJGLUtil$3
    Please consider reporting this to the maintainers of org.lwjgl.LWJGLUtil$3
    
    WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
    Use --illegal-access=warn to enable warnings of further illegal reflective access operations
    
    WARNING: All illegal access operations will be denied in a future release
    All illegal access operations will be denied in a future release
    
    
    > Task :desktop:DesktopLauncher.main() FAILED
    
    Execution failed for task ':desktop:DesktopLauncher.main()'.
    > Process 'command 'C:/Program Files/AdoptOpenJDK/jdk-11.0.9.101-hotspot/bin/java.exe'' finished with non-zero exit value -1
    
    * Try:
    Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.
    

Solution: Change from using `lwjgl` to `lwjgl3`.

Make the following changes:

Change `build.gradle` from:

        dependencies {
            ...
            api "com.badlogicgames.gdx:gdx-backend-lwjgl:$gdxVersion"
            ...
            
        }

To:

        dependencies {
            ...
            api "com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion"
            ...
            
        }

And change in `com/mygdx/game/desktop/DesktopLauncher.java` from:

    package com.mygdx.game.desktop;
    
    import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
    import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
    ...
    
    public class DesktopLauncher {
    	public static void main (String[] arg) {
    		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    		new LwjglApplication(new MyGdxGame(), config);
    	}
    }

To:

    package com.mygdx.game.desktop;
    
    import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
    import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
    ...
    
    public class DesktopLauncher {
    	public static void main (String[] arg) {
    		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    		new Lwjgl3Application(new MyGdxGame(), config);
    	}
    }
