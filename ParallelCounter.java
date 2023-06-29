import java.util.ArrayList;
import java.util.Arrays;

public class ParallelCounter 
{
    public ParallelCounter(String ip, String port, int numberOfThreads, String stringToCount, String wordToFind)
    {
        mStringToCount = stringToCount;
        mNumberOfThreads = numberOfThreads;
        mWordToFind = wordToFind;
        mIp = ip;
        mPort = port;
    }

    // split into two string arrays
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

        System.out.println("About to search!");
        long startTime = System.nanoTime();

        // define a thread for a local calculation
        LocalCountThread lt = new LocalCountThread(smallerArrays.get(0), mWordToFind);

        // define a thread for a remote call (remote calculation)
        RemoteCallThread rct = new RemoteCallThread(mIp, mPort, smallerArrays.get(1), mWordToFind);

        Thread localThread = new Thread(lt);
        Thread remoteThread = new Thread(rct);


        localThread.start();
        remoteThread.start();

        localThread.join();
        remoteThread.join();
        
        mCount += lt.GetCount();
        mCount += rct.GetCount();
        System.out.println("LA CUENTA ES: " + mCount);
        long stopTime = System.nanoTime();
        mTimeElapsed = stopTime - startTime;
        System.out.println("TIEMPO QUE PASO: " + mTimeElapsed);
    }

    public long GetTimeElapsed()
    {
        return mTimeElapsed;
    }

    public long GetCount()
    {
        return mCount;
    }

    private int mNumberOfThreads;
    private String mStringToCount;
    private String mWordToFind;
    private long mCount;
    private long mTimeElapsed;
    private String mIp;
    private String mPort;
}
