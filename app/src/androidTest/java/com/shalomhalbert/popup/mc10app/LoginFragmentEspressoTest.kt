package com.shalomhalbert.popup.mc10app

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * [Espresso] tests for [LoginFragment]
 */
@RunWith(AndroidJUnit4::class)
class LoginFragmentEspressoTest {

    @get:Rule
    public val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun usernameEditTextVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.usernameEditText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun passwordEditTextVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loginButtonVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}