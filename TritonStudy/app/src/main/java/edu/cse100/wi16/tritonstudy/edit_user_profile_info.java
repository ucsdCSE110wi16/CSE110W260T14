package edu.cse100.wi16.tritonstudy;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class edit_user_profile_info extends AppCompatActivity {

    // TODO: Change bio text orientation, should be top left

    int CROP_IMAGE = 1;
    int SET_BACKGROUND = 2;
    Uri uri;
    ImageView viewImage;
    Drawable myDrawable;
    Button b;
    private final Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile_info);

        Firebase.setAndroidContext(this);

        Log.d("STATE", "check if user is authenticated");
        rootRef.addAuthStateListener(new Firebase.AuthStateListener() {

            // @Override
            public void onAuthStateChanged(final AuthData authData) {
                if (authData != null) {
                    Log.d("STATE", "User is authenticated");

                    populateFields(authData);

                } else {
                    Log.d("DEBUG", "user is not logged in");
                    startActivity(new Intent(edit_user_profile_info.this, login.class));
                }
            }
        });
    }

    public void onSaveButtonClick(View v){

        Log.d("DEBUG", "onSaveButtonClick()");

        Log.d("DEBUG", "connect to edit texts");
        EditText etName = (EditText) findViewById(R.id.edit_user_profile_info_etName);
        EditText etEmail = (EditText) findViewById(R.id.edit_user_profile_info_etEmail);
        EditText etPhone = (EditText) findViewById(R.id.edit_user_profile_info_etPhone);
        EditText etBio = (EditText) findViewById(R.id.edit_user_profile_info_etBio);

        Log.d("DEBUG", "save value of edit texts");
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone= etPhone.getText().toString();
        String bio = etBio.getText().toString();

        String uid = rootRef.getAuth().getUid().toString();
        Log.d("DEBUG", "UID = " + uid);

        Firebase userRef = rootRef.child("users/"+uid);
        Log.d("DEBUG", "User reference =" + userRef);

        Log.d("DEBUG", "put changed value in map");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("phoneNumber",phone);
        map.put("bio", bio);

        Log.d("DEBUG", "update user info");
        userRef.updateChildren(map);
    }

    public void onEditCoursesButtonClick(View v){
        startActivity(new Intent(edit_user_profile_info.this, edit_user_courses.class));
    }

    public void onEditStudyTimesButtonClick(View v){
        startActivity(new Intent(edit_user_profile_info.this, edit_user_study_times.class));
    }

    public void onCancelButtonClick(View v){
        startActivity(new Intent(edit_user_profile_info.this, main_menu.class));
    }

    public void populateFields(AuthData authData){
        Log.d("DEBUG", "populateFields()");

        Log.d("DEBUG", "set firebase reference to logged in users folder");
        Firebase userRef = rootRef.child("users/" + authData.getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                
                    Student student = dataSnapshot.getValue(Student.class);

                    Log.d("DEBUG", "populate with users name");
                    EditText etName = (EditText) findViewById(R.id.edit_user_profile_info_etName);
                    etName.setText(student.getName() , EditText.BufferType.EDITABLE);

                    Log.d("DEBUG", "populate with users email");
                    EditText etEmail = (EditText) findViewById(R.id.edit_user_profile_info_etEmail);
                    etEmail.setText(student.getEmail(), TextView.BufferType.EDITABLE);

                    Log.d("DEBUG", "populate with users phone number");
                    EditText etPhone = (EditText) findViewById(R.id.edit_user_profile_info_etPhone);
                    etPhone.setText(student.getPhoneNumber(), TextView.BufferType.EDITABLE);

                    Log.d("DEBUG", "populate with users bio");
                    EditText etBio = (EditText) findViewById(R.id.edit_user_profile_info_etBio);
                    etBio.setText(student.getBio(), TextView.BufferType.EDITABLE);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("UpdateLesson error: " + firebaseError.getMessage());
            }
        });
    }

}



























      