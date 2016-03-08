package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class create_user_profile_info extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_profile_info);
        Firebase.setAndroidContext(this);

        Log.d("DEBUG", "create_user_profile_info reached");
    }

    public void onNextButtonClick(View v){
        Log.d("DEBUG", "Next button clicked");

        Log.d("DEBUG", "Connect to EditTexts");
        EditText etName = (EditText) findViewById(R.id.create_user_etName);
        EditText etEmail = (EditText) findViewById(R.id.create_user_etEmail);
        EditText etPhone = (EditText) findViewById(R.id.create_user_etPhone);
        EditText etBio = (EditText) findViewById(R.id.create_user_etBio);
        EditText etPassword = (EditText) findViewById(R.id.create_user_etPassword);
        EditText etPasswordx2 = (EditText) findViewById(R.id.create_user_etPasswordx2);

        // TODO: check that values of passwords are the same, toast if not
        // TODO: require that email contains @ucsd.edu, toast if not

        Log.d("DEBUG", "Create student object from EditTexts");
        Student student = new Student();
        student.setName(etName.getText().toString());
        student.setEmail(etEmail.getText().toString());
        student.setPhoneNumber(etPhone.getText().toString());
        student.setBio(etBio.getText().toString());
        student.setPassword(etPassword.getText().toString());

        Log.d("DEBUG", "Package student object");
        Intent mIntent = new Intent(this,create_user_choose_classes.class);
        Bundle mBundle = new Bundle();
        String PAR_KEY = "edu.cse100.wi16.tritonstudy";
        mBundle.putParcelable(PAR_KEY, student);
        mIntent.putExtras(mBundle);


        Log.d("DEBUG", "Send student object to next activity");
        startActivity(mIntent);

    }
}