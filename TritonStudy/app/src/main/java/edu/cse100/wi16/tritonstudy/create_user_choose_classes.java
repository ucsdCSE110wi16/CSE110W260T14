package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class create_user_choose_classes extends AppCompatActivity {


    Student student; // created here to guarentee access

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_choose_classes);

        Firebase.setAndroidContext(this); //required for firebase

        // receive student object from CreateUserInfo
        student = (Student)getIntent().getParcelableExtra(CreateUserInfo.PAR_KEY);

        setSpinnerValues();
    }

    private void setSpinnerValues(){

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

    }

    private void getSpinnerValues(){
        Spinner spClass1 =(Spinner) findViewById(R.id.createUser_spinner_class1);
        student.setClass1(spClass1.getSelectedItem().toString());
        Log.d("Student Class 1", student.getClass1());

        Spinner spClass2 =(Spinner) findViewById(R.id.createUser_spinner_class2);
        student.setClass2(spClass2.getSelectedItem().toString());

        Spinner spClass3 =(Spinner) findViewById(R.id.createUser_spinner_class3);
        student.setClass3(spClass3.getSelectedItem().toString());

        Spinner spClass4 =(Spinner) findViewById(R.id.createUser_spinner_class4);
        student.setClass4(spClass4.getSelectedItem().toString());
    }

    public void nextButton(View v){

        getSpinnerValues();

        // debug
        Log.d("DIVIDER", "//////////////////////////////////////////");
        Log.d("Student Class 1", student.getClass1());
        Log.d("Student Class 2", student.getClass2());
        Log.d("Student Class 3", student.getClass3());
        Log.d("Student Class 4", student.getClass4());

        String email = student.getEmail();
        Log.d("EMAIL", student.getEmail());

        String password = student.getPassword();
        Log.d("PASSWORD", student.getPassword());

        Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

        rootRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("DIVIDER", "//////////////////////////////////////////");
                Log.d("USER CREATED", "Successfully created user account for "+ student.getName());

                //preserve UID
                String uid = result.get("uid").toString();

                Log.d("name", student.getName());
                Log.d("ref", rootRef.toString());
                Log.d("users", rootRef.child("users/").toString());
                Log.d("uid", rootRef.child("users/").child(uid).toString());

                // save value to firebase
                ref.child("users").child(uid).setValue(student);

//                // save value to firebase
//                ref.child("users").child(uid).setValue(student);

//                Map<String, Object> map = new HashMap<String, Object>();//
//                result.put("name", student.getName());
//                result.put("email", student.getEmail());
//                result.put("password", student.getPassword());
//                result.put("major", student.getMajor());
//                result.put("bio", student.getBio());
//                result.put("class1", student.getClass1());
//                result.put("class2", student.getClass2());
//                result.put("class3", student.getClass3());
//                result.put("class4", student.getClass4());



            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(create_user_choose_classes.this, "Shit is Broke!", Toast.LENGTH_LONG).show();
            }
        });


        startActivity(new Intent(create_user_choose_classes.this, LoginActivity.class));

    }

    public void backButton (View v){
        startActivity(new Intent(create_user_choose_classes.this, CreateUserInfo.class));
    }

    public void skipButton (View v){
        startActivity(new Intent(create_user_choose_classes.this, CreateUserVerify.class));
    }

}
