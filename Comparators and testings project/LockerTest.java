import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import oop.ex3.spaceship.*;

import java.util.HashMap;
import java.util.Map;

public class LockerTest {
    private Locker testLocker;
    private Item testItem1 = ItemFactory.createSingleItem("baseball bat");
    private Item testItem2 = ItemFactory.createSingleItem("helmet, size 1");
    private Item testItem3 = ItemFactory.createSingleItem("helmet, size 3");
    private Item testItem4 = ItemFactory.createSingleItem("spores engine");
    private Item testItem5 = ItemFactory.createSingleItem("football");

    @Before
    public void resetLocker() {
        this.testLocker.longTermStorageGeneral.resetInventory();
        this.testLocker = new Locker(10);
    }
    /*
    checks the get capacity function
     */
    @Test
    public void testCapacity() {
        this.testLocker.addItem(testItem2,2);
        assertEquals(this.testLocker.getCapacity(),10);
    }

    /*
    test if the locker can add files in the range of its storage capability
     */
    @Test
    public void testAddItem1() {
        assertEquals(testLocker.addItem(testItem1, 1), 0);
    }

    /*
    tests if the locker will add items when they are out of its capability range
     */
    @Test
    public void testAddItem2() {
        assertEquals(testLocker.addItem(testItem3, 3), -1);

    }

    /*
    this test checks whether the additem succeeded in adding an amount of items so that it will move a certain amount
    of them to the strage locker
     */
    @Test
    public void testAdditem3() {
        testLocker.addItem(testItem1, 2);
        assertEquals(testLocker.addItem(testItem1,3), 1);

    }
    /*
    this function checks an insert to the locker of items that would be transferred to the storage locker, when the
    storage locker is out of space.
     */
    @Test
    public void testAddItem4(){
        testLocker.addItem(testItem1,3);
        for (int i=1;i < 250; i++){
            testLocker.addItem(testItem1,2);
        }
        assertEquals(testLocker.addItem(testItem1,2),-1);
    }
    /*
    tests the add item function while sending an invalid input
     */
    @Test
    public void testAddItem5(){
        assertEquals(testLocker.addItem(testItem2,-1),-1);
    }
    /*
    tests the add item function while sending a zero input
     */
    @Test
    public void testAddItem6(){
        assertEquals(testLocker.addItem(testItem2,0),0);
    }
    /*
    test the function getitemcount. test the function if the number of items in list was updated after moving them
    to the deep storage locker.
     */
    @Test
    public void testItemCount1(){
        testLocker.addItem(testItem2,2);
        testLocker.addItem(testItem3,1);
        assertEquals(testLocker.getItemCount("helmet, size 1"),0);
    }
    /*
    test the function getitemcount. test if thefunc. works well when item isn't in the list at all
    */
    @Test
    public void testItemCount2(){
        testLocker.addItem(testItem2,2);
        assertEquals(testLocker.getItemCount("spores engine"),0);
    }
    /*
    tests whether a valid insert to the locker would be shown
     */
    @Test
    public void testItemCount3(){
        testLocker.addItem(testItem1,2);
        assertEquals(testLocker.getItemCount("baseball bat"),2);
    }
    /*
    tests the get capacity func, in this case when we fill the locker with items
     */
    @Test
    public void testAvailableCapacity1(){
        testLocker.addItem(testItem1,1);
        testLocker.addItem(testItem2,1);
        testLocker.addItem(testItem3,1);
        assertEquals(testLocker.getAvailableCapacity(),0);
    }
    /*
    tests the get capacity func, specifically here to an empty locker
     */
    @Test
    public void testAvailableCapacity2(){
        testLocker.addItem(testItem4,2);
        assertEquals(testLocker.getAvailableCapacity(),10);
    }
    /*
    test removing more items than that are in the list
     */
    @Test
    public void testRemoveItem1(){
        testLocker.addItem(testItem1,2);
        assertEquals(testLocker.removeItem(testItem1,3),-1);
    }
    /*
    tests calling the remove item func with a negative number
     */
    @Test
    public void testRemoveItem2(){
        testLocker.addItem(testItem1,2);
        assertEquals(testLocker.removeItem(testItem1,-2),-1);
    }
    /*
    tests calling the remove item func with a valid input
     */
    @Test
    public void testRemoveItem3(){
        testLocker.addItem(testItem1,2);
        assertEquals(testLocker.removeItem(testItem1,2),0);
    }
    /*
    test whether the map created in the get inventory func matches the requirements
     */
    @Test
    public void testInventory(){
        testLocker.addItem(testItem1,3);
        testLocker.addItem(testItem2,1);
        testLocker.addItem(testItem1,1);
        Map<String,Integer> testLockerMap = new HashMap<String, Integer>();
        testLockerMap.put("baseball bat",2);
        testLockerMap.put("helmet, size 1",1);
        assertTrue(testLockerMap.equals(testLocker.getInventory()));
    }
    /*
    checking the item constraints when baseball bat is already in the locker
     */
    @Test
    public void testItemConstraints1(){
        testLocker.addItem(testItem5,1);
        assertEquals(this.testLocker.itemsConstraints(testItem1.getType()),-2);
    }
    /*
    checking the item constraints when football is already in the locker
     */
    @Test
    public void testItemConstraints2(){
        testLocker.addItem(testItem1,2);
        assertEquals(this.testLocker.itemsConstraints(testItem5.getType()),-2);
    }
    /*
    checks the item constraints from the add item function
     */
    @Test
    public void testItemConstraints3(){
        testLocker.addItem(testItem5,1);
        assertEquals(this.testLocker.addItem(testItem1,2),-2);
    }
    /*
    checks the item constraints from the add item function
     */
    @Test
    public void testItemConstraints4(){
        testLocker.addItem(testItem5,2);
        assertEquals(this.testLocker.addItem(testItem1,2),0);
    }
}
