package ie.gmit.sw.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DictionaryService extends Remote{
	
	public String findDefinition(String query) throws RemoteException; 
	
}
