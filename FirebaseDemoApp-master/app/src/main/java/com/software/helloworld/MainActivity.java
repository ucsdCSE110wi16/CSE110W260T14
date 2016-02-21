package com.software.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;



public class MainActivity extends AppCompatActivity {

    Student student = new Student();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void submit(View button) {

        EditText etName = (EditText) findViewById(R.id.EditTextName);
        EditText etId = (EditText) findViewById(R.id.EditTextId);
        EditText etEmail = (EditText) findViewById(R.id.EditTextEmail);
        EditText etBio = (EditText) findViewById(R.id.EditTextBio);
        EditText etMajor = (EditText) findViewById(R.id.EditTextMajor);
        EditText etPassword = (EditText) findViewById(R.id.EditTextPassword);

        String name = etName.getText().toString();
        String id = etId.getText().toString();
        String email = etEmail.getText().toString();
        String bio = etBio.getText().toString();
        String major = etMajor.getText().toString();
        String password = etPassword.getText().toString();

        Firebase myFirebaseRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");



        student.setName(name);
        student.setStudentId(id);
        student.setEmail(email);
        student.setBio(bio);
        student.setMajor(major);
        student.setPassword(password);

        myFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {

                result.put("name", student.getName());
                result.put("studentID", student.getStudentId());
                result.put("email", student.getEmail());
                result.put("bio", student.getBio());
                result.put("major", student.getMajor());

                Toast.makeText(MainActivity.this, "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_LONG).show();
            }
        });

        myFirebaseRef.child(student.getStudentId()).setValue(student);

        Intent output = new Intent();
        setResult(RESULT_OK, output);
        finish();
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
