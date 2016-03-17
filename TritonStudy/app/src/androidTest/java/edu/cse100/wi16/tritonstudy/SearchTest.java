package edu.cse100.wi16.tritonstudy;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/*
----- Scenario: -------
Given that I am logged in and currently in the Search Activity,
When I activate the radio button "Search by Course" search_radioCourse
   And I select "CSE 3" from the spinner, search_spCourse
   And I click the Search button, search_btnSearch
Then users that has CSE 3 in their course list are displayed in the search result.
*/

public class SearchTest {

    private String selectedClass;
    private String res1_Name;
    private String res1_Phone;

    @Rule
    public ActivityTestRule<Search> searchActivityRule = new ActivityTestRule<>(Search.class);


    @Before
    public void initSearchInfo() {
        // Specify the class that is used as search filter
        selectedClass = "CSE 3";

        // Specify the data of a user that is in the selected class (CSE 3)
        res1_Name = "Bandicoot";
        res1_Phone = "234-5678";
    }


    @Test
    public void doSearch_Test() {

        main_menu.rootRef.unauth();

        // Check if the selected course ("CSE 3") is displayed on the spinner
        onView(withId(R.id.search_tvCourse)).check(matches(withSpinnerText(containsString("Search by Course:"))));

        // Select a course ("CSE 3") from the spinner
        onView(withId(R.id.search_spCourse)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(selectedClass))).perform(click());

        // Click the Search button
        onView(withId(R.id.search_btnSearch)).perform(click());

        // Check that the result shown as expected (displays the user who is in the selected course)
        onView(withId(R.id.displayUser_tvName)).check(matches(withText(res1_Name)));
        onView(withId(R.id.displayUser_tvPhone)).check(matches(withText(res1_Phone)));
    }

}
