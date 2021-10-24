

Uses `gdx-tools` extension;

* https://github.com/libgdx/libgdx/tree/master/extensions/gdx-tools

Download:

* https://raw.githubusercontent.com/libgdx/libgdx/master/extensions/gdx-tools/assets/uiskin.json
* https://raw.githubusercontent.com/libgdx/libgdx/master/extensions/gdx-tools/assets/uiskin.png
* https://raw.githubusercontent.com/libgdx/libgdx/master/extensions/gdx-tools/assets/uiskin.atlas
* https://raw.githubusercontent.com/libgdx/libgdx/master/extensions/gdx-tools/assets/default.fnt
* https://raw.githubusercontent.com/libgdx/libgdx/master/extensions/gdx-tools/assets/default.png

Save in `core/assets` directory

```
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MyGdxGame extends ApplicationAdapter {
    Stage stage;

    public void create () {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Label nameLabel = new Label("Name:", skin);
        TextField nameText = new TextField("", skin);
        Label addressLabel = new Label("Address:", skin);
        TextField addressText = new TextField("", skin);

        Table table = new Table();
        stage.addActor(table);
        table.setSize(260, 195);
        table.setPosition(190, 142);
        // table.align(Align.right | Align.bottom);

        table.debug();

        TextureRegion upRegion = skin.getRegion("default-slider-knob");
        TextureRegion downRegion = skin.getRegion("default-slider-knob");
        BitmapFont buttonFont = skin.getFont("default-font");

        TextButton button = new TextButton("Button 1", skin);
        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touchDown 1");
                return false;
            }
        });
        table.add(button);
        // table.setTouchable(Touchable.disabled);

        Table table2 = new Table();
        stage.addActor(table2);
        table2.setFillParent(true);
        table2.bottom();

        TextButton button2 = new TextButton("Button 2", skin);
        button2.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("2!");
            }
        });
        button2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touchDown 2");
                return false;
            }
        });
        table2.add(button2);
    }

    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose () {
        stage.dispose();
    }
}
```
