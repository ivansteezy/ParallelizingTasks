public class CounterThread implements Runnable 
{
    private volatile long value;

    CounterThread(String[] arrayToCount, String wordToFind)
    {
        mArrayToCount = arrayToCount;
        mWordToFind = wordToFind;
    }

    @Override
    public void run()
    {
        System.out.println("numero de palabras" + mArrayToCount.length);
        System.out.println("PALABRA: " + mWordToFind);
        for(int i = 0; i < mArrayToCount.length; i++)
        {
            if(mWordToFind.equals(mArrayToCount[i]))
            {
                value++;
            }
        }

        System.out.println("El numero de ocurrencias es: " + value);
    }

    public long GetValue()
    {
        return value;
    }

    private String[] mArrayToCount;
    private String mWordToFind;
}
