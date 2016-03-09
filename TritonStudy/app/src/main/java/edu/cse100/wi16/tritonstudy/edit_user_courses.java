package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class edit_user_courses extends AppCompatActivity {

    // TODO: Change cancel button to red
    // TODO: Change save changes button to green, resize.

    final static Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

    // sets course array for entire app
    final static String[] chooseCoursesArray = new String[]{"Choose Course","CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
            "CSE 12", "CSE 15L", "CSE 30", "CSE 20", "CSE 21", "CSE 100", "CSE 101", "CSE 105",
            "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
            "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
            "CSE 167", "CSE 168", "CSE 169", "CSE 182"};

    final static String[] noneCoursesArray = new String[]{"None","CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
            "CSE 12", "CSE 15L", "CSE 30", "CSE 20", "CSE 21", "CSE 100", "CSE 101", "CSE 105",
            "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
            "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
            "CSE 167", "CSE 168", "CSE 169", "CSE 182"};

    Student student;
    String class1 = "";
    String class2 = "";
    String class3 = "";
    String class4 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("DEBUG", "create_user_choose_classes reached");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_courses);
        Firebase.setAndroidContext(this); //required for firebase

        Log.d("DEBUG", "check authentication status");
        rootRef.addAuthStateListener(new Firebase.AuthStateListener() {

            @Override
            public void onAuthStateChanged(final AuthData authData) {
                if (authData != null) {
                    Log.d("DEBUG", "User is authenticated");

                    Firebase userRef = rootRef.child("users/" + authData.getUid());
                    Log.d("DEBUG", userRef.toString());

                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Log.d("DEBUG", "get user student object");
                            student = dataSnapshot.getValue(Student.class);

                            Log.d("DEBUG", "extract current course choices");
                            class1 = student.getClass1();
                            class2 = student.getClass2();
                            class3 = student.getClass3();
                            class4 = student.getClass4();

                            setSpinnerValues();
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            System.out.println("UpdateLesson error: " + firebaseError.getMessage());
                        }
                    });
                } else {
                    Log.d("DEBUG", "User is not logged in");
                    startActivity(new Intent(edit_user_courses.this, login.class));

                }
            }
        });
    }

    public void setSpinnerValues() {
        Log.d("DEBUG", "Set values of spinners displaying course list");

        Log.d("DEBUG", "Set values for Class 1");
        Spinner spClass1 = (Spinner) findViewById(R.id.edit_user_courses_spClass1);
        ArrayAdapter<String> adClass1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chooseCoursesArray);
        spClass1.setAdapter(adClass1);

        Log.d("DEBUG", "Set values for Class 2");
        Spinner spClass2 = (Spinner) findViewById(R.id.edit_user_courses_spClass2);
        ArrayAdapter<String> adClass2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noneCoursesArray);
        spClass2.setAdapter(adClass2);

        Log.d("DEBUG", "Set values for Class 3");
        Spinner spClass3 = (Spinner) findViewById(R.id.edit_user_courses_spClass3);
        ArrayAdapter<String> adClass3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noneCoursesArray);
        spClass3.setAdapter(adClass3);

        Log.d("DEBUG", "Set values for Class 4");
        Spinner spClass4 = (Spinner) findViewById(R.id.edit_user_courses_spClass4);
        ArrayAdapter<String> adClass4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noneCoursesArray);
        spClass4.setAdapter(adClass4);

        Log.d("DEBUG", "Set value for users current Class 1, " + class1);
        int class1Position = adClass1.getPosition(class1);
        spClass1.setSelection(class1Position);

        Log.d("DEBUG", "Set value for users current Class 2, " + class2);
        int class2Position = adClass2.getPosition(class2);
        spClass2.setSelection(class2Position);

        Log.d("DEBUG", "Set value for users current Class 3, " + class3);
        int class3Position = adClass3.getPosition(class3);
        spClass3.setSelection(class3Position);

        Log.d("DEBUG", "Set value for users current Class 4, " + class4);
        int class4Position = adClass4.getPosition(class4);
        spClass4.setSelection(class4Position);
    }

    private void getSpinnerValues() {

        Log.d("DEBUG", "update values of spinners");

        Log.d("DEBUG", "Get values of class 1");
        Spinner spClass1 = (Spinner) findViewById(R.id.edit_user_courses_spClass1);
        class1 = spClass1.getSelectedItem().toString();
        Log.d("DEBUG", "The values of class 1 is " + student.getClass1());

        Log.d("DEBUG", "Get values of class 2");
        Spinner spClass2 = (Spinner) findViewById(R.id.edit_user_courses_spClass2);
        class2 = spClass2.getSelectedItem().toString();
        Log.d("DEBUG", "The values of class 2 is " + student.getClass2());

        Log.d("DEBUG", "Get values of class 3");
        Spinner spClass3 = (Spinner) findViewById(R.id.edit_user_courses_spClass3);
        class3 = spClass3.getSelectedItem().toString();
        Log.d("DEBUG", "The values of class 3 is " + student.getClass3());

        Log.d("DEBUG", "Get values of class 4");
        Spinner spClass4 = (Spinner) findViewById(R.id.edit_user_courses_spClass4);
        class4 = spClass4.getSelectedItem().toString();
        Log.d("DEBUG", "The values of class 4 is " + student.getClass4());
    }

    public void onSaveChangesButtonClick(View v) {

        Log.d("DEBUG", "onSaveChangesButtonClick()");

        String uid = rootRef.getAuth().getUid().toString();
        Log.d("DEBUG", "UID = " + uid);

        Firebase userRef = rootRef.child("users/"+uid);
        Log.d("DEBUG", "User reference =" + userRef);

        getSpinnerValues();


        Log.d("DEBUG", "class1 = "+class1);
        Log.d("DEBUG", "class2 = "+class2);
        Log.d("DEBUG", "class3 = "+class3);
        Log.d("DEBUG", "class4 = "+class4);
        Log.d("DEBUG", "put values in map");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("class1", class1);
        map.put("class2", class2);
        map.put("class3", class3);
        map.put("class4", class4);

        Log.d("DEBUG", "update user info in firebase");
        userRef.updateChildren(map);

        Toast.makeText(edit_user_courses.this,
                "Courses Saved", Toast.LENGTH_LONG).show();

        startActivity(new Intent(edit_user_courses.this, edit_user_profile_info.class));

    }

    public void onCancelButtonClick(View v) {
        Log.d("DEBUG", "onCancelButtonClick()");
        startActivity(new Intent(edit_user_courses.this, edit_user_profile_info.class));
    }
}
