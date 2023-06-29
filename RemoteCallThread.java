public class RemoteCallThread implements Runnable
{
    RemoteCallThread(String ip, String port, String[] arrayToSearch, String wordToFind)
    {
        mIp = ip;
        mPort = port;
        mArray = arrayToSearch;
        mWordToFind = wordToFind;
    }

    @Override
    public void run()
    {
        try 
        {
            RemoteInterface mir = (RemoteInterface)java.rmi.Naming.lookup("//" + mIp + ":" + mPort + "/Syro");
            mCount = mir.GetCount(mArray, mWordToFind);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public long GetCount()
    {
        return mCount;
    }
    
    String mIp;
    String mPort;
    long mCount;
    String[] mArray;
    String mWordToFind;
}
