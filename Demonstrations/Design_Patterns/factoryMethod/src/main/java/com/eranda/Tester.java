package com.eranda;

import com.eranda.offering.Offer;

public class Tester {

    public static void main(String[] args) {

        Offer offer = Order.getOffer(OfferType.PARTY_COMBO);
        System.out.println(offer);
    }
}
