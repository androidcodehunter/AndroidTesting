package com.sharif.androidtesting.ui

import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.sharif.androidtesting.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    //Activity rule will launch the activity at first.
    @Rule @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun greet(){
        //Check weather textview initially empty
        onView(withId(R.id.tv_greet))
            .check(matches(withText("")))

        onView(withId(R.id.btn_greet))
            .perform(click())
            .check(matches(not(isEnabled())))

        //Verify
        onView(withId(R.id.tv_greet))
            .check(matches(withText(R.string.hello)))
    }

    @Test
    fun toolbarTitle(){
        onView(
        allOf(
        isAssignableFrom(AppCompatTextView::class.java),
        withParent(isAssignableFrom(Toolbar::class.java))))
            .check(matches(withText(R.string.title)))
    }




}