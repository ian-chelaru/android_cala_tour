package com.ian.calatour.services;

import com.ian.calatour.R;
import com.ian.calatour.model.ChatMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatMessageService
{

    public static List<ChatMessage> getInitialMessages()
    {
        List<ChatMessage> chatMessages = new ArrayList<>();

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(1);
        chatMessage.setSender("Computer");
        chatMessage.setStarImage(R.drawable.star);
        chatMessage.setContent("Hi! How can I help you?");
        chatMessage.setDate(new Date());

        chatMessages.add(chatMessage);

        return chatMessages;
    }
}
