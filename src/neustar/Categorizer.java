package neustar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Categorizer {
	private static Pattern re = Pattern.compile("(?<category>PERSON|PLACE|ANIMAL|COMPUTER|OTHER)(?<item>(\\s\\w+)+)");
	private Map<String, HashSet<String>> categories;
	private String validList;
	
	public Map<String, HashSet<String>> getCategories(){
		return categories;
	}

	public String getValidList(){
		return validList;
	}
	
	public Categorizer(){
        categories = new HashMap<String, HashSet<String>>(){{
    		//initialize empty sets for each category
    		put("PERSON", new HashSet<String>());
    		put("PLACE", new HashSet<String>());
    		put("ANIMAL", new HashSet<String>());
    		put("COMPUTER", new HashSet<String>());
    		put("OTHER", new HashSet<String>());
    	}};
        
	}
	
	/**
	 * Reads from a file containing a list of items in a category and appends
	 * valid items to the category set.
	 * @param categories A map of categories to a set of items in a category
	 * @param filename The file name to ready the list of items from
	 * @throws IOException 
	 */
	public void categorize(String filename) throws IOException {
        validList = ""; //All valid items
        FileReader fReader = new FileReader(filename);
        BufferedReader bReader = new BufferedReader(fReader);

        String line;
        Matcher match;
        while((line = bReader.readLine()) != null) {
        	
            match = re.matcher(line);
            if(match.find()){
            	
            	String category = match.group("category");
            	String item = match.group("item");
            	if(categories.get(category).add(item)){ //unique item to category
            		validList += line + "\n";
            	}
            }
        }
        
        bReader.close();
	}
	

	
	/**
	 * Prints the category and the number of unique items in the category
	 * PERSON, PLACE, ANIMAL, COMPUTER, OTHER
	 * Along with the header "CATEGORY COUNT"
	 * @param categories
	 */
	public void printInOrder() {
		System.out.println("CATEGORY COUNT");
		printCategory("PERSON");
		printCategory("PLACE");
		printCategory("ANIMAL");
		printCategory("COMPUTER");
		printCategory("OTHER");
		System.out.println(validList.trim());
	}
	
	/**
	 * Prints a category and category size
	 * @param categories
	 * @param category
	 */
	private void printCategory(String category){
		Set<String> categorySet = categories.get(category);
		System.out.println(category + " " + Integer.toString(categorySet.size()) + "");
	}
}
