package com.ian.calatour.rest.service;

import com.ian.calatour.rest.model.MessageRequest;
import com.ian.calatour.rest.model.MessageResponseList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

public interface ChatService
{
    @PUT("sendmessage.php")
    @Headers("Content-Type: application/json")
    Call<Void> sendMessage(@Header ("Authorization") String sessionToken, @Body MessageRequest body);

    @GET("readmessages.php")
    @Headers("Content-Type: application/json")
    Call<MessageResponseList> getMessages(@Header ("Authorization") String sessionToken);
}
