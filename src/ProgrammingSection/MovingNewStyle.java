package ProgrammingSection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

public class MovingNewStyle{
	static LinkedList<pickupable> items = new LinkedList();
	//LevelDesigns levelHolder = new LevelDesigns(null);
	Wall wall1 = new Wall(500, 300, 70, 80);
	Boolean moveup = false;
	Boolean movedown = false;
	Boolean moveleft = false;
	Boolean moveright = false;
	Boolean winner = false;
	Boolean jumping = false;
	Boolean jumped = false;
	Boolean gamePaused = true;
	Boolean goodSize = false;
	JFrame frame = new JFrame("Test");
	LevelEditorPanel editor = new LevelEditorPanel();
	BufferedImage image1 = createBackground();
	BufferedImage image2 = create2Background();
	BufferedImage image3 = create3Background();
	BufferedImage image4 = create4Background();
	BufferedImage fullImage = createFullBackground();
	BufferedImage roboBall = createBall();
	int mouseCounter = 0;
	int mouseWallStartX, mouseWallStartY;
	int mouseWallX, mouseWallY;
	int playerSize = 50;
	int worldX = 0;
	int worldY = 0;
	static LinkedList<Wall> walls = new LinkedList();
	int gravity = 6;
	int jumpCounter = 0;
	int mapCounter = 0;
	int screenX = 1000;
	int screenY = 800;
	int x = 50;
	int y = 50;
	int timerCounter = 0;
	int gravityTimer = 0;
	int accelerationx = 0;
	int accelerationy = 0;
	Wall editWall = new Wall(0, 0);
	

	
	MouseInputAdapter mousey = new MouseInputAdapter(){

		@Override
		public void mouseClicked(MouseEvent arg0) {
			int mousex = arg0.getX();
			int mousey = arg0.getY();
			if(mousex > 0 && mousex < screenX && gamePaused){
				movingUnit.requestFocus();
			}
			if(editor.getAddCoinActive()){
				items.add(new pickupable(mousex-12, mousey+worldY-32));
			}
			
			else if(editor.getDeleteActive()){
				for(Wall b: walls){
					if(mousex-12 > b.getX() && mousex-12 < b.getX() + b.getSizeX() && mousey-32 > b.getupdatey() && mousey-32 < b.getupdatey() + b.getSizeY()){
						walls.remove(b);
					}
				}
				for(pickupable p: items){
					if(mousex-10 > p.getX() && mousex - 10 < p.getX() + 10 && mousey - 32 > p.getupdatey() && mousey - 32 < p.getupdatey() + 10){
						items.remove(p);
					}
				}
			}
		}
			
		

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(editor.getAddWallActive()){
				mouseWallStartX = e.getX() - 10;
				mouseWallStartY = e.getY() - 32;
				editWall.setLocX(mouseWallStartX);
				editWall.setLocY(mouseWallStartY + worldY);
				editWall.setupdatex(mouseWallStartX);
				editWall.setupdatey(mouseWallStartY + worldY);
				
				
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mouseWallX = e.getX();
			mouseWallY = e.getY();
			if(mouseWallX < mouseWallStartX){
				int holderint = mouseWallStartX;
				mouseWallStartX = mouseWallX;
				mouseWallX = holderint;
			}
			if(mouseWallY < mouseWallStartY){
				int holderint = mouseWallStartY;
				mouseWallStartY = mouseWallY;
				mouseWallY = holderint;
			}
			editWall.setLocX(0);
			editWall.setLocY(0);
			editWall.setupdatex(0);
			editWall.setupdatey(0);
			if(Math.abs(mouseWallX - mouseWallStartX) > 30 && Math.abs(mouseWallY - mouseWallStartY) > 30 && editor.getAddWallActive()){
			walls.add(new Wall(
					mouseWallStartX,
					mouseWallStartY + worldY, 
					Math.abs(mouseWallX - 10 - mouseWallStartX), 
					Math.abs(mouseWallY - 32 - mouseWallStartY)));
		}
		else{
			//TooSmall
			
			return;
		}
		
	}
		@Override
		public void mouseDragged(MouseEvent e) {
			//System.out.println("Ding!");
			if(editor.getAddWallActive()){
				mouseWallX = e.getX()-10;
				mouseWallY = e.getY()-32;
				
				//System.out.println(e.getX());
				editWall.setupdatex(mouseWallX);
				editWall.setupdatey(mouseWallY);
				
				
				editWall.setLocX(mouseWallStartX);
				editWall.setLocY(mouseWallStartY);
				editWall.setupdatex(mouseWallX);
				editWall.setupdatey(mouseWallY);
				if(Math.abs(mouseWallX - mouseWallStartX) > 30 && Math.abs(mouseWallY - mouseWallStartY) > 30 && editor.getAddWallActive()){
					goodSize = true;
				}
				else{
					goodSize = false;
				}
				movingUnit.repaint();
			}
			else if(editor.getAddCoinActive()){
				if(mouseCounter > 15){
					mouseCounter = 0;
					items.add(new pickupable(e.getX() - 10, e.getY() - 32));
				}
				else{
					mouseCounter ++;
				}
			}
			
			
		}
		
		
		
		
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	ActionListener timerAction = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
		//	if(moveup){
		//		accelerationy -= 1;
		//	}
			if(movingUnit.isFocusOwner()){
				gamePaused = false;
			}
			else if(!movingUnit.isFocusOwner()){
				gamePaused = true;
			}
			if(movedown){
				accelerationy += 2;
				overSpeed();
			}
			if(moveleft){				
				accelerationx -= 1;
				}
				
			
			else if(moveright){
				accelerationx += 1;
			}
			else{
				if(accelerationx > 0){
					accelerationx -= 1;
				}
				else if(accelerationx < 0){
					accelerationx += 1;
				}
			}
			if(jumping && !jumped){
				accelerationy -= 40;
				jumped = true;
				
			}

			if(editor.getLoseFocusActive()){
				accelerationy = 0;
				accelerationx = 0;
			}

			x += accelerationx;
			y += accelerationy;
			checkCollision();

			if (gravityTimer > 5 && !dontGoBottom()){
				gravityTimer = 0;
				accelerationy += gravity;
			}
			mapCounter ++;
			gravityTimer ++;

		
			movingUnit.repaint();
			mainCheckIfFound();
			dontGo();
			 for(Wall w: walls){
				 w.setupdatey(w.getY() - worldY);
			 }
			 for(pickupable b: items){
				 b.setupdatey(b.getY() - worldY);
			 }
			overSpeed();
			
			
			
		}
		
		
	};
	Timer time = new Timer(25, timerAction);
	
	
	
	
	JLabel movingUnit = new JLabel(){
		public void paintComponent(Graphics g){
			
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fillRect(0, 0, 2000, 2000);
			g.setColor(Color.red);
			g.drawLine(screenX +1, 0, screenX+1, 2000);
			/*if(worldY < -1750 && worldY > -2400){
				g.drawImage(image3, 0, -1400-worldY, null);
				}
			if(worldY < -300 && worldY > -1600){
			g.drawImage(image2, 0, -300-worldY, null);
			}
			if(worldY < 150 && worldY > -800){
			g.drawImage(image1, 0, 200- worldY, null);
			}
			*/
			g.drawImage(fullImage, 0, -1300-worldY, null);
			mapCounter = 0;
			
			g.setColor(Color.blue);
			g.drawImage(roboBall, x, y, playerSize, playerSize, null);
			//g.fill3DRect(x, y, playerSize, playerSize, true);
			//g.drawRect(mouseWallStartX-10, mouseWallStartY-32, mouseWallX - mouseWallStartX, mouseWallY - mouseWallStartY);
			g.setColor(Color.gray);
			for(Wall Wall: walls){
				if(Wall.getY()> worldY - 100 && Wall.getY() < worldY + screenY + 1000){
			g.fill3DRect(Wall.getX(), Wall.getupdatey(), Wall.getSizeX(), Wall.getSizeY(), true);
				}
				}
			if(editor.getAddWallActive()){
				if(!goodSize){
					g.setColor(Color.red);
				}
				else
					g.setColor(Color.blue);
			g.fill3DRect(editWall.getX(),editWall.getY(), editWall.getupdatex() - editWall.getX(), editWall.getupdatey() - editWall.getY() , true);
			}
			g.setColor(Color.gray);
			if (winner){
				
				g.setColor(Color.white);
				
				g.drawString("You did it. You've won the game! ", screenX/2, screenY/2);
				g.drawString("Way to be all right", screenX/2+30, screenY/2 + 50);
				
				timerCounter ++;
				if(timerCounter > 100){
					items.get(0).resetCount();
					items.removeAll(items);
					walls.removeAll(walls);
					winner = false;
					timerCounter = 0;
					x = 150;
					y = 100;
					worldY = 0;
					generatedots();
					generatewalls();

					
				}
			}
			g.setColor(Color.YELLOW);
			for(pickupable pickupable:items){
				if(!pickupable.getFound()){
					if(pickupable.getY() > worldY - 100 && pickupable.getY() < worldY + screenY + 1000){
				g.fill3DRect(pickupable.getX(), pickupable.getupdatey(), 10, 10, true);
				}
				}
				}
			g.setColor(Color.white);
			
			if(gamePaused){
				g.setColor(Color.gray);
				g.fill3DRect(screenX/4 - 20, screenY/2 - 20, 500, 30, true);
				g.setColor(Color.white);
				g.drawString("You have clicked away from the game, please click on the game to resume", screenX/4, screenY/2);
			}
			g.setColor(Color.black);
			g.fillRect(screenX+2, 0, 1000, 1500);

		}
	};
	
	
	
	
	public MovingNewStyle() throws IOException{
		KeyListener keyey = new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {
				char key = e.getKeyChar();
				if(key == 'w'){
					moveup = true;
				}
				else if(key == 's'){
					movedown = true;
				}
				 if(key == 'a'){
						moveleft = true;
					}
				 else if(key == 'd'){
						moveright = true;
					}
				 if(key == ' '){
					 jumping = true;
				 }
				 if(key == 'z'){
					System.exit(0);
				}
				 
			}
			@Override
			public void keyReleased(KeyEvent e) {
				char key = e.getKeyChar();
				if(key == 'w'){
					moveup = false;
				}
				else if(key == 's'){
					movedown = false;
				}
				 if(key == 'a'){
					moveleft = false;
				}
				 else if(key == 'd'){
					moveright = false;
				}
					if(key == ' '){
						jumping = false;
					}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
			
		};
		
		
		
		
		generatedots();
		generatewalls();


		BufferedImage background = image1;

		frame.setSize(screenX, screenY);
		frame.setBackground(Color.black);
		frame.add(editor);
		
		movingUnit.addKeyListener(keyey);
		frame.setIconImage(background);
		frame.addMouseListener(mousey);
		frame.addMouseMotionListener(mousey);
		frame.add(movingUnit);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		time.start();
	}
	
	public void mainCheckIfFound(){
		int count = 0;
		for(pickupable pickupable:items){
			
			if (pickupable.checkIfFound(x+playerSize/2, y+playerSize/2) && !pickupable.getFound()){
				
				pickupable.setFound(true);
				isDone();
			
			}
			count++;
			
		}
		}
	
	public void isDone(){
		int totalRemaining = 0;
		for(pickupable pickupable:items){
			
			if(pickupable.getFound()){
				totalRemaining ++;
				if(totalRemaining >= pickupable.getCount()){
					winner = true;
				}
			}
			
		}
	}
	
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	public boolean dontGo(){
		 if(y > 1200){
			 y = 500;
			accelerationy = 0;
		 }
		 if(worldY > 800){
			 worldY = -200;
			 y = 300;
		 }
		
		if(x+playerSize > screenX){
			x = screenX - playerSize;
			accelerationx = accelerationx*-1 +2;
			return true;
		}
		 if(x < 0){
			x = 0;
			accelerationx = accelerationx*-1 -2;
			return true;
		}
		if(y+playerSize > screenY-100){
			y = screenY - 100-playerSize;
			worldY += accelerationy;
			//jumped = false;
			return true;
		}
		 if(y < 50){
			y = 50;
			worldY += accelerationy;
			return true;
		}
	
		 
		 return false;
	}
	public boolean dontGoBottom(){
		if(y >= screenY){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void overSpeed(){
		if(accelerationx > 20){
			accelerationx = 20;
		}
		else if(accelerationx < -20){
			accelerationx = -20;
		}
		if (accelerationy > 25){
			accelerationy = 25;
		}
		else if(accelerationy < -25){
			accelerationy = -25;
		}
	}
	public void generatedots(){
		for(int i = 20; i > 0; i --){
			for(int l = randInt(0, 3); l > 0; l--){
			items.add(new pickupable(randInt(20, screenX-150), i*(-150) + 550));
			}
			}
		items.add(new pickupable(300, 500));
	}
	
	public void generatewalls(){
		for(int i = 20; i > 0; i --){
			walls.add(new Wall(randInt(0+40, screenX-40), i*(-150)+600, randInt(30, 250), randInt(30, 50)));
		}
		//helper walls
		walls.add(new Wall(0, screenY-100, 1000, 70));
	}
	

	
	public BufferedImage create2Background() throws IOException{
		BufferedImage background = ImageIO.read(this.getClass().getResource("images/Background2.png"));
		return background;
	}
	public BufferedImage createBackground() throws IOException{
		BufferedImage background = ImageIO.read((this.getClass().getResource("images/Background1.png")));
		return background;
	}
	public BufferedImage create3Background() throws IOException{
		BufferedImage background = ImageIO.read((this.getClass().getResource("images/Background3.png")));
		return background;
	}
	public BufferedImage create4Background() throws IOException{
		BufferedImage background = ImageIO.read((this.getClass().getResource("images/Background4.png")));
		return background;
	}
	public BufferedImage createFullBackground() throws IOException{
		BufferedImage background = ImageIO.read((this.getClass().getResource("images/BackgroundBig.png")));
		return background;
	}
	public BufferedImage createBall() throws IOException{
		BufferedImage background = ImageIO.read((this.getClass().getResource("images/RoboBall.png")));
		return background;
	}
	
	
	public void checkCollision(){
		
		for(Wall Wall: walls){
		 if(Wall.fallingHit(x, y, accelerationx, accelerationy, playerSize)){
			accelerationy = 0;
			y = Wall.getupdatey()-playerSize;
			jumped = false;
		}
		else if(Wall.fallingHit(x+playerSize/2, y, accelerationx, accelerationy, playerSize)){
			accelerationy = 0;
			y = Wall.getupdatey()-playerSize;
			jumped = false;
		}
		else if(Wall.fallingHit(x+playerSize, y, accelerationx, accelerationy, playerSize)){
			accelerationy = 0;
			y = Wall.getupdatey()-playerSize;
			jumped = false;
		}
		else if(Wall.leftHit(x, y, accelerationx, accelerationy, playerSize)){
			
			accelerationx = 0;
			x = Wall.getX()-playerSize;
			//System.out.println(Wall.getX());
		}
		else if(Wall.leftHit(x, y+playerSize/2, accelerationx, accelerationy, playerSize)){
			
			accelerationx = 0;
			x = Wall.getX()-playerSize;
			//System.out.println(Wall.getX());
		}
		else if(Wall.leftHit(x, y+playerSize, accelerationx, accelerationy, playerSize)){
			accelerationx = 0;
			x = Wall.getX()-playerSize;
		}
		else if(Wall.rightHit(x, y+playerSize, accelerationx, accelerationy, playerSize)){
			accelerationx = accelerationx*(-1/2);
			x = Wall.getX()+Wall.getSizeX();
		}
		else if(Wall.rightHit(x, y+playerSize/2, accelerationx, accelerationy, playerSize)){
			accelerationx = accelerationx*(-1/2);
			x = Wall.getX()+Wall.getSizeX();
		}
		else if(Wall.rightHit(x, y, accelerationx, accelerationy,playerSize)){
			accelerationx = accelerationx*(-1/2);
			x = Wall.getX()+Wall.getSizeX();
		}
		else if(Wall.jumpingHit(x, y, accelerationx, accelerationy, playerSize)){
				accelerationy = 1;
				y = Wall.getupdatey() + Wall.getSizeY();
			}
		else if(Wall.jumpingHit(x+playerSize/2, y, accelerationx, accelerationy, playerSize)){
				accelerationy = 1;
				y = Wall.getupdatey() + Wall.getSizeY();
			}
		else if(Wall.jumpingHit(x+playerSize, y, accelerationx, accelerationy, playerSize)){
				accelerationy = 1;
				y = Wall.getupdatey() + Wall.getSizeY();
			}

		}
	
		
	}

	public static LinkedList<Wall> getWalls(){
		return walls;
	}
	public static LinkedList<pickupable> getItems(){
		return items;
	}

	
	public static void main(String[] args) throws IOException{
  MovingNewStyle moving = new MovingNewStyle();

	}
	
}

