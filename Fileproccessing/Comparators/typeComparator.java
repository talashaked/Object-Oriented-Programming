package filesprocessing.Comparators;

import java.io.File;
import java.util.Comparator;
/**
 * A comparator, compares between two Files by their types.
 */
public class typeComparator implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {
        int i = o1.getName().lastIndexOf(".");
        String type1, type2;
        if (i >= 0) {
            type1 = o1.getName().substring(i);
        } else {
            type1 = "";
        }
        i = o2.getName().lastIndexOf(".");
        if (i >= 0) {
            type2 = o2.getName().substring(i);
        } else {
            type2 = "";
        }
        if (type1.compareTo(type2)>0){
            return 1;
        }
        else if(type1.compareTo(type2)<0){
            return -1;
        }
        else{
            return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
        }
    }
}
