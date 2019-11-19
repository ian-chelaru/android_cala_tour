package com.ian.calatour.services;

import com.ian.calatour.R;
import com.ian.calatour.model.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferService
{
    public static List<Offer> getOffers()
    {
        List<Offer> offers = new ArrayList<>();

        Offer barcelonaOffer = new Offer();
        barcelonaOffer.setId(1);
        barcelonaOffer.setName("Barcelona, 3 nights");
        barcelonaOffer.setDescription("Barcelona description");
        barcelonaOffer.setPrice(300);
        barcelonaOffer.setCurrency("EUR");
        barcelonaOffer.setImage(R.drawable.offer_1);

        Offer maldiveOffer = new Offer();
        maldiveOffer.setId(2);
        maldiveOffer.setName("Maldive, 7 nights");
        maldiveOffer.setDescription("Maldive description");
        maldiveOffer.setPrice(1050);
        maldiveOffer.setCurrency("EUR");
        maldiveOffer.setImage(R.drawable.offer_2);

        Offer thailandOffer = new Offer();
        thailandOffer.setId(3);
        thailandOffer.setName("Thailand, 10 nights");
        thailandOffer.setDescription("Thailand description");
        thailandOffer.setPrice(1250);
        thailandOffer.setCurrency("EUR");
        thailandOffer.setImage(R.drawable.offer_3);

        offers.add(barcelonaOffer);
        offers.add(maldiveOffer);
        offers.add(thailandOffer);

        return offers;
    }
}
