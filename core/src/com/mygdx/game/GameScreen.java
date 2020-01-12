package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
    SpriteBatch batch;
    private Texture snakeHead;
    private static final float MOVE_TIME=1F;
    private static final int SNAKE_MOVEMENT=32;
    private int snakeX=0,snakeY=0;
    private float timer=MOVE_TIME;
    @Override
    public void show () {
        batch = new SpriteBatch();
        snakeHead=new Texture(Gdx.files.internal("snakehead.png"));
    }
    @Override
    public void render (float delta) {
        timer-=delta;
        if(timer<=0)
        {
            timer=MOVE_TIME;
           // snakeX+=SNAKE_MOVEMENT;
        }
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                System.out.println(screenX+" "+screenY);
                snakeX = screenX;
                snakeY= Gdx.graphics.getHeight()-screenY;
                return super.touchDown(screenX, screenY, pointer, button);
            }
        });
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(snakeHead,snakeX,snakeY);
        batch.end();
    }

    private void checkForOutOfBounds()
    {
        if(snakeX>=Gdx.graphics.getWidth())
        {
            snakeX=0;
        }
    }
}
