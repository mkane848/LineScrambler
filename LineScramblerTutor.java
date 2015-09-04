package kane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/*******************************************************
Will check whether the scrambled code has been correctly unscrambled after each move in view.
It should provide the number of moves made by student to unscramble the code (which is maintained by student class).
It should also provide the edit distance of the current code from the final code - View should display this along with the number of moves made so far.
The tutor will provide feedback if the student picks an incorrect reason.
@author Mike Kane
@since 03/25/2014
******************************************************** */
public class LineScramblerTutor
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
	public LineScramblerTutor()
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
	 * Checks the move that the student just made and informs whether the line they moved is now in the right spot
	 * @param lineOne LineHolder that represents the line the Student chose to move
	 * @param myLines ArrayList of LineHolders
	 * @param originalCode the vector that holds the original code in it's original order
	 * @return boolean answer that lets us know if it's the right answer
	 */
	public String checkAnswer(LineHolder lineOne, ArrayList<LineHolder> myLines, Vector<String> originalCode)
	{	
		if(lineOne.getLineRef().equals(originalCode.get(lineOne.getCurrentLocationRef()-1))) 
		{
			if(lineOne.getCurrentLocationRef() == lineOne.getOriginalLocationRef())
			{
				return "Correct!";
			}
			else
			{
				LineHolder lineTwo;
				for(int i = 0; i < myLines.size(); i++)
				{
					lineTwo = myLines.get(i);
					if(lineOne.equals(lineTwo) && (lineOne.getOriginalLocationRef() != lineTwo.getOriginalLocationRef()))
					{
						int temp = lineOne.getOriginalLocationRef();
						lineOne.setOriginalLocation(lineTwo.getOriginalLocationRef());
						lineTwo.setOriginalLocation(temp);
					}
				}
				
				return "Correct!";
			}
		}
		return "Try Again!";
	}
	/**
	 * Calculates and returns the total edit distance of the current exercise
	 * @param myLines ArrayList of LineHolders
	 * @return integer value that holds the total edit distance
	 */
	public int returnTotalEditDistance(ArrayList<LineHolder> myLines)
	{
		int total = 0;
		LineHolder thisLine;
		for(int i = 0; i < myLines.size(); i++)
		{
			thisLine = myLines.get(i);
			total+=thisLine.getEditDistance();
		}
		
		return total;
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
