import javax.swing.*; // for JPanel and JComponent
import java.awt.*; // for abstract class Graphics
import java.awt.event.*; // for Action Listener and event and key event
import java.util.Random; // for Random element

public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int ObjectSize= 25;
    static final int NoOfObjects= (SCREEN_HEIGHT*SCREEN_WIDTH)/ObjectSize;
    static final int ScreenRate = 150;
    final int x[] = new int[NoOfObjects];
    final int y[] =  new int[NoOfObjects];
    int bodyParts = 24;
    int EatenFood=30;
    boolean running;
    char DirectionOfSnake = 'R';
    int XFoodCoordinate;
    int YFoodCoordinate;
    Random random;
    Timer timer;
    GamePanel(){
        random = new Random(); // creating random object
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT)); // sets the panel size
        this.setFocusable(true);
        this.setBackground(Color.black); // backgroung is set black
        this.addKeyListener(new MyKeyAdapter()); // adds the keylistener to panel, makes arrow keys to work
        startGame();

    }
    public void startGame(){
        newFood();
        running = true;
        timer= new Timer(ScreenRate,this); // there are two timers. timer is imported from javax.swing instead of java.util package
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g); // refers to g at paintComponent
        draw(g);
    }
    public void draw(Graphics g){

        if(running) {
            /*
/           for (int i = 0; i < SCREEN_WIDTH; i++) {
               g.drawLine(0, i * ObjectSize, SCREEN_WIDTH, i * ObjectSize);
                g.drawLine(i * ObjectSize, 0, i * ObjectSize, SCREEN_HEIGHT);
           }
           */
            g.setColor(Color.gray); // food color is set gray
            g.fillOval(XFoodCoordinate, YFoodCoordinate, ObjectSize, ObjectSize); // food shape and its properties

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.gray); //body color is set gray
                    g.fillRect(x[i], y[i], ObjectSize, ObjectSize); // body properties
                } else {
                    g.setColor(Color.lightGray); // head color is set light gray
                    g.fillRect(x[i], y[i], ObjectSize, ObjectSize); // head properties is set
                }
            }
            g.setColor(Color.GRAY); // this is for the Score and its properties
            g.setFont(new Font("Verdana", Font.BOLD,25));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+EatenFood, (SCREEN_WIDTH - metrics.stringWidth("Score: "))/2,g.getFont().getSize());
        }
        else{
            GameOver(g);
        }
    }
    public void newFood(){ // generates the food in random location on screen using random object
          XFoodCoordinate = random.nextInt((int)(SCREEN_WIDTH/ObjectSize))*ObjectSize;
          YFoodCoordinate = random.nextInt((int)(SCREEN_HEIGHT/ObjectSize))*ObjectSize;
    }
    public void move(){
        for(int i=bodyParts;i>0;i--){ // moves the snake subtracting i
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch(DirectionOfSnake){ //changes direction
            case 'U':
             y[0]= y[0]-ObjectSize;
             break;
            case 'D':
             y[0]=y[0]+ObjectSize;
             break;
            case 'L':
                x[0]=x[0]-ObjectSize;
                break;
            case 'R':
                x[0]=x[0]+ObjectSize;
                break;
        }

    }
    public void checkFood(){ //  For Food
        if((x[0] == XFoodCoordinate) && (y[0] == YFoodCoordinate)){ // if snake's heads x  and y coordinate collides with the food's x and y coordinate
            bodyParts++; // if it collides body part grows one more rectangle
            EatenFood++; // adds 1 more points to score
            newFood();
        }


    }
    // for head to head  collision
    public void detectCollision(){
        for(int i= bodyParts; i>0;i--){ //checks if head collides with the body
            if((x[0])== x[i] && (y[0]==y[i])){ // checks if the x and y coordinate of the head collides with the x and y coordinate of the snake's body
                running=false; // if it does collide then the snake is stopped from running
            }
        }
        // for head to wall collision
        if(x[0]<0){
            running= false; //checks if head touches left border
        }
        if(x[0]>SCREEN_WIDTH){ //checks if head touches right border
            running=false;
        }
        if(y[0]<0){
            running= false; //checks if head touches left border
        }
        if(y[0]>SCREEN_HEIGHT){ //checks if head touches right border
            running=false;
        }
        if(!running){
            timer.stop();
        }

    }
    public void GameOver(Graphics g){
        // Properties for Score:
        g.setColor(Color.GRAY);
        g.setFont(new Font("Verdana", Font.BOLD,25));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: "+EatenFood, (SCREEN_WIDTH - metrics.stringWidth("Score: "))/2,g.getFont().getSize());

        // Properties for Game Over
        g.setColor(Color.GRAY);
        g.setFont(new Font("Verdana", Font.BOLD,75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(running){
            move();
            checkFood();
            detectCollision();
        }
        repaint();

    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){ // makes the keys valid
                case KeyEvent.VK_LEFT :  // moves the snake left
                    if(DirectionOfSnake !='R')
                        DirectionOfSnake = 'L';
                    break;
                case KeyEvent.VK_RIGHT:// moves the snake right
                    if(DirectionOfSnake !='L')
                        DirectionOfSnake = 'R';
                    break;
                case KeyEvent.VK_UP:// moves the snake up
                    if(DirectionOfSnake != 'D')
                        DirectionOfSnake = 'U';
                    break;
                case KeyEvent.VK_DOWN:// moves the snake down
                    if(DirectionOfSnake != 'U')
                        DirectionOfSnake = 'D';
                    break;
            }
        }
    }
}
