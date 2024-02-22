package d3;
import java.util.List;
import java.util.ArrayList;

public class Model
{
    private final List<Integer> lottoResults;
    private List<Integer> lottoMatches;
    private List<List<Integer>> allDrawings;
    private List<List<Integer>> allMatches;
    private String displayResults;
 
    public Model()
    {
        lottoResults = new ArrayList<>();
    }

    public String doLottoDrawing(String numbers, String drawings)
    {
        displayResults = "";
        allMatches = new ArrayList<List<Integer>>();
        allDrawings = new ArrayList<List<Integer>>();
        
        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;
        int threeCount = 0;
        int fourCount = 0;
        int fiveCount = 0;
        int sixCount = 0;
        
        for(int r=0; r<Integer.parseInt(drawings); r++)
        {
            // utilizing instance of model, referred to https://www.w3schools.com/java/java_constructors.asp
            Model lottoModel = new Model();
            lottoMatches = new ArrayList<>();
            String[] numsArray = numbers.split("\\s+");
            int drawNum;

            while(lottoModel.lottoResults.size()<6)
            {
                drawNum = ((int)(Math.random()*60)+1);
                // stops duplicate numbers from being drawn
                if(!(lottoModel.lottoResults.contains(drawNum)))
                    lottoModel.lottoResults.add(drawNum);
            }

            for(int i=0; i<numsArray.length; i++)
            {
                for(int j=0; j<lottoModel.lottoResults.size(); j++)
                {
                    if(Integer.parseInt(numsArray[i])==(lottoModel.lottoResults.get(j)))
                        lottoMatches.add(Integer.parseInt(numsArray[i]));
                }
            }

            // adds lottery drawings lists to a list
            allDrawings.add(lottoModel.lottoResults);

            // adds matching number lists to a list
            allMatches.add(lottoMatches);
            
            // add to a count for 0-6 matches
            if(lottoMatches.size()==0)
                zeroCount++;
            else if(lottoMatches.size()==1)
                oneCount++;
            else if(lottoMatches.size()==2)
                twoCount++;
            else if(lottoMatches.size()==3)
                threeCount++;
            else if(lottoMatches.size()==4)
                fourCount++;
            else if(lottoMatches.size()==5)
                fiveCount++;
            else if(lottoMatches.size()==6)
                sixCount++;
        }
        
        displayResults += zeroCount + " drawings matched 0 of your numbers.\n" 
                + oneCount + " drawings matched 1 of your numbers.\n" 
                + twoCount + " drawings matched 2 of your numbers.\n" 
                + threeCount + " drawings matched 3 of your numbers.\n" 
                + fourCount + " drawings matched 4 of your numbers.\n" 
                + fiveCount + " drawings matched 5 of your numbers.\n" 
                + sixCount + " drawings matched 6 of your numbers.\n";
        
        return displayResults;
    }
    
    public List<Integer> getMatchingNums()
    {
        return lottoMatches;
    }
}
