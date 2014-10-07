package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;











import model.Jukebox;
//import model.Jukebox;
import model.SongCollection;

@SuppressWarnings("serial")
public class JukeboxGUI extends JFrame {

	private ModelMode mode;
	private JTable table;
	private JPanel songContainer;
	private JPanel studentContainer; //Iteration2
	private Jukebox jukebox;
	private JButton logoutButton;
	private JButton loginButton;
	private JTextField userNameField;
	private JTextField passwordField;
	private JLabel userNamePrompt;
	private JLabel passwordPrompt;
	private LoginListener loginListener;
	private LogoutListener logoutListener;
	//private SongCollection songCollection;
	private Banner feedback;
	private TextUpdater textUpdater; 
	
	//Listener for JTable that interacts with the jukebox
	class SongSelectionListener implements ListSelectionListener {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {

			//Trigger on mouse depress only
			if(table.getSelectionModel().getValueIsAdjusting() == false){
				
				//Getting row and col info
				int rowIndex = table.getSelectedRow();
				rowIndex = table.convertRowIndexToView(rowIndex);
				int colIndex = 1;
				
				//Printing song title at that row and col
				Object returns = table.getModel().getValueAt(rowIndex, colIndex);
				System.out.println((String)returns);
				//this adds song to queue
				//Note: this returns a bool
				jukebox.requestSong((String)returns);
			}
		}
	}	
	
	public JukeboxGUI()
	{
		mode = ModelMode.ACCOUNTMODE; //Use ACCOUNTMODE for iteration 2
		// Instantiate song collection
	    //songCollection = new SongCollection();
		jukebox = new Jukebox();
	    feedback = new Banner();
	
		// Create JTable from song collection
		table = new JTable(jukebox.getSongCollection());
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
		
		// Add Table listener
	    table.getSelectionModel().addListSelectionListener(new SongSelectionListener()); 
	   
		// JFrame setup
		//this.setLayout(new GridLayout(2, 1));
	    this.setLayout(null);
	    
	    //Login screen things:
	    userNamePrompt = new JLabel("User name:");
	    userNamePrompt.setSize(200, 30);
	    userNamePrompt.setLocation(200, 70);
	    userNameField = new JTextField();
	    userNameField.setSize(200, 30);
	    userNameField.setLocation(290, 70);
	    passwordPrompt = new JLabel("Password:");
	    passwordPrompt.setSize(200, 30);
	    passwordPrompt.setLocation(200, 110);
	    passwordField = new JTextField();
	    passwordField.setSize(200, 30);
	    passwordField.setLocation(290, 110);
	    //Buttons:
	    logoutButton = new JButton("Logout");
	    logoutButton.setSize(100, 20);
	    logoutButton.setLocation(230, 230);
	    logoutListener = new LogoutListener();
	    logoutButton.addActionListener(logoutListener);
	    
	    loginButton = new JButton("Sign In");
	    loginButton.setSize(100, 20);
	    loginButton.setLocation(230, 230);
	    loginListener = new LoginListener();
	    loginButton.addActionListener(loginListener);
	    
	    
	    feedback.setSize(600, 110);
	    feedback.setLocation(180,120);
		// JPanel layout
		songContainer = new JPanel();
		songContainer.setLayout(new BorderLayout());
		songContainer.setSize(500, 200);
		songContainer.setLocation(42,0);
		
		// Add Table to JPanel
		songContainer.add(new JScrollPane(table), BorderLayout.CENTER);
	
		// Add Label to JPanel
		JPanel top = new JPanel();
		top.add(new JLabel("JukeBox")); 
		songContainer.add(top, BorderLayout.NORTH);
		
		// Add appropriateJPanel to JFrame
		updateGUI();

		// JFrame final setup
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}
	
	//The next three methods are used to switch between GUI windows
	private void updateGUI(){
		if (mode==ModelMode.ACCOUNTMODE)
			drawAccountGUI();
		else
			drawPlaylistGUI();
	}
	
	private void drawAccountGUI(){
		this.setVisible(false);
		this.remove(songContainer);
		this.remove(feedback);
		this.remove(logoutButton);
		this.add(loginButton);
		this.add(userNameField);
		this.add(passwordField);
		this.add(userNamePrompt);
		this.add(passwordPrompt);
		this.setVisible(true);
	}
	
	private void drawPlaylistGUI(){
		this.setVisible(false);
		this.remove(loginButton);
		this.remove(userNameField);
		this.remove(passwordField);
		this.remove(userNamePrompt);
		this.remove(passwordPrompt);
		this.add(songContainer);
		this.add(feedback);
		this.add(logoutButton);
		this.setVisible(true);
	}
	
	//Reacts to "sign in" button
	private class LoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String userID = userNameField.getText();
			String userPassword = passwordField.getText();
			if (!jukebox.login(userID,  userPassword)){
				//TODO: Print failure statement
				System.out.println("DEBUG: Login unsuccessful");
				return;
			}
			else{
				userNameField.setText("");
				passwordField.setText("");
				mode = ModelMode.PLAYMODE;
				System.out.println("DEBUG: Login successful");
				updateGUI();
			}
		}	
	}
	//Reacts to "sign in" button
	private class LogoutListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jukebox.logout();
			mode = ModelMode.ACCOUNTMODE;
			updateGUI();
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
			g.drawString(jukebox.getStatus(), 50, 100);
		}
	}
	
	public static void main(String[] args)
	{
		new JukeboxGUI();
	}
}
