package filesprocessing;

import filesprocessing.Exceptions.ValueException;
import filesprocessing.Exceptions.badSubsectionNameException;
import filesprocessing.filters.FilterInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Predicate;

/**
 * this class is the parser, which processes an array list of strings, by their text into sections.
 */
public class commandFileParser {
    int errFilterIndexLine;
    int errOrderIndexLine;
    private final String filterString = "FILTER";
    private final String orderString = "ORDER";
    private final String defaultFilter = "all";
    private final String defaultOrder = "abs";
    private HashMap<String, FilterInterface> allFilters = loadFilters.createFilter();
    private HashMap<String, Comparator<File>> allOrders = loadComparators.createFilter();
    private ArrayList<String> commandFile;

    /**
     * in the constructor, builds an object of type parser.
     * the error lines are initialized as well.
     * @param commandFileAsStrings -for a array list of strings
     */
    public commandFileParser(ArrayList<String> commandFileAsStrings){
        this.commandFile = commandFileAsStrings;
        errFilterIndexLine=-1;
        errOrderIndexLine=-1;

    }

    /**
     * this is the main function of the parser.
     * when is run, goes over the array list, and separates it into sections, while checking
     * each lines is valid and matches all requirements.
     * @return - a section array.
     * @throws badSubsectionNameException - if there war an error in any of the sub seciton names- an error
     * is thrown.
     */
    public ArrayList<sectionProcessor> commandFileSectionCreator() throws badSubsectionNameException {
        ArrayList<sectionProcessor> sectionsArr = new ArrayList<>();
        int fixer = 0;
        Predicate<File> filterFunc = null;
        for (int i = 0; i < this.commandFile.size() ; i++) {
            if ((i + fixer) % 4 == 0)//in this case, the index is on the filter command line
            {
                if (!this.commandFile.get(i).equals(filterString)) {
                    throw new badSubsectionNameException();
                }
                this.errFilterIndexLine = -1;
                this.errOrderIndexLine = -1;
                if(i==this.commandFile.size()-1){
                    throw new badSubsectionNameException();
                }
            }
            else if ((i + fixer) % 4 == 1)//in this case the index is on the filtering method line
            {
                String[] filterLine = this.commandFile.get(i).split("#");
                filterFunc = this.filterMethodProcess(filterLine,i);
                if(i==this.commandFile.size()-1){
                    throw new badSubsectionNameException();
                }
            }
            else if ((i + fixer) % 4 == 2) //in this case, the index is on the orderCommand line
            {
                if (!this.commandFile.get(i).equals(orderString)) {
                    throw new badSubsectionNameException();
                }
                else{
                    if(i==this.commandFile.size()-1){
                        sectionsArr.add(createSection(filterFunc,this.allOrders.get(defaultOrder)));
                    }
                }
            }
            else // in this line the index is on the order method line
            {
                try{
                    orderArgsWrapper order = new orderArgsWrapper(this.commandFile.get(i).split("#"));
                    if (this.allOrders.containsKey(order.getOrder())) {
                        sectionsArr.add(this.validOrderProcess(filterFunc,order));
                    }
                    else if (order.getOrder().equals(filterString))
                        //in this case a new section should be started early, which means the order is empty(=default)
                    {
                        fixer++;
                        sectionsArr.add(createSection(filterFunc,this.allOrders.get(defaultOrder)));
                        this.errOrderIndexLine = -1;
                        this.errFilterIndexLine = -1;
                        if(i==this.commandFile.size()-1){
                            throw new badSubsectionNameException();
                        }
                    }
                    else{
                        this.errOrderIndexLine = i;
                        sectionsArr.add(createSection(filterFunc,this.allOrders.get(defaultOrder)));
                    }
                }
                catch (ValueException e){
                    this.errOrderIndexLine = i;
                    sectionsArr.add(createSection(filterFunc,this.allOrders.get(defaultOrder)));
                }


            }
        }
        return sectionsArr;
    }

    /**
     * in case of a valid order, this function creates a section out of the filter given, and the
     * order wrapper given.
     * @param filterFunc - a predicate given to build the section
     * @param order - the order wrapper
     * @return
     */
    private sectionProcessor validOrderProcess(Predicate<File> filterFunc, orderArgsWrapper order){
        if (order.getIsReversed()){
            return createSection(filterFunc,this.allOrders.get(order.getOrder()).reversed());
        }
        else{
            return createSection(filterFunc,this.allOrders.get(order.getOrder()));
        }
    }

    /**
     * this function is run in order to process the filter line method.
     * if the methods, that were given are valid, the relevant predicate would return,
     * and in any other case the default function will return.
     * @param filterWords - the filter method line, separated to strings
     * @param line - the line in the array list the filter is located in.
     * @return - the relevant predicate.
     */
    private Predicate<File> filterMethodProcess(String[] filterWords,int line){
        Predicate<File> filterFunc;
        if (this.allFilters.containsKey(filterWords[0]))
        {
            try {
                filterArgsWrapper argsForFilter = this.allFilters.get(filterWords[0]).whatThePredicateNeeds(filterWords);
                if (argsForFilter.getIsNot()){
                    filterFunc = (this.allFilters.get(filterWords[0]).filter(argsForFilter)).negate();
                }
                else{
                    filterFunc = this.allFilters.get(filterWords[0]).filter(argsForFilter);
                }
            } catch (ValueException e) {
                filterFunc = this.allFilters.get(defaultFilter).filter(new filterArgsWrapper());
                this.errFilterIndexLine = line;
            }
        } else {
            filterFunc = this.allFilters.get(defaultFilter).filter(new filterArgsWrapper());
            this.errFilterIndexLine = line;
        }
        return filterFunc;
    }

    /**
     * creates a section from a given filter and a given order.
     * @param filter - the predicate given to initialize a section
     * @param comp - the comparator given to initialize a section
     * @return
     */
    private sectionProcessor createSection(Predicate<File> filter, Comparator<File> comp){
        sectionProcessor section = new sectionProcessor(filter,comp);
        section.setFilterError(this.errFilterIndexLine);
        section.setOrderError(this.errOrderIndexLine);
        return section;
    }



}
