Usage: java -jar categorizer.jar <filename>
e.g. java -jar categorizer.jar neustar_input.txt

Design:

When I first saw that items had to be unique to a category I knew a set would be the easiest way to check that.
Checking if an item already exists in a set is O(1).
Since there would be a set to each category I decided I needed a hash table to map categories to their sets.

I decided to write some regex to take any of the determined categories then any item allowing spaces.