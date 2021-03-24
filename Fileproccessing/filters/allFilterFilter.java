package filesprocessing.filters;

import filesprocessing.filterArgsWrapper;
import filesprocessing.Exceptions.ValueException;

import java.io.File;
import java.util.function.Predicate;

public class allFilterFilter implements FilterInterface {
    @Override
    /**
     * returns a predicate that returns true for each file.
     */
    public Predicate<File> filter(filterArgsWrapper obj) {
        return x->true;
    }

    /**
     * check the args given by the user to this method. all is a filter that could be only followed
     * by 'NOT'. otherwise, in any other case, an error is raised.
     * @param words - the words values given in the command line.
     * @return - a wrapper that is empty,only implying if a 'NOT' string was given or not.
     * @throws ValueException ;
     */
    @Override
    public filterArgsWrapper whatThePredicateNeeds(String[] words) throws ValueException {
        filterArgsWrapper wrapper = new filterArgsWrapper();
        int wordsLength = words.length;
        if (wordsLength==2){
            if(words[1].equals("NOT")){
                wrapper.setIsNot();
                wordsLength--;
            }
        }
        if (wordsLength==1){
            return wrapper;
        }
        else{
            throw new ValueException();
        }
    }
}
