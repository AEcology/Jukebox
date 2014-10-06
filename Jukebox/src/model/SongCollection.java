package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongCollection implements TableModel{
	private ArrayList<Song> songs;
	private LinkedList<TableModelListener> tableModelListeners;

	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	
	public SongCollection(){
		songs = new ArrayList<Song>();
		tableModelListeners = new LinkedList<TableModelListener>();
		
		//Initializing known songs
	    add("BlueRidgeMountainMist.mp3");
	    add("DeterminedTumbao.mp3");
	    add("flute.aif");
	    add("spacemusic.au");
	    add("StringCheese.mp3");
	    add("tada.wav");
	    add("UntameableFire.mp3");
	}
	
	//Add new song to the collection
	public void add(String songName){
		
		//If already contains, exit
		for(Song eachSong: songs){
			if(eachSong.getTitle() == songName)
				return;
		}
		
		//Create the new song object
		Song song = new Song(songName);
		
		//Add song object to collection
		songs.add(song);
		changed();
	}
	
	public void changed(){
		for(TableModelListener l: tableModelListeners){
			l.tableChanged(new TableModelEvent(this));
		}
	}	
	
	
	
	
	// TODO: Remove. Students are only able to select songs that they see on GUI. UNNEEDED
//	public Song getSong(String name){
//		if (!songs.containsKey(name))
//			return null;
//		else
//			return songs.get(name);
//	}
//	public boolean canPlay(String name){
//		if (!songs.containsKey(name))
//			return false;
//		Song sng = songs.get(name);
//		return sng.canPlayToday();
//	}
//	public void resetDay(){
//		for (HashMap.Entry<String,Song> s:songs.entrySet()){
//			s.getValue().reset();
//		}
//	}
	
	
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





