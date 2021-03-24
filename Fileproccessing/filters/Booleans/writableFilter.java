package filesprocessing.filters.Booleans;

import filesprocessing.filterArgsWrapper;
import filesprocessing.filters.BooleanFilters;

import java.io.File;
import java.util.function.Predicate;

public class writableFilter extends BooleanFilters {
    /**
     * @param obj - a wrapper of the given values by the user. in this case 'YES"/'NO' and if 'NOT' was raised.
     * @return a predicate, that returns true or false(depending on the given input by the user)
     * if the file is writable.
     */
    @Override
    public Predicate<File> filter(filterArgsWrapper obj) {
        if (obj.getBool()){
            return x->x.canWrite();
        }
        else{
            return x->!x.canWrite();
        }
    }
}
