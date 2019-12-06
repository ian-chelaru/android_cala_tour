package com.ian.calatour.list.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ian.calatour.R;
import com.ian.calatour.model.ChatMessage;
import com.ian.calatour.viewholders.MessageViewHolder;

import java.util.List;

public class ChatMessagesAdapter extends RecyclerView.Adapter<MessageViewHolder>
{
    private List<ChatMessage> objects;
    private Context context;

    public ChatMessagesAdapter(List<ChatMessage> objects, Context context)
    {
        this.objects = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View layout = layoutInflater.inflate(R.layout.message_design1, parent, false);
        return new MessageViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position)
    {
        holder.bindViewHolder(objects.get(position));
    }

    @Override
    public int getItemCount()
    {
        return objects.size();
    }
}
