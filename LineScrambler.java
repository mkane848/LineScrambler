package kane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;


/*******************************************************
  Controls the LineScrambler exercise
  @author Mike Kane
  @since 03/25/2014
******************************************************** */

public class LineScrambler
{
    // *********************************************************
    // **************** Configuration Variables ****************
    // *********************************************************

    // *********************************************************
    // ******************** Class Constants ********************
    // *********************************************************

    // *********************************************************
    // ******************** Class Variables ********************
    // *********************************************************

    /** One line description of the variable */
	// Vector that holds the line of code
	private Vector<String> m_originalCode = new Vector<String>();
	
	// ArrayList that holds a all of our LineHolder objects
	private static ArrayList<LineHolder> m_myLines = new ArrayList();

	// ArrayList that we use to provide shuffled line numbers for our LineHolder objects
	private ArrayList<Integer> m_newLineNumbers = new ArrayList<Integer>();

    // *********************************************************
    // ******************** GUI Components *********************
    // *********************************************************

    // *********************************************************
    // ******************** Constructor ************************
    // *********************************************************
	public LineScrambler(Vector<String> code)
	{
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
		
		//runExercise(); 
	}

    // *********************************************************
    // ******************** Paint - View ***********************
    // *********************************************************

    // *********************************************************
    // ******************** actionPerformed - Controller *******
    // *********************************************************

    // *********************************************************
    // ******************** Selectors **************************
    // *********************************************************
	

    // *********************************************************
    // ******************** Mutators ***************************
    // *********************************************************

    // *********************************************************
    // ******************** Code Generation ********************
    // *********************************************************

    // *********************************************************
    // ******************** Code Explanation *******************
    // *********************************************************

    // *********************************************************
    // ******************** Utility Methods ********************
    // *********************************************************
	  /** 
	   For each function, one-line description of the function
	   @param parameterName Describe the parameter, starting with its data type
	   @return What the function returns - don't include if void. Also list special cases, such as what is returned if error.
	   */
	/**
	 * Provides text-based interface for user to complete the exercise
	 * @param thisStudent Student object reference
	 * @param thisTutor Tutor object reference
	 * @param thisView View object reference
	 * @param moveStack Stack object reference
	 */
	public void selectLines(LineScramblerStudent thisStudent, LineScramblerTutor thisTutor, LineScramblerView thisView, Stack moveStack)
	{
		Scanner s = new Scanner(System.in);
		int originalLine, newLine;
		boolean willUndo;
		boolean isValidMove;
		String answer;
		
		/*if(moveStack.size() > 0)
		{
			do{
				System.out.printf("Type \"Y\" to UNDO your last move: ");
				answer = s.next();
				if(answer.equals("Y"))
				{
					willUndo = true;
					System.out.printf("Undoing your last move...\n\n");
					thisView.undoMove(m_myLines, moveStack);
					thisView.printLines(m_myLines, thisStudent.returnNumberOfMovesRef(), thisTutor);
				}
				else
				{
					willUndo = false;
				}
				
			}while(willUndo && (moveStack.size() > 0));
			
		}*/
		
		do{
			System.out.printf("Select a line to move: ");
			originalLine = s.nextInt();
			isValidMove = validateLineNumber(originalLine);
		}while(isValidMove == false);
		
		do{
			System.out.printf("Select line to move it to: ");
			newLine = s.nextInt();
			isValidMove = validateLineNumber(newLine);
		}while(isValidMove == false);
		
		if(isValidMove == true)
		{
			swapLines(m_myLines, originalLine, newLine, thisTutor);
			thisStudent.pushToStack(originalLine, newLine);
		}
	}
	
	/**
	 * Uses the user inputs to make changes to the LineHolder objects and change their current positions
	 * @param myLines LineHolder ArrayList reference
	 * @param originalLine integer that holds the spot of the first line being referenced
	 * @param newLine integer that holds the spot of the second line being referenced
	 * @param thisTutor Tutor object reference
	 */
	public void swapLines(ArrayList<LineHolder> myLines, int originalLine, int newLine, LineScramblerTutor thisTutor)
	{
		LineHolder lineOne, lineTwo;
		int i = 0;
		//Store line being moved in lineOne
		do
		{
			lineOne = myLines.get(i);
			i++;
			
		}while(lineOne.getCurrentLocationRef() != originalLine);
		
		int count = 0;
		if(originalLine < newLine)
		{
			for(int j = originalLine+1; j <= newLine; j++)
			{
				do
				{
					lineTwo = myLines.get(count);
					count++;
				}while(lineTwo.getCurrentLocationRef() != j);
				lineTwo.decreaseCurrentLocation();
				//System.out.printf("Moving Line " + j + " to " + (lineTwo.getCurrentLocationRef() + "\n"));
				count = 0;
			}		
		}
		else if (originalLine > newLine)
		{
			for(int j = originalLine-1; j >= newLine; j--)
			{
				do
				{
					lineTwo = myLines.get(count);
					count++;
				}while(lineTwo.getCurrentLocationRef() != j);
				lineTwo.increaseCurrentLocation();
				//System.out.printf("Moving Line " + j + " to " + (lineTwo.getCurrentLocationRef() + "\n"));
				count = 0;
			}		
		}
		else
		{
			return;
		}
		lineOne.setCurrentLocation(newLine);
	}
	
	/**
	 * Validates the line numbers that the user inputs
	 * @param lineGiven integer that holds the line number the user wants to affect
	 * @return boolean true or false
	 */
	public boolean validateLineNumber(int lineGiven)
	{
		if(lineGiven > m_originalCode.size() || lineGiven == 0)
		{
			return false;
		}
		return true;
	}

	/**
	 * Runs the LineScrambler exercise after all of the pieces are set up
	 */
    public void runExercise()
    {
    	LineScramblerView thisView = new LineScramblerView();
    	LineScramblerStudent thisStudent = new LineScramblerStudent();
    	LineScramblerTutor thisTutor = new LineScramblerTutor();
    	
    	thisView.exerciseView(m_myLines, thisStudent.returnNumberOfMovesRef(), thisTutor);
    	while(thisTutor.returnTotalEditDistance(m_myLines) != 0)
    	{
    		selectLines(thisStudent, thisTutor, thisView, thisStudent.returnStackRef());
    		thisStudent.increaseNumberOfMoves();
    		thisView.printLines(m_myLines, thisStudent.returnNumberOfMovesRef(), thisTutor);	
    	}
    	
    	System.out.printf("You've completed the exercise! Congratulations");
    }
	// *********************************************************
    // ******************** Printing Methods *******************
    // *********************************************************


    // *********************************************************
    // ******************** Debugging Methods ******************
    // *********************************************************

    public static void main( String args[] )
    {	
    	/*Vector<String> demo = new Vector<String>();
    	demo.addElement("Line1");  
    	demo.addElement("Line23");  
    	demo.addElement("Line23");  
    	demo.addElement("Line4");  
    	demo.addElement("Line5");  
    	demo.addElement("Line6");  
    	
    	LineScrambler thisScrambler = new LineScrambler(demo);*/
 
    }
	
};

    // *********************************************************
    // ******************** Trash Methods **********************
    // *********************************************************