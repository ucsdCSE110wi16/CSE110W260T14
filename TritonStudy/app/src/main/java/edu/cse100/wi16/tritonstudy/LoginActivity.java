package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/users");

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

        //Login Button
        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText etEmail = (EditText) findViewById(R.id.editTextUsername);
                EditText etPassword = (EditText) findViewById(R.id.editTextPassword);

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(final AuthData authData) {

                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                for (DataSnapshot studentSnapshot : snapshot.getChildren()) {
                                    Student authStudent = studentSnapshot.getValue(Student.class);

//                                  Student authStudent = snapshot.child(authData.).getValue(Student.class);
                                    Toast.makeText(LoginActivity.this, "User " + authStudent.getName() + " is logged in",
                                                   Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled (FirebaseError firebaseError){
                            }

                        });


                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(LoginActivity.this, "Failed to log in user", Toast.LENGTH_LONG).show();
                    }

                });



                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        //New User Button
        Button newUserButton = (Button) findViewById(R.id.buttonNewUser);
        newUserButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CreateUserInfo.class));
            }
        });
    }


}
