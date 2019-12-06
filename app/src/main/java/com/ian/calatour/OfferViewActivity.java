package com.ian.calatour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ian.calatour.model.Offer;

public class OfferViewActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_view);

        fillViews();
    }

    private void fillViews()
    {
        Intent intent = getIntent();

        Offer offer = (Offer) intent.getSerializableExtra("offer");
        int times = intent.getIntExtra("times", -1);

        assert offer != null;
        ((TextView) findViewById(R.id.offer_view_name)).setText(offer.getName());
        ((ImageView) findViewById(R.id.offer_view_image)).setImageResource(offer.getImage());
        ((TextView) findViewById(R.id.offer_view_price)).setText(offer.getPrice() + " " + offer.getCurrency());
        ((TextView) findViewById(R.id.offer_view_description)).setText(offer.getDescription());
        String timesString = "Details page displayed: " + times + " times";
        ((TextView) findViewById(R.id.details_page)).setText(timesString);
    }
}
