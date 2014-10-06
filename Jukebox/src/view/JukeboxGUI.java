package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;




//import model.Jukebox;
import model.SongCollection;

@SuppressWarnings("serial")
public class JukeboxGUI extends JFrame {

	private JTable table;
//	private Jukebox jukebox;
	private SongCollection songCollection;
	
	//Listener for JTable that interacts with the jukebox
	class SongSelectionListener implements ListSelectionListener {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int rowIndex = table.getSelectionModel().getLeadSelectionIndex();
			int colIndex = 1;
			
			//Trigger on mouse depress only
			if(table.getSelectionModel().getValueIsAdjusting() == false){
				Object returns = table.getModel().getValueAt(rowIndex, colIndex);
				System.out.println((String)returns);
			}
		}
	}	
	
	public JukeboxGUI()
	{
		// Instantiate song collection
	    songCollection = new SongCollection();
	
		// Create JTable from song collection
		table = new JTable(songCollection);
		table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
		
		// TODO: add Table listeners
	    table.getSelectionModel().addListSelectionListener(new SongSelectionListener()); 
	   
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
