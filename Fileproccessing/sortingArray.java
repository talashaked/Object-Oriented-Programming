package filesprocessing;

import java.io.File;
import java.util.Comparator;

public class sortingArray {
    /**
     * this class is used as a sorter. by creating an onject of this class, the array given is sorted by
     * the quick sort method.
     * @param arr - the file array, that needs to be sorted
     * @param comp- the comparator, by which to sort the array.
     */
    public void sortByComp(File[] arr,Comparator<File> comp){
        quickSort(arr,0,arr.length-1,comp);
    }

    /**
     * this function is called straight from the constructor, then is recursively called until all the
     * files in the array are sorted.
     * @param arr - the array of files, given to sort
     * @param low - the start index to run on this specific call to quicksort
     * @param high - the end index to run to on this specific call to quicksort.
     * @param comp - the comparator to compare the items by.
     */
    private static void quickSort(File[] arr, int low, int high, Comparator<File> comp)
    {
        if (low < high)
        {
            int partitionIndex = partition(arr, low, high,comp);
            quickSort(arr, low, partitionIndex - 1,comp);
            quickSort(arr, partitionIndex + 1, high,comp);
        }
    }

    /**
     * the partition function. we use the partition as the last index in this part of the array(the highest
     * index). all items are compared to him, and in the end (after going over all files) moves the partition
     * index file to its place in the array, when all files left to him are smaller than him(by the comparing method
     * attributes) and bigger to the right.
     * @param arr - the file array
     * @param low - the low boundary index of the array
     * @param high - the high boundary index of the array
     * @param comp - the comparator given to compare files by.
     * @return - the index of the partition after replacing it.
     */
    public static int partition (File[] arr, int low,int high,Comparator<File> comp)
    {
        File pivot = arr[high];
        int lowIndex = (low - 1);
        for (int highIndex = low; highIndex <= high- 1; highIndex++)
        {
            if (comp.compare(pivot,arr[highIndex])>=0)
            {
                lowIndex++;
                swapInArray(arr,lowIndex,highIndex);
            }
        }
        swapInArray(arr,lowIndex+1,high);
        return (lowIndex + 1);
    }

    /**
     * swaps between to items in a given array, by their indexes.
     */
    public static void swapInArray(File[] arr, int i,int j){
        File temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
