package com.example.appproyectogrado

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
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
        every { statsScreenViewModel.cropDataD1.value } answers { cropData }
        every { statsScreenViewModel.chartPointsHum01.value } answers {
            listOf(
                Point(
                    x = 1f,
                    y = 1f
                )
            )
        }
        every { statsScreenViewModel.chartPointsLux.value } answers {
            listOf(
                Point(
                    x = 1f,
                    y = 1f
                )
            )
        }
        every { statsScreenViewModel.chartPointsTemp.value } answers {
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
        composeTestRule.onNodeWithTag("deviceName").assertIsDisplayed()
        composeTestRule.onNodeWithText("TestDevice").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lineChartHum01").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lineChartLux").assertIsDisplayed()
        composeTestRule.onNodeWithTag("lineChartTemp").assertIsDisplayed()
    }
}