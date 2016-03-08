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
    Student student;

    // sets course for entire app
    final static String[] coursesArray = new String[]{"Choose Course","CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
            "CSE 12", "CSE 15L", "CSE 30", "CSE 20", "CSE 21", "CSE 100", "CSE 101", "CSE 105",
            "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
            "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
            "CSE 167", "CSE 168", "CSE 169", "CSE 182"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("DEBUG", "create_user_choose_classes reached");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_choose_classes);
        Firebase.setAndroidContext(this); //required for firebase

        Log.d("DEBUG", "Receive student object");
        student = (Student)getIntent().getParcelableExtra(create_user_profile_info.PAR_KEY);
        Log.d("DEBUG", "test student object: name is " + student.getName());

        setSpinnerValues();
    }

    public void setSpinnerValues(){
        Log.d("DEBUG", "Set values of spinners displaying course list");
        Log.d("DEBUG", "test student object: name is " + student.getName());

        Log.d("DEBUG", "Set values for Class 1");
        Spinner spClass1 = (Spinner) findViewById(R.id.choose_classes_spClass1);
        ArrayAdapter<String> adClass1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coursesArray);
        spClass1.setAdapter(adClass1);

        Log.d("DEBUG", "Set values for Class 2");
        Spinner spClass2 = (Spinner) findViewById(R.id.choose_classes_spClass2);
        ArrayAdapter<String> adClass2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coursesArray);
        spClass2.setAdapter(adClass2);

        Log.d("DEBUG", "Set values for Class 3");
        Spinner spClass3 = (Spinner) findViewById(R.id.choose_classes_spClass3);
        ArrayAdapter<String> adClass3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coursesArray);
        spClass3.setAdapter(adClass3);

        Log.d("DEBUG", "Set values for Class 4");
        Spinner spClass4 = (Spinner) findViewById(R.id.choose_classes_spClass4);
        ArrayAdapter<String> adClass4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coursesArray);
        spClass4.setAdapter(adClass4);

    }

    private void getSpinnerValues(){

        // TODO: require class1 to be chosen, toast if not chosen
        // TODO: do not save any classes that are equal to "choose course"

        Log.d("DEBUG", "Get values of spinners");

        Log.d("DEBUG", "Get values of class 1");
        Spinner spClass1 =(Spinner) findViewById(R.id.choose_classes_spClass1);
        student.setClass1(spClass1.getSelectedItem().toString());
        Log.d("DEBUG", "The values of class 1 is " + student.getClass1());

        Log.d("DEBUG", "Get values of class 2");
        Spinner spClass2 =(Spinner) findViewById(R.id.choose_classes_spClass2);
        student.setClass2(spClass2.getSelectedItem().toString());
        Log.d("DEBUG", "The values of class 2 is " + student.getClass2());

        Log.d("DEBUG", "Get values of class 3");
        Spinner spClass3 =(Spinner) findViewById(R.id.choose_classes_spClass3);
        student.setClass3(spClass3.getSelectedItem().toString());
        Log.d("DEBUG", "The values of class 3 is " + student.getClass3());

        Log.d("DEBUG", "Get values of class 4");
        Spinner spClass4 =(Spinner) findViewById(R.id.choose_classes_spClass4);
        student.setClass4(spClass4.getSelectedItem().toString());
        Log.d("DEBUG", "The values of class 4 is " + student.getClass4());
    }

    public void onNextButtonClick(View v){

        Log.d("DEBUG", "Next button pressed");

        getSpinnerValues();

        Log.d("DEBUG", "Package student object");
        Intent mIntent = new Intent(create_user_choose_classes.this,
                create_user_add_studytime.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(create_user_profile_info.PAR_KEY, student);
        mIntent.putExtras(mBundle);

        Log.d("DEBUG", "Send student object to next activity");
        startActivity(mIntent);

    }

    public void onBackButtonClick (View v){
        Log.d("DEBUG", "back button pressed");
        startActivity(new Intent(create_user_choose_classes.this, create_user_profile_info.class));
    }

}
