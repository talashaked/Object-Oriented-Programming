import java.sql.SQLOutput;
import java.util.NoSuchElementException;

/**
 *
 */
public class OpenHashSet extends SimpleHashSet {
    LinkedListWrapper[] selfArray = new LinkedListWrapper[INITIAL_CAPACITY];
    int counterOfItems = 0;

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - the upper load factor of the hash table
     * @param lowerLoadFactor - the lower load factor of the hash table
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor,lowerLoadFactor);
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load factor (0.75)
     * and lower load factor (0.25).
     */
    public OpenHashSet(){
        super();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be ignored.
     * The new table has the default values of initial capacity (16), upper load factor (0.75), and lower load factor
     * (0.25).
     * @param data - Values to add to the set
     */
    public OpenHashSet(java.lang.String[] data){
        super();
        for (java.lang.String item : data) {
            add(item);
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return - false if newValue exists in the set, true if successful.
     */
    public boolean add(java.lang.String newValue){
        int index = clamp(newValue.hashCode());
        if (!(this.contains(newValue))){
            float loadFactor = ((this.size()+1)/(float)this.capacity());
            if (loadFactor>this.getUpperLoadFactor()) {
                this.resizeTable(this.capacity() * 2);
                index = clamp(newValue.hashCode());
            }
            if (this.selfArray[index]==null){
                this.selfArray[index] = new LinkedListWrapper();
            }
            this.selfArray[index].add(newValue);
            this.counterOfItems++;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * looks for a specified string in the set
     * @param searchVal Value to search for
     * @return - true if exists, false otherwise.
     */
    public boolean contains(java.lang.String searchVal){
        int index = clamp(searchVal.hashCode());
        if((this.selfArray[index]==null)||(!this.selfArray[index].contains(searchVal))){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * @return - The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return this.selfArray.length;
    }

    /**
     * @return - the number of elements currently in the set
     */
    public int size(){
        return this.counterOfItems;

    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return - false if string not found in the set, true otherwise
     */
    public boolean delete(java.lang.String toDelete){
        int index = clamp(toDelete.hashCode());
        if (!(this.contains(toDelete))){
            return false;
        }
        else{
            this.selfArray[index].remove(toDelete);
            this.counterOfItems--;
            float loadFactor = this.counterOfItems/(float)this.capacity();
            if((loadFactor<getLowerLoadFactor())&&(this.capacity()>1)){
                resizeTable(this.capacity()/2);
            }
            return true;
        }
    }

    /**
     * this function get a new capacity as an input, and resizes the length of the table in accordance to it, then
     * reinserts the strings that were in it.
     * @param newCapacity - the new capacity to change the set to.
     */
    private void resizeTable(int newCapacity) {
        java.lang.String[] strList = new java.lang.String[this.size()];
        int count =0;
        for (LinkedListWrapper item : this.selfArray){
            if(item!=null){
                for(java.lang.String str : item){
                    strList[count]=str;
                    count++;
                }
            }
        }
        this.selfArray = new LinkedListWrapper[newCapacity];
        for (java.lang.String str : strList) {
            int index = clamp(str.hashCode());
            if(this.selfArray[index]==null){
                this.selfArray[index] = new LinkedListWrapper();
            }
            this.selfArray[index].add(str);
            }
        }
    }



