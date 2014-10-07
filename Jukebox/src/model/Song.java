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
	String artist;
	String title;
	String fileName;
	int songLengthSeconds;
	int playsToday;
	
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
	public int getSongLength(){
		return songLengthSeconds;
	}	
	public String getFileName(){
		return fileName;
	}
	public boolean canPlayToday(){
		return playsToday < 5;
	}
	public void incrementPlayCount(){
		++playsToday;
	}
	public void clearPlaysToday(){
		playsToday = 0;
	}
}
