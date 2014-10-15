package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Jukebox;
import model.Song;
import model.SongCollection;
import model.StudentCollection;


/**
 * Defines 2 different GUI views
 * 	1-> Login screen for a Student
 *  2-> Playlist screen
 * @author Jonathan Snavely
 * @author Anthony Rodriguez
 *
 */
@SuppressWarnings("serial")
public class JukeboxGUI extends JFrame {

	private JTable table;
	private JList<Song> songList;
	private JPanel songContainer;
	private JButton playButton;
	private Jukebox jukebox;
	private JButton loginButton;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JPanel credentialsContainer;
	private JLabel jukeStatus;
	
	public JukeboxGUI()
	{
		if (!loadData())
			jukebox = new Jukebox();
	    
		// JTable and JList song collection setup
		table = new JTable(jukebox.getSongCollection());
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
		songContainer = new JPanel();
		songContainer.setLayout(new BoxLayout(songContainer, BoxLayout.X_AXIS));
		songContainer.setSize(600, 400);
		songContainer.setLocation(50, 0);
		songList = new JList<Song>(jukebox.getSongQueue());
		songContainer.add(new JScrollPane(songList));
		songContainer.add(Box.createRigidArea(new Dimension(5,0)));	//Gap
		songContainer.add(new JScrollPane(table));
		add(songContainer);
					   		    
	    //Username and Password setup
	    credentialsContainer = new JPanel();
	    credentialsContainer.setSize(220, 50);
	    credentialsContainer.setLocation(240, 500);
	    credentialsContainer.setLayout(new GridLayout(2, 2));
	    usernameLabel = new JLabel("Username");
	    usernameLabel.setHorizontalAlignment(SwingConstants.CENTER );
	    userNameField = new JTextField();    
	    passwordLabel = new JLabel("Password");
	    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER );
	    passwordField = new JPasswordField();
	    credentialsContainer.add(usernameLabel);
	    credentialsContainer.add(userNameField);
	    credentialsContainer.add(passwordLabel);
	    credentialsContainer.add(passwordField);
	    add(credentialsContainer);
	    
	    //Login Button setup
	    loginButton = new JButton("Login");
	    loginButton.setSize(100, 30);
	    loginButton.setLocation(240,  570);
	    loginButton.addActionListener(new LoginListener());
	    add(loginButton);

	    //Play Song button setup
	    playButton = new JButton("Add Song");
	    playButton.setSize(100, 30);
	    playButton.setLocation(350,  570);
	    playButton.addActionListener(new AddSongListener());
	    add(playButton);
	    
	    //Feedback banner setup
	    jukeStatus = new JLabel("Login to access jukebox!");
	    jukeStatus.setLocation(235, 470);
	    jukeStatus.setSize(400, 20);
	    add(jukeStatus);	    

		// JFrame final setup
	    this.setLayout(null);
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
		this.addWindowListener(new SaveListener());
	}
	
	//Reacts to "sign in" button
	private class LoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(loginButton.getText()== "Login"){
				String userID = userNameField.getText();
				String userPassword = new String(passwordField.getPassword());
					
				//Print failure statement
				if (!jukebox.login(userID,  userPassword)){
					jukeStatus.setText("Login unsuccessful");
					System.out.println("Login unsuccessful");
					return;
				}
				else{
					userNameField.setText("");
					passwordField.setText("");
					userNameField.disable();
					passwordField.disable();
					System.out.println("Login successful");
					loginButton.setText("Logout");
					jukeStatus.setText(jukebox.getLoggedStudent().toString());
				}			
			}
			else if(loginButton.getText()== "Logout"){
				userNameField.enable();
				passwordField.enable();
				jukeStatus.setText(jukebox.getLoggedStudent().getID() + " logged out");
				jukebox.logout();
				System.out.println("Logout successful");
				loginButton.setText("Login");
			}
		}	
	}
	
	//Reacts to "Add Song" button
	private class AddSongListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(table.getSelectionModel().getValueIsAdjusting() == false){
				
				//Getting row and col info
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1)	//Edge case - no row selected on table
					return;
				
				rowIndex = table.convertRowIndexToView(rowIndex);
				int colIndex = 1;
				
				//Printing song title at that row and col
				Object returns = table.getModel().getValueAt(rowIndex, colIndex);
				System.out.println((String)returns);
				//this adds song to queue
				Boolean bool = jukebox.requestSong((String)returns);
				if(bool){
					jukeStatus.setText(jukebox.getLoggedStudent().toString());
				}
			}
		}	
	}
	
//	private class Banner extends JPanel{
//		@Override
//		protected void paintComponent(Graphics g){
//			g.setColor(Color.red);
//			super.paintComponent(g);
//			g.drawString(jukebox.getStatus(), 0, 0);
//		}
//	}
	
	private class SaveListener implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			int ans = JOptionPane.showConfirmDialog(null,  "Save data?", "Save", JOptionPane.YES_NO_OPTION);
			if(ans == JOptionPane.YES_OPTION){
				saveData();
			}	
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//Save order: date -> songCollection -> studentRecord
	public void saveData(){
		try{
			FileOutputStream outFS = new FileOutputStream(new File("savedata.dat"));
			ObjectOutputStream outObj = new ObjectOutputStream(outFS);
			outObj.writeObject(jukebox.getDateLastPlayed());
			outObj.writeObject(jukebox.getSongCollection());
			outObj.writeObject(jukebox.getStudents());
			outObj.close();
			outFS.close();
			System.out.println("State saved");
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadData() {
		// TODO 2: implement loadData
	
		int ans = JOptionPane.showConfirmDialog(null,  "Load data?", "Load", JOptionPane.YES_NO_OPTION);
		if(ans == JOptionPane.NO_OPTION){
			return false;
		}
		try {
			File inputFile = new File("savedata.dat");
			if (!inputFile.exists() || inputFile.isDirectory()){
				System.out.println("No data to load!");
				return false;
			}
			FileInputStream inFS = new FileInputStream(inputFile);
			ObjectInputStream inObject = new ObjectInputStream(inFS);
			GregorianCalendar date = (GregorianCalendar) inObject.readObject();
			SongCollection songs = (SongCollection) inObject.readObject();
			StudentCollection students = (StudentCollection) inObject.readObject();
			jukebox = new Jukebox(date, songs, students);
			inObject.close();
			inFS.close();
		} catch (Exception e) {
			System.out.println("No loaded data!");
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args){
		new JukeboxGUI();
	}
}
