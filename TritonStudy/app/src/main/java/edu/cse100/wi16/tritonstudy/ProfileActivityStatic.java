package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ProfileActivityStatic extends AppCompatActivity {
//    TextView emailStatic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_static);

        Button profileEdit = (Button) findViewById(R.id.profileEdit);
        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivityStatic.this, edit_user_profile_info.class));
//                emailStatic = (TextView) findViewById(R.id.emailStatic);
//                emailStatic.setTextKeepState("Hello");
            }
        });

        Button buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivityStatic.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Method", "onStart");

        final Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");
        ref.addAuthStateListener(new Firebase.AuthStateListener() {

            @Override
            public void onAuthStateChanged(final AuthData authData) {
                if (authData != null) {
                    Log.d("STATE", "User is authenticated");

                    Firebase userRef = ref.child("users/" + authData.getUid());

                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            TextView tName = (TextView)findViewById(R.id.profileStaticName);
                            tName.setText(dataSnapshot.child("name").getValue().toString()
                                    , TextView.BufferType.EDITABLE);

                            TextView tEmail = (TextView)findViewById(R.id.profileStaticEmail);
                            tEmail.setText(dataSnapshot.child("email").getValue().toString()
                                    , TextView.BufferType.EDITABLE);

                            TextView tMajor = (TextView)findViewById(R.id.profileStaticMajor);
                            tMajor.setText(dataSnapshot.child("major").getValue().toString()
                                    , TextView.BufferType.EDITABLE);

                            TextView tBio = (TextView)findViewById(R.id.profileStaticBio);
                            tBio.setText(dataSnapshot.child("bio").getValue().toString(),
                                    TextView.BufferType.EDITABLE);
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



}
