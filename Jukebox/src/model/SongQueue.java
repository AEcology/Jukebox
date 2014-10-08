package model;

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
	
	//Add a song to the end of the queue
	public void add(Song song){
		songQueue.add(song);
		
		//If no song currently playing, play the song just added
		if(songQueue.size() == 1)
			SongPlayer.playFile(waiter, "./songfiles/" + song.getFileName());
	}
	
	//Remove the head of the queue and return it
	public Song pop(){
		return songQueue.poll();
	}
	
	//Look at the head of the queue without removing
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
