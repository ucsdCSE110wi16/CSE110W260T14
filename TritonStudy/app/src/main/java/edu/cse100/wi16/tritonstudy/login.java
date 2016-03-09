package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class login extends AppCompatActivity {


    // TODO: setup forgot password button

    // firebase database reference
    Firebase rootRef;

    // sets course array for entire app
    final static String[] chooseCoursesArray = new String[]{"Choose Course","CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
            "CSE 12", "CSE 15L", "CSE 30", "CSE 20", "CSE 21", "CSE 100", "CSE 101", "CSE 105",
            "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
            "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
            "CSE 167", "CSE 168", "CSE 169", "CSE 182"};

    final static String[] noneCoursesArray = new String[]{"None","CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
            "CSE 12", "CSE 15L", "CSE 30", "CSE 20", "CSE 21", "CSE 100", "CSE 101", "CSE 105",
            "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
            "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
            "CSE 167", "CSE 168", "CSE 169", "CSE 182"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("DEBUG", "login class reached");

        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // firebase database reference
        rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");



        Log.d("DEBUG", "check for firebase authentication");
        rootRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    Log.d("DEBUG", "user is logged in, go to main screen");
                    startActivity(new Intent(login.this, MainActivity.class));
                } else {
                    Log.d("DEBUG", "user is not logged in");
                }
            }
        });

    }

    public void onNewUserButtonClick(View v){
        Log.d("DEBUG", "new user button pressed");
        startActivity(new Intent(login.this, create_user_profile_info.class));
    }

    public void onSignInButtonClick(View v){
        Log.d("DEBUG", "login button pressed");

        Log.d("DEBUG", "get values of login fields");
        EditText etEmail = (EditText) findViewById(R.id.login_etEmail);
        EditText etPassword = (EditText) findViewById(R.id.login_etPassword);
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        Log.d("DEBUG", "authenticte user");
        rootRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(final AuthData authData) {
                Log.d("DEBUG", "user is authenticated, send to main screen");
                startActivity(new Intent(login.this, MainActivity.class));
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.d("DEBUG", "user is not authenticated, display toast");
                // TODO: change toast text to something better
                Toast.makeText(login.this, "Unable to login, check fields", Toast.LENGTH_LONG).show();
            }

        });
    }


}
