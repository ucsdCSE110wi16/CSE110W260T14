package edu.cse100.wi16.tritonstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GroupCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_create);

        //=============================================
        //Drop Down Box (spinner) for Classes (debug)
        //=============================================
        Spinner spClasses = (Spinner) findViewById(R.id.spinnerClass);
        String[] strClasses = new String[]{"Select your class:", "CSE 110", "BIOL 100", "MATH 100B", "LIST 1CX"};
        ArrayAdapter<String> adClasses = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, strClasses);
        spClasses.setAdapter(adClasses);

        //=============================================
        //Drop Down Box (spinner) for Max Members
        //=============================================
        Spinner spMax = (Spinner) findViewById(R.id.spinnerMaxMembers);
        String[] strMax = new String[]{"Set max # members:", "1", "2", "3", "4", "5", "6","7","8","9"};
        ArrayAdapter<String> adMax = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, strMax);
        spClasses.setAdapter(adMax);
    }
}
