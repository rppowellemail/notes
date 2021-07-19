# libgdx tutorial - drop - 2021-07-18

Reference:

* https://libgdx.com/dev/simple-game/

## Generating a Project

Generating a Project:

Download from:

* https://libgdx.com/assets/downloads/legacy_setup/gdx-setup_latest.jar

Run with:

    java -jar gdx-setup_latest.jar

### Setup

    Name:        drop
    Package:     com.badlogic.drop
    Game class:  Drop
    Destination: C:\Users\rppowell\Desktop\Learning\libGDX202107181800
    Android SDK: <na>

    Sub Projects
      [X] Desktop
      [ ] Android
      [ ] Ios
      [ ] Html
    Extensions
      [ ] ...

Click on `Generate`:

    To import to Intellij IDEA: File -> Open -> build.gradle

---

## Open and Run

Generating a Project (details):

    C:\Users\rppowell\Desktop\Learning\libGDX202107181800\desktop\src\com\badlogic\drop\desktop\DesktopLauncher.java

To run:

    Gradle
      libGDX202107181800
        desktop
          Tasks
            other
              run

---

Issue:

    WARNING: An illegal reflective access operation has occurred
    WARNING: Illegal reflective access by org.lwjgl.LWJGLUtil$3 (file:/C:/Users/rppowell/.gradle/caches/modules-2/files-2.1/org.lwjgl.lwjgl/lwjgl/2.9.3/3df168ac74e4a8c96562cdff24ad352e255bf89c/lwjgl-2.9.3.jar) to method java.lang.ClassLoader.findLibrary(java.lang.String)
    WARNING: Please consider reporting this to the maintainers of org.lwjgl.LWJGLUtil$3
    WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
    WARNING: All illegal access operations will be denied in a future release

---

## Initial Changes - Configuration

Add to `com/badlogic/drop/desktop/DesktopLauncher.java`:

    public class DesktopLauncher {
        public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Drop";
            config.width = 800;
            config.height = 480;
            new LwjglApplication(new Drop(), config);
        }
    }

---

## Adding Assets

Adding assets to asset directory:

    C:\Users\rppowell\Desktop\Learning\libGDX202107181800\core\assets

Asset Filename |  Downloaded File Name                   | url
-------------- | --------------------------------------- | --------------------------------------------------
`drop.wav`     | `30341__junggle__waterdrop24.wav`       | https://freesound.org/people/junggle/sounds/30341/
`rain.mp3`     | `28283__acclivity__undertreeinrain.mp3` | https://freesound.org/people/acclivity/sounds/28283/
`droplet.png`  | `drop.png`                              | https://raw.githubusercontent.com/Quillraven/SimpleKtxGame/01-app/android/assets/images/drop.png
`bucket.png`   | `bucket.png`                            | https://raw.githubusercontent.com/Quillraven/SimpleKtxGame/01-app/android/assets/images/bucket.png

## Implement Changes


Add to `com/badlogic/drop/Drop.java`:

    package com.badlogic.drop;
    
    import com.badlogic.gdx.ApplicationAdapter;
    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.audio.Music;
    import com.badlogic.gdx.audio.Sound;
    import com.badlogic.gdx.graphics.Texture;
    
    public class Drop extends ApplicationAdapter {
        private Texture dropImage;
        private Texture bucketImage;
        private Sound dropSound;
        private Music rainMusic;
        
        @Override
        public void create() {
            // load the images for the droplet and the bucket, 64x64 pixels each
            dropImage = new Texture(Gdx.files.internal("droplet.png"));
            bucketImage = new Texture(Gdx.files.internal("bucket.png"));
    
            // load the drop sound effect and the rain background "music"
            dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
            rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
    
            // start the playback of the background music immediately
            rainMusic.setLooping(true);
            rainMusic.play();
    
            // ... more to come ...
        }
    ...
    }

---
Add to `com/badlogic/drop/Drop.java`:

    private OrthographicCamera camera;
    private SpriteBatch batch;

In `create()`:

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    ...
    batch = new SpriteBatch();

---

Add to `com/badlogic/drop/Drop.java`:

    import com.badlogic.gdx.math.Rectangle;

    private Rectangle bucket;

In `create()`:

    bucket = new Rectangle();
    bucket.x = 800 / 2 - 64 / 2;
    bucket.y = 20;
    bucket.width = 64;
    bucket.height = 64;

---

In `render()`:

    ScreenUtils.clear(0, 0, 0.2f, 1);
    camera.update();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(bucketImage, bucket.x, bucket.y);
    batch.end();

---

In `render()`:

    if(Gdx.input.isTouched()) {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        bucket.x = touchPos.x - 64 / 2;
    }
    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

    if(bucket.x < 0) bucket.x = 0;
    if(bucket.x > 800 - 64) bucket.x = 800 - 64;

---

    private Array<Rectangle> raindrops;
    private long lastDropTime;

---

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

---

In `create()`:

    raindrops = new Array<Rectangle>();
    spawnRaindrop();

---

    batch.begin();
    batch.draw(bucketImage, bucket.x, bucket.y);
    for(Rectangle raindrop: raindrops) {
        batch.draw(dropImage, raindrop.x, raindrop.y);
    }
    batch.end();

    if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
    
    for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
        Rectangle raindrop = iter.next();
        raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
        if(raindrop.y + 64 < 0) iter.remove();
        if(raindrop.overlaps(bucket)) {
            dropSound.play();
            iter.remove();
        }
    }

---

Cleanup:

      dropImage.dispose();
      bucketImage.dispose();
      dropSound.dispose();
      rainMusic.dispose();
      batch.dispose();

---

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, bucket.x, bucket.y);
        for(Rectangle raindrop: raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64 / 2;
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
    
        if(bucket.x < 0) bucket.x = 0;
        if(bucket.x > 800 - 64) bucket.x = 800 - 64;
    
        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
    
        for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0) iter.remove();
            if(raindrop.overlaps(bucket)) {
                dropSound.play();
                iter.remove();
            }
        }
    }

---
