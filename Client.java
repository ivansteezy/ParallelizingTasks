public class Client 
{
    public static void main(String[] args) 
    {
        try 
        {
            RemoteInterface mir = (RemoteInterface)java.rmi.Naming.lookup("//" + "192.168.50.25" + ":" + "1234" + "/Syro");
            System.out.println("Number: " + mir.GetCount());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
}
