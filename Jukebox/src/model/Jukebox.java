package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The Jukebox class contains a collection of songs, collection of student accounts, and a song queue. 
 * To use the Jukebox, a user invokes the RequestSong method, which adds the requested song to the queue to be played if some criteria are met.
 * 
 * @author Jonathan Snavely
 * @author Anthony Rodriguez
 *
 */
public class Jukebox{
	private Student loggedStudent;
	private StudentCollection studentCollection;
	private SongQueue songQueue;
	private SongCollection songCollection;
	private GregorianCalendar dateLastPlayed; //used to reset new days
	private String statusMessage;
	
	public StudentCollection getStudents(){
		return studentCollection;
	}
	
	public GregorianCalendar getDateLastPlayed(){
		return dateLastPlayed;
	}
	
	//Returns the collection of songs available to play in the jukebox
	public SongCollection getSongCollection(){
		return songCollection;
	}
	
	//Returns the song queue
	public SongQueue getSongQueue(){
		return songQueue;
	}
	
	//Returns a string indicating the success of requesting a song
	public String getStatus(){
		return statusMessage;
	}
	
	public Jukebox(){
		statusMessage = "No data loaded. Select a song to play!";
		loggedStudent = null;
		studentCollection = new StudentCollection();
		songQueue = new SongQueue();
		songCollection = new SongCollection();
		dateLastPlayed = new GregorianCalendar();
	}
	
	//Constructor used for loaded data
	public Jukebox(GregorianCalendar date, SongCollection songs, StudentCollection students) {
		// TODO Auto-generated constructor stub
		statusMessage = "Data loaded. Select a song to play!";
		loggedStudent = null;
		studentCollection = students;
		songQueue = new SongQueue();
		songCollection = songs;
		dateLastPlayed = date;
	}

	//Returns logged in student (just used by model to see if anyone is logged in)
	public Student getLoggedStudent(){
		return loggedStudent;
	}
	
	//Must be called to use any other jukebox functionality
	public boolean login(String ID, String password){
		loggedStudent = studentCollection.getStudent(ID, password);
		if(loggedStudent == null)
			return false;
		
		statusMessage = "Select a song to play!";
		return true;
	}
		
	//Called to allow another Student to use jukebox
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
		if(!toPlay.canPlayToday()){
			statusMessage = "Limit per day exceeded!";
			return false;
		}
		
		//If user play limit reached
		if(loggedStudent==null || !loggedStudent.canPlayToday()){
			statusMessage = "You can't play anymore today!";
			return false;		
		}
		
		//If user has enough time left to play
		if(!loggedStudent.hasTimeLeft(toPlay)){
			statusMessage = "Max lifetime plays exceeded";
			return false;
		}
		
		//Add plays++ to song
		toPlay.incrementPlayCount();
		
		//Add plays++ to user and update user play time remaining
		loggedStudent.incrementPlayCount();
		loggedStudent.updateUsage(toPlay);
		
		//Add the song to the queue
		songQueue.add(toPlay);
		statusMessage = toPlay.title + " Added!";
		return true;
	}
	
	//Rick's implementation of finding day differences:
	private boolean sameDay(GregorianCalendar today, GregorianCalendar other) {
		return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
		        && today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
		        && today.get(Calendar.DAY_OF_MONTH) == other.get(Calendar.DAY_OF_MONTH);
	}
}
