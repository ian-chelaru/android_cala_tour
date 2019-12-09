package com.ian.calatour.rest.model;

import com.google.gson.annotations.SerializedName;

public class MessageRequest
{
    @SerializedName("message")
    private String message;

    public MessageRequest(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
