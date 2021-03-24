import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import oop.ex3.spaceship.*;

import java.util.HashMap;
import java.util.Map;

public class LongTermTest {
    private LongTermStorage testLongTermLocker;
    private Item testItem1 = ItemFactory.createSingleItem("baseball bat");
    private Item testItem2 = ItemFactory.createSingleItem("helmet, size 1");
    private Item testItem3 = ItemFactory.createSingleItem("helmet, size 3");
    private Item testItem4 = ItemFactory.createSingleItem("spores engine");
    private Item testItem5 = ItemFactory.createSingleItem("football");

    @Before
    public void resetLongTerm(){
        testLongTermLocker = new LongTermStorage();
    }

    /*
    tests the add item func when adding a valid input
     */
    @Test
    public void testAddItem1(){
        assertEquals(testLongTermLocker.addItem(testItem2,3),0);
    }
    /*
    tests the add item input while sending an invalid input
     */
    @Test
    public void testAddItem2(){
        assertEquals(testLongTermLocker.addItem(testItem4,101),-1);
    }
    /*
    tests the add item input while sending an invalid input
     */
    @Test
    public void testAddItem3(){
        assertEquals(testLongTermLocker.addItem(testItem4,-1),-1);
    }
    /*
    tests the add item input while sending an invalid input
     */
    @Test
    public void testAddItem4(){
        assertEquals(testLongTermLocker.addItem(testItem4,0),0);
    }
    /*
    chekcs the reset inventory func by checking the capacity of the long term locker after reseting it
     */
    @Test
    public void testResetInventory(){
        testLongTermLocker.addItem(testItem3,5);
        testLongTermLocker.resetInventory();
        assertEquals(testLongTermLocker.getCapacity(),1000);
    }
    /*
    tests theget capacity func after adding certain items to the storage
     */
    @Test
    public void testGetCapacity(){
        testLongTermLocker.addItem(testItem5,4);
        assertEquals(testLongTermLocker.getCapacity(),1000);
    }
    /*
    tests the get available capacity func after adding a certain amount of items
     */
    @Test
    public void testGetAvailableCapacity(){
        testLongTermLocker.addItem(testItem5,4);
        assertEquals(testLongTermLocker.getAvailableCapacity(),984);
    }

    /*
    tests the number of type "football" items in the storage after adding two different types of items
     */
    @Test
    public void testGetItemCount1(){
        testLongTermLocker.addItem(testItem5,4);
        testLongTermLocker.addItem(testItem3,10);
        assertEquals(testLongTermLocker.getItemCount("football"),4);
    }
    /*
    tests the amount of items from a certain type that isn't in the deep storage
     */
    @Test
    public void testGetItemCount2() {
        testLongTermLocker.addItem(testItem5, 4);
        assertEquals(testLongTermLocker.getItemCount("spores engine"), 0);
    }
    /*
    tests the map inventory of the deep storage after adding two different types of items
     */
    @Test
    public void testGetInventory1(){
        testLongTermLocker.addItem(testItem1,4);
        testLongTermLocker.addItem(testItem2,1);
        Map<String,Integer> testLongTermMap = new HashMap<String, Integer>();
        testLongTermMap.put("helmet, size 1",1);
        testLongTermMap.put("baseball bat",4);
        assertTrue(testLongTermMap.equals(testLongTermLocker.getInventory()));
    }
    /*
    tests the map inventory of the deep storage after adding, and then resetting the storage.
     */
    @Test
    public void testGetInventory2(){
        testLongTermLocker.addItem(testItem1,4);
        testLongTermLocker.addItem(testItem2,1);
        testLongTermLocker.resetInventory();
        Map<String,Integer> testLongTermMap = new HashMap<String, Integer>();
        testLongTermMap.put("helmet, size 1",1);
        testLongTermMap.put("baseball bat",4);
        assertFalse(testLongTermMap.equals(testLongTermLocker.getInventory()));
    }

    /*
    tests
     */

}
