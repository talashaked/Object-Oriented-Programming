package filesprocessing.filters;

import filesprocessing.filterArgsWrapper;
import filesprocessing.Exceptions.ValueException;

public abstract class StringFilters implements FilterInterface {
    /**
     * This functions wraps the inputs to a predicate of a filter by string.
     * all filters by string input are as follows: 'filter'#'value(#NOT)'. therefore,
     * this function wraps the relevant args if only they are given.
     * otherwise - in any other case - an error is raised.
     * @param words - the filter method lines by strings.
     * @return - a wrapper, including all the relevant values for the filters predicate.
     * @throws ValueException;
     */
    @Override
    public filterArgsWrapper whatThePredicateNeeds(String[] words) throws ValueException {
        filterArgsWrapper wrapper = new filterArgsWrapper();
        int wordsLength = words.length;
        if ((words[wordsLength-1].equals("NOT"))&&(wordsLength!=2)){
                wordsLength--;
                wrapper.setIsNot();
        }
        if(wordsLength==2){
                wrapper.setStr(words[1]);
                return wrapper;
        }
        else{
            throw new ValueException();
        }
    }

}
