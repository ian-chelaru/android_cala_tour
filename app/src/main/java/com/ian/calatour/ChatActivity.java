package com.ian.calatour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ian.calatour.list.adapters.ChatMessagesAdapter;
import com.ian.calatour.model.ChatMessage;
import com.ian.calatour.model.User;
import com.ian.calatour.services.ChatMessageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity
{
    private static int chatMessageId = 1;

    private ChatMessagesAdapter chatMessagesAdapter;
    private String sender;
    private List<ChatMessage> chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sender = User.getInstance().getUsername();
        chatMessages = ChatMessageService.getInitialMessages();

        TextView myUsername = findViewById(R.id.my_username);
        myUsername.setText(sender);

        RecyclerView recyclerViewReference = findViewById(R.id.messages_list_view);
        chatMessagesAdapter = new ChatMessagesAdapter(chatMessages, this);
        recyclerViewReference.setAdapter(chatMessagesAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewReference.setLayoutManager(linearLayoutManager);
    }

    public void sendMessage(View view)
    {
        EditText inputMessage = findViewById(R.id.message_input);
        String content = inputMessage.getText().toString();

        ChatMessage chatMessage = new ChatMessage();
        chatMessageId += 1;
        chatMessage.setId(chatMessageId);
        chatMessage.setSender(sender);
        chatMessage.setStarImage(R.drawable.star);
        chatMessage.setContent(content);
        chatMessage.setDate(new Date());

        chatMessages.add(0, chatMessage);
        chatMessagesAdapter.notifyDataSetChanged();
    }
}
