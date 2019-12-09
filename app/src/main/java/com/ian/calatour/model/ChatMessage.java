package com.ian.calatour.model;

import java.util.Date;

public class ChatMessage
{
    private int id;
    private String sender;
    private int starImage;
    private String content;
    private String date;

    public ChatMessage()
    {
    }

    public ChatMessage(int id, String sender, int starImage, String content, String date)
    {
        this.id = id;
        this.sender = sender;
        this.starImage = starImage;
        this.content = content;
        this.date = date;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public int getStarImage()
    {
        return starImage;
    }

    public void setStarImage(int starImage)
    {
        this.starImage = starImage;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
}
