package filesprocessing.filters.Strings;

import filesprocessing.filterArgsWrapper;
import filesprocessing.filters.BooleanFilters;
import filesprocessing.filters.StringFilters;

import java.io.File;
import java.util.function.Predicate;

public class fileFilter extends StringFilters {
    /**
     * @param obj - a wrapper of the given values by the user. in this case a single String and if 'NOT' was raised.
     * @return a predicate, that returns true if the file name is equal to the value given.
     */
    @Override
    public Predicate<File> filter(filterArgsWrapper obj) {
        return x -> x.getName().equals(obj.getStr());
        }
    }

