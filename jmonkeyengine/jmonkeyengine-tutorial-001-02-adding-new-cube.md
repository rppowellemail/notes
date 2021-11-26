JMonkeyEngine tutorial 001 - Part 2 - Creating a second Cube

Change/update:

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
    }

To:

    @Override
    public void simpleInitApp() {
        Vector3f v = new Vector3f(2.0f, 1.0f, -3.0f);
        
        Box b1 = new Box(1, 1, 1);
        Geometry geom1 = new Geometry("Box", b1);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Blue);
        geom1.setMaterial(mat1);
        
        rootNode.attachChild(geom1);

        Box b2 = new Box(1, 1, 1);
        Geometry geom2 = new Geometry("Box", b2);
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Yellow);
        geom2.setMaterial(mat2);
        geom2.setLocalTranslation(v);
        
        rootNode.attachChild(geom2);
    }

Playing with translate/scale/rotate:

    @Override
    public void simpleInitApp() {
        Vector3f v = new Vector3f(2.0f, 1.0f, -3.0f);
        
        Box b1 = new Box(1, 1, 1);
        Geometry geom1 = new Geometry("Box", b1);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Blue);
        geom1.setMaterial(mat1);
        
        rootNode.attachChild(geom1);

        Box b2 = new Box(1, 1, 1);
        Geometry geom2 = new Geometry("Box", b2);
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Yellow);
        geom2.setMaterial(mat2);
        geom2.setLocalTranslation(v);
        
        //geom2.setLocalScale(2.0f);
        //geom2.setLocalScale(1.0f);
        
        float rotationfloat = FastMath.DEG_TO_RAD * 45f;
        //geom2.rotate(rotationfloat, 0.0f, 0.0f);
        //geom2.rotate(0.0f, rotationfloat, 0.0f);
        
        Quaternion roll045 = new Quaternion();
        roll045.fromAngleAxis(30*FastMath.DEG_TO_RAD, Vector3f.UNIT_X);
        geom2.setLocalRotation(roll045);
        
        rootNode.attachChild(geom2);
    }
