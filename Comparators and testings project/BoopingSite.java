import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class BoopingSite{
    /*
    this class initializes a booping site object, with a array of hotels. the functions given by this class are sorting
    the hotels or by proximity r by star ranks.
     */
    Hotel[] hotelsArr;
    HotelsRankingComparator rankingComparator = new HotelsRankingComparator();

    public BoopingSite(String name){
        this.hotelsArr = HotelDataset.getHotels(java.lang.String.valueOf(name));

    }
    /*
    this function checks the validity of the inputs latitude and longitude, wether they are in the range of 180 to -180
    and 90 to -90 in accordance.
     */
    private boolean checkValidValues(double longitudeToCompareTo,double latitudeToCompareTo){
        if((longitudeToCompareTo>180)||(longitudeToCompareTo<-180)||(latitudeToCompareTo>90)||(latitudeToCompareTo<-90)){
            return false;
        }
        else{ return true;
        }
    }
    /*
    this function gets a city String as an input, and returns a new array consisting only of hotels in the city.
     */
    private Hotel[] filterHotelByCity(String city){
        ArrayList<Hotel> filteredHotelListByCity = new ArrayList<Hotel>();
        for (int i=0;i<this.hotelsArr.length;i++){
            if(city.equals(hotelsArr[i].getCity())==true){
                filteredHotelListByCity.add(hotelsArr[i]);
            }
        }
        return filteredHotelListByCity.toArray(new Hotel[0]);
    }
    /*
    this function get as an input a hotel array and latitude double, longitude double,
    and sorts the array by distance from the given coordinate.
     */
    public Hotel[] sortHotelsByproximity(Hotel[] hotelsArr, double latitude, double longitude){
        Hotel[] arrToReturn;
        boolean valid = checkValidValues(longitude,latitude);
        if (valid==true) {
            HotelDistanceComparator comparatorByDistance = new HotelDistanceComparator(longitude, latitude);
            Collections.sort(hotelsArr, comparatorByDistance);
            return hotelsArr;
        }
        else{
            return new Hotel[0];
        }
    }
    /*
    this function returns a filtered by city array of hotels, sorted by their star ranks
     */
    public Hotel[] getHotelsInCityByRating(String city){
        Hotel[] arrToReturn;
        arrToReturn = filterHotelByCity(city);
        Arrays.sort(arrToReturn,rankingComparator);
        return arrToReturn;

    }
    /*
    this functions returns the array list of hotels this object has sorted by its proximity from a given object
     */
    public Hotel[] getHotelsByProximity(double latitude,double longitude){
        return sortHotelsByproximity(this.hotelsArr,latitude,longitude);
    }
    /*
    this function returns the array list of hotels saved in this object filtered by the given city, and sorted
    by their proximity from the given coordinate.
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
        Hotel[] arrToReturn;
        arrToReturn = filterHotelByCity(city);
        return sortHotelsByproximity(arrToReturn,latitude,longitude);
    }






}
