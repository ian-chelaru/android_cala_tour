package com.ian.calatour.rest.model;

import com.google.gson.annotations.SerializedName;

public class AuthenticationRequest
{
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public AuthenticationRequest(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
