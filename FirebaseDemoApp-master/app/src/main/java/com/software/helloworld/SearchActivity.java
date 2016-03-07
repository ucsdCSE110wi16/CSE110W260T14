/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.software.helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.simplelogin.SimpleLogin;

import org.w3c.dom.Text;


public class SearchActivity extends ActionBarActivity {

    Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");
    TextView t;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Firebase.setAndroidContext(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("STATE", "onStart");

        final Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");
        ref.addAuthStateListener(new Firebase.AuthStateListener() {

            @Override
            public void onAuthStateChanged(final AuthData authData) {
                if (authData != null) {
                    Log.d("STATE", "User is authenticated");

                    Firebase userRef = ref.child("users/" + authData.getUid());
                    Log.d("User Ref", userRef.toString());

                    t = (TextView) findViewById(R.id.AuthStatus);

                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Log.d("SnapShot Name", dataSnapshot.child("name").getValue().toString());

                            String loggedIn = dataSnapshot.child("name").getValue().toString()
                            + " is logged in.";
                            t.setText(loggedIn);
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            System.out.println("UpdateLesson error: " + firebaseError.getMessage());
                        }
                    });
                } else {
                    // user is not logged in
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onSearchButtonClick(View button)
    {
        Intent intent = new Intent(this,SearchProfiles.class);
        startActivity(intent);


    }

    public void newUser(View button) {
        Intent intent = new Intent(this,NewUser.class);
        startActivity(intent);
    }

    public void logInUser(View button) {
        Intent intent = new Intent(this,LogInUser.class);
        startActivity(intent);
    }

    public void logOutUser(View button) {
        ref.unauth();
        AuthData authData = ref.getAuth();
        if (authData != null) {
            Toast.makeText(SearchActivity.this, "Failed to log out", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SearchActivity.this, "Successfully logged out user", Toast.LENGTH_LONG).show();
            String logOut = "No one is logged in";
            t.setText(logOut);
        }


    }


    public void showProfile(View button){
        Intent intent = new Intent(this, ShowProfile.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
