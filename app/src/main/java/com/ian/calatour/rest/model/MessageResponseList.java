package com.ian.calatour.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageResponseList
{
    @SerializedName("messages")
    private List<MessageResponse> messages;

    public MessageResponseList(List<MessageResponse> messages)
    {
        this.messages = messages;
    }

    public List<MessageResponse> getMessages()
    {
        return messages;
    }

    public void setMessages(List<MessageResponse> messages)
    {
        this.messages = messages;
    }
}
