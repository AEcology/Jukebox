package model;


//Used by view to determine whether to display student login GUI or playlist GUI
public enum ModelMode {
	PLAYMODE		(0),
	ACCOUNTMODE		(1);
	
	private final int value;
	ModelMode(int mode){
		this.value = mode;
	}
	public int getValue(){
		return this.value;
	}
}
