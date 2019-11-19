package com.ian.calatour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ian.calatour.list.adapters.OffersAdapter;
import com.ian.calatour.model.Offer;
import com.ian.calatour.services.OfferService;

public class OfferListActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_list);

        ListView listViewReference = findViewById(R.id.offers_list_view);
        OffersAdapter offersAdapter = new OffersAdapter(this, OfferService.getOffers());
        listViewReference.setAdapter(offersAdapter);

        registerForContextMenu(listViewReference);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.offers_list_view)
        {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;

            Offer offer = (Offer) ((ListView) v).getAdapter().getItem(info.position);

            menu.setHeaderTitle(offer.getName());

            getMenuInflater().inflate(R.menu.offers_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        return super.onContextItemSelected(item);
    }

}
