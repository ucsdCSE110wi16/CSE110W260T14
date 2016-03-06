
package com.software.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class ShowProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
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
                    Log.d("STATE", "authData !=null");
                    Firebase userRef = ref.child("users/"+authData.getUid());
                    Log.d("VARIABLE", userRef.toString());
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String uid = authData.getUid().toString();
                            Log.d("VARIABLE", "uid is: " + uid);
                            Log.d("Attempt 01", "name is: " + dataSnapshot.child("name").getValue().toString());
                            Log.d("Attempt 01", "password is: " + dataSnapshot.child("password").getValue().toString());

                            TextView nameText = (TextView)findViewById(R.id.show_profile_Name);
                            nameText.setText(dataSnapshot.child("name").getValue().toString()
                                    , TextView.BufferType.EDITABLE);

                            TextView idText = (TextView)findViewById(R.id.show_profile_Id);
                            idText.setText(dataSnapshot.child("password").getValue().toString()
                                    , TextView.BufferType.EDITABLE);

                            TextView emailText = (TextView)findViewById(R.id.show_profile_Email);
                            emailText.setText(dataSnapshot.child("email").getValue().toString()
                                    , TextView.BufferType.EDITABLE);

                            TextView majorText = (TextView)findViewById(R.id.show_profile_major);
                            majorText.setText(dataSnapshot.child("major").getValue().toString()
                                    , TextView.BufferType.EDITABLE);

                            TextView bioText = (TextView)findViewById(R.id.show_profile_Bio);
                            bioText.setText(dataSnapshot.child("bio").getValue().toString()
                                    , TextView.BufferType.EDITABLE);

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            System.out.println("UpdateLesson error: " + firebaseError.getMessage());
                        }
                    });
                }

                else {
                    // user is not logged in
                }
            }
        });
    }
}