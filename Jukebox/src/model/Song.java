package model;

/**
 * The Song class contains the name of the song, how many times it has played on a given day, and the length
 * of the song.
 * 
 * @author Jonathan Snavely
 * @author Anthony Rodriguez
 *
 */
public class Song {
	String name;
	int playsToday;
	int songLengthSeconds;
	
	public Song(String name){
		this.name = name;
	}
	
	public String getArtist(){
		return "Artist";
	}
	public String getTitle(){
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
	public int getSongLength(){
		return songLengthSeconds;
	}
	public void clearPlaysToday(){
		playsToday = 0;
	}
}
