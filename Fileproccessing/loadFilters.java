package filesprocessing;

import filesprocessing.filters.*;
import filesprocessing.filters.Booleans.executableFilter;
import filesprocessing.filters.Booleans.hiddenFilter;
import filesprocessing.filters.Booleans.writableFilter;
import filesprocessing.filters.Doubles.greaterThanFilter;
import filesprocessing.filters.Doubles.smallerThanFilter;
import filesprocessing.filters.Strings.containsFilter;
import filesprocessing.filters.Strings.fileFilter;
import filesprocessing.filters.Strings.prefixFilter;
import filesprocessing.filters.Strings.suffixFilter;

import java.util.HashMap;

public class loadFilters {
    enum filterRelevant{
        greater_than, between, smaller_than, file,contains,prefix,suffix,writable,executable,hidden,all;
    }

    /**
     * a factory, creating a HashMap consisting of filter names as keys, and filter objects as values.
     * @return - the created hash map.
     */
    public static HashMap<String, FilterInterface> createFilter(){
        HashMap<String, FilterInterface> filterMethods = new HashMap<>();
        filterMethods.put(filterRelevant.greater_than.toString(),new greaterThanFilter());
        filterMethods.put(filterRelevant.between.toString(),new BetweenFilter());
        filterMethods.put(filterRelevant.smaller_than.toString(),new smallerThanFilter());
        filterMethods.put(filterRelevant.file.toString(),new fileFilter());
        filterMethods.put(filterRelevant.contains.toString(),new containsFilter());
        filterMethods.put(filterRelevant.prefix.toString(),new prefixFilter());
        filterMethods.put(filterRelevant.suffix.toString(),new suffixFilter());
        filterMethods.put(filterRelevant.writable.toString(),new writableFilter());
        filterMethods.put(filterRelevant.executable.toString(), new executableFilter());
        filterMethods.put(filterRelevant.hidden.toString(), new hiddenFilter());
        filterMethods.put(filterRelevant.all.toString(), new allFilterFilter());
        return filterMethods;

    }
}
