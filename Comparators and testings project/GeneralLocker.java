import oop.ex3.spaceship.Item;

import java.util.HashMap;
import java.util.Map;
/*
a general abstract locker class, with functionalities such as a hashmap inventory, and observing it.
in addition there is a need when extending this class to implement the "add item" function.
 */
public abstract class GeneralLocker{
    protected int lockerCapacity;
    protected int sumItems;
    protected HashMap<String,Integer> lockerMap;
    /*
    the locker constructor, initiating its capacity according to the input, the lockers map, and an integer to keep track
    on the sum of items in the map.
     */
    public GeneralLocker(int capacity){
        this.lockerCapacity = capacity;
        this.lockerMap = new HashMap<String, Integer>();
        this.sumItems = 0;
    }

    /*
    return the capacity currently in the locker
     */
    public int getCapacity(){
        return this.lockerCapacity;
    }
    /*
    return the current available capacity in the locker
     */
    public int getAvailableCapacity(){
        return this.lockerCapacity - this.sumItems;
    }
    /*
    return a map of the lockers inventory
     */
    public Map<String,Integer> getInventory(){
        return new HashMap<String, Integer>(this.lockerMap);

    }
    /*
    return the number of items of type 'type' in the locker
     */
    public int getItemCount(String type){
        if(this.lockerMap.containsKey(type)){
            return this.lockerMap.get(type);
        }
        else{
            return 0;
        }
    }
    /*
    a fucntion that will able adding items to the locker. the function will return 0 if successful, a positive number if
    successful but with comments, and a negative number for unsuccessful operation.
     */
    public abstract int addItem(Item item,int n);
}
