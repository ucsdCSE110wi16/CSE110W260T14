/**
 * Created by Khoi on 3/10/2016.
 */
package edu.cse100.wi16.tritonstudy;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class UnitTest1 {

    /**
     * To work on unit tests, switch the Test Artifact in the Build Variants view.
     */
    //Search search_obj = new Search();
   // StudyTime time_obj =  new StudyTime("Monday","5pm", "6pm", "20",
     //       "40", "Geisel", "CSE110");



    @Test
    public void addStudyTimes_isCorrect() throws Exception {


        search_obj.addStudyTimes(


                assertEquals(4, 2 + 2);
    }

    private ArrayList<StudyTime> studyTimes = new ArrayList<StudyTime>();
    public ArrayList<StudyTime> getStudyTimes(){
        return this.studyTimes;
    }

    public void addStudyTimes(StudyTime studyTime){
        this.studyTimes.add(studyTime);
    }

    public ArrayList<StudyTime> findStudyTimes(String courseToFind) {

        ArrayList<StudyTime> courseStudyTimes = new ArrayList<StudyTime>();

        for (StudyTime studytime : studyTimes) {

            if (studytime.getCourse().equals(courseToFind)) {

                courseStudyTimes.add(studytime);
            }
        }

        return courseStudyTimes;
    }

}
