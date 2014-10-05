package demoSongPlayer;

/**
 * Play one audio file from the songfiles directory.
 * There is no listener for the end of song event.
 * 
 * @author Rick Mercer
 */
import songplayer.SongPlayer;

public class Play1SongNoListener {

  /**
   * Play one audio file with no listener for the end of song event.
   * A println is included to indicate the song is playing in a separate Thread.
   */
  public static void main(String[] args) {
    SongPlayer.playFile("./songfiles/BlueRidgeMountainMist.mp3");

    System.out
        .println("Song played in a separate Thread so this appears immediately");
    System.out.println("This program terminates only when song finishes...");
  }

}