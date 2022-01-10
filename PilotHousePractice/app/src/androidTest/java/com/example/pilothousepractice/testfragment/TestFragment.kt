package com.example.pilothousepractice.testfragment

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.pilothousepractice.FirstFragment
import com.example.pilothousepractice.FirstFragmentDirections
import com.example.pilothousepractice.R
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class TestFragment {

    @Test
    fun firstFragmentNavigationNavigates()
    {
        //given a fragment
        val fragmentScenario = launchFragmentInContainer<FirstFragment>(Bundle(), R.style.Theme_PilotHousePractice)
        val navController = Mockito.mock(NavController::class.java)

        fragmentScenario.onFragment{
            Navigation.setViewNavController(it.view!!, navController)
        }
        //when user clicks on FAB
        onView(withId(R.id.button_first)).perform(click())
        //then navigates to second fragment
        verify(navController).navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment("mString"))
    }
}