package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class edit_user_profile_info extends AppCompatActivity {

    Student myStudent; // logged in users student object

    // TODO: Change bio text orientation, should be top left

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile_info);
        Firebase.setAndroidContext(this);

        Log.d("STATE", "edit_user_profile_info onCreate()");

        Log.d("STATE", "check authentication status");

        main_menu.rootRef.addAuthStateListener(new Firebase.AuthStateListener() {

            @Override
            public void onAuthStateChanged(final AuthData authData) {
                if (authData != null) {
                    Log.d("STATE", "User is authenticated");

                    main();

                } else {
                    Log.d("STATE", "User is not authenticated, send to login screen");
                    startActivity(new Intent(edit_user_profile_info.this, login.class));
                }
            }
        });
    }

    public void main() {


        Firebase userRef = main_menu.rootRef.child("users/" + getAuth().getUid());
        Log.d("DEBUG", "value of userRef = " + userRef.toString());

        Log.d("DEBUG", "Get user student object from firebase");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Student student = dataSnapshot.getValue(Student.class);
                myStudent = student; // makes accessible to other methods

                populateFields();


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("DEBUG", "Could not read from Firebase");
                System.out.println("UpdateLesson error: " + firebaseError.getMessage());
            }
        });
    }

    // returns the firebase authentication of user.
    public AuthData getAuth() {
        AuthData authData = main_menu.rootRef.getAuth();

        return authData;
    }

    public void onSaveButtonClick(View v) {

        Log.d("DEBUG", "onSaveButtonClick()");

        Log.d("DEBUG", "connect to edit texts");
        EditText etName = (EditText) findViewById(R.id.edit_user_profile_info_etName);
        EditText etEmail = (EditText) findViewById(R.id.edit_user_profile_info_etEmail);
        EditText etPhone = (EditText) findViewById(R.id.edit_user_profile_info_etPhone);
        EditText etBio = (EditText) findViewById(R.id.edit_user_profile_info_etBio);

        Log.d("DEBUG", "save value of edit texts");
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String bio = etBio.getText().toString();

        String uid = main_menu.rootRef.getAuth().getUid().toString();
        Log.d("DEBUG", "UID = " + uid);

        Firebase userRef = main_menu.rootRef.child("users/" + uid);
        Log.d("DEBUG", "User reference =" + userRef);

        Log.d("DEBUG", "put changed value in map");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("phoneNumber", phone);
        map.put("bio", bio);

        Log.d("DEBUG", "update user info");
        userRef.updateChildren(map);

        Toast.makeText(edit_user_profile_info.this, "Saved Profile for " + myStudent.getName(),
                Toast.LENGTH_LONG).show();

        Log.d("DEBUG", "Send to main menu");
        startActivity(new Intent(edit_user_profile_info.this, main_menu.class));
    }

    public void onEditCoursesButtonClick(View v) {
        startActivity(new Intent(edit_user_profile_info.this, edit_user_courses.class));
    }

    public void onEditStudyTimesButtonClick(View v) {
        startActivity(new Intent(edit_user_profile_info.this, edit_user_study_times.class));
    }

    public void onCancelButtonClick(View v) {
        startActivity(new Intent(edit_user_profile_info.this, main_menu.class));
    }

    public void populateFields() {
        Log.d("DEBUG", "populateFields()");

        Log.d("DEBUG", "populate with users name");
        EditText etName = (EditText) findViewById(R.id.edit_user_profile_info_etName);
        etName.setText(myStudent.getName(), EditText.BufferType.EDITABLE);

        Log.d("DEBUG", "populate with users email");
        EditText etEmail = (EditText) findViewById(R.id.edit_user_profile_info_etEmail);
        etEmail.setText(myStudent.getEmail(), TextView.BufferType.EDITABLE);

        Log.d("DEBUG", "populate with users phone number");
        EditText etPhone = (EditText) findViewById(R.id.edit_user_profile_info_etPhone);
        etPhone.setText(myStudent.getPhoneNumber(), TextView.BufferType.EDITABLE);

        Log.d("DEBUG", "populate with users bio");
        EditText etBio = (EditText) findViewById(R.id.edit_user_profile_info_etBio);
        etBio.setText(myStudent.getBio(), TextView.BufferType.EDITABLE);
    }
}



























      