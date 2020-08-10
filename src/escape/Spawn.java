/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import java.util.Random;

/**
 *
 * @author Hasaan Majeed
 */
public class Spawn {
    
    private Handler hnd;
    private HUD hud;
    private Game game;
    private int scoreKeep = 0;
    private Random r = new Random();

    public Spawn(Handler hnd, HUD hud,Game game) {
        this.hnd = hnd;
        this.hud = hud;
        this.game = game; 
    }
    
    public void tick(){
        scoreKeep++;
        
        if(scoreKeep>=500){
            scoreKeep = 0;
            if(hud.getLevel()<11){
                hud.setLevel(hud.getLevel()+1);
            }
            //Normal Mode
            if(game.diff==0){
                if(hud.getLevel() == 2){
                    hnd.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 3){
                    hnd.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 4){
                    hnd.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, hnd));
                }
                else if(hud.getLevel() == 5){
                    hnd.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 6){
                    hnd.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, hnd));
                }
                else if(hud.getLevel() == 7){
                    hnd.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, hnd));
                }
                else if(hud.getLevel() == 8){
                    hnd.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 9){
                    hnd.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 10){
                    //SoundPlayer.playMusic("res\\background2.wav");
                    hnd.clearEnemies();
                    hnd.addObject(new EnemyBoss((Game.WIDTH/2)-48, -150, ID.EnemyBoss, hnd));
                }
            }
            //Hard Mode
            else if(game.diff==1){
                if(hud.getLevel() == 2){
                    hnd.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 3){
                    hnd.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 4){
                    hnd.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, hnd));
                }
                else if(hud.getLevel() == 5){
                  hnd.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 6){
                    hnd.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, hnd));
                }
                else if(hud.getLevel() == 7){
                    hnd.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, hnd));
                }
                else if(hud.getLevel() == 8){
                  hnd.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 9){
                  hnd.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
                }
                else if(hud.getLevel() == 10){
                    //SoundPlayer.playMusic("res\\background2.wav");
                    hnd.clearEnemies();
                    hnd.addObject(new EnemyBoss((Game.WIDTH/2)-48, -150, ID.EnemyBoss, hnd));
                }
            }
        }
    }
}
