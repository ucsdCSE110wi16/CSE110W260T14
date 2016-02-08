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
   /* EditText firstName = (EditText)findViewById(R.id.textNameFirst);
    EditText lastName = (EditText)findViewById(R.id.textNameLast);
    EditText middleName = (EditText)findViewById(R.id.textNameMiddle);
    TextView nameStatic = (TextView)findViewById(R.id.nameStatic);
   */
    EditText emailAddress = (EditText)findViewById(R.id.textEmail);
    TextView emailStatic = (TextView)findViewById(R.id.emailStatic);

    EditText major = (EditText)findViewById(R.id.majorText);
    TextView majorStatic = (TextView)findViewById(R.id.majorStatic);

    EditText bio = (EditText)findViewById(R.id.textBio);
    TextView bioStatic = (TextView)findViewById(R.id.bioStatic);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
       /* Button submitButton = (Button)findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                emailStatic.setText(emailAddress.getText().toString());
                majorStatic.setText(major.getText().toString());
                bioStatic.setText(bio.getText().toString());
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        }); */
    }
}
