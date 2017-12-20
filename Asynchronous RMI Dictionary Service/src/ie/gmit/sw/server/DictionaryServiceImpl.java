package ie.gmit.sw.server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService{

	private static final long serialVersionUID = 1L;
	private Dictionary dictionary;
	private HashMap<String, List<String>> dictMap;
	
	protected DictionaryServiceImpl() throws RemoteException, IOException {		
		dictionary = new Dictionary();
		dictMap = dictionary.getDictionary();	
	}

	@Override
	public String findDefinition(String query) throws RemoteException {
		
		//Check The Dictionary HashMap for the The Query from the user - Return Definition If found
		if(dictMap.containsKey(query.toLowerCase())){			
			return "Word: \"" + query.toLowerCase() + "\" ==> Definition: ==> " + dictMap.get(query.toLowerCase()).toString();
		}	
		else{
			// Definition Not Found For Query
			return "Word \"" + query.toLowerCase()  + "\" ==> Sorry, There is no Definition in the Dictionary for this word";
		
		}
		
	}

}
