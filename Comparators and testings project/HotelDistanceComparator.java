import oop.ex3.searchengine.Hotel;

public class HotelDistanceComparator implements java.util.Comparator<Hotel> {
    /*
    this class implements the comparator interface by adding the compare functions.
    in this case, this comparator will differ between hotel object's distance from the distance in the constructor,
    returning a negative number if hotel1 is closer, positive if hotel2 is closer, and if they are equal distanced,
    it returns a positive number if hotel2 has more poi's, negative otherwise. if equal 0 would be returned.
     */
    private double longitudeToCompareTo;
    private double latitudeToCompareTo;



    public HotelDistanceComparator(double longitude, double latitude){
        this.latitudeToCompareTo = latitude;
        this.longitudeToCompareTo = longitude;

    }
    /*
    calculating the euclidian distance of the location in the constructor to the hotel in the input
     */
    public double calculateDistance(Hotel hotelToCheck){
        return Math.sqrt(Math.pow((hotelToCheck.getLongitude()-this.longitudeToCompareTo),2)+
                Math.pow((hotelToCheck.getLatitude()-this.latitudeToCompareTo),2));
    }

    public int compare(Hotel hotel1 ,Hotel hotel2){
        if(calculateDistance(hotel1)<calculateDistance(hotel2)){
            return -1;
        }
        else if (calculateDistance(hotel1)>calculateDistance(hotel2)){
            return 1;

        }
        else{
            return hotel2.getNumPOI()-hotel1.getNumPOI();
        }


    }
}
