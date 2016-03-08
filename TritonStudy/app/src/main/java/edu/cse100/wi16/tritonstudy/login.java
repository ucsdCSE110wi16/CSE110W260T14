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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("DEBUG", "login class reached");

        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
