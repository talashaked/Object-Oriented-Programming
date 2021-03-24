package filesprocessing.filters.Doubles;

import filesprocessing.filterArgsWrapper;
import filesprocessing.filters.oneDoubleFilters;

import java.io.File;
import java.util.function.Predicate;

public class smallerThanFilter extends oneDoubleFilters {
    /**
     * @param obj - a wrapper of the given values by the user. in this case a single double and if 'NOT' was raised.
     * @return a predicate, that returns true if the size of the file is smaller than the value given.
     */
    @Override
    public Predicate<File> filter(filterArgsWrapper obj) {
        return x->((double)(x.length())/1024)<obj.getMainVal();
    }
}
