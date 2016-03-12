package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class edit_user_study_times extends AppCompatActivity {

    final Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");
    adapter_edit_study_times studytimeAdapter;
    ListView resultsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_study_times);

        Log.d("STATE", "edit_user_study_times()");
        Log.d("STATE", "check authentication status");

        rootRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(final AuthData authData) {
                if (authData != null) {
                    Log.d("STATE", "User is authenticated");
                    getStudyTimes(authData);

                } else {
                    // user is not logged in
                }
            }
        });

    }

    public void onAddStudyTimesButtonClick(View v){

        startActivity(new Intent(edit_user_study_times.this, edit_user_add_study_time.class));
    }

    public void onBackButtonClick(View v){

        startActivity(new Intent(edit_user_study_times.this, edit_user_profile_info.class));
    }

    public void getStudyTimes(AuthData authData){


        Log.d("Debug", "getStudyTimes()");

        Log.d("DEBUG", "set firebase reference to logged in users folder");
        Firebase userRef = rootRef.child("users/" + authData.getUid());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                Log.d("Debug", "hold results in arrayList");
                ArrayList<Student> searchResults = new ArrayList<Student>();

                Student student = snapshot.getValue(Student.class);
                Log.d("Debug", "student object's name is " + student.getName());

                ArrayList<StudyTime> studytimes = student.getStudyTimes();

                Log.d("Debug", "send arraylist to be displayed");
                displayResults(studytimes);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Debug", "The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public void displayResults(ArrayList<StudyTime> studytimes){

        Log.d("Debug", "Create adapter to convert the array to views");
        studytimeAdapter = new adapter_edit_study_times(edit_user_study_times.this, studytimes);

        Log.d("Debug", "Attach the adapter to a ListView");
        resultsListView  = (ListView) findViewById(R.id.edit_user_study_time_lvStudyTimes);
        resultsListView.setAdapter(studytimeAdapter);
//        highlightListItem(1); // this simple function call does the trick
    }

//    private void highlightListItem(int position) {
//        adapter_edit_study_times adapter = (adapter_edit_study_times) resultsListView.getAdapter();
//        adapter.setSelectedItem(position);
//        // in some cases, it may be necessary to re-set adapter (as in the line below)
//        resultsListView.setAdapter(adapter);
//    }

//    textView.setOnClickListener(View V);
//        resultsListView.performItemClick(resultsListView, position, resultsListView.getItemIdAtPosition(position));
//    }
}
