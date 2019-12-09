package com.ian.calatour.rest.service;

import com.ian.calatour.rest.model.AuthenticationRequest;
import com.ian.calatour.rest.model.AuthenticationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthenticationService
{
    @POST("authenticate.php")
    @Headers("Content-Type: application/json")
    Call<AuthenticationResponse> getAuthenticationResponse(@Body AuthenticationRequest body);

    @HTTP(method = "DELETE", path = "logout.php", hasBody = true)
    @Headers("Content-Type: application/json")
    Call<Void> getLogoutResponse(@Body AuthenticationRequest body);
}
