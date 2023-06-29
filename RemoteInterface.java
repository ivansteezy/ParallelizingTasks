import java.rmi.Remote;
import java.rmi.RemoteException;

// esto estara en el server
public interface RemoteInterface extends Remote
{
    // public void SearchForWord(String[] arrayTocount, String wordToFind) throws RemoteException;
    public long GetCount(String[] array, String wordToFind) throws RemoteException;
}
