## How to switch your libGDX project to LWJGL 3

To switch to libGDX's LWJGL 3 backend, open your root `build.gradle` file and replace the LWJGL backend dependency:

```
api "com.badlogicgames.gdx:gdx-backend-lwjgl:$gdxVersion"
```

with the LWJGL 3 backend dependency:

```
api "com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion"
``` 

Next, you need to fix up your desktop launcher class. It is located in your desktop project and should look something like this:

```
public class DesktopLauncher {
   public static void main (String[] arg) {
      LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
      new LwjglApplication(new MyGdxGame(), config);
   }
}
```

You’ll need to change it to this:

```
public class DesktopLauncher {
   public static void main (String[] arg) {
      Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
      new Lwjgl3Application(new MyGdxGame(), config);
   }
}
```

Reference:

* https://gist.github.com/crykn/eb37cb4f7a03d006b3a0ecad27292a2d
