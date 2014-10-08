package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Queue;

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
public class SongQueue {
	Queue<Song> songQueue;
	ObjectWaitingForSongToEnd waiter;
	
	public SongQueue(){
		songQueue = new LinkedList<Song>();
		waiter = new ObjectWaitingForSongToEnd();
	}
	
	public void add(Song song){
		songQueue.add(song);
		
		//If no song currently playing, play the song just added
		if(songQueue.size() == 1)
			SongPlayer.playFile(waiter, "./songfiles/" + song.getFileName());
	}
	
	public Song pop(){
		return songQueue.poll();
	}
	
	public Song peek(){
		return songQueue.peek();
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
}
