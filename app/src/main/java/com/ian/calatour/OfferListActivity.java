package com.ian.calatour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ian.calatour.list.adapters.OffersAdapter;
import com.ian.calatour.model.Offer;
import com.ian.calatour.services.OfferService;

public class OfferListActivity extends AppCompatActivity implements DialogInterface.OnClickListener
{
    private static final int VIEW_OFFER_REQUEST = 1;
    private static int times = 0;

    private OffersAdapter offersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_list);

        setTitle(R.string.offers_list);

        ListView listViewReference = findViewById(R.id.offers_list_view);
        offersAdapter = new OffersAdapter(this, OfferService.getOffers());
        listViewReference.setAdapter(offersAdapter);

        listViewReference.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, OfferViewActivity.class);
            intent.putExtra("offer", (Offer) listViewReference.getItemAtPosition(position));
            times += 1;
            intent.putExtra("times", times);
            startActivityForResult(intent, VIEW_OFFER_REQUEST);
        });

        registerForContextMenu(listViewReference);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.sign_out_id:
            {
                showLogOutDialog();
                break;
            }
            case R.id.reset_list_id:
            {
                offersAdapter.clear();
                offersAdapter.addAll(OfferService.getOffers());
                offersAdapter.notifyDataSetChanged();
                showResetListToast();
                break;
            }
            case R.id.clear_favorites_id:
            {
                break;
            }
            case R.id.chat_with_us:
                Intent intent = new Intent(this, ChatActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.add_offer_id:
            {
                Offer offer = OfferService.createOffer();
                offersAdapter.insert(offer, info.position);
                offersAdapter.notifyDataSetChanged();
                break;
            }
            case R.id.remove_offer_id:
            {
                Offer offer = offersAdapter.getItem(info.position);
                offersAdapter.remove(offer);
                offersAdapter.notifyDataSetChanged();
                break;
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        showLogOutDialog();
    }

    private void showLogOutDialog()
    {
        AlertDialog.Builder logOutDialog = new AlertDialog.Builder(this);
        logOutDialog.setTitle(R.string.log_out_title)
                .setMessage(R.string.log_out_message)
                .setPositiveButton(R.string.log_out_positive, this)
                .setNegativeButton(R.string.log_out_negative, this)
                .show();
    }

    private void showResetListToast()
    {
        Toast.makeText(this, getResources().getString(R.string.reset_toast), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        switch (which)
        {
            case DialogInterface.BUTTON_NEGATIVE:
            {
                dialog.dismiss();
                break;
            }
            case DialogInterface.BUTTON_POSITIVE:
                dialog.dismiss();
                finish();
                break;
        }
    }
}
