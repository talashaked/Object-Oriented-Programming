package filesprocessing.filters;

import filesprocessing.filterArgsWrapper;
import filesprocessing.Exceptions.ValueException;

public abstract class oneDoubleFilters implements FilterInterface {
    /**
     * This functions wraps the inputs to a predicate of a filter by double.
     * all filters by double input are as follows: 'filter'#'value(#NOT)'. therefore,
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
        if (words[wordsLength-1].equals("NOT")){
            wordsLength--;
            wrapper.setIsNot();
        }
        if(wordsLength==2){
            try{
                double val = Double.parseDouble(words[1]);
                if(val>=0) {
                    wrapper.setMainVal(val);
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
