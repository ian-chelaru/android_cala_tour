package com.ian.calatour.rest.model;

import com.google.gson.annotations.SerializedName;

public class AuthenticationResponse
{
    @SerializedName("id")
    private Integer id;

    @SerializedName("token")
    private String token;

    @SerializedName("display")
    private String display;

    private static AuthenticationResponse authenticationResponse;

    public static AuthenticationResponse getInstance()
    {
        if (authenticationResponse == null)
        {
            authenticationResponse = new AuthenticationResponse();
        }
        return authenticationResponse;
    }

    public static void setInstance(AuthenticationResponse instance)
    {
        authenticationResponse = instance;
    }

    public void init(Integer id, String token, String display)
    {
        authenticationResponse.id = id;
        authenticationResponse.token = token;
        authenticationResponse.display = display;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getDisplay()
    {
        return display;
    }

    public void setDisplay(String display)
    {
        this.display = display;
    }

    @Override
    public String toString()
    {
        return "AuthenticationResponse{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", display='" + display + '\'' +
                '}';
    }
}
