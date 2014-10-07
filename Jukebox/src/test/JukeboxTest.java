package test;

import static org.junit.Assert.*;
import model.Jukebox;

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
	

	
}
