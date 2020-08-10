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
public class Player extends GameObject{

    Random r = new Random();
    Handler hnd;
    GameObject boss;
    
    public Player(int x, int y, ID id, Handler hnd) {
        super(x, y, id);
        this.hnd = hnd;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }

    
    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        
        x = Game.clamp(x, 0, Game.WIDTH - 37);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
        
        hnd.addObject(new Trail((int)x, (int)y, id, Color.white, 32, 32, 0.05f , hnd));
        
        collision();
       
    }

    private void collision(){
        for(int i = 0;i<hnd.object.size(); i++){
            
            GameObject temp = hnd.object.get(i);  
            //collision code
            if(temp.getId() == ID.BasicEnemy || temp.getId() == ID.FastEnemy){
               if(getBounds().intersects(temp.getBounds())){
                    HUD.HEALTH -= 2;
                }
            }
            
            if(temp.getId() == ID.HardEnemy){
                if(getBounds().intersects(temp.getBounds())){
                    HUD.HEALTH -= 5;
                }
            }
            
            if(temp.getId() == ID.EnemyBoss){
                if(getBounds().intersects(temp.getBounds())){
                    HUD.HEALTH = 0;
                }
            }
            
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
    }
    
}
