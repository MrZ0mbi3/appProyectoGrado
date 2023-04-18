package com.example.appproyectogrado

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
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

    private val cropDataD1 = listOf(
        CropData(
            device = "TestDevice1",
            hum01 = listOf(1.1f),
            hum02 = listOf(1.1f),
            hygro01 = listOf(1.1f),
            lux01 = listOf(1.1f),
            temp01 = listOf(1.1f),
            temp02 = listOf(1.1f),
            publishedAt = "2023-03-30T15:30:40.231Z"
        )
    )
    private val cropDataD2 = listOf(
        CropData(
            device = "TestDevice2",
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
        every { microControllerScreenViewModel.cropDataD1.value } answers { cropDataD1 }
        every { microControllerScreenViewModel.cropDataD2.value } answers { cropDataD2 }
        every { microControllerScreenViewModel.cropDataD3.value } answers { listOf() }
        every { microControllerScreenViewModel.stateFirstMicroController.value } answers { true }
        every { microControllerScreenViewModel.stateSecondMicroController.value } answers { true }
        every { microControllerScreenViewModel.stateThirdMicroController.value } answers { false }
    }

    @Test
    fun cardWithDataOfMicroControllersDisplayed() {
        composeTestRule.setContent {
            MicroControllerStateScreen(microControllerScreenViewModel)
        }
        composeTestRule.onNodeWithTag("microcontrollerOneDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("microcontrollerOneState").assertIsDisplayed()
        composeTestRule.onNodeWithTag("microcontrollerOneState")
            .assertTextContains("Estado MicroControlador: Activo").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataDateMicrocontrollerOne").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerOne").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerHumOneDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerHygroOneDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerLuzOneDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerTempOneDevice").assertIsDisplayed()

        composeTestRule.onNodeWithTag("microcontrollerTwoDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("microcontrollerTwoState").assertIsDisplayed()
        composeTestRule.onNodeWithTag("microcontrollerTwoState")
            .assertTextContains("Estado MicroControlador: Activo").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataDateMicrocontrollerTwo").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerTwo").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerHumTwoDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerHygroTwoDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerLuzTwoDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerTempTwoDevice").assertIsDisplayed()

        composeTestRule.onNodeWithTag("microcontrollerThreeDevice").assertDoesNotExist()
        composeTestRule.onNodeWithTag("microcontrollerThreeState").assertDoesNotExist()
        composeTestRule.onNodeWithTag("lastDataDateMicrocontrollerThree").assertDoesNotExist()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerThree").assertDoesNotExist()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerHumThree").assertDoesNotExist()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerHygroThree").assertDoesNotExist()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerLuzThree").assertDoesNotExist()
        composeTestRule.onNodeWithTag("lastDataMicrocontrollerTempThree").assertDoesNotExist()
    }
}