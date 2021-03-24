
import oop.ex3.spaceship.*;

public class Locker extends GeneralLocker {
    public static LongTermStorage longTermStorageGeneral = new LongTermStorage();
    public Locker(int capacity){
        super(capacity);
    }
    /*
    this function tries to add items to the locker.
     If there is enough available capacity in the locker, two options will open:
        If this item type takes more than 50% of the room (after theoretically adding the number of items requested),
         it will add items of the given type to this locker so that eventually this item type will not take more than 20% of
         the lockers capacity(the rest will be added to the storage locker), and 1 will be returned.
         If the storage locker has no room for the remaining items, no items would
         be added and -1 will be retufrned.
        If this is not case, the items would just be added to the locker and 0 will be returned.
    in any other scenario, the items wouldn't be added and -1 will be returned.
     */
    @Override
    public int addItem(Item item, int n) {
        if (this.itemsConstraints(item.getType()) == -2){
            System.out.println("Error: your request cannot be completed at this time. Problem: the locker cannot" +
                    "contain items of type "+item.getType()+", as it conatins a contradicting item.");
            return -2;
        }
        else if(n==0){
            return 0;
        }
        else if(n<0){
            System.out.println("Error: Your request cannot be completed at this time.");
            return -1;
        }
        else if (this.getAvailableCapacity() >= n * item.getVolume()) {
            if ((this.getItemCount(item.getType()) + n)*item.getVolume() > 0.5 * this.lockerCapacity) {
                return this.addItemToStorage(item,n);
            }
            else {
                this.lockerMap.put(item.getType(), this.getItemCount(item.getType()) + n);
                this.sumItems += n*item.getVolume();
                return 0;
            }
        }
        else {
            System.out.println("Error: your request cannot be completed at this time. Problem: no room" +
                    " for "+n+" items of type "+item.getType());
            return -1;
        }

    }
    /*
        this function removes 'n' items out of the list. if there isn't n items of this type in the locker, -1 will be
        returned and a message will be printed. if successful 0 will be returned.

     */
    public int removeItem(Item item,int n){
        if(n<0){
            System.out.println("Error: Your request cannot be completed at this time. Problem: Cannot remove a negative" +
                    "number of items of type "+item.getType());
            return -1;
        }
        if(n==0){
            return 0;
        }
        else if(this.getItemCount(item.getType()) >= n) {
                this.lockerMap.put(item.getType(), this.lockerMap.get(item.getType()) - n);
                this.sumItems -= n * item.getVolume();
                if(this.getItemCount(item.getType())==0){
                    this.lockerMap.remove(item.getType());
                }
                return 0;
            } else {
                System.out.println("Error: Your request cannot be completed at this time. Problem: The locker does not " +
                        "contain " + n + " items of type " + item.getType());
                return -1;
            }
    }
    /*
    this function checks items constraints, and prevents from adding an item (such as "baseball bat") when an invalid item
    to put it with (continuing the example - "football") is already in the locker.
     */
    public int itemsConstraints(String type) {
        if (type.equals("baseball bat")) {
            if ((this.lockerMap.containsKey("football")) && (this.lockerMap.get("football") > 0)) {
                return -2;
            }
        } else if (type.equals("football")) {
            if ((this.lockerMap.containsKey("baseball bat")) && (this.lockerMap.get("baseball bat") > 0)) {
                return -2;
            }
        }
        return 0;
    }
    /*
    when adding items to the locker, if items should be moved to the storage this function is called.
    checks the available capacity in the main storage and transfers items there if possible, or returns -1 if not.
     */
    private int addItemToStorage(Item item,int n){
        int itemsToRemain;
        int numToMove;
        itemsToRemain = (int)((0.2*this.lockerCapacity)/(item.getVolume()));
        numToMove = (n + this.getItemCount(item.getType())) - itemsToRemain;
        if (longTermStorageGeneral.addItem(item,numToMove) == 0) {
            System.out.println("Warning: Action successful but has caused items to be moved to storage");
            this.sumItems += (itemsToRemain-this.getItemCount(item.getType()))*item.getVolume();
            this.lockerMap.put(item.getType(),itemsToRemain);
            return 1;
        }
        else{
            return -1;
        }
    }
}

