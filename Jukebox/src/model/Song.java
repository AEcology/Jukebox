package model;

public class Song {
	String name;
	int playsToday;
	double songLengthMinutes;
	public String getName(){
		return this.name;
	}
	public boolean canPlayToday(){
		return playsToday<5;
	}
	public void reset(){
		playsToday = 0;
	}
	public void incrementPlayCount(){
		++playsToday;
	}
	public double getSongLength(){
		return songLengthMinutes;
	}
	public boolean play(){
		//TODO: fill or delete if unnecessary
		return false;
	}
}
