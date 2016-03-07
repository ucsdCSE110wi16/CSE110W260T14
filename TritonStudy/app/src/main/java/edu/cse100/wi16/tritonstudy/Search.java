package edu.cse100.wi16.tritonstudy;

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

import java.util.Vector;

public class Search extends AppCompatActivity {

    Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Firebase.setAndroidContext(this);

        setSpinnerValues();

    }

    private void setSpinnerValues() {

        Spinner spinner = (Spinner) findViewById(R.id.search_spinner);
        String[] arrayCSEcourses = new String[]{"Choose Course", "CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
                "CSE 12", "CSE 15L", "CSE 20", "CSE 21", "CSE 30", "CSE 100", "CSE 101", "CSE 105",
                "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
                "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
                "CSE 167", "CSE 168", "CSE 169", "CSE 182"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayCSEcourses);
        spinner.setAdapter(arrayAdapter);
    }

    private String getSpinnerValues() {
        Spinner spinner = (Spinner) findViewById(R.id.search_spinner);
        String classToFind = spinner.getSelectedItem().toString();
        return classToFind;
    }

    public void onSearchButtonClick(View v) {

        final String classTofind = getSpinnerValues();

        Firebase usersRef = rootRef.child("users/");


        // Attach an listener to read the data at our posts reference
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                Log.d("Debug", "//////////////////////////");
                Log.d("DEBUG", "The class to find is " + classTofind);
                Log.d("Debug", "There are " + snapshot.getChildrenCount() + " children");

                Vector searchResults = new Vector(1, 1);

                for (DataSnapshot child : snapshot.getChildren()) {
                    Student student = child.getValue(Student.class);

                    String student1Ref = child.getRef().toString();
                    refToStudentTest(student1Ref);



                    if (student.getClass1().equals(classTofind) ||
                            student.getClass2().equals(classTofind) ||
                            student.getClass3().equals(classTofind) ||
                            student.getClass4().equals(classTofind)) {

                        Log.d("Debug", "The Student's name is " + student.getName());
                        searchResults.add(student.getName());

                    }
                }

                for (Object obj : searchResults) {
                    if (obj instanceof String) {
                        Log.d("Debug", "Search Result: " + obj);
                    }
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

    private void refToStudentTest(final String student1RefLocation){

        final Firebase student1Ref = new Firebase(student1RefLocation);

        Firebase usersRef = rootRef.child("users/");


        // Attach an listener to read the data at our posts reference
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                Vector searchResults = new Vector(1, 1);

                for (DataSnapshot child : snapshot.getChildren()) {

                    if (child.getRef().equals(student1Ref)){

                        Student student2 = child.getValue(Student.class);
                        Log.d("DEBUG", "Student2.getName() = " + student2.getName());
                    }

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });



    }
}
