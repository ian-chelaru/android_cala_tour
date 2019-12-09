package com.ian.calatour.rest.model;

import com.google.gson.annotations.SerializedName;

public class MessageResponse
{
    @SerializedName("sender")
    private String sender;

    @SerializedName("text")
    private String text;

    @SerializedName("timestamp")
    private String timestamp;

    public MessageResponse(String sender, String text, String timestamp)
    {
        this.sender = sender;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public String toString()
    {
        return "MessageResponse{" +
                "sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
