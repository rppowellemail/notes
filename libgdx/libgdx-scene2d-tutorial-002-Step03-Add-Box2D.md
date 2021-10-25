References:

* https://www.gamedevelopment.blog/full-libgdx-game-tutorial-project-setup/
* **https://www.gamedevelopment.blog/full-libgdx-game-tutorial-menu-control/**



# Step 1

Create a `B2DModel` class:

```
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class B2DModel {
    public World world;

    public B2DModel(){
        world = new World(new Vector2(0,-10f), true);
    }
    
    public void logicStep(float delta){
        world.step(delta , 3, 3);
    }
}
```

Add to the `MainScreen` constructor:

```
public class MainScreen implements Screen {
    private MyGdxGame parent;
    private B2DModel model;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    public MainScreen(MyGdxGame box2DTutorial) {
        parent = box2DTutorial;
        model = new B2DModel();
        camera = new OrthographicCamera(32,24);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
    }
...
}
```

And update `MainScreen` `render(float delta)`:
```
    @Override public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, camera.combined);
    }
```

# Adding Body

Add `bodyd` (dynamic), `bodys` (static), `bodyk` (kenematic) member:

```
public class B2DModel {
    public World world;
    private Body bodyd;
    private Body bodys;
    private Body bodyk;
...
```

Create method for `bodyd` (dynamic):

```
    private void createObject(){
        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);
        
        // add it to the world
        bodyd = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyd.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }
```

Create method for `bodys` (static body/Floor):

```
    private void createFloor() {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);
        // add it to the world
        bodys = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);
        // create the physical object in our body)
        // without this our body would just be data in the world
        bodys.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }
```

Create method for `bodyk` (kinematic):

```
    private void createMovingObject(){
        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);
        
        // add it to the world
        bodyk = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyk.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        bodyk.setLinearVelocity(0, 0.75f);
    }
```

Now add the method calls to `B2DModel` constructor:
```
    public B2DModel(){
        world = new World(new Vector2(0,-10f), true);
        createFloor();
        createObject();
        createMovingObject();
    }
```
