public class CollectionFacadeSet extends java.lang.Object implements SimpleSet{
    protected java.util.Collection<java.lang.String> collObj;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection - a given collection
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
        this.collObj = collection;
    }

    /**
     *Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return true if successful, false otherwise
     */
    public boolean add(java.lang.String newValue)
        {
        return this.collObj.add(newValue);
    }

    /**
     *Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return true if exists in set, false otherwise
     */
    public boolean contains(java.lang.String searchVal)
        {
            return this.collObj.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return true if successful, false if string not found in set
     */
    public boolean delete(java.lang.String toDelete)
    {
        return this.collObj.remove(toDelete);
    }

    /**
     * @return the current size, which is the amount of items currently in the set
     */
    public int size() {

        return this.collObj.size();
    }



}
