package model;

/**
 * Student contains ID, password, play counts, and play seconds. 
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
	public void updateUsage(Song toPlay) {
		totalSecondsPlayed += toPlay.getSongLength();
	}
	public void clearSongPlaysToday() {
		songsPlayedToday = 0;
	}
	public boolean hasTimeLeft(Song toPlay) {
		return totalSecondsPlayed + toPlay.getSongLength() < MAXPLAYTIME;
	}
}
