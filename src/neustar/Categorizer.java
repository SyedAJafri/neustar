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
	static Pattern re = Pattern.compile("(?<category>PERSON|PLACE|ANIMAL|COMPUTER|OTHER)(?<item>(\\s\\w+)+)");
	
	/**
	 * Reads from a file containing a list of items in a category and appends
	 * valid items to the category set.
	 * @param categories A map of categories to a set of items in a category
	 * @param filename The file name to ready the list of items from
	 * @return A string of all the valid items along with the items category
	 * @throws IOException 
	 */
	public static String categorize(Map<String, HashSet<String>> categories, String filename) throws IOException {
        String validList = ""; //All valid items
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
		return validList.trim();
	}
	
	public static void main(String[] args) throws IOException {

		if(args.length != 1){
			System.out.println("Usage: java -jar categorizer.jar <filename>");
			throw new FileNotFoundException("No file specified");
		}
		
		Map<String, HashSet<String>> categories = new HashMap<String, HashSet<String>>(){{
			//initialize empty sets for each category
			put("PERSON", new HashSet<String>());
			put("PLACE", new HashSet<String>());
			put("ANIMAL", new HashSet<String>());
			put("COMPUTER", new HashSet<String>());
			put("OTHER", new HashSet<String>());
		}};
		

		String validList = categorize(categories, args[0]);
		printInOrder(categories);
		System.out.println(validList);
	}
	
	/**
	 * Prints the category and the number of unique items in the category
	 * PERSON, PLACE, ANIMAL, COMPUTER, OTHER
	 * Along with the header "CATEGORY COUNT"
	 * @param categories
	 */
	private static void printInOrder(Map<String, HashSet<String>> categories) {
		System.out.println("CATEGORY COUNT");
		printCategory(categories, "PERSON");
		printCategory(categories, "PLACE");
		printCategory(categories, "ANIMAL");
		printCategory(categories, "COMPUTER");
		printCategory(categories, "OTHER");
	}
	
	/**
	 * Prints a category and category size
	 * @param categories
	 * @param category
	 */
	private static void printCategory(Map<String, HashSet<String>> categories, String category){
		Set<String> categorySet = categories.get(category);
		System.out.println(category + " " + Integer.toString(categorySet.size()) + "");
	}
}
