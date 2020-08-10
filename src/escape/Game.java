package escape;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hasaan Majeed
 */
public class Game extends Canvas implements Runnable{
    
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    
    public static boolean paused = false;
    public int diff = 0; //0=normal, 1=hard
    
    private Random r;
    private Handler hnd;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    
    public enum STATE{
        Menu(),
        Game(),
        Select(),
        Help(),
        End()
    };
    
    public static STATE gameState = STATE.Menu;
    
    public Game(){
        hnd = new Handler();
        hud = new HUD();
        menu = new Menu(this,hnd,hud);
        this.addKeyListener(new KeyboardListener(hnd,this));
        this.addMouseListener(menu);
        
        SoundPlayer.playMusic("res\\background.wav");
        new Window(WIDTH, HEIGHT, "The Escape: Save Your Buddy!",this);
        this.requestFocus();
        spawner = new Spawn(hnd, hud, this);
        r = new Random();
        if(gameState == STATE.Game){
            hnd.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, hnd));
            hnd.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, hnd));
            
        }
        else{
           for(int i = 0; i < 20; i++){
               hnd.addObject(new MenuParticles(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.MenuParticle, hnd));
           } 
        }
        
        
    }
      
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        this.requestFocus();
        running = true;
    }
    
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        this.requestFocus(); //To active window automatically
        //Game Loops
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer +=1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){
        if(gameState == STATE.Game){
            if(!paused){
                hnd.tick();
                hud.tick();
                spawner.tick();
                if(HUD.HEALTH <= 0){
                    SoundPlayer.playSound("res\\gameover2.wav");
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    hnd.clearEnemies();
                    for(int i = 0; i < 20; i++){
                        hnd.addObject(new MenuParticles(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.MenuParticle, hnd));
                    }
                }
            }
        }
        else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Help){
            hnd.tick();
        }
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        hnd.render(g);
        
        if(paused){
            g.setColor(Color.white);
            g.drawString("PAUSED", 100, 100);
        }
        
        if(gameState == STATE.Game){
            hud.render(g);
        }
        else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
            menu.render(g);
        }
        
        g.dispose();
        bs.show();
    }
    
    public static float clamp(float v,float min,float max){
        if(v >= max)
            return v = max;
        else if(v<=min)
            return v = min;
        else
            return v;
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
}
