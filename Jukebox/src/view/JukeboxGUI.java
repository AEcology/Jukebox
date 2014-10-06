package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//import model.Jukebox;
import model.SongCollection;

@SuppressWarnings("serial")
public class JukeboxGUI extends JFrame {

	private JTable table;
//	private Jukebox jukebox;
	private SongCollection songCollection;
	
	//TODO : Write listeners that interact with the jukebox
//	private class AddStudentListener implements ActionListener
//	{
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			try
//			{
//				String first = JOptionPane.showInputDialog("Enter the Student's first name");
//				String second = JOptionPane.showInputDialog("Enter " + first + "'s Last name");
//				long id = Long.parseLong(JOptionPane.showInputDialog("Enter " + first + "'s ID #"));
//				double gpa = Double.parseDouble(JOptionPane.showInputDialog("Enter " + first + "'s GPA"));
//				students.add(new Student(first, second, id, gpa));
//			}
//			catch (Exception err)
//			{
//				JOptionPane.showMessageDialog(null, "Error! Invalid Input");
//			}
//		}
//	}
	
	public JukeboxGUI()
	{
		// Instantiate song collection
	    songCollection = new SongCollection();
	
		// Create JTable from song collection
		table = new JTable(songCollection);
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
		
		// TODO: add Table listeners
//		add = new JButton("Add");
//		add.addActionListener(new AddStudentListener());
	
		// JFrame setup
		this.setLayout(new GridLayout(1, 2));
	
		// JPanel layout
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		
		// Add Table to JPanel
		container.add(new JScrollPane(table), BorderLayout.CENTER);
		
		// Add Label to JPanel
		JPanel top = new JPanel();
		top.add(new JLabel("JukeBox")); 
		container.add(top, BorderLayout.NORTH);
		
		// Add JPanel to JFrame
		this.add(container);
		
		// JFrame final setup
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}
	
	public static void main(String[] args)
	{
		new JukeboxGUI();
	}
}
