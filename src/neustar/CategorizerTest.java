package neustar;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;

public class CategorizerTest {

	@Test
	public void exampleTest() {
		Categorizer categorizer = new Categorizer();
		try {
			categorizer.categorize("neustar_input.txt");
		} catch (IOException e) {
			fail("Failed to read neustar_input.txt");
		}
		
		Map<String, HashSet<String>> categories = categorizer.getCategories();
		assertEquals(2, categories.get("PERSON").size());
		assertEquals(2, categories.get("PLACE").size());
		assertEquals(2, categories.get("ANIMAL").size());
		assertEquals(1, categories.get("COMPUTER").size());
		assertEquals(1, categories.get("OTHER").size());
		assertEquals(null, categories.get("NON-EXISTENT-KEY"));
		
	}
	
	@Test
	public void noFileTest() {
		try {
			new Categorizer().categorize("blahblah.txt");
			fail("Failed to throw error on non existent file");
		} catch (IOException e) { }
	}

}
