import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


public class ConcurrentCounter 
{
    public ConcurrentCounter(int numberOfThreads, String stringToCount, String wordToFind)
    {
        mNumberOfThreads = numberOfThreads;
        mStringToCount = stringToCount;
        mWordToFind = wordToFind;
        mCount = 0;
    }

    ArrayList<String[]> SplitArrayIntoChunks()
    {
        String a[] = mStringToCount.split(" ");
        System.out.println("numero de palabras: " + a.length);
        

        ArrayList<String[]> smallerArrays = new ArrayList<String[]>();
        for(int i = 0; i < a.length; i+= (a.length / mNumberOfThreads))
        {
            var arrays = Arrays.copyOfRange(a, i, (a.length / mNumberOfThreads) + i);
            smallerArrays.add(arrays);
        }
        System.out.println("Cada elemento tiene: " + smallerArrays.get(0).length);
        return smallerArrays;
    }

    public void Search() throws InterruptedException
    {
        var smallerArrays = SplitArrayIntoChunks();

        Thread[] pool = new Thread[mNumberOfThreads]; // 3
        long mTimeElapsed = 0;
        System.out.println("About to search!");
        long startTime = System.nanoTime();

        // definimos la tarea que hara cada uno de los 3 threads
        for(int i = 0; i < pool.length; i++)
        {
            CounterThread ct = new CounterThread(smallerArrays.get(i), "the");
            Thread t = new Thread(ct);
            t.start();
            t.join();
            mCount += ct.GetValue();
        }
        // System.out.println("LA CUENTA ES: " + mCount);

        long stopTime = System.nanoTime();
        mTimeElapsed = stopTime - startTime;
        // System.out.println("TIEMPO QUE PASO: " + mTimeElapsed);
    }
    

    private int mNumberOfThreads;
    private String mStringToCount;
    private String mWordToFind;
    private int mCount;
}
