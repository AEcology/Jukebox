package model;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Student contains ID, password, play counts, and play seconds. Students cannot play more than 90000 seconds over time, and cannot play more than 2 songs per day.
 * @author Jonathan Snavely
 * @author Anthony Rodriguez
 *
 */
public class Student implements Serializable {

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
	/**
	 * By convention, is called by model when it decides that a new day has occured
	 */
	public void clearSongPlaysToday() {
		songsPlayedToday = 0;
	}
	
	//Assess if playing the song will exceed the total time played limit
	public boolean hasTimeLeft(Song toPlay) {
		return totalSecondsPlayed + toPlay.getSongLength() < MAXPLAYTIME;
	}
	
	//Helper method used for formating time remaining in toString method
	private String timeFormatting(){ 
		int leftoverSeconds;
		int totalSecondsLeft = MAXPLAYTIME - totalSecondsPlayed;	
		int hours = Math.round(totalSecondsLeft/3600);
		leftoverSeconds = totalSecondsLeft - hours*3600;
		int minutes = Math.round(leftoverSeconds/ 60);
		leftoverSeconds -= minutes*60;
		return (hours + ":" + minutes + ":" + leftoverSeconds);
	}
	
	public String toString(){
		int songsPlaysLeftToday = 2 - songsPlayedToday;
		return "Logged in as: " + ID + ", Time left: " + timeFormatting() + ", Song plays left today: " + songsPlaysLeftToday;
	}
}
