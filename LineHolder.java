package kane;

/*******************************************************
  Takes original code, formats it to new lineHolder object
  @author Mike Kane
  @since 03/25/2014
******************************************************** */

public class LineHolder
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
	// The line of code
	private String m_lineOfCode;
	
	// The line number it was originally at
	private int m_originalLocation;
	
	// The line number it's at after a scramble/move
	private int m_currentLocation;
	

    // *********************************************************
    // ******************** GUI Components *********************
    // *********************************************************

    // *********************************************************
    // ******************** Constructor ************************
    // *********************************************************

	public LineHolder (String code, int oldLine, int newLine)
	{
		m_lineOfCode = code;
		
		m_originalLocation = oldLine;
		
		m_currentLocation = newLine;
		
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
	 * returns reference to this LineHolder's line of code
	 * @return String that holds the code
	 */
	public final String getLineRef()
	{
		return m_lineOfCode;
	}
	
	/**
	 * returns reference to this LineHolder's current position
	 * @return integer value that represent's the current line
	 */
	public final int getCurrentLocationRef()
	{
		return m_currentLocation;
	}
	
	/**
	 * returns reference to this LineHolder's original position
	 * @return integer value that represents the spot where this line belongs
	 */
	public int getOriginalLocationRef()
	{
		return m_originalLocation;
	}

    // *********************************************************
    // ******************** Mutators ***************************
    // *********************************************************
	/**
	 * Changes the current position value of this LineHolder
	 * @param newSpot integer that holds the line this LineHolder is moving to
	 */
	public void setCurrentLocation(int newSpot)
	{
		m_currentLocation = newSpot;
	}
	/**
	 * Changes the original position value of this LineHolder (main usage for when multiple lines with same String exist)
	 * @param newSpot integer that holds the new original location of this LineHolder
	 */
	public void setOriginalLocation(int newSpot)
	{
	
			m_originalLocation = newSpot;
		
	}
	
	public void increaseCurrentLocation()
	{
		m_currentLocation++;
	}
	
	public void decreaseCurrentLocation()
	{
		m_currentLocation--;
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
	 * calculates and returns the edit distance of this LineHolder
	 * @return integer value that holds this LineHolder's edit distance
	 */
	public int getEditDistance()
	{
		return (Math.abs(m_originalLocation - m_currentLocation));
	}
	
	@Override
	public boolean equals(Object o) { 	
	   return (o instanceof LineHolder) && (((LineHolder) o).getLineRef()).equals(this.getLineRef());
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

