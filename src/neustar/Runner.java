package neustar;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws IOException {

		if(args.length != 1){
			System.out.println("Usage: java -jar categorizer.jar <filename>");
			throw new FileNotFoundException("No file specified");
		}
		
		Categorizer categorizer = new Categorizer();
		categorizer.categorize(args[0]);
		categorizer.printInOrder();
	}

}
