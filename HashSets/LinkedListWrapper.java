import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * a class that wraps a linked lit of java
 */
public class LinkedListWrapper implements Iterable<java.lang.String> {
    LinkedList<java.lang.String> LLobject;

    /**
     * a default constructor, initializes a new linked list object fo
     */
    public LinkedListWrapper(){
        this.LLobject = new LinkedList<>();

    }

    /**
     * uses the add function of linked list to add a string the linked list object
     * @param item - the item given to add
     * @return true if successful, false otherwise
     */
    public boolean add(java.lang.String item){
        return this.LLobject.add(item);
    }
    /**
     * uses the contains function of linked list to to check if a string is contained in the linked list object
     * @param item - the item given to check about
     * @return true if is in list, false otherwise
     */
    public boolean contains(java.lang.String item){
        return this.LLobject.contains(item);
    }
    /**
     * uses the remove function of linked list to remove a string from the linked list object
     * @param item - the item given to remove
     * @return true if successful, false otherwise
     */
    public boolean remove(java.lang.String item){
        return this.LLobject.remove(item);
    }

    /**
     * an iterator used to that this linked list wrapper could be iterated on
     * @return - the linked list iterator
     */
    public Iterator<java.lang.String> iterator(){
        return this.LLobject.iterator();
    }
}
