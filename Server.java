import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements RemoteInterface
{
    protected Server() throws RemoteException 
    {
        super();
    }

    @Override
    public long GetCount() throws RemoteException 
    {
        return 42;
    }

    public static void main(String[] args) 
    {
        try
        {
            RemoteInterface s = new Server();
            java.rmi.Naming.rebind("//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":" + args[0] + "/Syro", s); // port passed by args
            System.out.println("El servidor esta arriba con la ip: " + java.net.InetAddress.getLocalHost().getHostAddress());
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }
    }
}
