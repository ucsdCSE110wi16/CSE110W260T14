package com.software.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class SearchProfiles extends AppCompatActivity {

    // define firebase reference location
    Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_profiles);
        Firebase.setAndroidContext(this); //required for firebase

        setSpinnerValues();
    }

    private void setSpinnerValues(){

        Spinner spinner = (Spinner) findViewById(R.id.SearchProfiles_spinner);
        String[] arrayCSEcourses = new String[]{"CSE 3", "CSE 8B", "CSE 11",
                "CSE 15L", "CSE 20", "CSE 21"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayCSEcourses);
        spinner.setAdapter(arrayAdapter);
    }

    private String getSpinnerValues(){
        Spinner spinner = (Spinner) findViewById(R.id.SearchProfiles_spinner);
        String classToFind = spinner.getSelectedItem().toString();
        return classToFind;
    }

    public void onSearchButtonClick(View v){

        final String classTofind = getSpinnerValues();
        Log.d("Debug", "The class to find is " + classTofind.toString());

        Firebase userRef = ref.child("users/");

        // Attach an listener to read the data at our posts reference
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                Log.d("Debug", "//////////////////////////");
                Log.d("Debug", "There are " + snapshot.getChildrenCount() + " children");

//                HashMap<String, Student> results = new HashMap<String, Student>();
                ArrayList<Student> results = new ArrayList<Student>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Student student = child.getValue(Student.class);

                    if (student.getClass1().equals(classTofind) ||
                            student.getClass2().equals(classTofind) ||
                            student.getClass3().equals(classTofind) ||
                            student.getClass4().equals(classTofind)) {

                        Log.d("Debug", student.getName() + " is in" + classTofind);
                        results.add(student);

                    }
                }

//                for (Object obj : searchResults) {
//                    if (obj instanceof String) {
//                        Log.d("Debug", "Search Result: "+ obj );
//                    }
//                }
                displayResults(results);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

    public void displayResults(ArrayList<Student> searchResults){

        // Create the adapter to convert the array to views
        UserAdapter adapter = new UserAdapter(this, searchResults);


        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvSearchResults);
        listView.setAdapter(adapter);



    }
}
