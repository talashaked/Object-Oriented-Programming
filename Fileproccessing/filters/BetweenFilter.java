package filesprocessing.filters;

import filesprocessing.filterArgsWrapper;
import filesprocessing.Exceptions.ValueException;

import java.io.File;
import java.util.function.Predicate;

public class BetweenFilter implements FilterInterface {
    /**
     * this filter is used to checks whether the size of the file is between the given values in the args given.
     * @param obj - a wrapper, consisting of the values by the user (2 sizes, and if 'NOT' was used)
     * @return
     */
    @Override
    public Predicate<File> filter(filterArgsWrapper obj) {
        return (x)->(((double)x.length()/1024>=obj.getFirstVal())&&((double)x.length()/1024<=obj.getSecondVal()));
    }

    /**
     * This functions wraps the inputs to a predicate of a filter by two doubles.
     * this function wraps the relevant args if only they are given.
     * otherwise - in any other case - an error is raised.
     * @param words - the filter method lines by strings.
     * @return - a wrapper, including all the relevant values for the filters predicate.
     * @throws ValueException
     */
    @Override
    public filterArgsWrapper whatThePredicateNeeds(String[] words) throws ValueException {
        filterArgsWrapper wrapper = new filterArgsWrapper();
        int wordsLength = words.length;
        if (words[wordsLength-1].equals("NOT")){
            wordsLength--;
            wrapper.setIsNot();
        }
        if(wordsLength==3){
            try{
                double first = Double.parseDouble(words[1]);
                double second = Double.parseDouble(words[2]);
                if(((first>=0)&&(second>=0))&&(first<=second)) {
                    wrapper.setTwoVals(first, second);
                    return wrapper;
                }
                else{
                    throw new ValueException();
                }
            }
            catch (NumberFormatException e){
                throw new ValueException();
            }
        }
        else{
            throw new ValueException();
        }
    }
}
