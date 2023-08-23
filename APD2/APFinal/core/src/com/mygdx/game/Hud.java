package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private static Integer Health1;
    private static Integer Health2;
    private static Integer Fuel1;
    private static Integer Fuel2;
    private int timeC;
    static Label u1Fuel;
    static Label u2Fuel;
    static Label h1Label;
    static Label h2Label;
    static Label f1Label;
    static Label f2Label;
    private Label user1Label;
    private Label user2Label;
    Label modeLabel;

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public static Integer getHealth1() {
        return Health1;
    }

    public static void setHealth1(Integer health1) {
        Health1 = health1;
    }

    public static Integer getHealth2() {
        return Health2;
    }

    public static void setHealth2(Integer health2) {
        Health2 = health2;
    }

    public static Integer getFuel1() {
        return Fuel1;
    }

    public static Integer getFuel2() {
        return Fuel2;
    }

    public int getTimeC() {
        return timeC;
    }

    public void setTimeC(int timeC) {
        this.timeC = timeC;
    }

    public Hud(SpriteBatch sb){
        Health1 = 300;
        Health2 = 300;
        Fuel1=100;
        Fuel2=100;
        viewport = new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        h1Label = new Label(String.format("%03d",Health1), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        h2Label = new Label(String.format("%03d",Health2), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        user1Label = new Label("USER1",new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        user2Label = new Label("USER2", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        f1Label = new Label("FUEL1",new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        f2Label = new Label("FUEL2", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        u1Fuel= new Label(String.format("%03d",Fuel1), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        u2Fuel = new Label(String.format("%03d",Fuel2), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        modeLabel = new Label("1v1", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        table.add(modeLabel).expandX().padTop(0);
        table.row();
        table.add(user1Label).expandX().padTop(10);
        table.add(user2Label).expandX().padTop(10);
        table.row();
        table.add(h1Label).expandX().padTop(10);
        table.add(h2Label).expandX().padTop(10);
        table.row();
        table.add(f1Label).expandX().padTop(10);
        table.add(f2Label).expandX().padTop(10);
        table.row();
        table.add(u1Fuel).expandX().padTop(10);
        table.add(u2Fuel).expandX().padTop(10);
        stage.addActor(table);
    }
    public static void decreaseHealth1(int value){
        Health1-=value;
        System.out.println(Health1);
        h1Label.setText(String.format("%03d",Health1));
    }
    public static void decreaseHealth2(int value){
        Health2-=value;
        h2Label.setText(String.format("%03d",Health2));
    }

    public static void setFuel1(Integer fuel1) {
        Fuel1 = fuel1;
        u1Fuel.setText(String.format("%03d",Fuel1));
    }

    public static void setFuel2(Integer fuel2) {
        Fuel2 = fuel2;
        u2Fuel.setText(String.format("%03d",Fuel2));

    }

    public void update(float dT){
        timeC+=dT;
    }

}
