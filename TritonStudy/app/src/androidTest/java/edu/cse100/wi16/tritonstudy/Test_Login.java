package edu.cse100.wi16.tritonstudy;

import android.app.Instrumentation;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.anyIntent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/*
----- Scenario: -------
Given that my account "a@a.com" with password "a" is valid and has been stored in the database,
When I enter my email ("a@a.com") and my password ("a") to the corresponding text areas
   And I click the Sign In button,
Then I'm taken to the Main Activity, which has a greeting with my name ("Antelope") on it.

*/


@RunWith(AndroidJUnit4.class)
@LargeTest
public class Test_Login{

    private static final String usernametest = "a@a.com";
    private static final String passwordtest = "a";


    @Rule
    public ActivityTestRule<login> mActivityRule = new ActivityTestRule<>(login.class);

//    public Test_Login() {
//        super(login.class);
//    }

//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        getActivity();
//    }

    @Test
    public void TestLogin() {

        Log.d("DEBUG", "Enter email");
        onView(withId(R.id.login_etEmail)).perform(typeText(usernametest),
                closeSoftKeyboard());

        Log.d("DEBUG", "Enter password");
        onView(withId(R.id.login_etPassword)).perform(typeText(passwordtest),
                closeSoftKeyboard());

        Log.d("DEBUG", "Click sign in button");
        onView(withId(R.id.login_btnSignIn)).perform(click());

        onView(withId(R.id.main_btnLogOut)).perform(click());

    }




}
