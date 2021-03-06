package com.software.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LogInUser extends AppCompatActivity {

    Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/users");

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


    public void MainMenu(View button){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }


    public void logIn(View button) {

        EditText etEmail = (EditText) findViewById(R.id.logIn_email);
        EditText etPassword = (EditText) findViewById(R.id.logIn_password);

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();


        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {

            @Override
            public void onAuthenticated(final AuthData authData) {

                Toast.makeText(LogInUser.this, "User " + authData.getProviderData().get("email") + " is logged in",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(LogInUser.this, "Failed to log in user", Toast.LENGTH_LONG).show();
            }

        });

    }
}
