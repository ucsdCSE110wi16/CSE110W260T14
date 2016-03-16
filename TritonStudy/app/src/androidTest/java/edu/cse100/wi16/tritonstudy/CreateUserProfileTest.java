package edu.cse100.wi16.tritonstudy;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

@RunWith (AndroidJUnit4.class)

/*
----- Scenario: -------
Given that I am on the login screen
And I do not have an account on StudyBuddy,
1) When I click New User button,
   Then I'm taken to the Create User Profile Screen/Activity.
2) When I enter my email address after entering my name in the corresponding text areas,
   Then I can see my name displayed on the name text area
3) When I finish entering information in all of the required fields
   And click the Next button,
   Then I'm taken to the Create User Classes Screen/Activity.
*/

public class CreateUserProfileTest {

    private String myName;
    private String myEmail;
    private String myPassword;
    private String myPhone;
    private String myBio;

    @Rule
    public ActivityTestRule<login> createUserRule = new ActivityTestRule<>(login.class);

    @Before
    public void initValidUser() {
        // Specify a user info that the account will be created under
        myName = "Felicia Gunawan";
        myEmail = "fel@ucsd.edu";
        myPassword = "fel";
        myPhone = "432-123-4567";
        myBio = "I'm a Bioinformatics student.";

    }


    @Test
    public void changeGreetings_inMainActivity() {

        main_menu.rootRef.unauth();
        /* Scenario Part 1: */

        // Click New user button
        onView(withId(R.id.login_btnNewUser)).perform(click());

        // Check that the user is taken to the Create User Profile Info Activity
        // Check if the (Name and email) text areas are empty
        onView(withId(R.id.create_user_tvName)).check(matches(withText("Name")));
        onView(withId(R.id.create_user_tvEmail)).check(matches(withText("Email")));
        onView(withId(R.id.create_user_etName)).check(matches(withText("")));
        onView(withId(R.id.create_user_etEmail)).check(matches(withText("")));

        /* Scenario Part 2: */

        // Enter the user name and email
        onView(withId(R.id.create_user_etName)).perform(typeText(myName), closeSoftKeyboard());
        onView(withId(R.id.create_user_etEmail)).perform(typeText(myEmail), closeSoftKeyboard());

        // Check if the user's name is still displayed on the name text area
        onView(withId(R.id.create_user_etName)).check(matches(withText(myName)));

        /* Scenario Part 3: */

        // Enter phone number, bio, password, and password confirmation
        onView(withId(R.id.create_user_etPhone)).perform(typeText(myPhone), closeSoftKeyboard());
        onView(withId(R.id.create_user_etBio)).perform(typeText(myBio), closeSoftKeyboard());
        onView(withId(R.id.create_user_etPassword)).perform(typeText(myPassword), closeSoftKeyboard());
        onView(withId(R.id.create_user_etPasswordConfirm)).perform(typeText(myPassword), closeSoftKeyboard());

        // Click Next button
        onView(withId(R.id.create_user_btnNext)).perform(click());

        // Check if the user is taken to the Create User Choose Classes Activity
        // by checking if there is a text "Choose your enrolled courses:" (on the top)
        onView(withId(R.id.choose_classes_tvTitle)).check(matches(withText("Choose your enrolled courses:")));

        // Choose CSE 3 for first class
        onView(withId(R.id.choose_classes_spClass1)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CSE 3"))).perform(click());

        // Choose CSE 7 for second class
        onView(withId(R.id.choose_classes_spClass2)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CSE 7"))).perform(click());

        // Choose CSE 8A for third class
        onView(withId(R.id.choose_classes_spClass3)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CSE 8A"))).perform(click());

        // Choose CSE 8B for fourth class
        onView(withId(R.id.choose_classes_spClass4)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CSE 8B"))).perform(click());

        // Click Next button
        onView(withId(R.id.create_user_choose_classes_btnNext)).perform(click());

        // Choose Day
        onView(withId(R.id.create_user_add_studyTime_spDays)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Wednesday")))
                .perform(click());

        onView(withId(R.id.add_studyTime_tvDisplayStartTime)).perform(click());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.add_studyTime_spCourse)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CSE 8B"))).perform(click());



    }



}