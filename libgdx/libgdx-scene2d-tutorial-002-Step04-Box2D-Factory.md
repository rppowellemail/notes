References:

* https://www.gamedevelopment.blog/full-libgdx-game-tutorial-menu-control/
* https://www.gamedevelopment.blog/full-libgdx-game-tutorial-box2d/
* **https://www.gamedevelopment.blog/full-libgdx-game-tutorial-box2d-body-factory/**

# Create BodyFactory class

Create `BodyFactory` class with singleton:

```
public class BodyFactory {
    private World world;
    private static BodyFactory thisInstance;

    private BodyFactory(World world){
        this.world = world;
    }

    public static BodyFactory getInstance(World world){
        if(thisInstance == null){
            thisInstance = new BodyFactory(world);
        }
        return thisInstance;
    }
}
```

Add `FixtureDef` method:

```
public class BodyFactory {
    private World world;
    private static BodyFactory thisInstance;
    public static final int STEEL = 0;
    public static final int WOOD = 1;
    public static final int RUBBER = 2;
    public static final int STONE = 3;
...    
    static public FixtureDef makeFixture(int material, Shape shape){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        switch(material){
            case 0:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.3f;
                fixtureDef.restitution = 0.1f;
                break;
            case 1:
                fixtureDef.density = 0.5f;
                fixtureDef.friction = 0.7f;
                fixtureDef.restitution = 0.3f;
                break;
            case 2:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0f;
                fixtureDef.restitution = 1f;
                break;
            case 3:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.9f;
                fixtureDef.restitution = 0.01f;
            default:
                fixtureDef.density = 7f;
                fixtureDef.friction = 0.5f;
                fixtureDef.restitution = 0.3f;
        }
        return fixtureDef;
    }
```

Adding method `makeCirclePolyBody()`:

```
    public Body makeCirclePolyBody(float posx, float posy, float radius, int material, BodyDef.BodyType bodyType, boolean fixedRotation){
        // create a definition
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = fixedRotation;

        //create the body to attach said definition
        Body boxBody = world.createBody(boxBodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius /2);
        boxBody.createFixture(makeFixture(material,circleShape));
        circleShape.dispose();
        return boxBody;
    }
```

Add to `B2DModel` constructor:

```
    public B2DModel(){
        world = new World(new Vector2(0,-10f), true);
        createFloor();
        createObject();
        createMovingObject();

        // get our body factory singleton and store it in bodyFactory
        BodyFactory bodyFactory = BodyFactory.getInstance(world);

        // add a new rubber ball at position 1, 1
        bodyFactory.makeCirclePolyBody(1, 1, 2, BodyFactory.RUBBER, BodyDef.BodyType.DynamicBody,false);

        // add a new steel ball at position 4, 1
        bodyFactory.makeCirclePolyBody(4, 1, 2, BodyFactory.STEEL, BodyDef.BodyType.DynamicBody,false);

        // add a new stone at position -4,1
        bodyFactory.makeCirclePolyBody(-4, 1, 2, BodyFactory.STONE, BodyDef.BodyType.DynamicBody,false);
    }
```
