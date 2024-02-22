package d3;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class Controller
{
    private final Model model;
    private final View view;
    private String errorMessages;

    public Controller()
    {
        model = new Model();
        view = new View(this);
    }
     
    public void showView()
    {
        view.displaySelf();
    }
    
    public boolean isValid(String userNums, String userDraws)
    {
        errorMessages = "";
        boolean digitsAreInBounds;
        boolean noDuplicatesFound;
        boolean drawingFormat;
        boolean correctFormat;
        String [] nums;
        
        
        // handles 111...11111 error in userDraws
        // I chose regex \d{1,9} because the largest int that can be parsed is 2147483647
        drawingFormat = (userDraws.matches("\\d{1,9}"));
        
        if(drawingFormat==true)
        {
            // check if number of drawings is integer between 1 and 100000
            if(Integer.parseInt(userDraws)<1 || Integer.parseInt(userDraws)>100000)
            {
                errorMessages += "Number of drawings entered must be integer from 1 to 100000.\n";
                drawingFormat = false;
            }
        }
        else
        {
            errorMessages += "Number of drawings entered must be integer from 1 to 100000.\n";
        }
        
        
        // check if user input matches format of digits and spaces using regex
        correctFormat = userNums.matches("\\d{1,2}\\s+\\d{1,2}\\s+\\d{1,2}\\s+\\d{1,2}\\s+\\d{1,2}\\s+\\d{1,2}");
        
        if(correctFormat==false)
            errorMessages+="Input is not exactly just 6 different one- or two-digit integers separated by one or more spaces\n";

        // check that all integers are in bounds from 1-60
        nums = userNums.split("\\s+");

        digitsAreInBounds = true;
        for(int i=0; i<nums.length; i++)
        {
            // added condition so that program does not crash if user enters letter/symbol, only checks bounds on numerical input
            if(nums[i].matches("\\d{1,9}"))
            {    
                if(Integer.parseInt(nums[i])>60 || Integer.parseInt(nums[i])<1)
                {
                digitsAreInBounds = false;
                break;
                }
            }
            else
            {
                digitsAreInBounds = false;
                break;
            }      
        }

        
        // adds bounds error to error message string
        if(digitsAreInBounds==false)
            errorMessages+="Input contains an integer that is out of bounds\n";
        
        
        // check that input does not contain duplicate integers
        noDuplicatesFound = false;
        int numCount = 0;
        Set<Integer> numsSet = new HashSet<>(); 

        for(int x=0; x<nums.length; x++)
        {
            if(nums[x].matches("\\d{1,9}"))
            {
            numsSet.add(Integer.parseInt(nums[x]));
            numCount++;
            }
        }
        if(numsSet.size()==numCount)
            noDuplicatesFound=true;

        // adds duplicate int error to message string
        if(noDuplicatesFound==false)
            errorMessages+="Input contains duplicate integers\n";

        return (drawingFormat&&correctFormat&&digitsAreInBounds&&noDuplicatesFound);        
    }
    
    public String getErrorMessages()
    {
        return errorMessages;
    }
    
    public String getLottoResults(String nums, String numDraw)
    {
        return model.doLottoDrawing(nums, numDraw);
    }
    
    public List<Integer> getMatches()
    {
        return model.getMatchingNums();
    }
}
