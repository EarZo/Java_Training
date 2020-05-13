package com.eranda;

import com.eranda.offering.Kids;
import com.eranda.offering.Offer;
import com.eranda.offering.PartyCombo;
import com.eranda.offering.TripleThreat;

public class Order {

    public static Offer getOffer(OfferType offerType) {

        switch (offerType) {
            case KIDS:
                return new Kids();
            case TRIPLE_THREAT:
                return new TripleThreat();
            case PARTY_COMBO:
                return new PartyCombo();
            default:
                return null;
        }
    }
}
