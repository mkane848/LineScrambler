package kane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

/*******************************************************
 
View class will display the code unscrambled so far with contiguous line numbers.

View class will provide an undo option with the help of Student

@author Mike Kane
@since 03/25/2014
******************************************************** */
public class LineScramblerView
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


    // *********************************************************
    // ******************** GUI Components *********************
    // *********************************************************

    // *********************************************************
    // ******************** Constructor ************************
    // *********************************************************
	
	public LineScramblerView()
	{
		
		
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
	 * Return the String to be displayed
	 * @param lineNumber integer that holds the line number being used as the key to find the correct LineHolder
	 * @param myLines ArrayList of LineHolders to seek through
	 * @return String of the lineHolder we're looking to display, returns "ERROR" if something goes wrong
	 */
	public String getLineRef(int lineNumber, ArrayList<LineHolder> myLines)
	{
		LineHolder thisLine;
		for(int i = 0; i < myLines.size(); i++)
		{
			thisLine = myLines.get(i);
			if(thisLine.getCurrentLocationRef() == lineNumber)
			{
				return thisLine.getLineRef();
			}
		}
		return "ERROR";
	}

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
	 * Allows the user to undo their last move
	 * @param thisScrambler
	 * @param moveStack
	 * @param myLines
	 * @param thisTutor
	 */
	public void undoMove(LineScrambler thisScrambler, Stack moveStack, ArrayList<LineHolder> myLines, LineScramblerTutor thisTutor)
	{
		int positionOne = (int) moveStack.pop();
		int positionTwo = (int) moveStack.pop();
		
		thisScrambler.swapLines(myLines, positionOne, positionTwo, thisTutor);
		
	}

    // *********************************************************
    // ******************** Printing Methods *******************
    // *********************************************************
	/**
	 * Provides the text view for the exercise
	 * @param myLines ArrayList of LineHolder objects
	 * @param numberOfMoves integer that holds the number of moves the Student has made
	 * @param thisTutor Tutor object
	 */
	public void exerciseView(ArrayList<LineHolder> myLines, int numberOfMoves, LineScramblerTutor thisTutor)
	{
		System.out.printf("-Welcome to the Scrambler!-\n");
		System.out.printf("Unscamble the following code:\n\n");
		//Print out scrambled code lines
		printLines(myLines, numberOfMoves, thisTutor);
		
	}
	/**
	 * Prints out the line numbers and line of codes
	 * @param myLines ArrayList of LineHolders
	 * @param numberOfMoves integer that holds the number of moves made by the student
	 * @param thisTutor Tutor object
	 */
	public void printLines(ArrayList<LineHolder> myLines, int numberOfMoves, LineScramblerTutor thisTutor)
	{
		for(int i=0; i<myLines.size(); i++)
		{
			System.out.printf((i+1) + ": " + getLineRef(i+1, myLines));
			System.out.println();
	    }
		System.out.printf("\nNumber of moves made: " + numberOfMoves + "\n");
		System.out.printf("Current Edit Distance: " + thisTutor.returnTotalEditDistance(myLines) + "\n\n");
	}
	

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
