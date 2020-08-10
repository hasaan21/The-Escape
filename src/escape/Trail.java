/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Hasaan Majeed
 */
public class Trail extends GameObject{

    private float alpha = 1;
    private float life;
    
    private Handler hnd;
    private Color c;
    
    private int width,height;
    
    //life = 0.001 - 0.1
    
    public Trail(int x, int y, ID id, Color c, int width, int height, float life, Handler hnd) {
        super(x, y, id);
        this.c = c;
        this.width = width;
        this.height = height;
        this.life = life;
        this.hnd = hnd;
    }

    @Override
    public void tick() {
        if(alpha > life){
            alpha -= (life - 0.0001f);
        }
        else
            hnd.removeObject(this);
    }
    
    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type,alpha));
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        
        g.setColor(c);
        g.fillRect((int)x, (int)y, width, height);
        
        g2d.setComposite(makeTransparent(1));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
}
