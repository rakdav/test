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
    private static final int RIGHT=0;
    private static final int LEFT=1;
    private static final int UP=2;
    private static final int DOWN=3;
    private int snakeDirection=RIGHT;
    private int curX1,curY1,curX2,curY2;
    private void moveSnake()
    {
        switch (snakeDirection)
        {
            case RIGHT:snakeX+=SNAKE_MOVEMENT;
                        return;
            case LEFT:snakeX-=SNAKE_MOVEMENT;
                return;
            case UP:snakeY+=SNAKE_MOVEMENT;
                return;
            case DOWN:snakeY-=SNAKE_MOVEMENT;
                return;
        }
    }
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
            if(Math.abs(curX2-curX1)>Math.abs(curY2-curY1)&&curX2-curX1>0) snakeDirection=RIGHT;
            if(Math.abs(curX2-curX1)>Math.abs(curY2-curY1)&&curX2-curX1<0) snakeDirection=LEFT;
            if(Math.abs(curX2-curX1)<Math.abs(curY2-curY1)&&curY2-curY1<0) snakeDirection=DOWN;
            if(Math.abs(curX2-curX1)<Math.abs(curY2-curY1)&&curY2-curY1>0) snakeDirection=UP;
            moveSnake();
            checkForOutOfBounds();
        }
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                System.out.println(screenX+" "+screenY);
                curX1 = screenX;
                curY1= Gdx.graphics.getHeight()-screenY;
//                if(snakeX<curX) snakeDirection=RIGHT;
//                else if(snakeX>curX) snakeDirection=LEFT;
//                if(snakeY>curY) snakeDirection=DOWN;
//                else if(snakeY<curY) snakeDirection=UP;

                return super.touchDown(screenX, screenY, pointer, button);
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                curX2=screenX;
                curY2= Gdx.graphics.getHeight()-screenY;
                return super.touchUp(screenX, screenY, pointer, button);
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
        if(snakeX<0)
        {
            snakeX=Gdx.graphics.getWidth()-SNAKE_MOVEMENT;
        }
        if(snakeY<0)
        {
            snakeY=Gdx.graphics.getHeight()-SNAKE_MOVEMENT;
        }
        if(snakeY>=Gdx.graphics.getHeight())
        {
            snakeY=0;
        }
    }
}
