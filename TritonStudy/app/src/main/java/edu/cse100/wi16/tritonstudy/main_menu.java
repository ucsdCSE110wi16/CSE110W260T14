package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class main_menu extends AppCompatActivity {

    static final Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");
    Student myStudent; // logged in users student object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Firebase.setAndroidContext(this);

        Log.d("STATE", "main_screen onCreate()");

        Log.d("STATE", "check authentication status");
        //final Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");
        rootRef.addAuthStateListener(new Firebase.AuthStateListener() {

            @Override
            public void onAuthStateChanged(final AuthData authData) {
                if (authData != null) {
                    Log.d("STATE", "User is authenticated");

                    main();

                } else {
                    Log.d("STATE", "User is not authenticated, send to login screen");
                    startActivity(new Intent(main_menu.this, login.class));
                }
            }
        });
    }

    public void main(){


        Firebase userRef = rootRef.child("users/" + rootRef.getAuth().getUid());
        Log.d("DEBUG", "value of userRef = "+userRef.toString());

        Log.d("DEBUG", "Get user student object from firebase");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Student student = dataSnapshot.getValue(Student.class);
                myStudent = student; // makes accessible to other methods

                setGreeting();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("DEBUG", "Could not read from Firebase");
                System.out.println("UpdateLesson error: " + firebaseError.getMessage());
            }
        });
    }


    public void onSearchButtonClick(View button) {
        Log.d("DEBUG", "onSearchButtonClick(), send to search screen");
        startActivity(new Intent(main_menu.this, Search.class));
    }

    public void onProfileButtonClick(View v) {
        Log.d("DEBUG", "onProfileButtonClick(), send to edit profile screen");
        startActivity(new Intent(main_menu.this, edit_user_profile_info.class));
    }

    public void setGreeting(){
        Log.d("DEBUG", "setGreeting(), sets Textview with users name");
        TextView tvGreeting = (TextView) findViewById(R.id.main_greeting);
        String name = "Hello, " +myStudent.getName();
        tvGreeting.setText(name);
    }

    public void onLogOutButtonClick(View v) {
        Log.d("DEBUG", "onLogOutButtonClick()");
        Log.d("DEBUG", "unauthorize user with firebase, send to ");
        rootRef.unauth();
        Toast.makeText(main_menu.this, "Logging out " + myStudent.getName(), Toast.LENGTH_LONG).show();
    }
}
