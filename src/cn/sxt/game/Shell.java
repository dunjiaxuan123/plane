package cn.sxt.game;

import java.awt.*;
import java.awt.Rectangle;

public class Shell extends GameObject {
    double degree;
    public Shell(){
        x = 450;
        y = 450;
        width = 10;
        height = 10;
        speed = 5;

        degree = Math.random()*Math.PI*2;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.yellow);

        g.fillOval((int)x,(int)y,width,height);
        x += speed*Math.cos(degree);
        y += speed*Math.sin(degree);

        if(x < 0|| x > Constant.GAME_WIDH-width)
        {
            degree = Math.PI - degree;
        }
        if(y < 30 || y>Constant.GAME_HEIGHT-height)
        {
            degree = - degree;
        }
        g.setColor(c);
    }

}
