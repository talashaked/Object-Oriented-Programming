package filesprocessing.filters;

import filesprocessing.filterArgsWrapper;
import filesprocessing.Exceptions.ValueException;

public abstract class BooleanFilters implements FilterInterface {
    /**
     * This functions wraps the inputs to a predicate of a filter by boolean values.
     * all filters by string input are as follows: 'filter'#'YES/NO(#NOT)'. therefore,
     * this function wraps the relevant args if only they are given.
     * otherwise - in any other case - an error is raised.
     * @param words - the filter method lines by strings.
     * @return - a wrapper, including all the relevant values for the filters predicate.
     */
    @Override
    public filterArgsWrapper whatThePredicateNeeds(String[] words) throws ValueException {
        filterArgsWrapper wrapper = new filterArgsWrapper();
        int wordsLength = words.length;
        if (words[wordsLength-1].equals("NOT")){
            wordsLength--;
            wrapper.setIsNot();
        }
        if(wordsLength==2){
            try{
                if(words[1].equals("YES")){
                    wrapper.setBool(true);
                }
                else if(words[1].equals("NO")){
                    wrapper.setBool(false);
                }
                else{
                    throw new ValueException();
                }
                return wrapper;
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
