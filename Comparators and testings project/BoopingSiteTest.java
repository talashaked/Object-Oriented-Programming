import java.lang.String;
import java.util.Arrays;

import oop.ex3.searchengine.Hotel;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
A testing class for Booping Site.
 */
public class BoopingSiteTest {
    private static BoopingSite hotelsChecklist;
    private static BoopingSite hotelsChecklistEmpty;
    private static String dataset1 = "hotels_tst1.txt";
    private static String dataset3 = "hotels_tst2.txt";
    private double testLatitude = -50;
    private double testLongitude = 50;
    private double InvalidLatitude = -95;
    private double InvalidLongitude = 181;
    private String InvalidCity = "TelAviv";
    private String cityTest1 = "manali";
    private String cityTest2 = "goa";


    @BeforeClass
    public static void reArrangeList(){
        hotelsChecklist = new BoopingSite(dataset1);
        hotelsChecklistEmpty = new BoopingSite(dataset3);
    }

    ///////////////////////////////////
    //   GENERAL TESTING FUNCS       //
    ///////////////////////////////////

    /*
    a general function to generate a location from the objects testLongitude and testLatitude
     */
    public double calculateDistance(double longitude,double latitude){
        return Math.sqrt(Math.pow((latitude-this.testLatitude),2)+
                Math.pow((longitude-this.testLongitude),2));
    }
    /*
    this function get as an input an assumed sorted array, and checks if the array is sorted as wanted.
    in this case the sort that is checked is whether the star rating of each object is sorted from high to low,
    and when equal by ABC order (from first letters to last).
    */
    public boolean checkSortByRating(Hotel[] arrToCheck){
        int nextRank;
        int previoiusRank = arrToCheck[0].getStarRating();
        for (int i=1;i<arrToCheck.length;i++){
            nextRank = arrToCheck[i].getStarRating();
            if(previoiusRank<nextRank){
                return false;
            }
            else if(previoiusRank==nextRank){
                if(arrToCheck[i-1].getPropertyName().compareTo(arrToCheck[i].getPropertyName())>0){
                    return false;
                }
            }
            previoiusRank = nextRank;
            }
        return true;
    }
    /*
        this function get as an input an assumed sorted array, and checks if the array is sorted as wanted.
        in this case the sort that is checked is whether the objects are sorted from smallest distance, to highest.
        when distances are equal, the sort should be by number of close POI's, now from high to low.
    */
    public boolean checkSortByDistance(Hotel[] arrToCheck){
        double currentDistance;
        double previousDistance = calculateDistance(arrToCheck[0].getLongitude(),arrToCheck[0].getLatitude());
        int previousNumOfPOI = arrToCheck[0].getNumPOI();
        for (Hotel item: arrToCheck){
            currentDistance = calculateDistance(item.getLongitude(),item.getLatitude());
            if (currentDistance == previousDistance){
                if (previousNumOfPOI<item.getNumPOI()){
                    return false;
                }
            }
            else{
                if (previousDistance>currentDistance){
                    return false;
                }
            }
            previousDistance = currentDistance;
            previousNumOfPOI = item.getNumPOI();
        }
        return true;
    }

        /*
        this function tests the getHotelsInCityByRating function, which sorts a Hotel list by a city, and then
        by ranking. this function test that the given back array is sorted by the hotels rating.
         */
    @Test
    public void TestHotelsByRating1(){
        Hotel[] sortedList = hotelsChecklist.getHotelsInCityByRating(cityTest1);
        assertTrue(checkSortByRating(sortedList));
    }

    /*
    this function tests the getHotelsInCityByRating function, which sorts a Hotel list by a city, and then
    by ranking. this function test that the given back array is sorted by the hotels rating.
     */
    @Test
    public void TestHotelsByRating2(){
        Hotel[] sortedList = hotelsChecklist.getHotelsInCityByRating(cityTest1);
        for (int i=1;i<sortedList.length;i++){
            assertTrue(sortedList[i].getCity().equals(cityTest1));
        }
    }
    /*
    test the same function for when a city with no hotels is sent as an input. the wanted output would be an empty
    list
     */
    @Test
    public void TestHotelsByRanking3(){
        Hotel[] sortedList = hotelsChecklist.getHotelsInCityByRating(InvalidCity);
        Hotel[] emptyList = new Hotel[0];
        assertArrayEquals(sortedList,emptyList);

    }
    /*
    this test checks the hotel by ranking function when creating a boopingsite object with an empty file.
     */
    @Test
    public void TestHotelsByRanking4(){
        Hotel[] sortedList = hotelsChecklistEmpty.getHotelsInCityByRating(cityTest1);
        Hotel[] emptyList = new Hotel[0];
        assertArrayEquals(sortedList,emptyList);

    }
    /*
    tests the second function of booping site. this function tests that the output list would be sorted by an increasing
    distance range, and additionally checks that if there are equal distanced sites, that they are ranked
    from the highest POI each of them have.
     */
    @Test
    public void TestHotelsByProximity1(){
        Hotel[] sortedList = hotelsChecklist.getHotelsByProximity(this.testLatitude,this.testLongitude);
        assertTrue(checkSortByDistance(sortedList));
    }
    /*
    test the same function (getHotelsByProximity) - in this case we send the function a non existing testLatitude
     */
    @Test
    public void TestHotelsByProximity3(){
        Hotel[] newEmptyList = new Hotel[0];
        assertArrayEquals(hotelsChecklist.getHotelsByProximity(95,50),newEmptyList);

    }
    /*
    test the same function (getHotelsByProximity) - in this case we send the function a non existing testLongitude
     */
    @Test
    public void TestHotelsByProximity4(){
        Hotel[] newEmptyList = new Hotel[0];
        assertArrayEquals(hotelsChecklist.getHotelsByProximity(50,-186),newEmptyList);
    }
    /*
    test the same function (getHotelsByProximity) - in this case we send the function a non existing testLongitude
     */
    @Test
    public void TestHotelsByProximity5(){
        Hotel[] newEmptyList = new Hotel[0];
        assertArrayEquals(hotelsChecklistEmpty.getHotelsByProximity(testLatitude,testLongitude),newEmptyList);
    }
    /*
    testing getHotelsInCityByProximity with valid inputs. this test checks that all hotels in list are in the city
     */
    @Test
    public void TestHotelsInCityByProximity1(){
        Hotel[] newSortedList = hotelsChecklist.getHotelsInCityByProximity(cityTest2, testLatitude, testLongitude);
        for (int i=1;i<newSortedList.length;i++){
            assertTrue(newSortedList[i].getCity().equals(cityTest2));
        }

    }
    /*
    testing getHotelsInCityByProximity with valid inputs. this test checks that all hotels in list are sorted by distance
    and that all equal distanced hotels are sorted by number of POI's
    */
    @Test
    public void TestHotelsInCityByProximity2() {
        Hotel[] sortedList = hotelsChecklist.getHotelsInCityByProximity(cityTest1, testLatitude, testLongitude);
        assertTrue(checkSortByDistance(sortedList));
    }
    /*
    testing getHotelsInCityByProximity with an invalid city
     */
    @Test
    public void TestHotelsInCityByProximity4(){
        Hotel[] newSortedList = hotelsChecklist.getHotelsInCityByProximity(InvalidCity, testLatitude, testLongitude);
        Hotel[] newEmptyList = new Hotel[0];
        assertTrue(Arrays.equals(newEmptyList,newSortedList)==true);
    }
    /*
    testing getHotelsInCityByProximity with an invalid testLongitude
     */
    @Test
    public void TestHotelsInCityByProximity5(){
        Hotel[] newSortedList = hotelsChecklist.getHotelsInCityByProximity(cityTest1,InvalidLatitude, testLongitude);
        Hotel[] newEmptyList = new Hotel[0];
        assertTrue(Arrays.equals(newEmptyList,newSortedList)==true);
    }
    /*
    testing getHotelsInCityByProximity with an invalid testLatitude
     */
    @Test
    public void TestHotelsInCityByProximity6(){
        Hotel[] newSortedList = hotelsChecklist.getHotelsInCityByProximity(cityTest1, testLatitude,InvalidLongitude);
        Hotel[] newEmptyList = new Hotel[0];
        assertTrue(Arrays.equals(newEmptyList,newSortedList)==true);
    }
    /*
    testing getHotelsInCityByProximity with an empty hotel list.
        */
    @Test
    public void TestHotelsInCityByProximity7() {
        Hotel[] sortedList = hotelsChecklistEmpty.getHotelsInCityByProximity(cityTest1, testLatitude, testLongitude);
        assertArrayEquals(this.hotelsChecklistEmpty.hotelsArr, sortedList);
    }


}

