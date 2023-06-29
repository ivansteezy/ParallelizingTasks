public class Client 
{
    public Client(String ip, String port)
    {
        mIp = ip;
        mPort = port;
    }
    
    public void StartClient()
    {
        try 
        {
            RemoteInterface mir = (RemoteInterface)java.rmi.Naming.lookup("//" + mIp + ":" + mPort + "/Syro");
            System.out.println("Number: " + mir.GetCount());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    String mIp;
    String mPort;
}
