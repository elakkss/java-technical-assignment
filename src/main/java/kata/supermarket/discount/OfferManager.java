package kata.supermarket.discount;

import java.util.ArrayList;
import java.util.List;

public class OfferManager {

    public static List<IOffer> OFFERS = new ArrayList<>();

    public static void addOffer(IOffer off) {
        OFFERS.add(off);
    }



}
