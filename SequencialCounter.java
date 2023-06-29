import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class SequencialCounter 
{
    public SequencialCounter(String stringToCount, String wordToFind)
    {
        mTimeElapsed = 0;
        mNumberOfFinds = 0;
        mStringToCount = stringToCount;
        mWordToFind = wordToFind;
    }

    private long SearchForWord()
    {
        String a[] = mStringToCount.split(" ");
        System.out.println("numero de palabras" + a.length);
        long count = 0;

        for(int i = 0; i < a.length; i++)
        {
            if(mWordToFind.equals(a[i]))
            {
                count++;
            }
        }

        System.out.println("El numero de ocurrencias es: " + count);
        return count;
    }

    public void Search()
    {
        System.out.println("About to search!");
        long startTime = System.nanoTime();
        mNumberOfFinds = SearchForWord();
        long stopTime = System.nanoTime();

        mTimeElapsed = stopTime - startTime;
    }

    public String GetNumberOfFindsAsString()
    {
        return Long.toString(mNumberOfFinds);
    }

    public String GetDurationAsString()
    {
        return Long.toString(mTimeElapsed);
    }
    
    private long  mNumberOfFinds;
    private String mStringToCount;
    private String mWordToFind;
    private long mTimeElapsed;
}
