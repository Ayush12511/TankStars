package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.PlayScreen;
import com.mygdx.game.TankStars;

public class StateSelect implements Screen {
    private TankStars game;
    private final Texture texture;
    private final Texture start;
    private final Texture resume;
    private final Texture exit;

    public StateSelect(TankStars game) {
        this.game = game;
        texture = new Texture("blankr.jpg");
        start = new Texture("StartButton.png");
        resume = new Texture("resumer.png");
        exit = new Texture("exitr.png");
    }
    public void handleInput(){
        if (Gdx.input.justTouched()) {
            if ((Gdx.input.getX() > (TankStars.V_WIDTH / 2 - start.getWidth() / 2) - 5 && Gdx.input.getY() < 720 - (TankStars.V_HEIGHT / 6) && Gdx.input.getX() < (TankStars.V_WIDTH / 2 - start.getWidth() / 2) + 315) && Gdx.input.getY() > 675 - (TankStars.V_HEIGHT / 6 + 100)) {
                tankSelecter mp=new tankSelecter(game);
                dispose();
                game.setScreen(mp);
            }
            if ((Gdx.input.getX() > (TankStars.V_WIDTH / 2 - start.getWidth() / 2- start.getWidth() / 2) - 5 && Gdx.input.getY() < 720 - (TankStars.V_HEIGHT / 6) && Gdx.input.getX() < (TankStars.V_WIDTH / 2 - start.getWidth() / 2- start.getWidth() / 2) + 315) && Gdx.input.getY() > 675 - (TankStars.V_HEIGHT / 6 + 200)) {
                tankSelecter mp=new tankSelecter(game);
                dispose();
                game.setScreen(mp);
            }
            if ((Gdx.input.getX() > (TankStars.V_WIDTH / 2 + start.getWidth() - start.getWidth() / 2) - 5 && Gdx.input.getY() < 720 - (TankStars.V_HEIGHT / 6) && Gdx.input.getX() < (TankStars.V_WIDTH / 2+ start.getWidth() - 150 - start.getWidth() / 2) + 315) && Gdx.input.getY() > 675 - (TankStars.V_HEIGHT / 6 + 200)) {
                MenuScreen mp=new MenuScreen(game);
                dispose();
                game.setScreen(mp);
            }
        }
    }
    @Override
    public void show() {

    }
    public void update(float dt){handleInput();}
    @Override
    public void render(float delta) {
        update(delta);
        game.batch.begin();
        game.batch.draw(texture, 0, 0);
        game.batch.draw(start, (TankStars.V_WIDTH / 2 - start.getWidth() / 2) - 5, TankStars.V_HEIGHT / 6);

        game.batch.draw(resume, (TankStars.V_WIDTH / 2 - start.getWidth()) - 200, TankStars.V_HEIGHT / 6);
        game.batch.draw(exit, (TankStars.V_WIDTH / 2) + start.getWidth() - 150, TankStars.V_HEIGHT / 6);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        texture.dispose();
        start.dispose();
    }
}
