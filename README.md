# Description
This program was designed to test the speed of various sorting algorithms using arrays of increasing sizes.
The outer for loop will run for as many sizes of arrays are desired
Each inner for loop generates an array of random integers of the current length and then calls a specific
sorting algorithm including Radix Sort, Quick Sort, Merge Sort, Insertion Sort, and Bubble Sort. The time in
milliseconds is displayed after each sorting algorithm is run. Each algorithm will be called on five different
arrays of the same size to get a decent sample size. After all five sorting methods are run on that
size, the next size array is then run by each algorithm until completion.

## Compiling
To compile and run the program, you can load the program into your favorite IDE or in your preferred online 
Java compiler like https://www.programiz.com/java-programming/online-compiler/. You can also compile the program 
from the command prompt by typing javac Project2.java and then type java Project2 to run the program.

### Modifications
It is possible to make some modifications to the program by changing the following:
1. By changing the value of NUM_RUNS on line 12 will change the number of runs per algorithm and size if a larger or 
smaller sample size is desired.
2. The array size can be modified on line 13 by changing the values or inserting/deleting values into the array.
3. The display time can be changed in the getTime method. To display the time in milliseconds, change line 63 to
return System.currentTimeMillis(); Note: The display will still output the ns unit, but each print statement can be
changed (lines 41, 44, 47, 50, 53)
#### Warning!
```
The Insertion and Bubble Sort algorithms will take a significant amount of time for the larger array sizes.
It is recommended to delete the 5000000 size array from line number 13 making it 
int[] arraySizes = new int[]{10000, 50000, 100000, 500000, 1000000}; instead or stopping the program after 
running the merge sort on the 5000000 element array.
