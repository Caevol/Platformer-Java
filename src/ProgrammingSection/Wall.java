package ProgrammingSection;

public class Wall {
	
	
	
	int locationx = 0;
	int sizeX = 0;
	int sizeY = 0;
	int updatex = 0;
	int updatey = 0;
	static int counter = 0;
	int locationy = 0;
	Boolean found = false;	
	
		public Wall(int x, int y){
			locationx = x;
			locationy = y;
			sizeX = 10;
			sizeY = 10;
			counter ++;
			updatex = x;
			updatey = y;
			
		}
		public Wall(int x, int y, int sizeX, int sizeY){
			locationx = x;
			locationy = y;
			this.sizeX = sizeX;
			this.sizeY = sizeY;
			updatex = x;
			updatey = y;
		}
		public void setLocX(int x){
			locationx = x;
		}
		public void setLocY(int y){
			locationy = y;
		}
		
		public void setFound(Boolean t){
			found = t;
		}
		public int getupdatey(){
			return updatey;
		}
		public int getupdatex(){
			return updatex;
		}
		public void setupdatex(int x){
			updatex = x;
		}
		public void setupdatey(int y){
			updatey = y;
		}
		public Boolean getFound(){
			return found;
		}
		
		public int getY(){
			return locationy;
		}
		
		public int getX(){
			return locationx;
		}
		
		public int getCount(){
			return counter;
		}
		
		public void resetCount(){
			counter = 0;
		}
		public int getSizeX(){
			return sizeX;
		}
		public int getSizeY(){
			return sizeY;
		}
		
		
		public boolean leftHit(int x, int y, int accelerationX, int accelerationY, int playerSize){
			if(accelerationX > 0 && x+playerSize > locationx && x+playerSize < locationx + sizeX/2 && y < updatey + sizeY && y > updatey){
		//		System.out.println("left");
				return true;
			}
			else{
				return false;
			}
		}
		public boolean rightHit(int x, int y, int accelerationX, int accelerationY, int playerSize){
			if(accelerationX < 0 && x > locationx+ sizeX/2 && x < locationx+sizeX && y < updatey + sizeY && y > updatey){
				//System.out.println("right");
				return true;
			}
			else{
				return false;
			}
		}
		public boolean fallingHit(int x, int y, int accelerationX, int accelerationY, int playerSize){
			if(accelerationY > 0 && y+playerSize > updatey && y+playerSize < updatey+ (30) && x > locationx && x < locationx + sizeX){
				//System.out.println("fall");
				return true;
			}
			else{
				return false;
			}
		}
		public boolean jumpingHit(int x, int y, int accelerationX, int accelerationY, int playerSize){
			if(accelerationY < 0 && y > updatey+(1*sizeY/4) && y < updatey+sizeY && x > locationx && x < locationx + sizeX){
				
				//System.out.println("jump");
				return true;
			}
			else{
				return false;
			}
		}

		
	}

