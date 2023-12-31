public class Client implements Runnable
{
    public Client(String ip, String port)
    {
        mIp = ip;
        mPort = port;
    }
    
    @Override
    public void run()
    {
        try 
        {
            RemoteInterface mir = (RemoteInterface)java.rmi.Naming.lookup("//" + mIp + ":" + mPort + "/Syro");
            mCount = mir.GetCount();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    String mIp;
    String mPort;
    long mCount;
}
