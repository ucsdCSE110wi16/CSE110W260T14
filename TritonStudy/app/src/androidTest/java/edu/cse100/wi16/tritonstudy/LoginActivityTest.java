package edu.cse100.wi16.tritonstudy;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/*
----- Scenario: -------
Given that my account "a@a.com" with password "a" is valid and has been stored in the database,
1) When I enter my email ("a@a.com") and my password ("a") to the corresponding text areas
   And I click the Sign In button,
   Then I'm taken to the Main Activity, which has a greeting with my name ("Antelope") on it.

*/


@RunWith (AndroidJUnit4.class)

public class LoginActivityTest {

    private String valUser;
    private String valPassw;

    @Rule
    public ActivityTestRule<login> loginActivityRule = new ActivityTestRule<>(login.class);


    @Before
    public void initValidUser() {
        // Specify a valid username "a@a.com" and password "a"
        valUser = "a@a.com";
        valPassw = "a";
    }


    @Test
    public void changeGreetings_inMainActivity() {

        // Type in a valid user name "a@a.com" and its valid password "a"
        onView(withId(R.id.login_etEmail)).perform(typeText(valUser), closeSoftKeyboard());
        onView(withId(R.id.login_etPassword)).perform(typeText(valPassw), closeSoftKeyboard());

        // Click the Sign In button
        onView(withId(R.id.login_btnSignIn)).perform(click());

        // Check that the greetings in the MainActivity has the user's name ("Antelope")
        onView(withId(R.id.main_greeting)).check(matches(withText("Hello, Antelope")));
    }

}