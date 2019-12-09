package com.ian.calatour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ian.calatour.model.User;
import com.ian.calatour.rest.model.AuthenticationRequest;
import com.ian.calatour.rest.model.AuthenticationResponse;
import com.ian.calatour.rest.service.AuthenticationService;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

    private AuthenticationService authenticationService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://cgisdev.utcluj.ro/moodle/chat-piu/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authenticationService = retrofit.create(AuthenticationService.class);

        AuthenticationRequest authenticationRequest = new AuthenticationRequest("janel", "pass14");
        authenticationService.getLogoutResponse(authenticationRequest).enqueue(new Callback<Void>()
        {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.isSuccessful())
                {
                    System.out.println("LOGOUT successful");
                }
                else
                {
                    int errorStatusCode = response.code();
                    String errorMessage;
                    try
                    {
                        assert response.errorBody() != null;
                        errorMessage = response.errorBody().string();
                    } catch (IOException e)
                    {
                        errorMessage = "Error message cannot be obtained!";
                        e.printStackTrace();
                    }
                    System.out.println(errorStatusCode);
                    System.out.println(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                System.out.println("FAILURE");
            }
        });
    }


    public void singIn(View view)
    {
        EditText usernameEditText = findViewById(R.id.username);
        TextView usernameError = findViewById(R.id.username_error);
        String username = usernameEditText.getText().toString();

        EditText passwordEditText = findViewById(R.id.password);
        TextView passwordError = findViewById(R.id.password_error);
        String password = passwordEditText.getText().toString();

        boolean valid = true;

        if ("".equals(username))
        {
            usernameError.setText("Username cannot be empty!");
            valid = false;
        }
        else if (username.length() < 3)
        {
            usernameError.setText("Username is too short!");
            valid = false;
        }
        else
        {
            usernameError.setText("");
        }

        if ("".equals(password))
        {
            passwordError.setText("Password cannot be empty!");
            valid = false;
        }
        else if (password.length() < 6)
        {
            passwordError.setText("Password is too short!");
            valid = false;
        }
        else
        {
            passwordError.setText("");
        }

        if (valid)
        {
            TextView credentialsError = findViewById(R.id.credentials_error);

            AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);

            Intent intent = new Intent(this, OfferListActivity.class);

            authenticationService.getAuthenticationResponse(authenticationRequest).enqueue(new Callback<AuthenticationResponse>()
            {
                @Override
                public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response)
                {
                    if (response.isSuccessful())
                    {
                        AuthenticationResponse.setInstance(response.body());
                        System.out.println(AuthenticationResponse.getInstance());

                        credentialsError.setText("Login successful!");
                        credentialsError.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));

                        User user = User.getInstance();
                        user.setUsername(username);
                        user.setPassword(password);

                        startActivity(intent);
                    }
                    else
                    {
                        credentialsError.setText("Login failed. Username or password are incorrect!");
                        credentialsError.setTextColor(Color.RED);

                        int errorStatusCode = response.code();
                        String errorMessage;
                        try
                        {
                            assert response.errorBody() != null;
                            errorMessage = response.errorBody().string();
                        } catch (IOException e)
                        {
                            errorMessage = "Error message cannot be obtained!";
                            e.printStackTrace();
                        }
                        System.out.println(errorStatusCode);
                        System.out.println(errorMessage);
                    }
                }

                @Override
                public void onFailure(Call<AuthenticationResponse> call, Throwable t)
                {
                    System.out.println("Internet error connection or bad request");
                }
            });

//            if ("admin".equals(username) && "password".equals(password))
//            {
//                credentialsError.setText("Login successful!");
//                credentialsError.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
////                credentialsError.setTextColor(Color.GREEN);
//
//                User user = User.getInstance();
//                user.setUsername(username);
//                user.setPassword(password);
//
//                Intent intent = new Intent(this, OfferListActivity.class);
//                startActivity(intent);
//            }
//            else
//            {
//                credentialsError.setText("Login failed. Username or password are incorrect!");
//                credentialsError.setTextColor(Color.RED);
//            }
        }
    }
}
