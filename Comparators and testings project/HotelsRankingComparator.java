import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

public class HotelsRankingComparator implements Comparator<Hotel> {
    /*
    this class implements the comparator interface by adding the compare funcitons.
    in this case, this comparator will differ between hotel object's star ranking, returning a positive number
    for the higher star ranked.
    in case equal, it will return a positive number for if the second hotel comes before the first by the letters considerations.
    else - a negative number. if they are completely equal
     */
    public int compare(Hotel hotel1, Hotel hotel2) {
        if (hotel1.getStarRating() > hotel2.getStarRating()) {
            return -1;
        } else if (hotel1.getStarRating() < hotel2.getStarRating()){
            return 1;
        }
            return hotel1.getPropertyName().compareTo(hotel2.getPropertyName());

    }

}
