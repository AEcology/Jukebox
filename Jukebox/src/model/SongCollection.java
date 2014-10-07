package model;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongCollection implements TableModel{
	private ArrayList<Song> songs;
	private LinkedList<TableModelListener> tableModelListeners;
	
	public SongCollection(){
		songs = new ArrayList<Song>();
		tableModelListeners = new LinkedList<TableModelListener>();
		
		//Initializing known songs
		add("Ralph Schuckett", "Blue Ridge Mountain Mist", 38, "BlueRidgeMountainMist.mp3");
	    add("FreePlay Music", "Determined Tumbao", 20, "DeterminedTumbao.mp3");
	    add("Sun Microsystems", "Flute", 5, "flute.aif");
	    add("Unknown", "Space Music", 6, "spacemusic.au");
	    add("FreePlay Music", "Swing Cheese", 15, "StringCheese.mp3");
	    add("Microsoft", "Tada", 2, "tada.wav");
	    add("Pierre Langer", "Untameable Fire", 282, "UntameableFire.mp3");
	}
	
	//Add new song to the collection
	public void add(String artist, String title, int songLengthSeconds, String fileName){
		
		//If already contains, exit
		for(Song eachSong: songs){
			if(eachSong.getTitle() == title)
				return;
		}
		
		//Create the new song object
		Song song = new Song(artist, title, songLengthSeconds, fileName);
		
		//Add song object to collection
		songs.add(song);
		changed();
	}
	
	public void changed(){
		for(TableModelListener l: tableModelListeners){
			l.tableChanged(new TableModelEvent(this));
		}
	}	
	
	//Return Song if exists, null otherwise
	public Song getSong(String name){
		
		for(Song eachSong: songs){
			if(eachSong.getTitle() == name)
				return eachSong;
		}
		return null;
	}
	
	public void clearSongPlaysToday() {
		for(Song eachSong: songs){
			eachSong.clearPlaysToday();
		}
	}
	
/////////////////////////////TableModel Interface/////////////////////////////////
	@Override
	public int getRowCount() {
		return songs.size();
	}
	@Override
	public int getColumnCount() {
		return 3;
	}
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: 
			return "Artist";
		case 1:
			return "Title";	
		case 2:
			return "Seconds";
		}
		return null;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: 
			return String.class;
		case 1:
			return String.class;	
		case 2:
			return Integer.class;
		}
		return null;
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0: return songs.get(rowIndex).getArtist();
		case 1: return songs.get(rowIndex).getTitle();	
		case 2: return songs.get(rowIndex).getSongLength();
		}
		return null;
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// NO IMPLEMENTATION
	}
	@Override
	public void addTableModelListener(TableModelListener l) {
		tableModelListeners.add(l);
	}
	@Override
	public void removeTableModelListener(TableModelListener l) {
		tableModelListeners.remove(l);
	}
}