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

    Student student_obj = new Student();

    @Test
    public void student_obj_tests() throws Exception {

        //testing some setters in the Student class
        student_obj.setName("Khoi");
        assertEquals("Khoi", student_obj.getName());

        student_obj.setName("NotKhoi");
        assertNotEquals("Khoi", student_obj.getName());

        student_obj.setClass1("TDAC102") ;
        assertEquals("TDAC102", student_obj.getClass1());

        student_obj.setBio("I like to go fishing") ;
        assertEquals("I like to go fishing", student_obj.getBio());

        student_obj.setPhoneNumber("7142608903") ;
        assertEquals("7142608903", student_obj.getPhoneNumber());


    }


}
