package com.example.appproyectogrado

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class FirstScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun WelcomeMessageAndButtonDisplayed() {
        composeTestRule.setContent {
            FirstScreen()
        }
        composeTestRule.onNodeWithText("Hola! \n Bienvenido").assertIsDisplayed()
        composeTestRule.onNodeWithText("Conectando con la base de datos...").assertIsDisplayed()
        composeTestRule.onNodeWithText("Entrar").assertIsDisplayed()
    }
}