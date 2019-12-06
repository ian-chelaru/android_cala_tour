package com.ian.calatour.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ian.calatour.R;
import com.ian.calatour.model.ChatMessage;

public class MessageViewHolder extends RecyclerView.ViewHolder
{
    private View itemView;
    private TextView senderTextView;
    private ImageView starImageView;
    private TextView contentTextView;
    private TextView dateTextView;

    public MessageViewHolder(View itemView)
    {
        super(itemView);
        this.itemView = itemView;
        senderTextView = itemView.findViewById(R.id.message_sender);
        starImageView = itemView.findViewById(R.id.star_image);
        starImageView.setVisibility(View.INVISIBLE);
        contentTextView = itemView.findViewById(R.id.message_content);
        dateTextView = itemView.findViewById(R.id.message_date);
    }

    public void bindViewHolder(ChatMessage chatMessage)
    {
        senderTextView.setText(chatMessage.getSender());
        starImageView.setImageResource(chatMessage.getStarImage());
        contentTextView.setText(chatMessage.getContent());
        dateTextView.setText(chatMessage.getDate().toString());

        itemView.setOnClickListener(v -> {
            starImageView.setVisibility(View.VISIBLE);
        });
    }
}
