package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DictionaryServiceSetup {

	public static void main(String[] args) throws Exception{
		DictionaryService ds = new DictionaryServiceImpl();
		
		LocateRegistry.createRegistry(1099);
		
		Naming.rebind("dictionaryService", ds);
		
		System.out.println("Server Running...");
	}

}
