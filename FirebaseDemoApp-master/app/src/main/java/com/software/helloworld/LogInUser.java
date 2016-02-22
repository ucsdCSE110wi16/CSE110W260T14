package com.software.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LogInUser extends AppCompatActivity {

    Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_user);

        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                } else {
                    // user is not logged in
                }
            }
        });
    }


    public void logIn(View button){

        EditText etEmail = (EditText) findViewById(R.id.logIn_email);
        EditText etPassword = (EditText) findViewById(R.id.logIn_password);

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();



        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(LogInUser.this, "Successfully logged in user account  with uid: " + authData.getUid().toString() , Toast.LENGTH_LONG).show();
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(LogInUser.this, "Failed to log in user", Toast.LENGTH_LONG).show();
            }
        });
    }


}
