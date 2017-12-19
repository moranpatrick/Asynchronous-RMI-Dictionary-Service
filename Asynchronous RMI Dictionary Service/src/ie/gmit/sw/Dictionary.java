package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* This Class, which Makes a Dictionary HashMap from a csv file, was taken from my second year OO Project Book Indexing Project */
public class Dictionary {
	private String csvFile = "Resources/dictionary.csv";
	private StringBuilder wordDefinition = new StringBuilder();
	private HashMap<String, List<String>> dictionary = new HashMap<String, List<String>>();
	private String word = null;
	private String line = null;
	
	public Dictionary() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile)));

		//Loop through the file line by line
		while((line = br.readLine()) != null){
	
			if(line.startsWith("\"")){
				//if we're not on the first line, do this
				if(word != null){
					addDefinition(word.toLowerCase(), wordDefinition.toString());
					wordDefinition = new StringBuilder();	//reset string builder
				}
				
				//otherwise find the end of the word as an integer
				int endOfWord = line.indexOf('"', 1);
				
				word = line.substring(1,  endOfWord);//makes a word from one quote to the next double quote
				wordDefinition.append((line.substring(endOfWord + 2)));//add the rest of the line to wordDefiniton 2 chars after end of the word
				wordDefinition.append("\n");//add a new line

			}
			else{
				wordDefinition.append((line));//other wise append the full line of text
				wordDefinition.append("\n");
			}
		}//while
		br.close();

	}
	
	//add the word and the definition to the dictionary hash map
	public void addDefinition(String word, String def){
		if(dictionary.containsKey(word)){
			List<String> definition = dictionary.get(word);
			definition.add(def);
			dictionary.put(word, definition);
		}
		else{
			List<String> definition = new ArrayList<String>();
			definition.add(def);
			dictionary.put(word, definition);
		}
	}//addDefinition()
	
	// Gets The Definition When Called 
	public List<String> get(String definition) {
		return dictionary.get(definition);
	}
	
	//Return The Dictionary HashMap when Called 
	public HashMap<String, List<String>> getDictionary(){		
		return this.dictionary;
	}
}
