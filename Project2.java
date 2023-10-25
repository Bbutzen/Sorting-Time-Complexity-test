public class Project2 {
    /**
     * This program was designed to test the speed of various sorting algorithms using arrays of increasing sizes.
     * The outer for loop will run for as many sizes of arrays are desired
     * Each inner for loop generates an array of random integers of the current length and then calls a specific
     * sorting algorithm including Radix Sort, Quick Sort, Merge Sort, Insertion Sort, and Bubble Sort. The time in
     * nanoseconds is displayed after each sorting algorithm is run. Each algorithm will be called on five different
     * arrays of the same size to get a decent sample size. After all five sorting methods are run on that
     * size, the next size array is then run by each algorithm until completion.
     */
    public static void main(String[] args) {
        final int NUM_RUNS = 5; //Number of times to run each sorting algorithm
        int[] arraySizes = new int[]{10000, 50000, 100000, 500000, 1000000, 5000000};//Size of each array
        int currentSize = 0; //Index of the size of the current array being tested

        for (int i = 0; i < arraySizes.length; i++) {//run the sorting algorithms for each array size

            //Call the sort method running once for each sorting algorithm (repetition is handled in the method)
            for (int currentSort = 0; currentSort < 5; currentSort++) {
                sort(arraySizes[currentSize], currentSort, NUM_RUNS);
            }
            currentSize++;//go to the next sized array
        }
    }

    /**
     * The sort method handles the calling and output of the results for each sorting algorithm. The outer loop will be
     * run for as many times as the numRuns parameter. The array with random integers is created, and then in the
     * switch/case the specific sorting method is called in the output statement.
     * @param size The current size of the array being sorted.
     * @param sortNumber The numeric representation for which sort method to call 0-Radix, 1-Quick, ...
     * @param numRuns The pre-defined number of times that each sort should be run.
     */
    public static void sort(int size, int sortNumber, int numRuns) {
        for (int i = 0; i < numRuns; i++){
            int[] array = new int [size];
            for (int j = 0; j < size; j++)
                array[j] = (int) (Math.random() * Integer.MAX_VALUE + 1);
            switch (sortNumber) {
                case 0 -> {
                    System.out.println("Radix Sort " + size + ": "+ radixSort(array) + " ns.");
                }
                case 1 -> {
                    System.out.println("Quick Sort " + size + ": " + quickSort(array, 0, size - 1) + " ns.");
                }
                case 2 -> {
                    System.out.println("Merge Sort " + size + ": " + mergeSort(array) + " ns.");
                }
                case 3 -> {
                    System.out.println("Insertion Sort" + size + ": " + insertionSort(array) + " ns.");
                }
                case 4 -> {
                    System.out.println("Bubble Sort " + size + ": " + bubbleSort(array) + " ns.");
                }
            }
        }
    }
    /**
     * The getTime method returns the time in nanoseconds to help determine how long the sort took
     * @return The time in nanoseconds
     */
    public static long getTime() {
        return System.nanoTime();
    }
    /*
     * Since we care more about the time associated with each algorithm, the
     * sorting algorithms return the run time instead of the sorted array.
     */
    /**
     * The insertionSort method performs the Insertion sort algorithm on the provided array and returns the difference in
     * the start and stop times returned from the getTime method. The Insertion sort has a time complexity of O(n^2), and
     * it performs it by moving the element to the required position shifting any elements that need to be shifted.
     * @param list The array to be sorted
     * @return The time taken to sort the array (default is in nanoseconds)
     */
    public static long insertionSort(int[] list){
        long startTime = getTime();
        for(int i = 1; i < list.length; i++){
            int current = list[i];
            int j;
            for(j = i-1; j >=0 && list[j]> current; j--){
                list[j+1] = list[j];
            }
            list[j+1] = current;
        }
        long stopTime = getTime();
        return stopTime-startTime;
    }

    /**
     * Thr bubbleSort method performs the Bubble sort algorithm on the provided array and returns the difference in the
     * start and stop times returned from the getTime method. The Bubble sort has a time complexity of O(n^2), and it
     * performs it by examining each neighboring element and switching them if needed. The larger numbers will "rise" to
     * the end of the array like a bubble would.
     * @param list The array to be sorted
     * @return The time taken to sort the array (default is in nanoseconds)
     */
    public static long bubbleSort(int[] list){
        boolean nextPassflag = true;
        long startTime = getTime();
        for(int j = 1; j< list.length && nextPassflag; j++){
            nextPassflag = false;
            for(int i = 0; i < list.length-j; i++){
                if(list[i] > list[i+1]){
                    int temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                    nextPassflag = true;
                }
            }
        }
        long stopTime = getTime();
        return stopTime - startTime;
    }

    /**
     * The mergeSort method performs the Merge sort algorithm on the provided array and returns the difference in the
     * start and stop times returned from the getTime method. The Merge sort has a time complexity of (n logn), and it
     * performs it by dividing the array into smaller sub arrays and then merging them using the merge method in the c
     * sorted order
     * @param list The array to be sorted
     * @return The time taken to sort the array (default is nanoseconds)
     */
    public static long mergeSort(int[] list) {
        long startTime = getTime();
        if (list.length > 1) {
            // Merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            // Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2,
                    secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list);
        }
        long stopTIme = getTime();
        return stopTIme - startTime;
    }

    /**
     * THe merge method assists the mergeSort method by merging the two sub-arrays in the proper order
     * @param list1 The first sub-array to be merged
     * @param list2 The second sub-array to be merged
     * @param temp The sorted array that the other two will be merged into
     */
    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2])
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }

    /**
     * The quickSort method performs the Quicksort algorithm on the provided array and returns the difference in the
     * start and stop time returned by the getTime method. The Quicksort algorithm has a time complexity of O(n logn),
     * and it performs the sort by using the partition method to create a pivot and divide the array into two arrays
     * with one containing elements less than the pivot, and the other containing elements that are greater than the
     * pivot. This is performed recursively until the entire list is sorted.
     * @param list The array to be sorted
     * @param first The index for the forward search (initialized at 0)
     * @param last The index for the backwards search (initialized at length -1)
     * @return The time taken to sort the array (default is in nanoseconds).
     */
    private static long quickSort(int[] list, int first, int last) {
        long startTime = getTime();
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
        long stopTime = getTime();
        return stopTime - startTime;
    }
    /** Partition the array list[first..last] */
    private static int partition(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search
        while (high > low) {
// Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;
// Search backward from right
            while (low <= high && list[high] > pivot)
                high--;
// Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && list[high] >= pivot)
            high--;
// Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else {
            return first;
        }
    }

    /**
     * The radixSort method performs the Radix sort algorithm on the provided array and returns the difference in start
     * time and stop time returned by the getTime method. The Radix sort has a time complexity of O(n), and it performs
     * the sort by sorting the array into "buckets", and it performs the sort for as many digits are in the largest
     * element in the array.
     * @param list The array to be sorted.
     * @return The time taken to sort the array (default is in nanoseconds).
     */

    public static long radixSort(int[] list) {
        long startTime = getTime();
        {
            // i is a counter, max is the largest element, n is the size of the array
            int i, max = list[0], exp = 1, n = list.length;
            int[] b = new int[n];
            for (i = 1; i < n; i++)
                if (list[i] > max)
                    max = list[i];
            while (max / exp > 0)
            {
                //make the 10 buckets
                int[] bucket = new int[10];

                for (i = 0; i < n; i++)
                    bucket[(list[i] / exp) % 10]++;
                for (i = 1; i < 10; i++)
                    bucket[i] += bucket[i - 1];
                for (i = n - 1; i >= 0; i--)
                    b[--bucket[(list[i] / exp) % 10]] = list[i];
                for (i = 0; i < n; i++)
                    list[i] = b[i];
                exp *= 10;
            }
        }
        long stopTime = getTime();
        return stopTime - startTime;
    }
}

