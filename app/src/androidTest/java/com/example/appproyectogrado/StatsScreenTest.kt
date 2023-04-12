package com.example.appproyectogrado

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import co.yml.charts.common.model.Point
import com.example.appproyectogrado.data.CropData
import com.example.appproyectogrado.viewmodel.StatsScreenViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StatsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @RelaxedMockK
    lateinit var statsScreenViewModel: StatsScreenViewModel
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

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { statsScreenViewModel.cropDataD1.value } answers { cropDataD1 }
        every { statsScreenViewModel.cropDataD2.value } answers { listOf() }
        every { statsScreenViewModel.cropDataD3.value } answers { listOf() }
        every { statsScreenViewModel.makeListPointsHum01(any()) } answers {
            listOf(
                Point(
                    x = 1f,
                    y = 1f
                )
            )
        }
        every { statsScreenViewModel.makeListPointsLight(any()) } answers {
            listOf(
                Point(
                    x = 1f,
                    y = 1f
                )
            )
        }
        every { statsScreenViewModel.makeListPointsTemp(any()) } answers {
            listOf(
                Point(
                    x = 1f,
                    y = 1f
                )
            )
        }
    }


    @Test
    fun chartsAreDisplayed() {
        composeTestRule.setContent {
            Stats(statsScreenViewModel)
        }
        composeTestRule.onNodeWithTag("deviceNameDevice1").assertTextContains("TestDevice1")
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("lineChartHum01Device1")
            .assertTextContains("Humedad en el cultivo durante 24 horas").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lineChartLuxDevice1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lineChartTempDevice1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("deviceNameDevice2").assertDoesNotExist()
        composeTestRule.onNodeWithTag("lineChartHum01Device2").assertDoesNotExist()
        composeTestRule.onNodeWithTag("lineChartLuxDevice2").assertDoesNotExist()
    }
}