package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class create_user_profile_info extends AppCompatActivity {

    // TODO: change orientation of bio field, it is currently vertically centered
    // TODO: Move next button to the left, it is currently a bit off screen
    // TODO: Change phone and password fields to match their content
    // TODO: require ucsd.edu in email field.

    final static String PAR_KEY = "edu.cse100.wi16.tritonstudy";


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
        EditText etPasswordConfirm = (EditText) findViewById(R.id.create_user_etPasswordConfirm);

        if ( !(etPassword.getText().toString().matches(etPasswordConfirm.getText().toString()) ) ){
            Toast.makeText(create_user_profile_info.this, "Passwords don't match, please try again", Toast.LENGTH_LONG).show();
        }
        else if ( !(etEmail.getText().toString().contains("@ucsd.edu") ) ){
            Toast.makeText(create_user_profile_info.this, "You must enter an UCSD email address", Toast.LENGTH_LONG).show();
        }
        else {

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
            Intent mIntent = new Intent(this, create_user_choose_classes.class);
            Bundle mBundle = new Bundle();
            mBundle.putParcelable(PAR_KEY, student);
            mIntent.putExtras(mBundle);


            Log.d("DEBUG", "Send student object to next activity");
            startActivity(mIntent);
        }

    }
}
