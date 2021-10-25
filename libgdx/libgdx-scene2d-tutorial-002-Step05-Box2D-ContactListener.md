Reference:

* Step 1 - https://www.gamedevelopment.blog/full-libgdx-game-tutorial-project-setup/
* Step 2 - https://www.gamedevelopment.blog/full-libgdx-game-tutorial-menu-control/
* Step 3 - https://www.gamedevelopment.blog/full-libgdx-game-tutorial-box2d/
* Step 4 - https://www.gamedevelopment.blog/full-libgdx-game-tutorial-box2d-body-factory/
* **Step 5 - https://www.gamedevelopment.blog/full-libgdx-game-tutorial-box2d-contact-listener/**

# Step 5 - Scene2d ContactListener

## Adding ContactListener

Create class `B2DContactListener` with the following:

```
public class B2DContactListener implements ContactListener {
    private B2DModel parent;

    public B2DContactListener(B2DModel parent) {
        this.parent = parent;
    }
    @Override
    public void beginContact(Contact contact) { }

    @Override
    public void endContact(Contact contact) { }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) { }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) { }
}
```

## Implement ContactListener beginContact method

### beginContact(Contact contact)

```
    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
    }
```

### Use ContactListener beginContact method

```
    public B2DModel(){
        world = new World(new Vector2(0,-10f), true);
        world.setContactListener(new B2DContactListener(this));
        createFloor();
        createObject();
        createMovingObject();
    }
```

### Do something on contact

```
    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
        if(fa.getBody().getType() == BodyDef.BodyType.StaticBody){
            System.out.println("Adding Force");
            fb.getBody().applyForceToCenter(new Vector2(-100000,-100000), true);
        }
    }
```

This may not work because it could have race conditions.

Refactor/implement a different way.

Add method:

```
    private void shootUpInAir(Fixture staticFixture, Fixture otherFixture){
        System.out.println("Adding Force");
        otherFixture.getBody().applyForceToCenter(new Vector2(-100000,-100000), true);
    }
```

And update `beginContact()`:

```
    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
        if(fa.getBody().getType() == BodyDef.BodyType.StaticBody){
            this.shootUpInAir(fa, fb);
        }else if(fb.getBody().getType() == BodyDef.BodyType.StaticBody){
            this.shootUpInAir(fb, fa);
        }else{
            // neither a nor b are static so do nothing
        }
    }
```
