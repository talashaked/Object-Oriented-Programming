package filesprocessing.Comparators;

import java.io.File;
import java.util.Comparator;

/**
 * A comparator, compares between two Files by their names.
 */
public class lettersComparator implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {
        return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
    }
}
