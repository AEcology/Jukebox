package demoSongPlayer;

/**
 * Play three songs assuming you close the modal dialog box.
 * When run, you can actually have two songs playing at the same time.
 * 
 * The end of song listener prints the fully qualified file name and the
 * time the song finished playing pass as the argument to the listener.
 * 
 * @author Rick Mercer
 */

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class Play3SongsWithAListener {

  /**
   * Play three songs assuming you close the modal dialog box. When run, you can
   * actually have two songs playing at the same time.
   * 
   * @param args
   *          An array of strings not often used by anybody
   */
  public static void main(String[] args) {
    ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();

    JOptionPane.showMessageDialog(null, "Play short aif file");
    SongPlayer.playFile(waiter, baseDir + "flute.aif");

    JOptionPane.showMessageDialog(null, "Play tada.wav");
    SongPlayer.playFile(waiter, baseDir + "tada.wav");

    JOptionPane.showMessageDialog(null, "Play an MP3");
    SongPlayer.playFile(waiter, baseDir + "SwingCheese.mp3");

  }

  /**
   * An inner class that allows an instance of this to receive a
   * songFinishedPlaying when the audio file has been played. Note: static was
   * added here because it is called from main.
   */
  private static class ObjectWaitingForSongToEnd implements EndOfSongListener {

    public void songFinishedPlaying(EndOfSongEvent eosEvent) {
      System.out.print("Finished " + eosEvent.fileName());
      GregorianCalendar finishedAt = eosEvent.finishedTime();
      System.out.println(" at " + finishedAt.get(Calendar.HOUR_OF_DAY) + ":"
          + finishedAt.get(Calendar.MINUTE) + ":"
          + finishedAt.get(Calendar.SECOND));
    }
  }

  /**
   * baseDir will be the fully qualified path to the directory in which this
   * program is running on any machine. System.getProperty("file.separator")
   * returns "\" when running on Unix or "/" when running on windows.
   */
  public static String baseDir = System.getProperty("user.dir")
      + System.getProperty("file.separator") + "songfiles"
      + System.getProperty("file.separator");

}