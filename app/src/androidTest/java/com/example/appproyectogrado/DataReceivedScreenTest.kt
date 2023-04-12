package com.example.appproyectogrado

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.appproyectogrado.data.CropData
import com.example.appproyectogrado.viewmodel.DataReceivedScreenViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DataReceivedScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @RelaxedMockK
    lateinit var dataReceivedScreenViewModel: DataReceivedScreenViewModel

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
        every { dataReceivedScreenViewModel.cropDataD1.value } answers { cropData }
        every { dataReceivedScreenViewModel.cropDataD2.value } answers { listOf() }
        every { dataReceivedScreenViewModel.cropDataD3.value } answers { cropData }
    }

    @Test
    fun cropDataDisplayed() {
        composeTestRule.setContent {
            DataReceived(dataReceivedScreenViewModel = dataReceivedScreenViewModel)
        }
        composeTestRule.onNodeWithTag("dataReceivedD1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("dataReceivedD2").assertDoesNotExist()
        composeTestRule.onNodeWithTag("dataReceivedD3").assertIsDisplayed()

    }
}