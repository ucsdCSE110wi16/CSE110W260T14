package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class create_user_profile_info extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_profile_info);
        Firebase.setAndroidContext(this);
    }

    public void onNextButtonClick(View v){


        // connect to edittexts
        EditText etName = (EditText) findViewById(R.id.create_user_etName);
        EditText etEmail = (EditText) findViewById(R.id.create_user_etEmail);
        EditText etPhone = (EditText) findViewById(R.id.create_user_etPhone);
        EditText etBio = (EditText) findViewById(R.id.create_user_etBio);
        EditText etPassword = (EditText) findViewById(R.id.create_user_etPassword);
        EditText etPasswordx2 = (EditText) findViewById(R.id.create_user_etPasswordx2);

        // create student with content from editTexts
        Student student = new Student();
        student.setName(etName.getText().toString());
        student.setEmail(etEmail.getText().toString());
        student.setPhoneNumber(etPhone.getText().toString());
        student.setBio(etBio.getText().toString());
        student.setPassword(etPassword.getText().toString());

        // Package student object
        Intent mIntent = new Intent(this,create_user_choose_classes.class);
        Bundle mBundle = new Bundle();
        String PAR_KEY = "edu.cse100.wi16.tritonstudy";
        mBundle.putParcelable(PAR_KEY, student);
        mIntent.putExtras(mBundle);

        // Send student to next activity
        startActivity(mIntent);

    }
}
