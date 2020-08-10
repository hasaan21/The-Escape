/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Hasaan Majeed
 */
public class FastEnemy extends GameObject {

    private Handler hnd;
    
    public FastEnemy(int x, int y, ID id, Handler hnd) {
        super(x, y, id);
        
        this.hnd = hnd;
        
        velX = 2;
        velY = 9;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
    
    public void tick(){
        x+=velX;
        y+=velY;
        
        if(y<=0 || y>=Game.HEIGHT - 32) velY*=-1;
        if(x<=0 || x>=Game.WIDTH - 16) velX*=-1;
        
        hnd.addObject(new Trail((int)x, (int)y, id.Trail, Color.cyan, 16, 16, 0.02f , hnd));
    }
    
    public void render(Graphics g){
        g.setColor(Color.cyan);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
