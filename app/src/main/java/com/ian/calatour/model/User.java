package com.ian.calatour.model;

public class User
{
    private String username;
    private String password;

    private static User user;

    public static User getInstance()
    {
        if (user == null)
        {
            user = new User();
        }
        return user;
    }

    public void init(String username, String password)
    {
        user.username = username;
        user.password = password;
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
