package demoSongPlayer;

/**
 * This program allows you to play a song every time the button is
 * clicked.  One other GUI components was added to show the GUI 
 * still works even though 1, 2, 3, 4, or more songs are playing
 * The end of song listener simply prints the file name.
 * 
 *   // 1) Create a listener
 *   ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();
 *   // 2) Call a static method that passes the listener and the file name as arguments
 *   SongPlayer.playFile(waiter,  "/.songfiles/tada.wav");
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import songplayer.SongPlayer;

public class PlayASongWithEachButtonClick extends JFrame {
  
  /**
   * This program allows you to play a song every time the button is clicked.
   */
  public static void main(String[] args) {
    new PlayASongWithEachButtonClick().setVisible(true);
  }

  // The sound files must be in the project under folder /songfiles/
  public static String baseDir = System.getProperty("user.dir")
      + System.getProperty("file.separator") + "songfiles"
      + System.getProperty("file.separator");

  private JButton button = new JButton("Play a song");

  private JTextField textField = new JTextField(
      "File names here, but can be edited to show GUI still works");

  public PlayASongWithEachButtonClick() {
    // lay out GUI
    this.setSize(600, 120);
    this.setLocation(210, 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    button.setSize(120, 30);
    button.setLocation(10, 5);
    button.addActionListener(new ButtonListener());
    add(button);

    textField.setSize(590, 30);
    textField.setLocation(5, 50);
    textField.setBackground(Color.PINK);
    add(textField);

    // set up model
    populateList();
    index--;
  }

  private int index;

  ArrayList<String> audioFileNames = new ArrayList<String>();

  public void populateList() {
    audioFileNames.add(baseDir + "tada.wav");
    audioFileNames.add(baseDir + "spacemusic.au");
    audioFileNames.add(baseDir + "flute.aif");
    audioFileNames.add(baseDir + "BlueRidgeMountainMist.mp3");
    audioFileNames.add(baseDir + "SwingCheese.mp3");
    audioFileNames.add(baseDir + "DeterminedTumbao.mp3");
    audioFileNames.add(baseDir + "UntameableFire.mp3");
  }

  private class ButtonListener implements ActionListener {

    // Play one of the songs stored in the file names shown in populate
    // list in a circular buffer. playFile begins each song in a new Thread
    // so many can play at once. It is up to you to figure out what to do
    // when each songs ends. The listener above only prints the file name
    // passed back from the object that just finished playing that song.
    public void actionPerformed(ActionEvent e) {
      index++;
      if (index >= audioFileNames.size())
        index = 0;
      String audioFileName = audioFileNames.get(index);
      textField.setText(audioFileName);

      // This is how you play a song once you have created an audioFileName
      SongPlayer.playFile(audioFileName);
    }
  }
}