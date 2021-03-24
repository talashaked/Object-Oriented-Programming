import oop.ex3.spaceship.*;

import java.util.HashMap;
import java.util.Map;


public class LongTermStorage extends GeneralLocker {
    final static int STORAGE_LENGTH = 1000;

    /*
    the long term storage constructor, generated a map for the object and a sumitems parameter to keep track of the capacity
     */
    public LongTermStorage() {
        super(STORAGE_LENGTH);
    }

    /*
        a function that will try and add items to storage. if there is enough room, the items would be added and 0
        would be returned, else, a message would be printed and so will -1 be returned.
     */
    @Override
    public int addItem(Item item, int n) {
        int itemVolume = item.getVolume();
        if(n<0){
            return -1;
        }
        else if(n==0){
            return 0;
        }
        else if ((n * itemVolume + this.sumItems) > STORAGE_LENGTH) {
            System.out.println("Error: Your request cannot be completed at this time. Problem: No room for " + n +
                    " items of type " + item.getType());
            return -1;
        }
        else {
            if (this.lockerMap.containsKey(item.getType())) {
                this.lockerMap.put(item.getType(), this.lockerMap.get(item.getType()) + n);
            } else {
                this.lockerMap.put(item.getType(), n);
            }
            this.sumItems += n * itemVolume;
            return 0;
        }
    }

    /*
        a function that will reset the inventory of the storage locker. both the map would reset and the sum of items.
    */
    public void resetInventory() {
        this.lockerMap.clear();
        this.sumItems = 0;
    }
}