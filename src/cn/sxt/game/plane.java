package cn.sxt.game;


import java.awt.*;
import java.awt.event.KeyEvent;

public class plane extends GameObject {
    boolean up,down,left,right;
    boolean live = true;

    public plane(Image img, double x, double y) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.speed = 5;
        this.width = 15;
        this.height = 15;
    }

    @Override
    public void drawSelf(Graphics g) {
        //super.drawSelf(g);
        if(live) {
            g.drawImage(img, (int) x, (int) y, null);

            if (left)
                x -= speed;
            if (right)
                x += speed;
            if (up)
                y -= speed;
            if (down)
                y += speed;
        }
    }

    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                        right = true;
                        break;
            case KeyEvent.VK_UP:
                    up = true;
                    break;
            case KeyEvent.VK_DOWN:
                    down = true;
                    break;
            default:
                break;
        }
    }

    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                    left = false;
                    break;
            case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                    down = false;
                    break;
        }
    }
}
