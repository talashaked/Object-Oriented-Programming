package filesprocessing;

import filesprocessing.Comparators.lettersComparator;
import filesprocessing.Comparators.sizeComparator;
import filesprocessing.Comparators.typeComparator;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;

public class loadComparators {
    enum comparatorRelevant{
        abs,type,size;
    }

    /**
     * a factory, creating a hash map of sorting method by names as keys, and their relevant matched
     * comparators as values.
     * @return - the created hash map.
     */
    public static HashMap<String, Comparator<File>> createFilter(){
        HashMap<String, Comparator<File>> comparatorMethods = new HashMap<>();
        comparatorMethods.put(comparatorRelevant.abs.toString(),new lettersComparator());
        comparatorMethods.put(comparatorRelevant.type.toString(),new typeComparator());
        comparatorMethods.put(comparatorRelevant.size.toString(),new sizeComparator());
        return comparatorMethods;
    }
}
