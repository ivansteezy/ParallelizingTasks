import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
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
    public long GetCount(String[] array, String wordToFind) throws RemoteException 
    {
        System.out.println("Numero de palabras a contar en el Server" + array.length);
        long count = 0;
        for(int i = 0; i < array.length; i++)
        {
            if(wordToFind.equals(array[i]))
            {
                count++;
            }
        }

        System.out.println("El numero de ocurrencias que el servidor conto es: " + count);
        return count;
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
