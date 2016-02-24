package ProgrammingSection;

public class pickupable {
	
int locationx = 0;
static int counter = 0;
int locationy = 0;
int updatey = 0;
int updatex = 0;
Boolean found = false;	
	public pickupable(int x, int y){
		locationx = x;
		locationy = y;
		counter ++;
		updatey = y;
		updatex = x;
	}
	public pickupable(int x, int y, Boolean good){
		locationx = x;
		locationy = y;
		updatey = y;
		updatex = x;
	}
	public void setupdatey(int y){
		updatey = y;
	}
	public void setupdatex (int x){
		updatex = x;
	}
	public void setFound(Boolean t){
		found = t;
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
	public int getupdatex(){
		return updatex;
	}
	public int getupdatey(){
		return updatey;
	}
	public int getCount(){
		return counter;
	}
	public void resetCount(){
		counter = 0;
	}
	public boolean checkIfFound(long l, long m){
		if( (l > updatex -30 &&  l < updatex +30) && (m > updatey - 30 && m < updatey + 30)){
			return true;
		}
		else{
			return false;
		}
	}

}
