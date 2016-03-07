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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CreateUserInfo extends AppCompatActivity {

    Firebase ref;
    public  final static String SER_KEY = "edu.cse100.wi16.tritonstudy";
    public  final static String PAR_KEY = "edu.cse100.wi16.tritonstudy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_info);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

        //Submit Button
//        Button submitButton = (Button) findViewById(R.id.buttonNext);
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                //code to push to firebase
//                EditText etName = (EditText) findViewById(R.id.editTextNameFirst);
//                EditText etEmail = (EditText) findViewById(R.id.editTextEmail);
//                EditText etPhone = (EditText) findViewById(R.id.editTextPhone);
//                EditText etMajor = (EditText) findViewById(R.id.editTextMajor);
//                EditText etPassword = (EditText) findViewById(R.id.editTextPassword);
//
////                HashMap<String, Object> map = new HashMap<String, Object>();
////
////                map.put("name",etName.getText().toString());
////                map.put("email", etEmail.getText().toString());
////                map.put("phone", etPhone.getText().toString());
////                map.put("major", etMajor.getText().toString());
////                map.put("password", etPassword.getText().toString());
//
//                Student student = new Student();
//                student.setName(etName.getText().toString());
//                student.setEmail(etEmail.getText().toString());
//                student.setPhoneNumber(etPhone.getText().toString());
//                student.setMajor(etMajor.getText().toString());
//                student.setPassword(etPassword.getText().toString());
//
//
//
////                Intent intent = new Intent(CreateUserInfo.this, CreateUserClassesActivity.class);
////                intent.putExtra("NewStudent", student);
////                startActivity(intent);
//
//                Intent mIntent = new Intent(CreateUserInfo.this,CreateUserClassesActivity.class);
//
//                Bundle mBundle = new Bundle();
//
//                mBundle.putSerializable(SER_KEY,student);
//
//                Log.d("TEST", student.getEmail());
//
//                mIntent.putExtras(mBundle);
//
//
//
//            }
//        });

    }
    public void submit(View v){


        //code to push to firebase
        EditText etName = (EditText) findViewById(R.id.editTextNameFirst);
        EditText etEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText etPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText etMajor = (EditText) findViewById(R.id.editTextMajor);
        EditText etPassword = (EditText) findViewById(R.id.editTextPassword);
        EditText etBio = (EditText) findViewById(R.id.editTextBio);

        Student student = new Student();
        student.setName(etName.getText().toString());
        student.setEmail(etEmail.getText().toString());
        student.setPhoneNumber(etPhone.getText().toString());
        student.setMajor(etMajor.getText().toString());
        student.setPassword(etPassword.getText().toString());
        student.setBio(etBio.getText().toString());

        Intent mIntent = new Intent(this,CreateUserClassesActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(PAR_KEY, student);
        mIntent.putExtras(mBundle);

        Log.d("TEST", student.getEmail());

        startActivity(mIntent);





    }
}
