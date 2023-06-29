public class Client 
{
    public static void main(String[] args) 
    {
        try 
        {
            RemoteInterface mir = (RemoteInterface)java.rmi.Naming.lookup("//" + args[0] + ":" + args[1] + "/Syro");
            System.out.println("Number: " + mir.GetCount());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
}
