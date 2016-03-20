Usage: java -jar categorizer.jar <filename>
e.g. java -jar categorizer.jar neustar_input.txt

Design:

When I first saw that items had to be unique to a category I knew a set would be the easiest way to check that.
Checking if an item already exists in a set is O(1).
Since there would be a set to each category I decided I needed a hash table to map categories to their sets.

I decided to write some regex to take any of the determined categories then any item allowing spaces.

Tests:

JUnit tests are included unser /src/neustar/CategorizerTest.java
The first test uses a file with the same text given in the description, and tests if the correct number of items were added.
The second tests a non existent file.
Other test cases I could write include: no items read, no items read for some categories, what happens if I read a file more than once.