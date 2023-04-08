package com.example.appproyectogrado

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.appproyectogrado.data.CropData
import com.example.appproyectogrado.viewmodel.MicroControllerScreenViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MicroControllerScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @RelaxedMockK
    lateinit var microControllerScreenViewModel: MicroControllerScreenViewModel

    private val cropData = listOf(
        CropData(
            device = "TestDevice",
            hum01 = listOf(1.1f),
            hum02 = listOf(1.1f),
            hygro01 = listOf(1.1f),
            lux01 = listOf(1.1f),
            temp01 = listOf(1.1f),
            temp02 = listOf(1.1f),
            publishedAt = "2023-03-30T15:30:40.231Z"
        )
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { microControllerScreenViewModel.cropData.value } answers { cropData }
        every { microControllerScreenViewModel.stateFirstMicroController.value } answers { true }
    }

    @Test
    fun dataDisplayed() {
        composeTestRule.setContent {
            MicroControllerStateScreen(microControllerScreenViewModel)
        }
        composeTestRule.onNodeWithTag("microcontrollerOneDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("microcontrollerOneState").assertIsDisplayed()
        composeTestRule.onNodeWithText("Estado MicroControlador: Activo").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataDate").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastData").assertIsDisplayed()
    }
}