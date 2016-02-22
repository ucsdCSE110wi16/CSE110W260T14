package com.software.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class NewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        Firebase.setAndroidContext(this);
    }

    public void submit(View button) {

        EditText etEmail = (EditText) findViewById(R.id.EditTextEmail);
        EditText etPassword = (EditText) findViewById(R.id.EditTextPassword);

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {

                Toast.makeText(NewUser.this, "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(NewUser.this, "There was an error", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void back(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
