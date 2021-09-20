Based on
* https://www.gamedevelopment.blog/full-libgdx-game-tutorial-project-setup/

Get gdx-skin from here:
* https://github.com/czyzby/gdx-skins

Copy from archive `gdx-skins-master/glassy` to project `assets/skin`:

    assets/skin/font-big-export.fnt
    assets/skin/font-export.fnt
    assets/skin/glassy-ui.atlas
    assets/skin/glassy-ui.json
    assets/skin/glassy-ui.png
    
# Step 1 - Project Setup

Create with options:

* Name: `my-gdx-game`
* Package: `com.mygdx.game`
* Game class: `Box2DTutorial'


# Step 2 - Create screens

Create the following Screen classes:

* `LoadingScreen
* `MenuScreen`
* `PreferencesScreen`
* `MainScreen`
* `EndScreen`

Creating `LoadingScreen.java`:

``` java
package com.mygdx.game;

import com.badlogic.gdx.Screen;

public class LoadingScreen implements Screen {
    private Box2DTutorial parent;
    public LoadingScreen(Box2DTutorial box2DTutorial) {
        parent = box2DTutorial;
    }
    @Override public void show() { }
    @Override public void render(float delta) { }
    @Override public void resize(int width, int height) { }
    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void hide() { }
    @Override public void dispose() { }
}
```

# Step 3 - Wire up Screens

Edit the following Screen classes:

* `LoadingScreen
* `MenuScreen`
* `PreferencesScreen`
* `MainScreen`
* `EndScreen`

Add constructor taking `Box2DTutorial` as parameter:

``` java
public class LoadingScreen implements Screen {
    private Box2DTutorial parent;
    public LoadingScreen(Box2DTutorial box2dTutorial){
        parent = box2dTutorial;
    }
    @Override public void show() { }
    @Override public void render(float delta) { }
    @Override public void resize(int width, int height) { }
    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void hide() { }
    @Override public void dispose() { }
}
```

# Step 4

Edit `Box2DTutorial.java` to:

``` java
public class Box2DTutorial extends Game {

    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;

    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this); // added (this)
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this); // added (this)
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if(mainScreen == null) mainScreen = new MainScreen(this); //added (this)
                this.setScreen(mainScreen);
                break;
            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);  // added (this)
                this.setScreen(endScreen);
                break;
        }
    }

    @Override
    public void create () {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

}
```

Edit `LoadingScreen.java` and change behavior for `render()`:

``` java
    @Override
    public void render(float delta) {
        parent.changeScreen(Box2DTutorial.MENU);
    }
```

# Step 5

Setup `MenuScreen` to use scene:

``` java
public class MenuScreen implements Screen {
    private Box2DTutorial parent;
    private Stage stage;

    public MenuScreen(Box2DTutorial box2DTutorial) {
        parent = box2DTutorial;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton newGame = new TextButton("New Game", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);

        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();
    }
...
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
...
    @Override public void dispose() {
        stage.dispose();
    }
}
```
