package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Jukebox;
import model.ModelMode;
import model.SongCollection;
import model.Student;
import model.Song;


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

	private ModelMode mode;
	private JTable table;
	private JList<Song> songList;
	
	private JPanel songContainer;
	private JPanel queueContainer;
	
	private JPanel studentContainer; //Iteration2
	private JButton playButton;
	private Jukebox jukebox;
	private JButton logoutButton;
	private JButton loginButton;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JLabel userNamePrompt;
	private JLabel passwordPrompt;
	private LoginListener loginListener;
	//private LogoutListener logoutListener;
	private AddSongListener addListener;
	private Banner feedback;
	private TextUpdater textUpdater; 
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	JPanel credentialsContainer;
	
	
	
	public JukeboxGUI()
	{
		//mode = ModelMode.ACCOUNTMODE; //Use ACCOUNTMODE for iteration 2
		jukebox = new Jukebox();
	    
		// JTable song collection setup
		table = new JTable(jukebox.getSongCollection());
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
		songContainer = new JPanel();
		songContainer.setLayout(new BoxLayout(songContainer, BoxLayout.X_AXIS));
		songContainer.setSize(600, 400);
		songContainer.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		songList = new JList<Song>(jukebox.getSongQueue());
		songContainer.add(new JScrollPane(songList));
		songContainer.add(Box.createRigidArea(new Dimension(5,0)));
		songContainer.add(new JScrollPane(table));
		add(songContainer);
					   
		// JList queue setup
//		queueContainer = new JPanel();
		
		// JFrame setup
	    this.setLayout(null);
	    
	    //Username and Password Stuff
	    credentialsContainer = new JPanel();
	    credentialsContainer.setSize(220,  50);
	    credentialsContainer.setLocation(350, 500);
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
	    loginButton.setLocation(400,  570);
	    loginButton.addActionListener(new LoginListener());
	    add(loginButton);

	    //Play Song button setup
	    playButton = new JButton("Add Song");
	    playButton.setSize(100, 20);
	    playButton.setLocation(500,  570);
	    playButton.addActionListener(new AddSongListener());
	    add(playButton);
	    
	    //Feedback banner setup
	    feedback = new Banner();
	    feedback.setSize(600, 200);
	    feedback.setLocation(205,190);
	    
	   
//		// Add Label to JPanel
//		JPanel top = new JPanel();
//		top.add(new JLabel("JukeBox")); 
//		songContainer.add(top, BorderLayout.NORTH);
//		
//		// Add appropriate JPanel to JFrame
//		updateGUI();

		// JFrame final setup
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}
	
	//The next three methods are used to switch between GUI windows
//	private void updateGUI(){
//		if (mode==ModelMode.ACCOUNTMODE)
//			drawAccountGUI();
//		else
//			drawPlaylistGUI();
//	}
//	
//	private void drawAccountGUI(){
//		this.setVisible(false);
//		this.remove(songContainer);
//		this.remove(feedback);
//		this.remove(logoutButton);
//		this.remove(playButton);
//		this.add(loginButton);
//		this.add(userNameField);
//		this.add(passwordField);
//		this.add(userNamePrompt);
//		this.add(passwordPrompt);
//		this.setVisible(true);
//	}
//	
//	private void drawPlaylistGUI(){
//		this.setVisible(false);
//		this.remove(loginButton);
//		this.remove(userNameField);
//		this.remove(passwordField);
//		this.remove(userNamePrompt);
//		this.remove(passwordPrompt);
//		this.add(playButton);
//		this.add(songContainer);
//		this.add(logoutButton);
//		this.add(feedback);
//		feedback.repaint();
//		this.setVisible(true);
//	}
	
	//Reacts to "sign in" button
	private class LoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(loginButton.getText()== "Login"){
				String userID = userNameField.getText();
				String userPassword = new String(passwordField.getPassword());
					
				//Print failure statement
				if (!jukebox.login(userID,  userPassword)){
					System.out.println("Login unsuccessful");
					return;
				}
				else{
					userNameField.setText("");
					passwordField.setText("");
					System.out.println("Login successful");
					loginButton.setText("Logout");
					//feedback.repaint();
				}			
			}
			else if(loginButton.getText()== "Logout"){
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
				//Note: this returns a bool
				jukebox.requestSong((String)returns);
				//drawPlaylistGUI();
			}
		}	
	}
	
	private class TextUpdater implements Observer{
		public TextUpdater(){
		}
		@Override
		public void update(Observable o, Object arg) {
			feedback.repaint();
		}
	}
	
	private class Banner extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawString(jukebox.getStatus(), 15, 10);
		}
	}
	
	public static void main(String[] args)
	{
		new JukeboxGUI();
	}
}
