package edu.cse100.wi16.tritonstudy;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class create_user_add_studytime extends FragmentActivity {

    // TODO: Make sure that study times match class in student object
    // TODO: implement logic for logged in user
    // TODO: Add title and label to page, "Please choose time you will study..."

    //Variables for activity elements
    private TextView tvDisplayDate;
    private TextView tvTimeStart;
    private TextView tvTimeEnd;
    //private Spinner dropdown;

    private int yearInt;
    private int monthInt;
//    private int day;
    private int hourStartInt;
    private int hourEndInt;
    private int minuteStartInt;
    private int minuteEndInt;

    private String yearString;
    private String monthString;
    private String hourStartString;
    private String hourEndString;
    private String minuteStartString = "";
    private String minuteEndString = "";

    Student student;
    String location;
    String course;
    String dayName;


    static final int DATE_DIALOG_ID       =  999;
    static final int TIME_START_DIALOG_ID = 1000;
    static final int TIME_END_DIALOG_ID   = 1001;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            Firebase.setAndroidContext(this);

            Log.d("Debug", "create_user_add_studytime()");

        Log.d("Debug", "get current time");
        //Get current date, current time, and default end time (1 hour from current time,
        //if current time is > 23:00 [11pm] then end time is 23:59)
        final Calendar c = Calendar.getInstance();
//        yearInt = c.get(Calendar.YEAR);
//        monthInt = c.get(Calendar.MONTH);
//        day = c.get(Calendar.DAY_OF_MONTH);

        hourStartInt = c.get(Calendar.HOUR_OF_DAY);
        minuteStartInt = c.get(Calendar.MINUTE);

        if (hourStartInt == 23)
        {
            hourEndInt = 23;
            minuteEndInt = 59;
        }
        else
        {
            hourEndInt= hourStartInt + 1;
            minuteEndInt= minuteStartInt;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_add_studytime);

        setSpinners();

    }

    public void setSpinners(){

        Log.d("DEBUG", "setSpinner()");

        Log.d("DEBUG", "set day spinner");
        Spinner spDays = (Spinner) findViewById(R.id.add_studyTime_spDays);
        ArrayAdapter<String> adDays = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, login.dayNameArray);
        spDays.setAdapter(adDays);

        Log.d("DEBUG", "set location spinner");
        Spinner spLocations = (Spinner) findViewById(R.id.add_studyTime_spLocation);
        ArrayAdapter<String> adLocs = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, login.locationArray);
        spLocations.setAdapter(adLocs);

        Log.d("DEBUG", "set course spinner");
        Spinner spCourses = (Spinner) findViewById(R.id.add_studyTime_spCourse);
        ArrayAdapter<String> adClasses = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item,
                        login.chooseCoursesArray);
        spCourses.setAdapter(adClasses);

    }

    private void getSpinnerValues() {

        Log.d("DEBUG", "Get values of spinners");

        Log.d("DEBUG", "Get values of location");
        Spinner spLocation = (Spinner) findViewById(R.id.add_studyTime_spLocation);
        location = (spLocation.getSelectedItem().toString());
        Log.d("DEBUG", "The values of location is " + location);

        Log.d("DEBUG", "Get values of course");
        Spinner spCourse = (Spinner) findViewById(R.id.add_studyTime_spCourse);
        course = (spCourse.getSelectedItem().toString());
        Log.d("DEBUG", "The values of course is " + course);

        Log.d("DEBUG", "Get values of day");
        Spinner spDayName = (Spinner) findViewById(R.id.add_studyTime_spDays);
        dayName = (spDayName.getSelectedItem().toString());
        Log.d("DEBUG", "The values of day is " + dayName);

        Log.d("DEBUG", "convert start time");
        hourStartString = Integer.toString(hourStartInt);
        Log.d("DEBUG", "The value of hourStartString = "+ hourStartString);

        if (minuteStartInt < 10){
            Log.d("DEBUG", "minuteStartInt < 10");
            minuteStartString = "0";
        }
        minuteStartString = minuteStartString.concat(Integer.toString(minuteStartInt));
        Log.d("DEBUG", "The value of minuteStartString = "+ minuteStartString);

        Log.d("DEBUG", "convert end time");

        hourEndString = Integer.toString(hourEndInt);
        Log.d("DEBUG", "The value of hourEndString = "+ hourEndString);

        if (minuteEndInt < 10){
            Log.d("DEBUG", "minuteEndInt < 10");
            minuteEndString = "0";
        }
        minuteEndString = minuteEndString.concat(Integer.toString(minuteEndInt));
        Log.d("DEBUG", "The value of minuteEndString = "+ minuteEndString);
    }


    public void onSubmitButtonClick(View v){

        Log.d("DEBUG", "onSubmitButtonClick()");

        getSpinnerValues();

        Log.d("DEBUG", "Receive student object");
        student = (Student) getIntent().getParcelableExtra(create_user_profile_info.PAR_KEY);
        Log.d("DEBUG", "test student object: name is " + student.getName());

        StudyTime studyTime = new StudyTime(
                dayName,
                hourStartString,
                hourEndString,
                minuteStartString,
                minuteEndString,
                location,
                course);

        Log.d("DEBUG", "add studyTime to student");
        student.addStudyTimes(studyTime);

        Log.d("DEBUG", "get values of email, password for account creation");
        String email = student.getEmail();
        Log.d("DEBUG", "email = " + student.getEmail());
        String password = student.getPassword();
        Log.d("DEBUG", "password = " + student.getPassword());

        Log.d("DEBUG", "set location of firebase root");
        final Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

        rootRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {

                Log.d("DEBUG", "Successfully created user account for " + student.getName());

                Log.d("DEBUG", "get user account ID");
                String uid = result.get("uid").toString();

                Log.d("DEBUG", "save student object to firebase");
                rootRef.child("users").child(uid).setValue(student);

                Toast.makeText(create_user_add_studytime.this, "Account created for " + student.getName(),
                        Toast.LENGTH_LONG).show();

                Log.d("DEBUG", "Send to login");
                startActivity(new Intent(create_user_add_studytime.this, login.class));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d("DEBUG", "Account not created. Error: " + firebaseError);

                if (firebaseError.toString().contains("specified email address is already")) {

                    Toast.makeText(create_user_add_studytime.this,
                            "The specified email address is already in use.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    };

    public void dateDialog(View v) {
        showDialog(DATE_DIALOG_ID);
    }

    public void timeStartDialog(View v) {
        showDialog(TIME_START_DIALOG_ID);
    }

    public void timeEndDialog(View v) {
        showDialog(TIME_END_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
//            case DATE_DIALOG_ID:
//                // date picker dialog
//                return new DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog, datePickerListener, year, month, day);
            case TIME_START_DIALOG_ID:
                // Start time picker dialog
                return new TimePickerDialog(
                        this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        timeStartPickerListener,
                        hourStartInt,
                        minuteStartInt,
                        true);
            case TIME_END_DIALOG_ID:
                // End time picker dialog
                return new TimePickerDialog(
                        this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        timeEndPickerListener,
                        hourEndInt,
                        minuteEndInt, true);
            default:
                return null;
        }
    }

    // To pad the hour and minutes if less than 10
    // Ex. '9' returns '09'
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

//    //===================================
//    //= Dialog Fragment for date picker =
//    //===================================
//    private DatePickerDialog.OnDateSetListener datePickerListener
//            = new DatePickerDialog.OnDateSetListener() {
//
//        // when dialog box is closed, below method will be called.
//        public void onDateSet(DatePicker view, int selectedYear,
//                              int selectedMonth, int selectedDay) {
//
//            tvDisplayDate = (TextView) findViewById(R.id.textDate);
//
//            year = selectedYear;
//            month = selectedMonth;
//            day = selectedDay;
//
//            // set selected date into textview
//            String dateString = Integer.toString(month+1) + "-" + Integer.toString(day) + "-" + Integer.toString(year);
//            tvDisplayDate.setText(dateString);
//
//            // set selected date into datepicker also
//            //dpResult.init(year, month, day, null);
//
//        }
//    };

    //================================================
    //= Dialog Fragment for Time Picker - Start time =
    //================================================
    private TimePickerDialog.OnTimeSetListener timeStartPickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {

                    tvTimeStart = (TextView)findViewById(R.id.add_studyTime_tvDisplayStartTime);
                    tvTimeEnd = (TextView)findViewById(R.id.add_studyTime_tvDisplayEndTime);

                    hourStartInt = selectedHour;
                    minuteStartInt= selectedMinute;

                    if (hourStartInt == 23)
                    {
                        hourEndInt= 23;
                        minuteEndInt = 59;
                    }
                    else
                    {
                        hourEndInt= hourStartInt + 1;
                        minuteEndInt= minuteStartInt;
                    }

                    // set selected time into textview
                    tvTimeStart.setText(new StringBuilder().append(pad(hourStartInt))
                            .append(":").append(pad(minuteStartInt)));
                    tvTimeEnd.setText(new StringBuilder().append(pad(hourEndInt))
                            .append(":").append(pad(minuteEndInt)));
                }
            };

    //==============================================
    //= Dialog Fragment for Time Picker - End time =
    //==============================================
    private TimePickerDialog.OnTimeSetListener timeEndPickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {

                    tvTimeEnd = (TextView)findViewById(R.id.add_studyTime_tvDisplayEndTime);

                    hourEndInt = selectedHour;
                    minuteEndInt = selectedMinute;

                    // set selected time into textview
                    tvTimeEnd.setText(new StringBuilder().append(pad(hourEndInt))
                            .append(":").append(pad(minuteEndInt)));
                }
            };


}
