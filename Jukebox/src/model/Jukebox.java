package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Jukebox {
	private Student loggedStudent;
	private StudentCollection studentCollection;
	private SongQueue songQueue;
	private SongCollection songCollection;
	private GregorianCalendar dateLastPlayed; //used to reset new days
	
	public Jukebox(){
		loggedStudent = null;
		studentCollection = new StudentCollection();
		songQueue = new SongQueue();
		songCollection = new SongCollection();
		dateLastPlayed = new GregorianCalendar();
	}
	
	public boolean login(String ID, String password){
		loggedStudent = studentCollection.getStudent(ID, password);
		if(loggedStudent == null)
			return false;
		return true;
	}
		
	public void logout(){
		loggedStudent = null;
	}
	
	//Adds song to the song queue if conditions met
	public boolean requestSong(String name){
		
		GregorianCalendar today = new GregorianCalendar();

		//If new day, clear song counts in system
		if(!sameDay(today,dateLastPlayed)){
			songCollection.clearSongPlaysToday();
			studentCollection.clearSongPlaysToday();
			dateLastPlayed = today;
		}
			
		//Grab song from collection
		Song toPlay = songCollection.getSong(name);
		
		//If song exists
		if(toPlay == null)
			return false;
		
		//If song play limit reached
		if(!toPlay.canPlayToday())
			return false;
		
		//If user play limit reached
		if(!loggedStudent.canPlayToday())
			return false;		
		
		//If user has enough time left to play
		if(!loggedStudent.hasTimeLeft(toPlay))
			return false;
		
		//Add plays++ to song
		toPlay.incrementPlayCount();
		
		//Add plays++ to user and update user play time remaining
		loggedStudent.incrementPlayCount();
		loggedStudent.updateUsage(toPlay);
		
		//Add the song to the queue
		songQueue.add(toPlay);
		
		return true;
	}
	
	//Rick's implementation of finding day differences:
	private boolean sameDay(GregorianCalendar today, GregorianCalendar other) {
		return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
		        && today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
		        && today.get(Calendar.DAY_OF_MONTH) == other.get(Calendar.DAY_OF_MONTH);
	}
}
