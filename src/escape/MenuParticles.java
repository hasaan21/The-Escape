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
public class MenuParticles extends GameObject {

    private Handler hnd;
    Random r = new Random();
    private Color col;
    
    public MenuParticles(int x, int y, ID id, Handler hnd) {
        super(x, y, id);
        
        this.hnd = hnd;
        
        velX = (r.nextInt(14) -7);
        velY = (r.nextInt(14) -7);
        if(velX == 0) velX = 1;
        if(velY == 0) velY = 1;
      
        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
    
    public void tick(){
        x+=velX;
        y+=velY;
        
        if(y<=0 || y>=Game.HEIGHT - 32) velY*=-1;
        if(x<=0 || x>=Game.WIDTH - 16) velX*=-1;
        
        hnd.addObject(new Trail((int)x, (int)y, id.Trail, col, 16, 16, 0.05f , hnd));
    }
    
    public void render(Graphics g){
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
