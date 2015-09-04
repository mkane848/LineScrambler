package kane;

import java.util.Stack;

/*******************************************************
Define a student class that will keep track of every move made by the student.
View class will provide an undo option, and student class will help it
rewind the scrambled code of the student all the way back to the original code.
   
@author Mike Kane
@since 03/25/2014
******************************************************** */
public class LineScramblerStudent
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
	private int m_numberOfMoves;
	
	//Stack that holds record of all moves made [for use by UNDO]
	private Stack m_moveStack = new Stack();

    // *********************************************************
    // ******************** GUI Components *********************
    // *********************************************************

    // *********************************************************
    // ******************** Constructor ************************
    // *********************************************************
	
	public LineScramblerStudent()
	{
		m_numberOfMoves = 0;
		
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
	/**
	 * returns a reference to the Stack that holds the moves
	 * @return moveStack reference
	 */
	public Stack returnStackRef()
	{
		return m_moveStack;
	}
	/**
	 * return the total number of moves made by student
	 * @return integer that holds total number of moves
	 */
	public int returnNumberOfMovesRef()
	{
		return m_numberOfMoves;
	}
	/**
	 * pops a line value from the stack
	 * @return integer value that holds the line number affected
	 */
	public int popFromStack()
	{
		return (int) m_moveStack.pop();
	}

    // *********************************************************
    // ******************** Mutators ***************************
    // *********************************************************
	/**
	 * Increase the total moves made by student by one
	 */
	public void increaseNumberOfMoves()
	{
		m_numberOfMoves++;
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
	/**
	 * Places the line numbers of the last two lines affected by the student into the stack
	 * @param originalSpot the line number of the line the student selected first
	 * @param newSpot the line number of the spot where the student moved the line to
	 */
	public void pushToStack(int originalSpot, int newSpot)
	{
		m_moveStack.push(originalSpot);
		m_moveStack.push(newSpot);
	}
	

    // *********************************************************
    // ******************** Printing Methods *******************
    // *********************************************************
	

    // *********************************************************
    // ******************** Debugging Methods ******************
    // *********************************************************

    public static void main( String args[] )
    {	
    	
    }
	
};

    // *********************************************************
    // ******************** Trash Methods **********************
    // *********************************************************