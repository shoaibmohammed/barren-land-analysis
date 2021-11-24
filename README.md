##Solution
- The Land is modeled like a 2 dimensional matrix.  All the cells are initialized with 0.
- When user inputs barren land coordinates, all those cells are updated to -1 .
- So a land at any given coordinate is Fertile if the value is 0 and Barren is the value is -1.
- To calculate the area of all fertile land(s)
- Begin at (0,0) and update all the neighboring coordinates (seed fill) with values of 0 to 1 using DFS algorithm and increment a counter each time a cell is updated.
- When there are no more neighbouring 0 cells are left, the counter value is moved to an array of fertile areas.
- Then continue iterating through the remaining matrix and repeating the seed fill each time a node with a value of 0 is found.
- After the whole matrix is iterated through, sort the array of fertile areas and print the result.

###Setup
- Download or Clone the project from Github. (then unzip if downloaded)

- Open a new Maven project in an IDE like Intellij or Eclipse.

- In Intellij - File -> New -> Project From Existing Sources -> choose the pom.xml file in the downloaded directory.

- Then open Analyzer.java file in src/main/java/com/shoaib/barrenlandanalysis package.

- Then right click in the file and select "Run".

- Follow the instructions to enter the input.

###Sample Input and Output

----------Barren Land Analysis--------
Enter one or more barren land rectangles seperated by comma
Each barren land is entered as 4 numbers,
The first two integers are the coordinates of the bottom left corner in the given rectangle,
and the last two integers are the coordinates of the top right corner.

For Example : 0 292 399 307

OR

48 192 351 207, 48 392 351 407, 120 52 135 547, 260 52 275 547

--------------------------------------

0 292 399 307

The Area of fertile land(s) in the order of size :
116800 116800 