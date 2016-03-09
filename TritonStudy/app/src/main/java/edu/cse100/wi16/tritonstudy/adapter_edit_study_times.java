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
 * Created by john on 3/8/16.
 */
public class adapter_edit_study_times extends ArrayAdapter<StudyTime> {

    ArrayList<StudyTime> studyTimes;

    public adapter_edit_study_times(Context context, ArrayList<StudyTime> studytimes) {
        super(context, 0, studytimes);

        studyTimes = studytimes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Log.d("DEBUG", "Get the data item for this position");
        StudyTime studyTime = getItem(position);

        Log.d("DEBUG", "Check if an existing view is being reused, otherwise inflate the view");
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.display_user_study_times, parent, false);
        }

        Log.d("DEBUG", "Lookup view for data population");
        TextView tvStudyTime = (TextView) convertView.findViewById(R.id.display_edit_user_studytime_tvStudyTime);

        Log.d("DEBUG", "Populate the data into the template view using the data object");

        Log.d("DEBUG", "Construct string of study times");

        String strStudyTimes = "";

        for (StudyTime studytime : studyTimes) {

            Log.d("DEBUG", "studytime.getCourse().equals(courseToFind)");
            Log.d("DEBUG", "Concatenate String");
            strStudyTimes = strStudyTimes.concat(
                    studytime.getDay() + ": "
                            + studytime.getHourStart() + ":"
                            + studytime.getMinuteStart() + " - "
                            + studytime.getHourEnd() + ":"
                            + studytime.getMinuteEnd() + " at "
                            + studytime.getLocation() + "\r");

            Log.d("DEBUG", "strStudyTimes = " + strStudyTimes);
        }

        Log.d("DEBUG", "Set tvStudyTime");
        tvStudyTime.setText(strStudyTimes);

        Log.d("DEBUG", "Return the completed view to render on screen");
        return convertView;
    }
}
