package com.example.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.firebaselogin.auth.FireBaseManager;

public class MainActivity extends AppCompatActivity {

    private ImageView beanImage;
    private Button signUp;
    private Button signIn;
    private Button logout;
    private EditText emailText;
    private EditText passwordText;
    private FireBaseManager fireBaseManager = new FireBaseManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning.
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        fireBaseManager = new FireBaseManager();

        signUp = findViewById(R.id.signUpButton);
        signIn = findViewById(R.id.signInButton);

        logout = findViewById(R.id.logOutButton);
        logout.setVisibility(View.GONE); // Initial state of logout is invisible

        beanImage = findViewById(R.id.beanImage);

    }

    public void signIn(View view){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.length() > 0 && password.length() > 0){ //Checks for more that 0 chars
            fireBaseManager.signIn(email,password,this); //Signin method from firebasemanager class
            logout.setVisibility(View.VISIBLE);
            emailText.setVisibility(View.GONE);
            passwordText.setVisibility(View.GONE);
            signIn.setVisibility(View.GONE);
            signUp.setVisibility(View.GONE);
            beanImage.setVisibility(View.VISIBLE);

            Intent myIntent = new Intent(MainActivity.this, secretActivity.class);

            MainActivity.this.startActivity(myIntent);

        }


    }

    public void signUp(View view){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.length() > 0 && password.length() > 0){ //Checks for more that 0 chars
            fireBaseManager.signUp(email,password,this); //Same as above, just signup.
        }


    }

    public void logOut(View view){
        fireBaseManager.logOut();
        logout.setVisibility(View.GONE); //state of logout is invisible after logout
        emailText.setVisibility(View.VISIBLE);
        passwordText.setVisibility(View.VISIBLE);
        signUp.setVisibility(View.VISIBLE);
        signIn.setVisibility(View.VISIBLE);
        beanImage.setVisibility(View.GONE);
    }
}
