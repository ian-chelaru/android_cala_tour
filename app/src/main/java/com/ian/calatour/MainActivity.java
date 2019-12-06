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

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            if ("admin".equals(username) && "password".equals(password))
            {
                credentialsError.setText("Login successful!");
                credentialsError.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
//                credentialsError.setTextColor(Color.GREEN);

                User user = User.getInstance();
                user.setUsername(username);
                user.setPassword(password);

                Intent intent = new Intent(this, OfferListActivity.class);
                startActivity(intent);
            }
            else
            {
                credentialsError.setText("Login failed. Username or password are incorrect!");
                credentialsError.setTextColor(Color.RED);
            }
        }
    }
}
