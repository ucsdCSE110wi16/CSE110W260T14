package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class search extends AppCompatActivity {

    // TODO: implement Radio buttons
    // TODO: account for case of no matches
    // TODO: Results show without studytimes, consider options

    Firebase rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Debug", "search Class:");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Firebase.setAndroidContext(this);

        rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

        Log.d("DEBUG", "check for firebase authentication");
        rootRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    Log.d("DEBUG", "user is logged in");

                } else {
                    Log.d("DEBUG", "user is not logged in, return to login");
                    startActivity(new Intent(search.this, login.class));
                }
            }
        });

        setSpinnerValues();
    }

    private void setSpinnerValues() {

        Log.d("Debug", "setSpinnerValues()");
        Spinner spinner = (Spinner) findViewById(R.id.search_spCourse);
        String[] arrayCSEcourses = new String[]{"CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
                "CSE 12", "CSE 15L", "CSE 20", "CSE 21", "CSE 30", "CSE 100", "CSE 101", "CSE 105",
                "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
                "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
                "CSE 167", "CSE 168", "CSE 169", "CSE 182"};

        Log.d("Debug", "create adapter");
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, login.chooseCoursesArray);

        Log.d("Debug", "set spinner values");
        spinner.setAdapter(arrayAdapter);
    }

    private String getSpinnerValues() {
        Log.d("Debug", "getSpinnerValues()");

        Log.d("Debug", "return spinner values");
        Spinner spinner = (Spinner) findViewById(R.id.search_spCourse);
        return (spinner.getSelectedItem().toString());
    }


    public void onSearchButtonClick(View v){

        Log.d("Debug", "onSearchButtonClick()");

        final String classTofind = getSpinnerValues();
        Log.d("Debug", "The class to find is " + classTofind.toString());

        Log.d("Debug", "users/ folder reference  ");
        Firebase userRef = rootRef.child("users/");
        Log.d("Debug", "userRef = " + userRef.toString());

        Log.d("Debug", "search DB for values");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("Debug", "There are " + snapshot.getChildrenCount() + " results");

                Log.d("Debug", "hold results in arrayList");
                ArrayList<Student> searchResults = new ArrayList<Student>();

                for (DataSnapshot child : snapshot.getChildren()) {
                    Student student = child.getValue(Student.class);

                    if (student.getClass1().equals(classTofind) ||
                            student.getClass2().equals(classTofind) ||
                            student.getClass3().equals(classTofind) ||
                            student.getClass4().equals(classTofind)) {

                        Log.d("Debug", student.getName() + " is in " + classTofind);
                        Log.d("Debug", student.getName() + " added to arrayList");
                        searchResults.add(student);
                    }
                }
                Log.d("Debug", "send arraylist to be displayed");
                displayResults(searchResults,classTofind);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Debug", "The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public void displayResults(ArrayList<Student> searchResults, String classToFind){

        Log.d("Debug", "Create adapter to convert the array to views");
        adapter_search adapter = new adapter_search(this, searchResults, classToFind);

        Log.d("Debug", "Attach the adapter to a ListView");
        ListView listView = (ListView) findViewById(R.id.search_lvSearchResults);
        listView.setAdapter(adapter);
    }
}
