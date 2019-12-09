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
import com.ian.calatour.rest.model.AuthenticationResponse;
import com.ian.calatour.rest.model.MessageRequest;
import com.ian.calatour.rest.model.MessageResponseList;
import com.ian.calatour.rest.service.ChatService;
import com.ian.calatour.services.ChatMessageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity
{
    private static int chatMessageId = 1;

    private ChatMessagesAdapter chatMessagesAdapter;
    private String sender;
    private List<ChatMessage> chatMessages;

    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sender = User.getInstance().getUsername();
        chatMessages = ChatMessageService.getInitialMessages();
//        initChatMessages();

        TextView myUsername = findViewById(R.id.my_username);
        myUsername.setText(sender);

        RecyclerView recyclerViewReference = findViewById(R.id.messages_list_view);
        chatMessagesAdapter = new ChatMessagesAdapter(chatMessages, this);
        recyclerViewReference.setAdapter(chatMessagesAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewReference.setLayoutManager(linearLayoutManager);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://cgisdev.utcluj.ro/moodle/chat-piu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        chatService = retrofit.create(ChatService.class);
    }

    public void sendMessage(View view)
    {
        EditText inputMessage = findViewById(R.id.message_input);
        String content = inputMessage.getText().toString();

        AuthenticationResponse authenticationResponse = AuthenticationResponse.getInstance();
        System.out.println(authenticationResponse);
        MessageRequest messageRequest = new MessageRequest(content);
        chatService.sendMessage("Bearer " + authenticationResponse.getToken(), messageRequest).enqueue(new Callback<Void>()
        {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                int responseCode = response.code();
                if (response.isSuccessful())
                {
                    System.out.println("send message status code: " + responseCode);

                    initChatMessages();
                }
                else
                {
                    System.out.println("send message status code: " + responseCode);
                    String errorMessage;
                    try
                    {
                        assert response.errorBody() != null;
                        errorMessage = response.errorBody().string();
                    } catch (IOException e)
                    {
                        errorMessage = "Error message cannot be obtained!";
                        e.printStackTrace();
                    }
                    System.out.println(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                System.out.println("SEND MESSAGE FAILURE");
            }
        });

//        ChatMessage chatMessage = new ChatMessage();
//        chatMessageId += 1;
//        chatMessage.setId(chatMessageId);
//        chatMessage.setSender(sender);
//        chatMessage.setStarImage(R.drawable.star);
//        chatMessage.setContent(content);
//
//        chatMessages.add(0, chatMessage);
//        chatMessagesAdapter.notifyDataSetChanged();
    }

    private void initChatMessages()
    {
        String token = AuthenticationResponse.getInstance().getToken();
        chatService.getMessages("Bearer " + token).enqueue(new Callback<MessageResponseList>()
        {
            @Override
            public void onResponse(Call<MessageResponseList> call, Response<MessageResponseList> response)
            {
                if (response.isSuccessful())
                {
                    System.out.println("SUCCESSFUL");
                    MessageResponseList messages = response.body();
                    messages.getMessages().forEach(m -> {
                        System.out.println(m);
                        ChatMessage chatMessage = new ChatMessage();
                        chatMessageId += 1;
                        chatMessage.setId(chatMessageId);
                        chatMessage.setSender(m.getSender());
                        chatMessage.setStarImage(R.drawable.star);
                        chatMessage.setContent(m.getText());
                        chatMessage.setDate(m.getTimestamp());

                        chatMessages.add(0, chatMessage);
                    });
                    chatMessagesAdapter.notifyDataSetChanged();
                }
                else
                {
                    int errorStatusCode = response.code();
                    String errorMessage;
                    try
                    {
                        assert response.errorBody() != null;
                        errorMessage = response.errorBody().string();
                    } catch (IOException e)
                    {
                        errorMessage = "Error message cannot be obtained!";
                        e.printStackTrace();
                    }
                    System.out.println(errorStatusCode);
                    System.out.println(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<MessageResponseList> call, Throwable t)
            {
                System.out.println("Internet error connection or bad request");
            }
        });
    }
}
