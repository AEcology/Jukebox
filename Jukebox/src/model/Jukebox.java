package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class Jukebox {
	private Student loggedStudent;
	SongCollection songs;
	Map<Integer, StudentRecord> studentRecords;
	GregorianCalendar dateLastPlayed; //used to reset new days
	public Jukebox(){
		dateLastPlayed = new GregorianCalendar();
		loggedStudent = null;
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
		GregorianCalendar today = new GregorianCalendar();
		if (!sameDay(today, dateLastPlayed)){
			 for(Map.Entry<Integer, StudentRecord> s:studentRecords.entrySet()){
				 s.getValue().resetDay();
			 }
			 songs.resetDay();
		}
		dateLastPlayed = today;
		if (!songs.canPlay(name))
			return false;
		else queueSong(name);
		return true;
	}
	//Rick's implementation of finding day differences:
	private boolean sameDay(GregorianCalendar today, GregorianCalendar other) {
		return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
		        && today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
		        && today.get(Calendar.DAY_OF_MONTH) == other.get(Calendar.DAY_OF_MONTH);
	}
	//Internal to Jukebox: play the song
	private boolean queueSong(String name){
		//TODO
		return false;
	}
}
