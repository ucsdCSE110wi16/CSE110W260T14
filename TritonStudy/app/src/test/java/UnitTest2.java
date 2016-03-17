/**
 * Created by Khoi on 3/10/2016.
 */
package edu.cse100.wi16.tritonstudy;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class UnitTest2 {

    /**
     * To work on unit tests, switch the Test Artifact in the Build Variants view.
     */


   /* StudyTime(
            String day,
            String hour_start,
            String hour_end,
            String minute,
            String minute_end,
            String location,
            String course)*/

    StudyTime studyTime_obj = new StudyTime("Wednesday", "11am", "2pm",
            "50", "20", "Geisel", "CSE110");


    @Test
    public void studyTime_obj_tests() throws Exception {


        //testing some methods in the StudyTime class.
        assertEquals("Wednesday", studyTime_obj.getDay());
        assertEquals("11am", studyTime_obj.getHourStart());
        assertEquals("2pm", studyTime_obj.getHourEnd());
        assertEquals("50", studyTime_obj.getMinuteStart());
        assertEquals("20", studyTime_obj.getMinuteEnd());
        assertEquals("Geisel", studyTime_obj.getLocation());
        assertEquals("CSE110", studyTime_obj.getCourse());

    }


}
