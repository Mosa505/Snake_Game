
package game.snake_game;

import java.util.Random;


public class food {
    
    private final int posX;
    private final int posY;
    
    public  food(){
    posX = generatePos(Graphics.width);
    posY = generatePos(Graphics.height);

    
    }
    private int generatePos (int size ){
    Random random = new Random ();
    return random.nextInt(size /Graphics.Tick_size)* Graphics.Tick_size;
    
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    
    
    
}
