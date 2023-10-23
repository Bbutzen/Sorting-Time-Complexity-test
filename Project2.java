

public class Project2 {
    /**
     * This program was designed to test the speed of various sorting algorithms using arrays of increasing sizes.
     * The outer for loop will run for as many sizes of arrays are desired
     * Each inner for loop generates an array of random integers of the current length and then calls a specific
     * sorting algorithm including Radix Sort, Quick Sort, Merge Sort, Insertion Sort, and Bubble Sort. The time in
     * milliseconds is displayed after each sorting algorithm is run. Each algorithm will be called on five different
     * arrays of the same size to get a decent sample size. After all five sorting methods are run on that
     * size, the next size array is then run by each algorithm until completion.
     */
    public static void main(String[] args) {
        final int NUM_RUNS = 5; //Number of times to run each sorting algorithm
        int[] arraySizes = new int[]{10000, 50000, 100000, 500000, 1000000, 5000000};//Size of each array
        int currentSize = 0; //Index of the size of the current array being tested

        for (int i = 0; i < arraySizes.length; i++) {//run the sorting algorithms for each array size
            /*
             * Since we care more about the time associated with each algorithm, the
             * sorting algorithms return the run time instead of the sorted array.
             */
            //Begin the radix sort
            System.out.println("Radix Sort " + arraySizes[currentSize] + ":");
            for (int j = 0; j < NUM_RUNS; j++) {
                int[] radixList = new int[arraySizes[currentSize]];//generate an empty array of current size
                for (int k = 0; k < arraySizes[currentSize]; k++)//generate the random numbers and place into array
                    radixList[k] = (int) (Math.random() * Integer.MAX_VALUE + 1);
                //call the radix sort method and get the run time
                long runTime = radixSort(radixList);//9 is the number of digits in max int value
                System.out.println(runTime + " nanoseconds");
            }
            System.out.println("Quick Sort " + arraySizes[currentSize] + ":");
            for (int j = 0; j < NUM_RUNS; j++) {
                int[] quickList = new int[arraySizes[currentSize]];
                for (int k = 0; k < arraySizes[currentSize]; k++)
                    quickList[k] = (int) (Math.random() * Integer.MAX_VALUE + 1);
                long runTime = quickSort(quickList, 0, arraySizes[currentSize] - 1);
                System.out.println(runTime + " nanoseconds");
            }
            System.out.println("Merge Sort " + arraySizes[currentSize] + ":");
            for (int j = 0; j < NUM_RUNS; j++) {
                int[] mergeList = new int[arraySizes[currentSize]];
                for (int k = 0; k < arraySizes[currentSize]; k++)
                    mergeList[k] = (int) (Math.random() * Integer.MAX_VALUE + 1);
                long runTime = mergeSort(mergeList);
                System.out.println(runTime + " nanoseconds");
            }
            System.out.println("Insertion Sort " + arraySizes[currentSize] + ":");
            for (int j = 0; j < NUM_RUNS; j++) {
                int[] insertList = new int[arraySizes[currentSize]];
                for (int k = 0; k < arraySizes[currentSize]; k++)
                    insertList[k] = (int) (Math.random() * Integer.MAX_VALUE + 1);
                long runTime = insertionSort(insertList);
                System.out.println(runTime + " nanoseconds");
            }
            System.out.println("Bubble Sort " + arraySizes[currentSize] + ":");
            for (int j = 0; j < NUM_RUNS; j++) {
                int[] bubbleList = new int[arraySizes[currentSize]];
                for (int k = 0; k < arraySizes[currentSize]; k++)
                    bubbleList[k] = (int) (Math.random() * Integer.MAX_VALUE + 1);
                long runTime = bubbleSort(bubbleList);
                System.out.println(runTime + " nanoseconds");
            }
            currentSize++;
        }
    }


    public static long getTime() {
        return System.nanoTime();
    }
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

    public static long bubbleSort(int[] list){
        boolean nextpassflag = true;
        long startTime = getTime();
        for(int j = 1; j< list.length && nextpassflag; j++){
            nextpassflag = false;
            for(int i = 0; i < list.length-j; i++){
                if(list[i] > list[i+1]){
                    int temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                    nextpassflag = true;
                }
            }
        }
        long stopTime = getTime();
        return stopTime - startTime;
    }

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

    //counting sort algorithm to sort elements into correct "buckets"
    public static long radixSort(int[] a) {
        long startTime = getTime();
        {
            int i, m = a[0], exp = 1, n = a.length;
            int[] b = new int[n];
            for (i = 1; i < n; i++)
                if (a[i] > m)
                    m = a[i];
            while (m / exp > 0)
            {
                int[] bucket = new int[10];

                for (i = 0; i < n; i++)
                    bucket[(a[i] / exp) % 10]++;
                for (i = 1; i < 10; i++)
                    bucket[i] += bucket[i - 1];
                for (i = n - 1; i >= 0; i--)
                    b[--bucket[(a[i] / exp) % 10]] = a[i];
                for (i = 0; i < n; i++)
                    a[i] = b[i];
                exp *= 10;
            }
        }
        long stopTime = getTime();
        return stopTime - startTime;
    }

    /** Return the digit at the specified position.
     * The last digit's position is 0. */
    public static int getKey(int number, int position) {
        int result = 1;
        for (int i = 0; i < position; i++)
            result *= 10;

        return (number / result) % 10;
    }
}

