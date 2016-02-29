package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CreateUserClassesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_classes);

        //Next Button
        Button nextButton = (Button) findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CreateUserClassesActivity.this, CreateUserClassesConfirmActivity.class));
            }
        });

        //Back Button
        Button backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CreateUserClassesActivity.this, CreateUserInfo.class));
            }
        });

        Spinner spClass1 = (Spinner) findViewById(R.id.createUser_spinner_class1);
        String[] arrayCSEcourses = new String[]{"Choose Course","CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
                "CSE 12", "CSE 15L", "CSE 30", "CSE 20", "CSE 21", "CSE 100", "CSE 101", "CSE 105",
                "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
                "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
                "CSE 167", "CSE 168", "CSE 169", "CSE 182"};
        ArrayAdapter<String> adClass1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayCSEcourses);
        spClass1.setAdapter(adClass1);

        Spinner spClass2 = (Spinner) findViewById(R.id.createUser_spinner_class2);
        ArrayAdapter<String> adClass2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayCSEcourses);
        spClass2.setAdapter(adClass2);

        Spinner spClass3 = (Spinner) findViewById(R.id.createUser_spinner_class3);
        ArrayAdapter<String> adClass3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayCSEcourses);
        spClass3.setAdapter(adClass3);

        Spinner spClass4 = (Spinner) findViewById(R.id.createUser_spinner_class4);
        ArrayAdapter<String> adClass4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayCSEcourses);
        spClass4.setAdapter(adClass4);

        //Skip Button
        Button skipButton = (Button) findViewById(R.id.buttonSkip);
        skipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CreateUserClassesActivity.this, CreateUserVerify.class));



            }
        });
    }
}
