package model;

public class StudentRecord {

	double totalMinutesPlayed;
	int songsPlayedToday;
	
	
	public StudentRecord(){
		totalMinutesPlayed = 0;
		songsPlayedToday = 0;
	}
	public boolean canPlaySongs() {
		// TODO Auto-generated method stub
		return false;
	}
	public void incrementPlayCount(){
		++songsPlayedToday;
	}
	public void resetDay(){
		songsPlayedToday = 0;
	}
	public boolean canPlay(){
		return ((totalMinutesPlayed<1500) && (songsPlayedToday<5));
	}

}
