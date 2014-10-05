package model;

import java.util.GregorianCalendar;
import java.util.Map;

public class Jukebox {
	private Student loggedStudent;
	SongCollection songs;
	Map<Integer, StudentRecord> studentRecords;
	GregorianCalendar dateLastPlayed; //used to reset new days
	public Jukebox(){
		
	}
	public boolean login(int ID){
		if (!studentRecords.containsKey(ID))
			return false;
		else
			return studentRecords.get(ID).canPlaySongs();
	}
	//External play function: return false if we cannot play song
	public boolean requestSong(String name){
		//TODO: If new day, clear song counts in system
		//TODO
		return false;
	}
	//Internal to Jukebox: play the song
	private boolean playSong(String name){
		//TODO
		return false;
	}
}
