package edu.cse100.wi16.tritonstudy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by john on 3/7/16.
 */
public class adapter_search extends ArrayAdapter<Student> {

    String courseToFind;

    public adapter_search(Context context, ArrayList<Student> users, String courseToFind) {
        super(context, 0, users);
        Log.d("DEBUG", "Set courseToFind = "+courseToFind);
        this.courseToFind = courseToFind;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Log.d("DEBUG", "Get the data item for this position");
        Student student = getItem(position);

        Log.d("DEBUG", "Check if an existing view is being reused, otherwise inflate the view");
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.display_search_results, parent, false);
        }

        Log.d("DEBUG", "Lookup view for data population");
        TextView tvName = (TextView) convertView.findViewById(R.id.displayUser_tvName);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.displayUser_tvPhone);
        TextView tvStudyTime = (TextView) convertView.findViewById(R.id.displayUser_tvStudyTime);

        Log.d("DEBUG", "Populate the data into the template view using the data object");
        Log.d("DEBUG", "tvName = "+student.getName());
        tvName.setText(student.getName());

        Log.d("DEBUG", "Find student studytimes for course: "+courseToFind);
        ArrayList<StudyTime> studyTimes = student.findStudyTimes(courseToFind);

        Log.d("DEBUG", "Construct string of study times");
        String strStudyTimes = "";

        for (StudyTime studytime : studyTimes) {

            if (studytime.getCourse().equals(courseToFind)) {

                Log.d("DEBUG", "studytime.getCourse().equals(courseToFind)");
                Log.d("DEBUG", "Concatenate String");
                strStudyTimes = strStudyTimes.concat(
                        studytime.getDay() + ": "
                                + studytime.getHourStart() +":"
                                + studytime.getMinuteStart()+" - "
                                + studytime.getHourEnd()+":"
                                + studytime.getMinuteEnd()+" at "
                                + studytime.getLocation()+"\r");

                Log.d("DEBUG", "strStudyTimes = "+strStudyTimes);
            }
        }

        Log.d("DEBUG", "Set tvStudyTime");
        tvStudyTime.setText(strStudyTimes);

        Log.d("DEBUG", "Set tvPhone ");
        tvPhone.setText(student.getPhoneNumber());

        Log.d("DEBUG", "Return the completed view to render on screen");
        return convertView;
    }
}