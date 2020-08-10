/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import escape.Game.STATE;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Hasaan Majeed
 */
public class KeyboardListener extends KeyAdapter {
    
    private Handler hnd;
    private boolean[] keyDown = new boolean[4];
    Game game;
    
    public KeyboardListener(Handler hnd,Game game) {
        this.hnd = hnd;
        this.game=game;
        for(int i=0;i<4;i++){
            keyDown[i] = false;
        }
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i=0; i< hnd.object.size(); i++){
            try {
                GameObject tempObj = hnd.object.get(i);
            
                if(tempObj.getId() == ID.Player){
                    if(key == KeyEvent.VK_UP) { tempObj.setVelY(-5); keyDown[0] = true; }
                    if(key == KeyEvent.VK_DOWN) { tempObj.setVelY(5); keyDown[1] = true; }
                    if(key == KeyEvent.VK_RIGHT) { tempObj.setVelX(5); keyDown[2] = true; }
                    if(key == KeyEvent.VK_LEFT) { tempObj.setVelX(-5); keyDown[3] = true; }
                }
            } catch (Exception exp) {
            }
        }
        
        if(key == KeyEvent.VK_P){
            if(game.gameState==STATE.Game){
                if(Game.paused) Game.paused = false;
                else Game.paused = true;
                }
            }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i=0; i< hnd.object.size(); i++){
            
            try {
                GameObject tempObj = hnd.object.get(i);
            
                if(tempObj.getId() == ID.Player){
                    if(key == KeyEvent.VK_UP) keyDown[0] = false; 
                    if(key == KeyEvent.VK_DOWN) keyDown[1] = false; 
                    if(key == KeyEvent.VK_RIGHT) keyDown[2] = false; 
                    if(key == KeyEvent.VK_LEFT) keyDown[3] = false; 
            
                    //vertical Movement
                    if(!keyDown[0] && !keyDown[1]) tempObj.setVelY(0);
                    //Horizontal Movement
                    if(!keyDown[2] && !keyDown[3]) tempObj.setVelX(0);
                }
            } catch (Exception exp) {}
        }
    }
}