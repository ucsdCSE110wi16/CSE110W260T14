package edu.cse100.wi16.tritonstudy;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringStartsWith.startsWith;

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



    @Test
    public void doSearch_Test() {


        // Choose CSE 8B for course
        onView(withId(R.id.search_spCourse)).perform(click());
        onData(allOf(CoreMatchers.is(CoreMatchers.instanceOf(String.class)), CoreMatchers.is("CSE 8B"))).perform(click());


        // Click the Search button
        onView(withId(R.id.search_btnSearch)).perform(click());

        // Check that the result shown as expected (displays the user who is in the selected course)
        onView(withId(R.id.search_lvSearchResults));
        onData(instanceOf(Student.class))
                .inAdapterView(allOf(withId(R.id.search_lvSearchResults), isDisplayed()))
                .atPosition(0)
                .check(matches(isDisplayed()));

        //Sleep for 10 seconds
        //This is to give the app time to render the next activity fully
        try {
            Thread.sleep(10000);                 //in milliseconds
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }

}
