package model;

import java.io.Serializable;

/**
 * The Song class contains the name of the song, how many times it has played on a given day, and the length
 * of the song.
 * 
 * @author Jonathan Snavely
 * @author Anthony Rodriguez
 *
 */
public class Song implements Serializable{
	String artist;
	String title;
	String fileName;
	int songLengthSeconds;
	int playsToday;
	
	/**
	 * Constructor with initializations
	 */
	public Song(String artist, String title, int songLengthSeconds, String fileName){
		this.artist = artist;
		this.title = title;
		this.songLengthSeconds = songLengthSeconds;
		this.fileName = fileName;
		this.playsToday = 0;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getPlaysLeft(){
		return 5-playsToday;
	}
	
	public int getSongLength(){
		return songLengthSeconds;
	}	
	
	//Returns the filename of the audio file. (i.e. example.mp3)
	public String getFileName(){
		return fileName;
	}
	
	//Returns true if the Song has not been played 5 times
	public boolean canPlayToday(){
		return playsToday < 5;
	}
	
	public void incrementPlayCount(){
		++playsToday;
	}
	
	//Reset of the song plays
	public void clearPlaysToday(){
		playsToday = 0;
	}
	
	//Invoked by the JList to display contents
	public String toString(){
		return getTitle();
	}
}
