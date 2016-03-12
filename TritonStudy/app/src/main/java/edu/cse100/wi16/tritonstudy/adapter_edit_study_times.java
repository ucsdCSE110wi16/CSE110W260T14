package edu.cse100.wi16.tritonstudy;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 3/8/16.
 */
public class adapter_edit_study_times extends ArrayAdapter<StudyTime> {

    //    List<StudyTime> userStudyTimes;
    private int selectedItem = -1; // no item selected by default

    public adapter_edit_study_times(Context context, ArrayList<StudyTime> passedUserStudyTimes) {
        super(context, 0, passedUserStudyTimes);
        Log.d("DEBUG", "adapter_edit_study_times()");

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


        Log.d("DEBUG", "studytime.getCourse().equals(courseToFind)");
        Log.d("DEBUG", "Concatenate String");
        strStudyTimes = strStudyTimes.concat(
                studyTime.getCourse() + ": "
                        + studyTime.getDay() + ", "
                        + studyTime.getHourStart() + ":"
                        + studyTime.getMinuteStart() + " - "
                        + studyTime.getHourEnd() + ":"
                        + studyTime.getMinuteEnd() + " at "
                        + studyTime.getLocation() + "\r");

        Log.d("DEBUG", "strStudyTimes = " + strStudyTimes);


        Log.d("DEBUG", "Set tvStudyTime");
        tvStudyTime.setText(strStudyTimes);

        Log.d("DEBUG", "Return the completed view to render on screen");
        return convertView;
    }
}

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private void highlightItem(int position, View result) {
//        if(position == selectedItem) {
//            // you can define your own color of selected item here
//            result.setBackgroundColor(Color.YELLOW);
//        }
//    }
//
//    public void setSelectedItem(int selectedItem) {
//        this.selectedItem = selectedItem;
//    }
//}
