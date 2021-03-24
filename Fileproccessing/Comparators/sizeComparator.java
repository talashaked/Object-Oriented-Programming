package filesprocessing.Comparators;

import java.io.File;
import java.util.Comparator;
/**
 * A comparator, compares between two Files by their sizes.
 */
public class sizeComparator implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        if (o1.length()>o2.length()){
            return 1;
        }
        else if (o1.length()<o2.length()){
            return -1;
        }
        else{
            return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
        }
    }
}
