package filesprocessing;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * this method represents a section of the command file - consisting of a filter predicate, and a comparator,
 * and error lines.
 */
public class sectionProcessor {
    final sortingArray sorter = new sortingArray();
    Predicate<File> secFilter;
    Comparator<File> secOrder;
    int errFilter;
    int errOrder;

    /**
     * each section need to receive as an input a predicate by Files and a comparator.
     * @param filter - a predicate relevant for this given section
     * @param order - an order given to this given section
     */
    public sectionProcessor(Predicate<File> filter, Comparator<File> order)
    {
            this.secFilter = filter;
            this.secOrder = order;
            this.errFilter=0;
            this.errOrder=0;
    }

    /**
     * set the filter line error
     * @param i;
     */
    public void setFilterError(int i){
        this.errFilter = i+1;
    }

    /**
     * set the order line error
     * @param i;
     */
    public void setOrderError(int i){
        this.errOrder = i+1;
    }

    /**
     * given a file array, this function runs the predicate as a filter on the array, than sorts it
     * by the comparator.
     * than the filter error and the order error are printed(if present)
     * and in the end prints the sorted&filtered array.
     * @param fileArr - a given file array.
     */
    public void process(File[] fileArr){
        if (this.errFilter!=0){
            System.err.println("Warning in line "+this.errFilter);
        }
        if(this.errOrder!=0){
            System.err.println("Warning in line "+this.errOrder);
        }
        File[] filteredArr = Arrays.stream(fileArr).filter(this.secFilter).toArray(File[]::new);
        sorter.sortByComp(filteredArr,this.secOrder);
        for(File file:filteredArr) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }


        }
    }
}
