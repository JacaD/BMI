package com.example.bmi


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTestAuto {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTestAuto() {
        val appCompatEditText2 = onView(
            withId(R.id.weight_ET)
        )
        appCompatEditText2.perform(replaceText("80"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
                withId(R.id.height_ET)
        )
        appCompatEditText3.perform(replaceText("180"), closeSoftKeyboard())

        val appCompatButton = onView(
                withId(R.id.button)
        )
        appCompatButton.perform(click())

        val textView = onView(
                withId(R.id.result_TV)
        )
        textView.check(matches(withText("24.69")))
    }
}
