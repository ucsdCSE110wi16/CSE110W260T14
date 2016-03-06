package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class CreateUserClassesActivity extends AppCompatActivity {


    Student student;
    Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //receive
        student = (Student)getIntent().getParcelableExtra(CreateUserInfo.PAR_KEY);

        Log.d("TEST", student.getName());




        setContentView(R.layout.activity_create_user_classes);

//        //Next Button
//        Button nextButton = (Button) findViewById(R.id.buttonNext);
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//
//                startActivity(new Intent(CreateUserClassesActivity.this, CreateUserClassesConfirmActivity.class));
//            }
//        });

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

    public void next(View v){

        Spinner spClass1 =(Spinner) findViewById(R.id.createUser_spinner_class1);
        student.setClass1(spClass1.getSelectedItem().toString());
        Log.d("Student Class 1", student.getClass1());

        Spinner spClass2 =(Spinner) findViewById(R.id.createUser_spinner_class2);
        student.setClass2(spClass2.getSelectedItem().toString());

        Spinner spClass3 =(Spinner) findViewById(R.id.createUser_spinner_class3);
        student.setClass3(spClass3.getSelectedItem().toString());

        Spinner spClass4 =(Spinner) findViewById(R.id.createUser_spinner_class4);
        student.setClass4(spClass4.getSelectedItem().toString());


        Log.d("Student Class 2", student.getClass2());
        Log.d("Student Class 3", student.getClass3());
        Log.d("Student Class 4", student.getClass4());

        String email = student.getEmail();
        String password = student.getPassword();

        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {

                String uid = result.get("uid").toString();

                Log.d("ref", ref.toString());
                Log.d("users", ref.child("users").toString());
                Log.d("uid", ref.child("users").child(uid).toString());

//                Map<String, Object> map = new Map<String, Object>();
                HashMap<String, Object> map = new HashMap<String, Object>();

                map.put("name", student.getName());
                map.put("email", student.getEmail());
                map.put("password", student.getPassword());
                map.put("major", student.getMajor());
                map.put("bio", student.getBio());
                map.put("class1", student.getClass1());
                map.put("class2", student.getClass2());
                map.put("class3", student.getClass3());
                map.put("class4", student.getClass4());



                ref.child("users").child(uid).setValue(map);

//                ref.child("users/"+student.getName()).setValue(student);

                System.out.println("Successfully created user account for " + student.getName());
                Toast.makeText(CreateUserClassesActivity.this, "Successfully created user account for " + student.getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(CreateUserClassesActivity.this, "Shit is Broke!", Toast.LENGTH_LONG).show();
            }
        });


        startActivity(new Intent(CreateUserClassesActivity.this, LoginActivity.class));



    }
}
