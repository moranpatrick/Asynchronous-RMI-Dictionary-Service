package ie.gmit.sw;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService{

	private static final long serialVersionUID = 1L;
	private Dictionary d;
	HashMap<String, List<String>> dictMap;
	
	protected DictionaryServiceImpl() throws RemoteException, IOException {
		
		d = new Dictionary();
		dictMap = d.getDictionary();	
	}

	@Override
	public String findDefinition(String query) throws RemoteException {
		
		if(dictMap.containsKey(query.toLowerCase())){			
			return query.toLowerCase() + " --> " + dictMap.get(query.toLowerCase()).toString();			
		}	
		else{
			return query.toLowerCase()  + "--> There is no Definition for this word!";
		
		}
		
	}

}
