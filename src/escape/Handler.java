/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Hasaan Majeed
 */
public class Handler {
    LinkedList<GameObject> object = new LinkedList<>();
    
    public void tick(){
        for(int i = 0; i<object.size(); i++){
            GameObject  temp = object.get(i);
            temp.tick();
        }
    }
    
    public void render(Graphics g){
        for(int i = 0; i<object.size(); i++){
            
            try {
                GameObject  temp = object.get(i);
                temp.render(g);
            } catch (Exception e) {
            }
        }
    }
    
    public void clearEnemies(){
        for(int i = 0; i<object.size(); i++){
            GameObject  temp = object.get(i);
            
            if(temp.getId()==ID.Player){
                object.clear();
                if(Game.gameState != Game.STATE.End)
                addObject(new Player((int)temp.getX(), (int)temp.getY(), ID.Player, this));
            }
        }
    }
    
    public void addObject(GameObject obj){
        this.object.add(obj);
    }
    
    public void removeObject(GameObject obj){
        this.object.remove(obj);
    }
}