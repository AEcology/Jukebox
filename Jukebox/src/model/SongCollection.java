package model;

import java.util.HashMap;

public class SongCollection {
	private HashMap<String, Song> songs;
	
	public Song getSong(String name){
		if (!songs.containsKey(name))
			return null;
		else
			return songs.get(name);
	}
	public boolean canPlay(String name){
		if (!songs.containsKey(name))
			return false;
		Song sng = songs.get(name);
		return sng.canPlayToday();
	}
	public void resetDay(){
		for (HashMap.Entry<String,Song> s:songs.entrySet()){
			s.getValue().reset();
		}
	}
	
}
