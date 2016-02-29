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

import java.util.Map;

public class CreateUserInfo extends AppCompatActivity {


    Student student = new Student();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_info);
        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

        //Submit Button
        Button submitButton = (Button) findViewById(R.id.buttonNext);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //code to push to firebase
                EditText etName = (EditText) findViewById(R.id.editTextNameFirst);
                EditText etId = (EditText) findViewById(R.id.editTextID);
                EditText etEmail = (EditText) findViewById(R.id.editTextEmail);
                EditText etPhone = (EditText) findViewById(R.id.editTextPhone);
                EditText etMajor = (EditText) findViewById(R.id.editTextMajor);
                EditText etPassword = (EditText) findViewById(R.id.editTextPassword);

                student.setName(etName.getText().toString());
                student.setStudentId(etId.getText().toString());
                student.setEmail(etEmail.getText().toString());
                student.setPhoneNumber(etPhone.getText().toString());
                student.setMajor(etMajor.getText().toString());
                student.setPassword(etPassword.getText().toString());

                String email = student.getEmail();
                String password = student.getPassword();
                ref.child("users/"+student.getName());


                ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {

                        ref.child("users/"+student.getName()).setValue(student);

                        System.out.println("Successfully created user account for " +student.getName());
                        Toast.makeText(CreateUserInfo.this, "Successfully created user account for " + student.getName(),
                                       Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(CreateUserInfo.this, "Shit is Broke!", Toast.LENGTH_LONG).show();
                    }
                });

                startActivity(new Intent(CreateUserInfo.this, CreateUserClassesActivity.class));
            }
        });
    }
}
