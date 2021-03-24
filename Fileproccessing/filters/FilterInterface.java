package filesprocessing.filters;

import filesprocessing.filterArgsWrapper;
import filesprocessing.Exceptions.ValueException;

import java.io.File;
import java.util.function.Predicate;

/**
 * a filter interface, requiring each class using it to implement a filtering method which returns a predicate,
 * and 'what the predicate needs' method that returns a wrapper wrapping the args of relevant to the filter.
 */
public interface FilterInterface {


    Predicate<File> filter(filterArgsWrapper obj);
    filterArgsWrapper whatThePredicateNeeds(String[] words) throws ValueException;
}

