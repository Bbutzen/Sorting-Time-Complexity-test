import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Project2 {

    public static void main(String[] args) throws IOException {

        int[] arraySizes = new int[]{10000, 50000, 100000, 500000, 1000000, 5000000};
        int currentSize = 0;

        for (int i = 0; i < arraySizes.length; i++) {
            for (int j = 0; j < 5; j++) {
                int[] radixList = new int[arraySizes[currentSize]];
                for (int k = 0; k < arraySizes[currentSize]; k++)
                    radixList[k] = (int) (Math.random() * Integer.MAX_VALUE);
                long startTime = getTime();
                radixSort(radixList, 10);
                long stopTime = getTime();
                System.out.println("Radix Sort " + arraySizes[currentSize] + ": " + (stopTime - startTime) + " ms");
            }
            for (int j = 0; j < 5; j++) {
                int[] quickList = new int[arraySizes[currentSize]];
                for (int k = 0; k < arraySizes[currentSize]; k++)
                    quickList[k] = (int) (Math.random() * Integer.MAX_VALUE);
                long startTime = getTime();
                quickSort(quickList, 0, arraySizes[currentSize] - 1);
                long stopTime = getTime();
                System.out.println("Quick Sort " + arraySizes[currentSize] + ": " + (stopTime - startTime) + " ms");
            }
            for (int j = 0; j < 5; j++) {
                int[] mergeList = new int[arraySizes[currentSize]];
                for (int k = 0; k < arraySizes[currentSize]; k++)
                    mergeList[k] = (int) (Math.random() * Integer.MAX_VALUE);
                long startTime = getTime();
                mergeSort(mergeList);
                long stopTime = getTime();
                System.out.println("Merge Sort " + arraySizes[currentSize] + ": " + (stopTime - startTime) + " ms");
            }
            for (int j = 0; j < 5; j++) {
                int[] insertList = new int[arraySizes[currentSize]];
                for (int k = 0; k < arraySizes[currentSize]; k++)
                    insertList[k] = (int) (Math.random() * Integer.MAX_VALUE);
                long startTime = getTime();
                insertionSort(insertList);
                long stopTime = getTime();
                System.out.println("Insertion Sort " + arraySizes[currentSize] + ": " + (stopTime - startTime) + " ms");
            }
            for (int j = 0; j < 5; j++) {

                int[] bubbleList = new int[arraySizes[currentSize]];
                for (int k = 0; k < arraySizes[currentSize]; k++)
                    bubbleList[k] = (int) (Math.random() * Integer.MAX_VALUE);
                long startTime = getTime();
                bubbleSort(bubbleList);
                long stopTime = getTime();
                System.out.println("Bubble Sort " + arraySizes[currentSize] + ": " + (stopTime - startTime) + " ms");
            }
            currentSize++;
        }
    }


    public static long getTime() {
        return System.currentTimeMillis();
    }
    public static void insertionSort(int[] list){
        for(int i = 1; i < list.length; i++){
            int current = list[i];
            int j;
            for(j = i-1; j >=0 && list[j]> current; j--){
                list[j+1] = list[j];
            }
            list[j+1] = current;
        }
    }

    public static void bubbleSort(int[] list){
        boolean nextpassflag = true;
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
    }

    public static void mergeSort(int[] list) {
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

    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
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
    public static void radixSort(int[] list, int numberOfDigits) {
        java.util.ArrayList<Integer>[] buckets = new java.util.ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new java.util.ArrayList<Integer>();
        }

        for (int position = 0; position <= numberOfDigits; position++) {
            // Clear buckets
            for (int i = 0; i < buckets.length; i++) {
                buckets[i].clear();
            }

            // Distribute the elements from list to buckets
            for (int i = 0; i < list.length; i++) {
                int key = getKey(list[i], position);
                buckets[key].add(list[i]);
            }

            // Now move the elements from the buckets back to list
            int k = 0; // k is an index for list
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < buckets[i].size(); j++)
                    list[k++] = buckets[i].get(j);
            }
        }
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

