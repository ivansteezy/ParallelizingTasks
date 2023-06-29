import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

// this class will be running always, the sequencial/concurrent demo would be in the client app and 
public class Server extends UnicastRemoteObject implements RemoteInterface
{
    protected Server(String port) throws RemoteException 
    {
        super();
        mPort = port;
    }

    @Override
    public long GetCount() throws RemoteException 
    {
        return 42;
    }

    public void StartServer()
    {
        try 
        {
            java.rmi.Naming.rebind("//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":" + mPort + "/Syro", this);
            System.out.println("El servidor esta arriba con la ip: " + java.net.InetAddress.getLocalHost().getHostAddress());
        } catch (RemoteException | MalformedURLException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    String mPort;
}
