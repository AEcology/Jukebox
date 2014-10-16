package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

/**
 * This class keeps a FIFO of the songs to be played, and plays them until the FIFO is empty. Songs played sequentially without overlap. 
 * 
 * @author Jonathan Snavely
 * @author Anthony Rodriguez
 *
 */
public class SongQueue implements ListModel<Song>, Serializable{
	private Queue<Song> songQueue;
	private ObjectWaitingForSongToEnd waiter;
	private LinkedList<ListDataListener> listDataListeners;
	
	public SongQueue(){
		songQueue = new LinkedList<Song>();
		waiter = new ObjectWaitingForSongToEnd();
		listDataListeners = new LinkedList<ListDataListener>();
	}
	
	//Add a song to the end of the queue
	public void add(Song song){
		songQueue.add(song);
		changed();
		
		//If no song currently playing, play the song just added
		if(songQueue.size() == 1)
			SongPlayer.playFile(waiter, "./songfiles/" + song.getFileName());
	}
	
	//Remove the head of the queue and return it
	public Song pop(){
		changed();
		return songQueue.poll();
	}
	
	//Look at the head of the queue without removing
	public Song peek(){
		return songQueue.peek();
	}
	
	public void changed(){
		for(ListDataListener l: listDataListeners){
			l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, songQueue.size()));
		}	
	}	
	

	/**
	 * A Listener that is called when a song ends. Starts up next song if one exists
	 */
	private class ObjectWaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {			
			//Remove just played song from the stack, and start next song
			pop();
			if(!songQueue.isEmpty())
				SongPlayer.playFile(waiter, "./songfiles/" + peek().getFileName());	
		}
	}

	
	///////////////ListModel Interface//////////////
	@Override
	public int getSize() {
		return songQueue.size();
	}

	@Override
	public Song getElementAt(int index) {
		Iterator<Song> it = songQueue.iterator();
		Song obj = null;
		for(int i=-1; i<index; ++i)
			obj = it.next();
		return obj;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listDataListeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listDataListeners.remove(l);
	}
}
