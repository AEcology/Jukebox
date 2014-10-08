package model;

/**
 * Student contains ID, password, play counts, and play seconds. Students cannot play more than 90000 seconds over time, and cannot play more than 2 songs per day.
 * @author Jonathan Snavely
 * @author Anthony Rodriguez
 *
 */
public class Student {

	private String ID;
	private String password;
	private int totalSecondsPlayed = 0;
	private int songsPlayedToday = 0;
	private static final int MAXPLAYTIME = 90000;
	
	public Student(String ID, String password){
		this.ID = ID;
		this.password = password;
	}
	public String getID(){
		return this.ID;
	}
	public String getPassword() {
		return this.password;
	}
	public int getPlayCount(){
		return songsPlayedToday;
	}
	public boolean canPlayToday() {
		return songsPlayedToday < 2;
	}
	public void incrementPlayCount(){
		++songsPlayedToday;
	}
	
	//Add a song's time length to the student time played
	public void updateUsage(Song toPlay) {
		totalSecondsPlayed += toPlay.getSongLength();
	}
	public void clearSongPlaysToday() {
		songsPlayedToday = 0;
	}
	
	//Assess if playing the song will exceed the total time played limit
	public boolean hasTimeLeft(Song toPlay) {
		return totalSecondsPlayed + toPlay.getSongLength() < MAXPLAYTIME;
	}
}
