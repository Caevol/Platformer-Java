package ProgrammingSection;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;





public class LevelEditorPanel extends JLabel{
	JLabel gameLabel = new JLabel();
	JButton deleter = new JButton("Remover");
	JButton addCoin = new JButton("Coin");
	JButton addWall = new JButton("Wall");
	JButton disableMapMoving = new JButton("Stop Map Moving");
	JButton loseFocus = new JButton("  Pause box  ");
	JButton printAll = new JButton("Print");
	Boolean loseFocusActive = false;
	Boolean deleteActive = false;
	Boolean addCoinActive = false;
	Boolean addWallActive = false;
	Boolean disableMapMovingActive = false;
	
	public Boolean getDeleteActive(){
		return deleteActive;
	}
	public Boolean getAddCoinActive(){
		return addCoinActive;
	}
	public Boolean getAddWallActive(){
		return addWallActive;
	}
	public Boolean getDisableMapMovingActive(){
		return disableMapMovingActive;
	}
	
	public Boolean getLoseFocusActive(){
		return loseFocusActive;
	}
	
	public LevelEditorPanel(){

		setBounds(1000, 150, 200, 300);
		deleter.addActionListener(deleterAction);
		addCoin.addActionListener(addCoinAction);
		addWall.addActionListener(addWallAction);
		disableMapMoving.addActionListener(disableMapMovingAction);
		loseFocus.addActionListener(loseFocusAction);
		printAll.addActionListener(printAllAction);
		this.add(loseFocus);
		this.add(deleter);
		this.add(addCoin);
		this.add(addWall);
		this.add(printAll);
		//this.add(disableMapMoving);
		this.setLayout(new FlowLayout());
		setBackground(Color.black);
	}
	
	//Action listeners for each button
	ActionListener printAllAction = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {

			for(Wall w: MovingNewStyle.getWalls()){
				System.out.println("walls.add(new Wall(" + w.getX() + ", " + w.getY() + ", " + w.getSizeX() + ", " + w.getSizeY() + "));");
			}
			for(pickupable p: MovingNewStyle.getItems()){
				System.out.println("items.add(new pickupable(" + p.getX() + ", " + p.getY() + "));");
			}
		}
		
	};
	ActionListener loseFocusAction = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			if(loseFocusActive == false){
				//setAllDisabled(loseFocus);
				loseFocusActive = true;
				loseFocus.setText("Unpause box");
				//code to lose focus here
			}
			else if(loseFocusActive == true){
				loseFocus.setText("  Pause box  ");
				loseFocusActive = false;
				
				setAllEnabled();
				setAllButtonsFalse();
			}
		}
	
	};
	ActionListener deleterAction = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (deleteActive == false){
			setAllDisabled(deleter);
			deleteActive = true;
			}
			else if(deleteActive == true){
				setAllButtonsFalse();
				setAllEnabled();
			}
		}
	};
	ActionListener addCoinAction = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(addCoinActive == false){
				setAllDisabled(addCoin);
				addCoinActive = true;
			}
			else if(addCoinActive == true){
				setAllButtonsFalse();
				setAllEnabled();
			}
		}
	};
	ActionListener addWallAction = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(addWallActive == false){
				setAllDisabled(addWall);
				addWallActive = true;
			}
			else if(addWallActive == true){
				setAllButtonsFalse();
				setAllEnabled();
			}
		}
	};
	ActionListener disableMapMovingAction = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(disableMapMovingActive == false){
				setAllDisabled(disableMapMoving);
				disableMapMovingActive = true;
			}
			else if(disableMapMovingActive == true){
				setAllButtonsFalse();
				setAllEnabled();
			}
		}
		
	};
	
	
	//each of the pieces used to enable and disable buttons, 
	//as well as set it true and false
public void setAllEnabled(){
		addCoin.setEnabled(true);
		addWall.setEnabled(true);
		disableMapMoving.setEnabled(true);
		deleter.setEnabled(true);
		//loseFocus.setEnabled(true);
	}
	public void setAllDisabled(JButton button){
		
		if(!(button == addCoin)){
			addCoin.setEnabled(false);
		}
		if(!(button == addWall)){
			addWall.setEnabled(false);
		}
		if(!(button == disableMapMoving)){
			disableMapMoving.setEnabled(false);
		}
		if(!(button == deleter)){
			deleter.setEnabled(false);
		}
		/*if(!(button == loseFocus)){
			loseFocus.setEnabled(false);
		}
		*/
	}
	public void setAllButtonsFalse(){
		addCoinActive = false;
		addWallActive = false;
		disableMapMovingActive = false;
		deleteActive = false;
		//loseFocusActive = false;
	}
	
	
}
