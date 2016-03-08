package edu.cse100.wi16.tritonstudy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Calendar;
import java.util.Map;

public class add_studytime extends FragmentActivity {

    //Variables for activity elements
    private TextView tvDisplayDate;
    private TextView tvTimeStart;
    private TextView tvTimeEnd;
    //private Spinner dropdown;

    private int year;
    private int month;
    private int day;
    private int hour_start;
    private int hour_end;
    private int minute;
    private int minute_end;

    Student student;
    String location;
    String course;
    String dayName;

    static final int DATE_DIALOG_ID       =  999;
    static final int TIME_START_DIALOG_ID = 1000;
    static final int TIME_END_DIALOG_ID   = 1001;

    // use for all location spinner
    static final String[] locationArray = new String[]{"Select your location:", "Geisel Library", "Price Center East", "Price Center West", "The Old Student Center",
            "Biomedical Library", "Galbraith Hall", "North Break (The Village)", "Marshall: P Hall Res Lounge", "Marshall: Q Hall Res Lounge",
            "Marshall: R Hall Res Lounge", "Marshall: U Hall Res Lounge", "Marshall: Fireside Lounge", "Marshall: Ocean View Lounge",
            "Muir: Tioga & Tenaya Res Hall Lounges", "Muir: Tioga Hall 11th Floor Mtg Rm", "Muir: Tuolumne Apts Lounge", "Muir: Tamarack Apts Lounges",
            "Revelle: Blake Hall: Commuter Lounge", "Revelle: Blake Hall: College Center", "Revelle: Blake Hall: Blake 4 Lounge",
            "Muir: Argo Res Hall Lounges", "Muir: Fleet Res Lounge Halls", "Galbraith Hall: The Think Tank", "Galbraith Hall: Barnwood",
            "Muir: Keeling 1 Lounge", "Muir: Keeling 3 Lounge", "ERC: Africa Hall", "ERC: Asia Hall", "ERC: Europe Hall", "ERC: Latin Americaa Hall",
            "ERC: North America Hall", "I-House: Asante House", "I-House: Cuzco House", "I-House: Kathmandu House", "ERC: Commuter Lounge",
            "Sixth: College Lodge", "Sixth: Dogg House", "Sixth: Commuter Center", "Sixth: Digital Playroom", "Warren: The Courtroom", "Warren: JK Wood Lounge",
            "Warren: Harlan Res Hall Lounges", "Warren: Frankfurt Res Hall Lounges", "Warren: Stewart Res Hall Lounges", "Warren: CSE Study Lounge"};
    static final String[] dayNameArray = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};


    Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            Firebase.setAndroidContext(this);

            Log.d("Debug", "add_studytime()");


        Log.d("DEBUG", "Receive student object");
        student = (Student)getIntent().getParcelableExtra(create_user_profile_info.PAR_KEY);
        Log.d("DEBUG", "test student object: name is " + student.getName());



        Log.d("Debug", "get current time");
        //Get current date, current time, and default end time (1 hour from current time,
        //if current time is > 23:00 [11pm] then end time is 23:59)
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        hour_start = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        if (hour_start == 23)
        {
            hour_end = 23;
            minute_end = 59;
        }
        else
        {
            hour_end = hour_start + 1;
            minute_end = minute;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_studytime);

        setSpinners();

    }

    public void setSpinners(){

        Log.d("DEBUG", "setSpinner()");

        Log.d("DEBUG", "set day spinner");
        Spinner spDays = (Spinner) findViewById(R.id.add_studyTime_spDays);
        ArrayAdapter<String> adDays = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, dayNameArray);
        spDays.setAdapter(adDays);

        Log.d("DEBUG", "set location spinner");
        Spinner spLocations = (Spinner) findViewById(R.id.add_studyTime_spLocation);
        ArrayAdapter<String> adLocs = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, locationArray);
        spLocations.setAdapter(adLocs);

        Log.d("DEBUG", "set course spinner");
        Spinner spCourses = (Spinner) findViewById(R.id.add_studyTime_spCourse);
        ArrayAdapter<String> adClasses = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item,
                        create_user_choose_classes.coursesArray);
        spCourses.setAdapter(adClasses);

    }

    private void getSpinnerValues() {

        // TODO: Make sure that study times match class in student object

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



    }

    public void onBackButtonClick(View v){
        // TODO: Implement
    }

    public void onSubmitButtonClick(View v){

        getSpinnerValues();

        StudyTime studyTime = new StudyTime(dayName, hour_start, hour_end, minute, minute_end, location, course);

        student.addStudyTimes(studyTime);

        Log.d("DEBUG", "get location of root directory of database");
        final Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");

        Log.d("DEBUG", "get values of email, password for account creation");
        String email = student.getEmail();
        Log.d("DEBUG", "email = " + student.getEmail());
        String password = student.getPassword();
        Log.d("DEBUG", "password = " + student.getPassword());

        rootRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {

                Log.d("DEBUG", "Successfully created user account for " + student.getName());

                Log.d("DEBUG", "get user account ID");
                String uid = result.get("uid").toString();

                Log.d("DEBUG", "save student object to firebase");
                rootRef.child("users").child(uid).setValue(student);

                Toast.makeText(add_studytime.this, "Successfully created user" + student.getName(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d("DEBUG", "Account not created. Error: " + firebaseError);
                // TODO: create toast for error
            }
        });

    }

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
                return new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, timeStartPickerListener, hour_start, minute, true);
            case TIME_END_DIALOG_ID:
                // End time picker dialog
                return new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, timeEndPickerListener, hour_end, minute_end, true);
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

                    hour_start = selectedHour;
                    minute = selectedMinute;

                    if (hour_start == 23)
                    {
                        hour_end = 23;
                        minute_end = 59;
                    }
                    else
                    {
                        hour_end = hour_start + 1;
                        minute_end = minute;
                    }


                    // set selected time into textview
                    tvTimeStart.setText(new StringBuilder().append(pad(hour_start))
                            .append(":").append(pad(minute)));
                    tvTimeEnd.setText(new StringBuilder().append(pad(hour_end))
                            .append(":").append(pad(minute_end)));
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

                    hour_end = selectedHour;
                    minute_end = selectedMinute;

                    // set selected time into textview
                    tvTimeEnd.setText(new StringBuilder().append(pad(hour_end))
                            .append(":").append(pad(minute_end)));
                }
            };


}
