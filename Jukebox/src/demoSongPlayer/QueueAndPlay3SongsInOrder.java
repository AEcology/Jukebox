package demoSongPlayer;

import model.Song;
import model.SongQueue;


/**
 * This class simply adds three songs to the song queue. 
 * These songs are played in sequential order, not overlapping.
 * @author Jonathan Snavely, Anthony Rodriguez
 *
 */
public class QueueAndPlay3SongsInOrder {

	public static void main(String[] args) {
		// Assign the responsibility of queuing Songs and playing them in order, and not overlapping
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SongQueue songQueue = new SongQueue();
				songQueue.add(new Song("flute.aif"));
				songQueue.add(new Song("spacemusic.au"));
				songQueue.add(new Song("tada.wav"));
			}
		});
	}
}
