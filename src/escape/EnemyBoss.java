/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Hasaan Majeed
 */
public class EnemyBoss extends GameObject {

    private Handler hnd;
    private int timer = 80;
    private int timer2 = 50;
    Random r = new Random();
    
    public EnemyBoss(int x, int y, ID id, Handler hnd) {
        super(x, y, id);
        
        this.hnd = hnd;
        
        velX = 0;
        velY = 2;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,96,96);
    }
    
    public void tick(){
        x+=velX;
        y+=velY;
        
        if(timer<=0) velY = 0;
        else timer--;
        
        if(timer<=0) timer2--;
        if(timer2<=0){
            if(velX == 0) velX=2;
            
            if(velX>0) velX += 0.005f;
            else if(velX < 0) velX -= 0.005f;
            
            velX = Game.clamp(velX, -10, 10);
            
            int spawn = r.nextInt(10);
            if(spawn == 0) hnd.addObject(new EnemyBossBullet((int)x+48, (int)y+48, id.BasicEnemy, hnd));
        }
        if(x<=0 || x>=Game.WIDTH - 96) velX*=-1;
    }
    
    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 96, 96);
    }
}
