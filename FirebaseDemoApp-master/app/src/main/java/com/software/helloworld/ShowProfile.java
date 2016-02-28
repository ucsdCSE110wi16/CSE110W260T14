package com.software.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ShowProfile extends AppCompatActivity {

    Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/users");
    Student authStudent = new Student();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Firebase.setAndroidContext(this);
    }

    @Override
    public void onStart() {
        super.onStart();


        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {





                if (authData != null) {

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            for (DataSnapshot studentSnapshot : snapshot.getChildren()) {
                                Student authStudent = studentSnapshot.getValue(Student.class);
                                TextView emailText = (TextView)findViewById(R.id.show_profile_Email);
                                emailText.setText(authStudent.getEmail(), TextView.BufferType.EDITABLE);
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                        }
                    });





                } else {

                }
            }
        });


    }
}
