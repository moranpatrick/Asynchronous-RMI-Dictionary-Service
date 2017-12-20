package ie.gmit.sw.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DictionaryServiceSetup {

	public static void main(String[] args) throws Exception{
		//Create an instance of a DictionaryServiceImpl.
		DictionaryService ds = new DictionaryServiceImpl();
		
		//Start The RMI Registry on port 1099
		LocateRegistry.createRegistry(1099);
		
		// Bind our remote object to the registry with the human-readable name "dictionaryService"
		Naming.rebind("dictionaryService", ds);
		
		System.out.println("Server Running...");
	}

}
