// https://mvnrepository.com/artifact/com.badlogicgames.gdx
@Grapes([
    @Grab("com.badlogicgames.gdx:gdx:1.9.13"),
    @Grab("com.badlogicgames.gdx:gdx-platform:1.9.13"),
    @Grab("com.badlogicgames.gdx:gdx-platform:1.9.13:natives-desktop"),
    @Grab("com.badlogicgames.gdx:gdx-backend-lwjgl:1.9.13")
])

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.graphics.GL20

class MyGroovyGdxGame extends ApplicationAdapter {
    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}

println "Hello libGDX!"

LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
new LwjglApplication(new MyGroovyGdxGame(), config);
