package demoSongPlayer;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
		GregorianCalendar today = new GregorianCalendar();
		System.out.println(today.get(Calendar.DATE));
		
		// Assign the responsibility of queuing Songs and playing them in order, and not overlapping
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SongQueue songQueue = new SongQueue();
				songQueue.add(new Song("Sun Microsystems", "Flute", 5, "flute.aif"));
				songQueue.add(new Song("Unknown", "Space Music", 6, "spacemusic.au"));
				songQueue.add(new Song("Microsoft", "Tada", 2, "tada.wav"));
			}
		});
	}
}
