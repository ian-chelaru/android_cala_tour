package com.ian.calatour.list.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ian.calatour.R;
import com.ian.calatour.model.Offer;

import java.util.List;

public class OffersAdapter extends ArrayAdapter<Offer>
{
    public OffersAdapter(Context context, List<Offer> offers)
    {
        super(context, 0, offers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View myRow = (convertView == null) ? inflater.inflate(R.layout.offer_list_element, parent, false)
                : convertView;

        Offer offer = getItem(position);

        TextView refOfferName = myRow.findViewById(R.id.offer_name);
        refOfferName.setText(offer.getName());

        ImageView refOfferImage = myRow.findViewById(R.id.offer_image);
        refOfferImage.setImageResource(offer.getImage());

        TextView refOfferPrice = myRow.findViewById(R.id.offer_price);
        refOfferPrice.setText(offer.getPrice() + " " + offer.getCurrency());

        TextView refOfferDescription = myRow.findViewById(R.id.offer_description);
        refOfferDescription.setText(offer.getDescription());

        return myRow;
    }

}
