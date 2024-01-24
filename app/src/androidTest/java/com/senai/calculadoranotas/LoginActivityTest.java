package com.senai.calculadoranotas;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void verificaPresencaDoBotao() {
        // Verifica se o botão com a ID "seuBotao" está presente na Activity
        Espresso.onView(ViewMatchers.withId(R.id.buttonEntrar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
