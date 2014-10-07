package test;

import static org.junit.Assert.*;
import model.Jukebox;
import model.Song;

import org.junit.Test;

//Attempting to Simulate the possible interactions between the jukebox and the GUI
public class JukeboxTest {

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
	
	//Song Testing
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
}
