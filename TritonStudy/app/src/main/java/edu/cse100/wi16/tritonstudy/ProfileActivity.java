package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    EditText firstName, lastName, middleName, emailAddress, major, bio;
    TextView nameStatic, emailStatic, majorStatic, bioStatic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firstName = (EditText)findViewById(R.id.textNameFirst);
        lastName = (EditText)findViewById(R.id.textNameLast);
        middleName = (EditText)findViewById(R.id.textNameMiddle);
        nameStatic = (TextView)findViewById(R.id.nameStatic);

        emailAddress = (EditText)findViewById(R.id.textEmail);
        emailStatic = (TextView)findViewById(R.id.emailStatic);

        major = (EditText)findViewById(R.id.majorText);
        majorStatic = (TextView)findViewById(R.id.majorStatic);

        bio = (EditText)findViewById(R.id.textBio);
        bioStatic = (TextView)findViewById(R.id.bioStatic);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //=============================================
        //Drop Down Box (spinner) for Enrolled Colleges
        //=============================================
        Spinner dropdown = (Spinner)findViewById(R.id.spinnerColleges);
        String[] items = new String[]{"Select your college:", "Revelle", "John Muir", "Thurgood Marshall", "Earl Warren", "Eleanor Roosevelt", "Sixth"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        //Submit Button
       Button submitButton = (Button)findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                emailStatic.setText(emailAddress.getText().toString());
                majorStatic.setText(major.getText().toString());
                bioStatic.setText(bio.getText().toString());
                startActivity(new Intent(ProfileActivity.this, ProfileActivityStatic.class));
            }
        });

        //Edit Times Button
        Button editTimesButton = (Button) findViewById(R.id.buttonEditStudy);

        editTimesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, edit_times.class));
            }
        });
    }
}
