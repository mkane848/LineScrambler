package kane;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*******************************************************
Creates the LineScrambler GUI
@author Mike Kane
@since 03/25/2014
******************************************************** */

public class LineScramblerGui {
	
	// *********************************************************
    // **************** Configuration Variables ****************
    // *********************************************************

    // *********************************************************
    // ******************** Class Constants ********************
    // *********************************************************

    // *********************************************************
    // ******************** Class Variables ********************
    // *********************************************************


	private JFrame frame;
	private JTable table;
	private JTextField newPosition;
	private JTextField selectedLine;
	private JLabel guiEditDistance;
	private JLabel guiMovesMade;
	private DefaultTableModel thisModel;
	
	private LineScrambler thisScrambler;
	private LineScramblerView thisView;
	private LineScramblerStudent thisStudent;
	private LineScramblerTutor thisTutor;
	private LineHolder lineHolder;
	private LineHolder temp;
	private LineHolder lineOne;
	private Vector<Vector<String>> tableStrings;
	Vector<String> m_originalCode = new Vector<String>();
	
	
	
	private int count = 0;

	 // *********************************************************
    // ******************** GUI Components *********************
    // *********************************************************

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Comment out after testing
					Vector<String> demo = new Vector<String>();
					demo.addElement("Line1");  
			    	demo.addElement("Line2");  
			    	demo.addElement("Line3");  
			    	demo.addElement("Line4");  
			    	demo.addElement("Line5");  
			    	demo.addElement("Line6");  
					
					LineScramblerGui window = new LineScramblerGui(demo);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 // *********************************************************
    // ******************** Constructor ************************
    // *********************************************************
	public LineScramblerGui(Vector<String> code) {
		initialize(code);
	}

	 /** 
	   For each function, one-line description of the function
	   @param parameterName Describe the parameter, starting with its data type
	   @return What the function returns - don't include if void. Also list special cases, such as what is returned if error.
	   */

	// *********************************************************
	// ******************** Paint - View ***********************
	// *********************************************************

	// *********************************************************
    // ******************** actionPerformed - Controller *******
	// *********************************************************

	// *********************************************************
	// ******************** Selectors **************************
	// *********************************************************
	public void updateTable(ArrayList<LineHolder> myLines, JPanel panel, DefaultTableModel thisModel)
	{
	
		for(int i = 0; i < myLines.size(); i++)
		{
			thisModel.setValueAt((i+1) + ": " + thisView.getLineRef(i+1, myLines), i, 0);
		}
	
	}
	
	public JTextField getSelectedLine() 
	{
		return newPosition;
	}
	public JTextField getNewPosition() 
	{
		return selectedLine;
	}
	
	public Vector<String> getOriginalCode()
	{
		return m_originalCode;
	}
	
	// *********************************************************
    // ******************** Mutators ***************************
    // *********************************************************
	public void setEditDisplay(String distance)
	{
		guiEditDistance.setText(distance);
	}
	
	public void setMoveDisplay(String moves)
	{
		guiMovesMade.setText(moves);
	}
	
    // *********************************************************
    // ******************** Code Generation ********************
    // *********************************************************

    // *********************************************************
    // ******************** Code Explanation *******************
    // *********************************************************

    // *********************************************************
    // ******************** Utility Methods ********************
    // *********************************************************
	
	private void initialize(Vector<String> code) {
		
		
		thisScrambler = new LineScrambler(code);
		thisView = new LineScramblerView();
    	thisStudent = new LineScramblerStudent();
    	thisTutor = new LineScramblerTutor();
    	
    	ArrayList<Integer> m_newLineNumbers = new ArrayList<Integer>();
    	final ArrayList<LineHolder> m_myLines = new ArrayList();
    	
    	//makes a deep copy of the code provided by the tutor
    	m_originalCode = (Vector)code.clone();
    			
    	//Create a shuffled collection of line numbers to assign to our LineHolder
    	for(int i = 1; i < m_originalCode.size()+1; i++)
    	{
    		m_newLineNumbers.add(i);
    	}
    	Collections.shuffle(m_newLineNumbers);
    			
    	//Populate LineHolder ArayList
    	for(int i = 0; i < m_originalCode.size(); i++)
    	{
    		m_myLines.add(new LineHolder(m_originalCode.get(i), i+1, m_newLineNumbers.get(i)));
    	}
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/**
		 * 
		 */
		JButton undo = new JButton("Undo");
		undo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(thisStudent.returnStackRef().size() >= 2)
				{
					thisView.undoMove(thisScrambler, thisStudent.returnStackRef(), m_myLines, thisTutor);
					updateTable(m_myLines, panel, thisModel);
				}
			}
		});
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		undo.setBounds(685, 296, 89, 23);
		panel.add(undo);
		/**
		 * 
		 */
		JLabel lblNewLabel = new JLabel("Selected Line:");
		lblNewLabel.setBounds(608, 195, 98, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewPosition = new JLabel("New Position:");
		lblNewPosition.setBounds(608, 226, 89, 14);
		panel.add(lblNewPosition);
		
		guiEditDistance = new JLabel("Current Edit Distance: " + thisTutor.returnTotalEditDistance(m_myLines));
		guiEditDistance.setBounds(581, 355, 193, 14);
		panel.add(guiEditDistance);
		
		guiMovesMade = new JLabel("Total Moves Made: " + thisStudent.returnNumberOfMovesRef());
		guiMovesMade.setBounds(581, 393, 193, 14);
		panel.add(guiMovesMade);
		
		final JLabel guiAnswerStatus = new JLabel("");
		guiAnswerStatus.setBounds(608, 143, 166, 14);
		panel.add(guiAnswerStatus);
		
		
		String[] columnNames = new String[1];
		columnNames[0] = "Code";
		thisModel = new DefaultTableModel(columnNames, 0);
		
	
		tableStrings = new Vector<Vector<String>>();
		for(int i = 0; i < m_myLines.size(); i++)
		{
			temp = m_myLines.get(i);
			Vector<String> tempVector = new Vector<String>();
			tempVector.add((i+1) + ": " + thisView.getLineRef(i+1, m_myLines));
			tableStrings.add(tempVector);
		}
		
		//System.out.println(tableStrings);
		for(int i=0; i<tableStrings.size(); i++){
			thisModel.addRow(tableStrings.get(i));
		}
		
		
		table = new JTable();
		table.setModel(thisModel);
		table.setShowVerticalLines(false);
		table.setBounds(32, 50, 539, 458);
		panel.add(table);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selectedValue = selectedLine.getText();
				String newValue = newPosition.getText();
				if(((Integer.parseInt(selectedValue) > 0) && Integer.parseInt(selectedValue) <= m_myLines.size()) && (((Integer.parseInt(newValue) > 0) && Integer.parseInt(newValue) <= m_myLines.size())))
				{
					thisScrambler.swapLines(m_myLines, Integer.parseInt(selectedValue), Integer.parseInt(newValue), thisTutor);
					updateTable(m_myLines, panel, thisModel);
					thisStudent.increaseNumberOfMoves();
					setEditDisplay("Current Edit Distance: " + thisTutor.returnTotalEditDistance(m_myLines));
					setMoveDisplay("Total Moves Made: " + thisStudent.returnNumberOfMovesRef());
					thisStudent.pushToStack((Integer.parseInt(selectedValue)), (Integer.parseInt(newValue)));
					count = 0;
					do
					{
						lineOne = m_myLines.get(count);
						count++;
						
					}while(lineOne.getCurrentLocationRef() != Integer.parseInt(newValue));
					guiAnswerStatus.setText(thisTutor.checkAnswer(lineOne, m_myLines, m_originalCode));
					selectedLine.setText("");
					newPosition.setText("");
					if(thisTutor.returnTotalEditDistance(m_myLines) == 0)
					{
						guiAnswerStatus.setText("You finished!");
					}
				}
				
			}
		});
		submit.setBounds(685, 262, 89, 23);
		panel.add(submit);
		
		newPosition = new JTextField();
		newPosition.setBounds(688, 223, 86, 20);
		panel.add(newPosition);
		newPosition.setColumns(10);
		
		selectedLine = new JTextField();
		selectedLine.setBounds(688, 192, 86, 20);
		panel.add(selectedLine);
		selectedLine.setColumns(10);
		
		
	}
}
