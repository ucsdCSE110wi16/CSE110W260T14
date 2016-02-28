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

//        EditText etName = (EditText) findViewById(R.id.EditTextName);
//        EditText etId = (EditText) findViewById(R.id.EditTextId);
//        EditText etEmail = (EditText) findViewById(R.id.EditTextEmail);
//        EditText etBio = (EditText) findViewById(R.id.EditTextBio);
//        EditText etMajor = (EditText) findViewById(R.id.EditTextMajor);
//        EditText etPassword = (EditText) findViewById(R.id.EditTextPassword);
//
//        String email = etEmail.getText().toString();
//        String password = etPassword.getText().toString();
//
//        final Firebase myFirebaseRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");
//
//        student.setName(etName.getText().toString());
//        student.setStudentId(etId.getText().toString());
//        student.setEmail(etEmail.getText().toString());
//        student.setBio(etBio.getText().toString());
//        student.setMajor(etMajor.getText().toString());
//        student.setPassword(etPassword.getText().toString());
//
//        myFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
//            @Override
//            public void onSuccess(Map<String, Object> result) {
//
//                String uid = result.get("uid").toString();
//
//                myFirebaseRef.child(uid).setValue(student);
//
//                Toast.makeText(MainActivity.this, "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(FirebaseError firebaseError) {
//                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//
//        Intent output = new Intent();
//        setResult(RESULT_OK, output);
//        finish();
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
