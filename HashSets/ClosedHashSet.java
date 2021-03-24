public class ClosedHashSet extends SimpleHashSet{
    String[] strArr = new String[INITIAL_CAPACITY];
    int itemCounter = 0;
    private static final String removeTag = new String("100");

    public ClosedHashSet() {
        super();
    }
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor,lowerLoadFactor);
    }
    public ClosedHashSet(String[] data){
        super();
        for (String item : data) {
            add(item);
        }
    }

    /**
     * this function adds a string to the set. In addition if adding the item to the set makes the
     * size of the set be above the upper bound, resizes the set
     * @param newValue New value to add to the set
     * @return : true if successful, false if string not in array
     */
    public boolean add(String newValue) {
        int relevantIndex = this.findOptimalIndex(newValue);
        if ((relevantIndex==-1)||(this.strArr[relevantIndex]==null)||(this.strArr[relevantIndex]==removeTag)){
            float loadFactor = (float)(this.size()+1)/this.capacity();
            if(loadFactor>getUpperLoadFactor()) {
                this.resizeTable(this.capacity() * 2);
            }
            this.strArr[this.findOptimalIndex(newValue)] = newValue;
            this.itemCounter++;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * this function finds the optimal index of the string searchVal. if it doesn't exist in the array -
     * it returns the first time its location was removed (if there isn't - returns the null index for it,
     * and if it doesn't exist as well - returns -1). if the String exists in the array - its index is returned.
     * @param searchVal - the string to search for in the array
     * @return the relevant index
     */
    private int findOptimalIndex(String searchVal){
        int firstRemovedIndex=-1;
        for(int i=0; i<this.capacity(); i++){
            int index = indexCalc(searchVal,i);
            if(this.strArr[index]==null){
                if(firstRemovedIndex==-1){
                    return index;
                }
                else{
                    return firstRemovedIndex;
                }
            }
            else if(this.strArr[index] == removeTag)
                {
                if(firstRemovedIndex==-1) {
                    firstRemovedIndex = index;
                }
            }
            else if((this.strArr[index].equals(searchVal))&&(this.strArr[index]!=removeTag)){
                return index;
            }
        }
        return firstRemovedIndex;
    }

    /**
     * checks whether the string is contained in the array.
     * @param searchVal Value to search for
     * @return true if contained, false otherwise
     */
    public boolean contains(String searchVal){
        int relevantIndex = this.findOptimalIndex(searchVal);
        if((relevantIndex==-1)||(this.strArr[relevantIndex] == null)||(this.strArr[relevantIndex]==removeTag)){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * returns the capacity of the set
     * @return the length of the set
     */
    public int capacity(){
        return this.strArr.length;

    }

    /**
     * @return returns the amount of strings in the array
     */
    public int size(){
        return this.itemCounter;
    }

    /**
     * this function calculates the index for a given string and an integer i
     * @param str - the string relevant
     * @param index - the i relevant
     * @return - the relevant index in the set for the clamp of both string and i
     */
    public int indexCalc(String str,int index){
        return clamp(str.hashCode() + ((index + (index*index))/2));
    }

    /**
     * given a string, this function searches for it in the set, and deletes it from the set.
     * @param toDelete Value to delete
     * @return - true if successful, false if item not found in set.
     */
    public boolean delete(String toDelete){
        int relevantIndex = this.findOptimalIndex(toDelete);
        if((relevantIndex==-1)||(this.strArr[relevantIndex]==null)||(this.strArr[relevantIndex]==removeTag)){
            return false;
        }
        else{
            this.strArr[relevantIndex]=removeTag;
            this.itemCounter--;
            float loadFactor = (float)(this.size()) /this.capacity();
            if(((loadFactor<getLowerLoadFactor())&&(this.capacity()>1))){
                this.resizeTable(this.capacity()/2);
            }
            return true;
        }
    }

    /**
     * given te new capacity, this function rearranges the set in a new bigger or smaller set.
     * @param newCapacity - the new capacity to change the set to
     */
    private void resizeTable(int newCapacity){
        String[] strArrClone = this.strArr;
        this.strArr = new String[newCapacity];
        for(String strClone:strArrClone){
            if((strClone!=null)&&(strClone!=removeTag)){
                int relevantIndex = this.findOptimalIndex(strClone);
                this.strArr[relevantIndex] = strClone;
            }
        }

    }
}
