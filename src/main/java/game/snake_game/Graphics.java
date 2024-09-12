
package game.snake_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;


public class Graphics extends JPanel implements ActionListener {
 
    static final int width =800;
    static final int height = 800;
    static final int Tick_size = 50;
    static final int Board_size =(width*height)/(Tick_size * Tick_size);
    
    final Font font  =new Font("TimesRoman",Font.BOLD,30);
    
   
    int[] SnakeposX=new int [Board_size];
    int[] SnakeposY=new int [Board_size];
    int Snake_lenght;
    food food ;
    int foodEaten; 
    
    int[][] walls;
 

    
    char direction ='R';
    boolean isMoving=false;
    final Timer timer = new Timer(150, (ActionListener) this);


    public Graphics(){
    this.setPreferredSize(new Dimension(width, height));
    this.setBackground(Color.white);
    this.setFocusable(true);
    this.addKeyListener(new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e) {
            if(isMoving){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R')
                    {
                    direction ='L';
                    }
                    break;
                    
                    case KeyEvent.VK_RIGHT:
                    if(direction != 'L')
                    {
                    direction ='R';
                    }
                    break;
                    
                    case KeyEvent.VK_UP:
                    if(direction != 'D')
                    {
                    direction ='U';
                    }
                    break;
                    case KeyEvent.VK_DOWN:
                    if(direction != 'U')
                    {
                    direction ='D';
                    }
                        
                    break;
            
            }
            
            }
            else{
            Start();
            }
        }
    
    
    });
    
    
    Start();
    }
    
    protected void Start(){
    SnakeposX =new int[Board_size];
    SnakeposY =new int[Board_size];
    Snake_lenght =5;
    direction ='R';
    foodEaten=0;
    isMoving =true;
    timer.start();
    initWalls();
    spawnFood();
     
    
    }
    
    
    @Override
    protected void paintComponent(java.awt.Graphics g ){
    super.paintComponent(g);
    g.setColor(Color.GRAY);
    for (int[] wall : walls) {
        g.fillRect(wall[0], wall[1], Tick_size, Tick_size);
    }
    if(isMoving){
        g.setColor(Color.red);
        g.fillOval(food.getPosX(),food.getPosY() ,Tick_size , Tick_size );
    g.setColor(Color.BLACK);
    for(int i =0;i<Snake_lenght ; i++){
    g.fillRect(SnakeposX[i],SnakeposY[i],Tick_size,Tick_size);
    
    
    }
    
    }
    else {
        
        String scoreText = String.format("THE END.... Score: %d.... Press any Key TO Play again!", foodEaten);
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString(scoreText, (width - getFontMetrics(g.getFont()).stringWidth(scoreText)) / 2, height / 2);
    }
    
    
    }
    
    protected void move(){
    for(int i =Snake_lenght ; i >0 ; i--){
    
    SnakeposX[i]=SnakeposX[i-1];
    SnakeposY[i]=SnakeposY[i-1];

    }
    
    switch (direction){
        case 'U' -> SnakeposY[0]-= Tick_size;
        case 'D' -> SnakeposY[0]+= Tick_size;
        case 'L' -> SnakeposX[0]-= Tick_size;
        case 'R' -> SnakeposX[0]+= Tick_size;


    
    }
    
    }
    
    protected void spawnFood(){
    food =new food();
    
    }
    
    
    protected void eatFood() {
    if (SnakeposX[0] == food.getPosX() && SnakeposY[0] == food.getPosY()) {
        Snake_lenght++;
        foodEaten++;
        spawnFood();  
    }
}

    
    protected void collisionTest (){
    for(int i = Snake_lenght;i>0 ; i--){
    if((SnakeposX[0]==SnakeposX[i])&&(SnakeposY[0]==SnakeposY[i])){
    isMoving=false;
    break;
    }
    if(SnakeposX[0]<0 ||SnakeposX[0]>width-Tick_size || SnakeposY[0]<0 || SnakeposY[0]>height-Tick_size){
    isMoving =false;
    
    }
    for (int[] wall : walls) {
        if (SnakeposX[0] == wall[0] && SnakeposY[0] == wall[1]) {
            isMoving = false;
            break;
        }
    }
    if(!isMoving){
    timer.stop();
    }
    
    }
    
    }
    
    protected void initWalls() {
    walls = new int[][] {
        {100, 100}, {150, 100}, {200, 100}, 
        {300, 300}, {300, 350}, {300, 400}, 
        
    };
}

    
   
   
    @Override
    public void actionPerformed(ActionEvent e){
    if(isMoving){
        move();
        collisionTest();
        eatFood();
        
    }
    repaint();
    }
    
}
