package edu.cse100.wi16.tritonstudy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class edit_times extends FragmentActivity {

    //Variables for activity elements
    private TextView tvDisplayDate;
    private TextView tvTimeStart;
    private TextView tvTimeEnd;

    private int year;
    private int month;
    private int day;
    private int hour_start;
    private int hour_end;
    private int minute;
    private int minute_end;

    static final int DATE_DIALOG_ID       =  999;
    static final int TIME_START_DIALOG_ID = 1000;
    static final int TIME_END_DIALOG_ID   = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        setContentView(R.layout.activity_edit_times);

        //Back Button
        Button backButton = (Button) findViewById(R.id.buttonBack);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(edit_times.this, ProfileActivity.class));
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
            case DATE_DIALOG_ID:
                // date picker dialog
                return new DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog, datePickerListener, year, month, day);
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

    //===================================
    //= Dialog Fragment for date picker =
    //===================================
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            tvDisplayDate = (TextView) findViewById(R.id.textDate);

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            String dateString = Integer.toString(month+1) + "-" + Integer.toString(day) + "-" + Integer.toString(year);
            tvDisplayDate.setText(dateString);

            // set selected date into datepicker also
            //dpResult.init(year, month, day, null);

        }
    };

    //================================================
    //= Dialog Fragment for Time Picker - Start time =
    //================================================
    private TimePickerDialog.OnTimeSetListener timeStartPickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {

                    tvTimeStart = (TextView)findViewById(R.id.textTimeStart);
                    tvTimeEnd = (TextView)findViewById(R.id.textTimeEnd);

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

                    tvTimeEnd = (TextView)findViewById(R.id.textTimeEnd);

                    hour_end = selectedHour;
                    minute_end = selectedMinute;

                    // set selected time into textview
                    tvTimeEnd.setText(new StringBuilder().append(pad(hour_end))
                            .append(":").append(pad(minute_end)));
                }
            };
}
