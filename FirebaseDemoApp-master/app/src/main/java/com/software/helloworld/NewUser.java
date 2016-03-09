package com.software.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class NewUser extends AppCompatActivity {

    Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");
    Student student; // created here so all methods can access


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        Firebase.setAndroidContext(this);
    }

    public void submit(View button) {

        EditText etName = (EditText) findViewById(R.id.et_NewUserName);
        EditText etEmail = (EditText) findViewById(R.id.et_NewUserEmail);
        EditText etPhone = (EditText) findViewById(R.id.et_NewUserPhone);
        EditText etBio = (EditText) findViewById(R.id.et_NewUserBio);
        EditText etMajor = (EditText) findViewById(R.id.et_NewUserMajor);
        EditText etPassword = (EditText) findViewById(R.id.et_NewUserPassword);

        student.setName(etName.getText().toString());
        student.setEmail(etEmail.getText().toString());
        student.setPhoneNumber(etPhone.getText().toString());
        student.setBio(etBio.getText().toString());
        student.setMajor(etMajor.getText().toString());
        student.setPassword(etPassword.getText().toString());

        String email = student.getEmail();
        String password = student.getPassword();
        ref.child("users/"+student.getName());

        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("DIVIDER", "//////////////////////////////////////////");
                Log.d("USER CREATED", "Successfully created user account for "+ student.getName());

                String uid = result.get("uid").toString();

                // save value to firebase
                ref.child("users").child(uid).setValue(student);

//              ref.child("users/"+student.getName()).setValue(student);

                System.out.println("Successfully created user account for " +student.getName());
                Toast.makeText(NewUser.this, "Successfully created user account for " + student.getName(), Toast.LENGTH_LONG).show(
            );
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(NewUser.this, "Shit is Broke!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
