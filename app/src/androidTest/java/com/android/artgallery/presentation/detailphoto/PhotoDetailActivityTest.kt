package com.android.artgallery.presentation.detailphoto


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.android.artgallery.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PhotoDetailActivityTest {

    val STRING_TO_BE_TYPED = "Espresso"

    @get:Rule
    var activityRule = ActivityTestRule(PhotoDetailActivity::class.java)


    @Test
    fun isFabDisplayed() {
        onView(withId(R.id.detail_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_fab)).perform(click())
    }

    @Test
    fun isToolbarImageDisplayed(){
        onView(withId(R.id.detail_toolbar_image_view)).check(matches(isDisplayed()))
    }


    fun isTitleDisplayed(){
        onView(allOf(withId(R.id.detail_title_text_view), withText(STRING_TO_BE_TYPED))).check(matches(isDisplayed()))
        //onView(withId(R.id.detail_title_text_view))
    }




}