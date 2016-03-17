//Unit test
/**
 * Created by Khoi on 3/10/2016.
 */
package edu.cse100.wi16.tritonstudy;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class UnitTest3 {

    /**
     * To work on unit tests, switch the Test Artifact in the Build Variants view.
     */



    login login_obj = new login();
    String[] testDayNameArray = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};



    @Test
    public void student_obj_tests() throws Exception {


        //testing out array structures made in the Login class.
        assertArrayEquals(login_obj.dayNameArray, testDayNameArray);




    }


}
