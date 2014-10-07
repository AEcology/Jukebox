package test;

import static org.junit.Assert.*;

import javax.swing.event.TableModelListener;

import model.Jukebox;
import model.Song;
import model.SongCollection;
import model.SongQueue;
import model.StudentCollection;

import org.junit.Test;

//Attempting to Simulate the possible interactions between the jukebox and the GUI
public class JukeboxTest {

	///////////////////Jukebox Testing/////////////////
	@Test
	public void testLogin(){
		Jukebox jukebox = new Jukebox();
		assertEquals(jukebox.login("A wrong ID", "A wrong password"), false);
		assertEquals(jukebox.login("Ali", "1111"), true);
	}
	
	@Test
	public void testUserPlayLimit(){
		Jukebox jukebox = new Jukebox();
		jukebox.login("Ali", "1111");
		assertEquals(true, jukebox.requestSong("Blue Ridge Mountain Mist"));
		assertEquals(true, jukebox.requestSong("Blue Ridge Mountain Mist"));		
		assertEquals(false, jukebox.requestSong("Blue Ridge Mountain Mist"));		
	}	
	
	@Test
	public void testSongPlayLimit(){
		Jukebox jukebox = new Jukebox();
		assertEquals(jukebox.login("Ali", "1111"), true);
		assertEquals(true, jukebox.requestSong("Blue Ridge Mountain Mist"));
		assertEquals(true, jukebox.requestSong("Blue Ridge Mountain Mist"));		
		jukebox.logout();
		
		assertEquals(true, jukebox.login("Chris", "2222"));
		assertEquals(true, jukebox.requestSong("Blue Ridge Mountain Mist"));
		assertEquals(true, jukebox.requestSong("Blue Ridge Mountain Mist"));		
		jukebox.logout();
		
		assertEquals(true, jukebox.login("River", "3333"));
		assertEquals(true, jukebox.requestSong("Blue Ridge Mountain Mist"));
		assertEquals(false, jukebox.requestSong("Blue Ridge Mountain Mist"));
	}	
	
	@Test
	public void testPlaySongNotThere(){
		Jukebox jukebox = new Jukebox();
		assertEquals(jukebox.login("Ali", "1111"), true);
		assertEquals(false, jukebox.requestSong("Blue Ridge ... Mist"));
	}		
	
	/////////////////////Song Testing////////////////////
	@Test
	public void testSongGetArtist(){
		Song song = new Song("Sun Microsystems", "Flute", 5, "flute.aif");
		assertEquals("Sun Microsystems", song.getArtist());
	}
	
	@Test
	public void testSongPlayCountLimit(){
		Song song = new Song("Sun Microsystems", "Flute", 5, "flute.aif");
		
		//0 plays today
		assertEquals(true, song.canPlayToday());
		
		//1 play today
		song.incrementPlayCount();
		assertEquals(true, song.canPlayToday());
		
		//2 plays today
		song.incrementPlayCount();		
		assertEquals(true, song.canPlayToday());
		
		//3 plays today
		song.incrementPlayCount();
		assertEquals(true, song.canPlayToday());
		
		//4 plays today
		song.incrementPlayCount();
		assertEquals(true, song.canPlayToday());
		
		//5 plays today -> this should fail because 5 songs have already been played
		song.incrementPlayCount();
		assertEquals(false, song.canPlayToday());
	}

	@Test
	public void testSongPlayCountReset(){
		Song song = new Song("Sun Microsystems", "Flute", 5, "flute.aif");
		song.incrementPlayCount();
		song.incrementPlayCount();
		song.incrementPlayCount();
		song.incrementPlayCount();
		song.incrementPlayCount();
		assertEquals(false, song.canPlayToday());
		song.clearPlaysToday();		//Reset
		assertEquals(true, song.canPlayToday());
		song.incrementPlayCount();
		assertEquals(true, song.canPlayToday());
		song.incrementPlayCount();
		assertEquals(true, song.canPlayToday());
		song.incrementPlayCount();
		assertEquals(true, song.canPlayToday());
		song.incrementPlayCount();
		assertEquals(true, song.canPlayToday());
		song.incrementPlayCount();
		assertEquals(false, song.canPlayToday());
	}
	
	
	///////////////////////SongCollection Tests/////////////////////////
	@Test
	public void testClearSongPlaysTodayForSongCollection(){
		SongCollection songCollection = new SongCollection();
		Song song = songCollection.getSong("Blue Ridge Mountain Mist");
		song.incrementPlayCount();
		song.incrementPlayCount();
		song.incrementPlayCount();
		song.incrementPlayCount();
		song.incrementPlayCount();
		assertEquals(false, song.canPlayToday());
		
		songCollection.clearSongPlaysToday();
		assertEquals(true, song.canPlayToday());		
	}
	
	@Test
	public void testSongCollectionTableModelMethods(){
		SongCollection songCollection = new SongCollection();
		
		assertEquals(7, songCollection.getRowCount());
		assertEquals(3, songCollection.getColumnCount());
		assertEquals("Artist", songCollection.getColumnName(0));
		assertEquals("Title", songCollection.getColumnName(1));
		assertEquals("Seconds", songCollection.getColumnName(2));
		assertEquals(null, songCollection.getColumnName(3));
		
		assertEquals(String.class, songCollection.getColumnClass(0));
		assertEquals(String.class, songCollection.getColumnClass(1));
		assertEquals(Integer.class, songCollection.getColumnClass(2));
		assertEquals(null, songCollection.getColumnClass(3));

		assertEquals(false, songCollection.isCellEditable(0, 0));
		
		assertEquals("FreePlay Music", songCollection.getValueAt(1,0));
		assertEquals("Determined Tumbao", songCollection.getValueAt(1,1));
		assertEquals(20, songCollection.getValueAt(1,2));
		assertEquals(null, songCollection.getValueAt(1,3));
	}
	
	@Test
	public void testAddDuplicateSong(){
		SongCollection songCollection = new SongCollection();
		assertEquals(7, songCollection.getRowCount());
		songCollection.add("Microsoft", "Tada", 2, "tada.wav");
		assertEquals(7, songCollection.getRowCount());
	}

	@Test
	public void justNeedOneMorePercentCoverage(){
			//TODO
	}
	
	
	///////////////////////SongQueue Tests/////////////////////////
	@Test
	public void testPopAndPeek(){
		SongQueue songQueue = new SongQueue();
		assertEquals(null, songQueue.pop());
		assertEquals(null, songQueue.peek());
		
		Song someSong = new Song("Sun Microsystems", "Flute", 5, "flute.aif");
		songQueue.add(someSong);
		assertEquals(someSong, songQueue.peek());
		assertEquals(someSong, songQueue.pop());
		assertEquals(null, songQueue.peek());
	}
	
	
	///////////////////StudentCollection Tests////////////////////
	@Test
	public void testClearSongsPlayedToday(){
		//TODO

	}	
}
