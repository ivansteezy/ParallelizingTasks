public class LocalCountThread implements Runnable 
{
    LocalCountThread(String[] arrayToSearch, String wordToFind)
    {
        mArray = arrayToSearch;
        mWordToFind = wordToFind;
    }

    @Override
    public void run()
    {
        System.out.println("numero de palabras" + mArray.length);

        for(int i = 0; i < mArray.length; i++)
        {
            if(mWordToFind.equals(mArray[i]))
            {
                mCount++;
            }
        }

        System.out.println("El numero de ocurrencias es: " + mCount);
    }

    public long GetCount()
    {
        return mCount;
    }

    long mCount;
    String[] mArray;
    String mWordToFind;
}
