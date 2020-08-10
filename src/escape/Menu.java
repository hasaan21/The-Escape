/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import escape.Game.STATE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 *
 * @author Hasaan Majeed
 */
public class Menu extends MouseAdapter{
    
    private Game game;
    private Handler hnd;
    private HUD hud;
    private Random r = new Random();
    
    public Menu(Game game, Handler hnd, HUD hud){
        this.game = game;
        this.hnd = hnd;
        this.hud = hud;
    }
    
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(game.gameState == STATE.Menu){
            //Play Button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                SoundPlayer.playSound("res\\button.wav");
                game.gameState = STATE.Select;
                return;
            }
        
            //Help Button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                SoundPlayer.playSound("res\\button.wav");
                game.gameState = STATE.Help;
            }
        
            //Quit Button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                SoundPlayer.playSound("res\\button.wav");
                System.exit(1);
            }       
        }
        
        if(game.gameState == STATE.Select){
            //Normal Button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                SoundPlayer.playSound("res\\button.wav");
                game.gameState = STATE.Game;
                hnd.addObject(new Player(game.getWidth()/2-32, game.getHeight()/2-32, ID.Player, hnd));
                hnd.clearEnemies();
                hnd.addObject(new BasicEnemy(r.nextInt(game.getWidth() - 50), r.nextInt(game.getHeight() - 50), ID.BasicEnemy, hnd));
                
                game.diff = 0;
            }
        
            //Hard Button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                SoundPlayer.playSound("res\\button.wav");
                game.gameState = STATE.Game;
                hnd.addObject(new Player(game.getWidth()/2-32, game.getHeight()/2-32, ID.Player, hnd));
                hnd.clearEnemies();
                hnd.addObject(new HardEnemy(r.nextInt(game.getWidth() - 50), r.nextInt(game.getHeight() - 50), ID.BasicEnemy, hnd));
                
                game.diff = 1;
            }
        
            //Back Button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                SoundPlayer.playSound("res\\button.wav");
                game.gameState = STATE.Menu;
                return;
            }       
        }
        
        //Back button for Help
        if(game.gameState == STATE.Help){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                SoundPlayer.playSound("res\\button.wav");
                game.gameState = STATE.Menu;
                return;
            }
        }
        
        //Back button for Try Again
        if(game.gameState == STATE.End){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                SoundPlayer.playSound("res\\button.wav");
                hud.setLevel(1);
                hud.setScore(0);
                game.gameState = STATE.Menu;
            }
        }
    }
    
    
    private boolean mouseOver(int mx,int my, int x,int y,int width, int height){
        if(mx > x && mx < x+width){
            if(my > y && my < y+height){
                return true;
            }
            else return false;
        }
        else return false;
    }
    
    
    public void render(Graphics g){
        if(game.gameState == STATE.Menu){
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,30);
        
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("The Escape", 170, 70);
        
            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);
        
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);
        
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);
        }
        else if(game.gameState == STATE.Help){
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,30);
            Font fnt3 = new Font("arial",1,20);
            
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);
            
            g.setFont(fnt3);
            g.drawString("1. Use Arrow Keys to Control Player and Dodge Enemies", 40, 150);
            g.drawString("2. Player's Power Decrease After Collision with Enemy's Head", 40, 180);
            g.drawString("3. Collision With the Boss Costs Complete Life", 40, 210);
            g.drawString("4. Press P to pause during Game", 40, 240);
            
            g.setFont(fnt2);
            
            g.drawString("Developed By: HM Games", 140, 310);
            
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }
        else if(game.gameState == STATE.End){
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,30);
            Font fnt3 = new Font("arial",1,20);
            
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 180, 70);
            
            g.setFont(fnt3);
            g.drawString("You Score: " + hud.getScore(), 225, 200);
            
            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);
        }
        else if(game.gameState == STATE.Select){
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,30);
        
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("SELECT DIFFICULTY", 70, 70);
        
            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 270, 190);
        
            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 270, 290);
        
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }
        
    }
}
