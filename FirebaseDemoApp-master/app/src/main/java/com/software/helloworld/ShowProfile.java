package com.software.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class ShowProfile extends AppCompatActivity {

    Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        Firebase.setAndroidContext(this);

        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    TextView profile_name = (TextView) findViewById(R.id.show_profile_Name);
                    profile_name.setText(authData.getProviderData().get("name").toString());
                } else {
                    // user is not logged in
                }
            }
        });


    }
}
