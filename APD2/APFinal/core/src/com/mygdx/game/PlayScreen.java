package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Factory.TankEnemyFactory;
import com.mygdx.game.Factory.TankFactory;
import com.mygdx.game.Screens.GameOver;

import javax.swing.text.View;
import java.io.*;
import java.util.ArrayList;

public class PlayScreen implements Screen , Serializable {
    public TankStars game;


    transient Texture texture;
    private OrthographicCamera entryCam;
    private Viewport entryPort;
    private Hud hud;
    transient private TmxMapLoader mapLoader;
    transient private CollisionListener cLw1;
    transient private CollisionListener cLw2;
    transient private CollisionListener cLwC;
    private TiledMap map;
    transient private OrthogonalTiledMapRenderer mapRenderer;
    transient private World world;
    transient private World worldEnemy;
    transient public World canonWorld;
    transient private Box2DDebugRenderer b2dRenderer;
    private static int UsersSaved=0;
//    private int typeUser1;

    public Tank user1;
    private Array<Body> bodies;
    public TankEnemy user2;
    public int turn;
//    public Canonball cT;
    public ArrayList<Sprite> arrayList;
    public boolean isPresent;
    transient private Stage stage;
//    private int typeUser2;
    transient private Skin pause;
    private int typeUser1;
    private int typeUser2;
    public PlayScreen(TankStars game, Hud hud, TmxMapLoader mapLoader, TiledMap map, World world, World worldEnemy, World canonWorld, Tank user1, TankEnemy user2, int turn, ArrayList<Sprite> arrayList, boolean isPresent,int typeUser1,int typeUser2) {
        this.game = game;
        this.hud = hud;
        this.mapLoader = mapLoader;
        this.map = map;
        this.world = world;
        this.worldEnemy = worldEnemy;
        this.canonWorld = canonWorld;
        this.user1 = user1;
        this.user2 = user2;
        this.turn = turn;
        this.arrayList = arrayList;
        this.isPresent = isPresent;
//        stage =new Stage();
        texture = new Texture("LoadScreen.png");
        entryCam = new OrthographicCamera();
        entryCam.position.set(0, 0, 0);
        bodies = new Array<Body>();

        entryPort = new FitViewport(TankStars.V_WIDTH/TankStars.PPM,TankStars.V_HEIGHT/TankStars.PPM,entryCam);
//        pause = new Skin(Gdx.files.internal("gdx-skins-master/golden-spiral/skin/golden-ui-skin.json"));
//        ImageButton nextButton = new ImageButton(pause);
//        nextButton.setSize(120, 180);
//        nextButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
//        nextButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("gdx-skins-master/golden-spiral/raw/progress-bar.png")));
//        nextButton.setPosition(900,130);
//        nextButton.addListener(new ClickListener(){
//            @Override
//            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
////                PauseScreen ps = new PauseScreen(this.game);
////                game.setScreen(ps);
//            }
//            @Override
//            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
//                return true;
//            }
//        });
//
//        stage.addActor(nextButton);
        entryCam.position.set( entryPort.getWorldWidth()/2 , entryPort.getWorldHeight()/2, 0);

        mapRenderer = new OrthogonalTiledMapRenderer(map,1/ (TankStars.PPM));
        entryCam.position.set( entryPort.getWorldWidth()/2 , entryPort.getWorldHeight()/2, 0);
        b2dRenderer = new Box2DDebugRenderer(false,false,false,false,false,false);
    }


    public PlayScreen(TankStars ts, int typeUser1, int typeUser2) {
        this.game = ts;
        this.typeUser1 = typeUser1;
        this.typeUser2 = typeUser2;
        stage =new Stage();
        texture = new Texture("LoadScreen.png");
        arrayList = new ArrayList<Sprite>();
        entryCam = new OrthographicCamera();
        entryCam.position.set(0, 0, 0);
        bodies = new Array<Body>();

        entryPort = new FitViewport(TankStars.V_WIDTH/TankStars.PPM,TankStars.V_HEIGHT/TankStars.PPM,entryCam);
        hud = new Hud(game.batch);
        entryCam.position.set( entryPort.getWorldWidth()/2 , entryPort.getWorldHeight()/2, 0);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map2.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map,1/ (TankStars.PPM));
        entryCam.position.set( entryPort.getWorldWidth()/2 , entryPort.getWorldHeight()/2, 0);
        world = new World(new Vector2(0,-10),true);
        worldEnemy = new World(new Vector2(0,-10),true);
        canonWorld = new World(new Vector2(0,-10),true);
        TankFactory tankFactory = new TankFactory(this.world,this);
        TankEnemyFactory tankEnemyFactory = new TankEnemyFactory(this.worldEnemy,this);
        user1 = tankFactory.createTank(typeUser1);
        user2 = tankEnemyFactory.createTankEnemy(typeUser2);
        canonWorld.setContactListener(new CollisionListener(this.user1,this.user2,this));
        turn =1;
        b2dRenderer = new Box2DDebugRenderer(false,false,false,false,false,false);


        for (MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
//            if(object.getProperties().get("type").equals("solid")) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                Body body;
                PolygonShape shape = new PolygonShape();
                BodyDef bdef = new BodyDef();
                FixtureDef fdef = new FixtureDef();
                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set((rect.getX() + rect.getWidth() / 2) / TankStars.PPM, (rect.getY() + rect.getHeight() / 2) / TankStars.PPM);
                body = world.createBody(bdef);
                shape.setAsBox(rect.getWidth() / 2 / TankStars.PPM, rect.getHeight() / 2 / TankStars.PPM);
                fdef.shape = shape;
                body.createFixture(fdef);
                shape.dispose();
        }
        for (MapObject object: map.getLayers().get(3).getObjects()) {
            PolygonMapObject ob = (PolygonMapObject) object;
            Body body;
            PolygonShape shape = new PolygonShape();
            BodyDef bdef = new BodyDef();
            FixtureDef fdef = new FixtureDef();
            bdef.type =  BodyDef.BodyType.StaticBody;
            bdef.position.set(ob.getPolygon().getX()/TankStars.PPM,ob.getPolygon().getY() / TankStars.PPM);
            body = world.createBody(bdef);
            float[] transformedVertices = new float[6];

            for (int i = 0; i < 6; i++) {
                transformedVertices[i] = ob.getPolygon().getVertices()[i]/TankStars.PPM;
            }
            shape.set(transformedVertices);
            fdef.shape = shape;
            body.createFixture(fdef);
            shape.dispose();

        }

        for (MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
//            if(object.getProperties().get("type").equals("solid")) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            Body body;
            PolygonShape shape = new PolygonShape();
            BodyDef bdef = new BodyDef();
            FixtureDef fdef = new FixtureDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / TankStars.PPM, (rect.getY() + rect.getHeight() / 2) / TankStars.PPM);
            body = worldEnemy.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / TankStars.PPM, rect.getHeight() / 2 / TankStars.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
            shape.dispose();
        }
        for (MapObject object: map.getLayers().get(3).getObjects()) {
            PolygonMapObject ob = (PolygonMapObject) object;
            Body body;
            PolygonShape shape = new PolygonShape();
            BodyDef bdef = new BodyDef();
            FixtureDef fdef = new FixtureDef();
            bdef.type =  BodyDef.BodyType.StaticBody;
            bdef.position.set(ob.getPolygon().getX()/TankStars.PPM,ob.getPolygon().getY() / TankStars.PPM);
            body = worldEnemy.createBody(bdef);
            float[] transformedVertices = new float[6];

            for (int i = 0; i < 6; i++) {
                transformedVertices[i] = ob.getPolygon().getVertices()[i]/TankStars.PPM;
            }
            shape.set(transformedVertices);
            fdef.shape = shape;
            body.createFixture(fdef);
            shape.dispose();

        }

        for (MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
//            if(object.getProperties().get("type").equals("solid")) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            Body body;
            PolygonShape shape = new PolygonShape();
            BodyDef bdef = new BodyDef();
            FixtureDef fdef = new FixtureDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / TankStars.PPM, (rect.getY() + rect.getHeight() / 2) / TankStars.PPM);
            body = canonWorld.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / TankStars.PPM, rect.getHeight() / 2 / TankStars.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
            shape.dispose();
        }
        for (MapObject object: map.getLayers().get(3).getObjects()) {
            PolygonMapObject ob = (PolygonMapObject) object;
            Body body;
            PolygonShape shape = new PolygonShape();
            BodyDef bdef = new BodyDef();
            FixtureDef fdef = new FixtureDef();
            bdef.type =  BodyDef.BodyType.StaticBody;
            bdef.position.set(ob.getPolygon().getX()/TankStars.PPM,ob.getPolygon().getY() / TankStars.PPM);
            body = canonWorld.createBody(bdef);
            float[] transformedVertices = new float[6];

            for (int i = 0; i < 6; i++) {
                transformedVertices[i] = ob.getPolygon().getVertices()[i]/TankStars.PPM;
            }
            shape.set(transformedVertices);
            fdef.shape = shape;
            body.createFixture(fdef);
            shape.dispose();

        }
    }

    public void save(){
        if(PlayScreen.UsersSaved>=3){
            System.out.println("Two many games saved");
        }else{
            try{
                ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(new File("save1.dat")));
                save.writeObject(this);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public static PlayScreen load(int saveNo){
        try{
            ObjectInputStream in = null;
            if(saveNo==1){
                in = new ObjectInputStream(new FileInputStream("save1.dat"));

            } else if (saveNo == 2) {
                in = new ObjectInputStream(new FileInputStream("save2.dat"));
            }else if (saveNo == 3) {
                in = new ObjectInputStream(new FileInputStream("save3.dat"));
            }
            PlayScreen ps = (PlayScreen) in.readObject();
//            return new PlayScreen(ps.game,ps.hud,ps.mapLoader,ps.map,ps.world,ps.user1,ps.user2,ps.turn,ps.arrayList,ps.isPresent);
            return ps;
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("No such file");
            return null;
        }
    }

    public void handleInput(float dt){
        if (Gdx.input.isTouched()){
            entryCam.position.x += 100*dt/TankStars.PPM;
        }
    }

    public void update(float dt){

        handleInput(dt);
//        entryCam.position.set(this.user1.position.x,this.user1.position.y,-TankStars.V_HEIGHT/2);
        entryCam.update();
        mapRenderer.setView(entryCam);
        if(turn==1) {
            user1.update(dt);
            user2.update(dt);
            if (user1.getCanonball() == null) {
//                System.out.println("Yes");
            } else {
                user1.getCanonball().update(dt);
            }
            if (user2.getCanonball() == null) {

            } else {
                user2.getCanonball().update(dt);
            }
        }else{
            user2.update(dt);
            user1.update(dt);
            if (user2.getCanonball() == null) {

            } else {
                user2.getCanonball().update(dt);
            }
            if (user1.getCanonball() == null) {

            } else {
                user1.getCanonball().update(dt);
            }
        }
        updateWorldBodies();
        hud.update(dt);
//        for (Sprite sprite :
//                arrayList) {
//            arrayList.remove(sprite);
//            Canonball c = (Canonball) sprite;
//            Body b = c.b2body;
//            if(!c.world.isLocked()) {
////                cleanUp
//                c.b2body.setUserData(null);
//                for (Fixture fixture :c.b2body.getFixtureList()) {
//                    c.b2body.destroyFixture(fixture);
//                }
//                c.world.destroyBody(c.b2body);
//            }
////            c.b2body = null;
//        }
        canonWorld.step(1 / 60f, 1, 1);


//        world.step(1/60f, 1, 6);
    }
    public void updateWorldBodies(){
        bodies.clear();
        world.getBodies(bodies);
        for (Body body : bodies) {
            Sprite spriteBody = (Sprite) body.getUserData();
            if(spriteBody!=null) {
                spriteBody.position = body.getPosition();
            }
        }
        bodies.clear();
        worldEnemy.getBodies(bodies);
        for (Body body : bodies) {
            Sprite spriteBody = (Sprite) body.getUserData();
            if(spriteBody!=null) {
                spriteBody.position = body.getPosition();
            }
        }
//        int i=0;
        bodies.clear();
        canonWorld.getBodies(bodies);
        for (Body body : bodies) {
            Sprite spriteBody = (Sprite) body.getUserData();
            if(spriteBody!=null) {
                spriteBody.position = body.getPosition();
//                System.out.println(i);
//                i++;
            }
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Hud.getHealth1()<=0||Hud.getHealth2()<=0){
            GameOver gameOver = new GameOver(this.game,typeUser1,typeUser2);
            game.setScreen(gameOver);
        }
        update(delta);
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.render();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        b2dRenderer.render(world,entryCam.combined);
        game.batch.begin();
        if(turn==1) {
            user1.draw(game.batch);
            user2.draw(game.batch);
            if (user1.getCanonball() == null) {
            } else {
                user1.getCanonball().draw(game.batch);
            }
            if (user2.getCanonball() == null) {
            } else {
                user2.getCanonball().draw(game.batch);
            }
        }else{
            user2.draw(game.batch);
            user1.draw(game.batch);
            if (user2.getCanonball() == null) {
            } else {
                user2.getCanonball().draw(game.batch);
            }
            if (user1.getCanonball() == null) {
            } else {
                user1.getCanonball().draw(game.batch);
            }
        }
//        cT.draw(game.batch);
        game.batch.end();
//        stage.act();
//        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        entryPort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
//        hud.dispose();
    }
}
