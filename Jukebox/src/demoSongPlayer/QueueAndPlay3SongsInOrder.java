package demoSongPlayer;
//package controller;
//
//import model.PlayList;
//import model.Song;
//
//// This is Rick's version for a separate test that a type exists to queue up songs and
//// play them in FIFO order entirely and without overlapping.  This code needs a Song type
//// with a very specific constructor and a PlayList type with a specific method, both
//// of which are unlikely to exist in your design. 
//public class QueueAndPlay3SongsInOrder {
//
//  public static String baseDir = System.getProperty("user.dir")
//      + System.getProperty("file.separator") + "songfiles"
//      + System.getProperty("file.separator");
//
//  public static void main(String[] args) {
//    // Assign the responsibility of queuing Songs and playing them in order, and not overlapping
//    javax.swing.SwingUtilities.invokeLater(new Runnable() {
//      public void run() {
//        PlayList playList = new PlayList();
//
//        Song a = (new Song("Space Music", 7, "Sun Microsytems", baseDir
//            + "spacemusic.au"));
//        Song b = (new Song("Flute", 7, "Sun Microsytems", baseDir + "flute.aif"));
//        Song c = (new Song("Blue Ridge Mountain Mist", 39, "Schuckett, Ralph",
//            baseDir + "BlueRidgeMountainMist.mp3"));
//      
//        // Play 3 songs in FIFO order
//        playList.queueUpNextSong(a);
//        playList.queueUpNextSong(b);
//        playList.queueUpNextSong(c);  
//      }
//    });
//  }
//}
