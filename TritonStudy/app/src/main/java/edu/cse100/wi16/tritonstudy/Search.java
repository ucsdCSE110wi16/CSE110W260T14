package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Search extends AppCompatActivity {

    Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");
//    Hashtable<String, String> searchResults = new Hashtable<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Debug", "Search Class:");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Firebase.setAndroidContext(this);

        setSpinnerValues();
    }

    private void setSpinnerValues() {

        Log.d("Debug", "set spinner values");
        Spinner spinner = (Spinner) findViewById(R.id.search_spinner);
        String[] arrayCSEcourses = new String[]{"CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
                "CSE 12", "CSE 15L", "CSE 20", "CSE 21", "CSE 30", "CSE 100", "CSE 101", "CSE 105",
                "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
                "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
                "CSE 167", "CSE 168", "CSE 169", "CSE 182"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayCSEcourses);
        spinner.setAdapter(arrayAdapter);
    }

    private String getSpinnerValues() {
        Spinner spinner = (Spinner) findViewById(R.id.search_spinner);
        return (spinner.getSelectedItem().toString());

    }

    public void onSearchButtonClick(View v) {
        Hashtable<String, String> results = new Hashtable<String, String>();
        Firebase usersRef = rootRef.child("users/");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                String classTofind = getSpinnerValues();
                HashMap<String, String> results = new HashMap<String, String>();


                for (DataSnapshot child : snapshot.getChildren()) {
                    Student student = child.getValue(Student.class);

                    if (student.getClass1().equals(classTofind) ||
                            student.getClass2().equals(classTofind) ||
                            student.getClass3().equals(classTofind) ||
                            student.getClass4().equals(classTofind)) {

                        Log.d("Debug", "The Student's name is " + student.getName());
                        String studentRef = child.getRef().toString();
                        results.put(student.getName(), studentRef);
                    }
                }

                Log.d("Debug", "get UID from hashmap" + results.get("Antelope"));
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("results", results);
//
//                Intent intent = new Intent(Search.this, testReceiveResults.class);
//                intent.putExtras(bundle);
//                startActivity(intent);

                startActivity(new Intent(Search.this, testReceiveResults.class).putExtra("hashMapKey", results));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private void addToSearchResults(Hashtable<String,String> results){
        this.searchResults = results;
    }

//    private Hashtable<String, String> getSearchResults(){
//
//
//        return results;
//
//    }

    private void findSearchResults(){

        Firebase usersRef = rootRef.child("users/");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                String classTofind = getSpinnerValues();
                Hashtable<String, String> results = new Hashtable<String, String>();

                for (DataSnapshot child : snapshot.getChildren()) {
                    Student student = child.getValue(Student.class);

                    if (student.getClass1().equals(classTofind) ||
                            student.getClass2().equals(classTofind) ||
                            student.getClass3().equals(classTofind) ||
                            student.getClass4().equals(classTofind)) {

                        Log.d("Debug", "The Student's name is " + student.getName());
                        String studentRef = child.getRef().toString();
                        results.put(student.getName(), studentRef);
                    }
                }

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

//    private void refToStudentTest(final String student1RefLocation){
//
//        final Firebase student1Ref = new Firebase(student1RefLocation);
//
//        Firebase usersRef = rootRef.child("users/");
//
//
//        // Attach an listener to read the data at our posts reference
//        usersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//                Vector searchResults = new Vector(1, 1);
//
//                for (DataSnapshot child : snapshot.getChildren()) {
//
//                    if (child.getRef().equals(student1Ref)){
//
//                        Student student2 = child.getValue(Student.class);
//                        Log.d("DEBUG", "Student2.getName() = " + student2.getName());
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                System.out.println("The read failed: " + firebaseError.getMessage());
//            }
//        });
//
//
//
//    }
}
