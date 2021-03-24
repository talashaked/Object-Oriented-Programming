package filesprocessing.filters.Strings;
import filesprocessing.filterArgsWrapper;
import filesprocessing.filters.StringFilters;

import java.io.File;
import java.util.function.Predicate;

public class containsFilter extends StringFilters {
    /**
     * @param obj - a wrapper of the given values by the user.
     * @return a predicate, that returns true if the file name contains the string given in the args,
     * false otherwise.
     */
    @Override
    public Predicate<File> filter(filterArgsWrapper obj) {
        return x -> x.getAbsolutePath().contains(obj.getStr());
    }
}
