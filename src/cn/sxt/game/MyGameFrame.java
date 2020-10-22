package cn.sxt.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;


public class MyGameFrame extends Frame {
    Image plane = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");
    plane p = new plane(plane,100,100);
    Shell [] s = new Shell[50];
    Date startTime = new Date();
    Date endTime ;
    int time;
    Explode bao;
    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        Color c = g.getColor();
        g.drawImage(bg,0,0,null);

        p.drawSelf(g);
        for(int i = 0;i<s.length;i++) {
            s[i].draw(g);
            boolean peng = s[i].getRect().intersects(p.getRect());

            if (peng) {
                p.live = false;
                if (bao == null) {
                    bao = new Explode(p.x, p.y);

                    endTime = new Date();
                    time = (int) (endTime.getTime() - startTime.getTime()) / 1000;
                }
                bao.draw(g);
            }
            if (!p.live) {
                g.setColor(Color.yellow);
                Font f = new Font("宋体", Font.BOLD, 50);
                g.setFont(f);
                g.drawString("你坚持了" + time + "秒！", (int) p.x, (int) p.y);
            }
        }
        g.setColor(c);
    }

    class PaintThread extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class keyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            p.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            p.minusDirection(e);
        }
    }

    public void launchFrame(){
        this.setTitle("飞机大战！");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDH,Constant.GAME_HEIGHT);
        this.setLocation(300,100);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        });
        new PaintThread().start();
        addKeyListener(new keyMonitor());

        for(int i = 0;i<s.length;i++){
            s[i] = new Shell();
        }
    }
    private Image offScreenImage = null;

    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(Constant.GAME_WIDH,Constant.GAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }

    public static  void main(String[] args){
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
}
