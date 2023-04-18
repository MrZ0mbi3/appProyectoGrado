package com.example.appproyectogrado

import com.example.appproyectogrado.data.CropData
import com.example.appproyectogrado.viewmodel.MicroControllerScreenViewModel
import com.example.appproyectogrado.viewmodel.StatsScreenViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ViewModelsUnitTest {
    @RelaxedMockK
    lateinit var microControllerScreenViewModel: MicroControllerScreenViewModel

    @RelaxedMockK
    lateinit var statsScreenViewModel: StatsScreenViewModel

    private val cropDataD1 = listOf(
        CropData(
            device = "TestDevice1",
            hum01 = listOf(1f, 2f, 3f),
            hum02 = listOf(1.1f),
            hygro01 = listOf(1.1f),
            lux01 = listOf(5f, 5f),
            temp01 = listOf(1.1f),
            temp02 = listOf(1.1f),
            publishedAt = "2023-03-30T15:30:40.231Z"
        )
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { microControllerScreenViewModel.cropDataD1.value } answers { cropDataD1 }
        every { microControllerScreenViewModel.makeAverage(any()) } answers { callOriginal() }
        every { statsScreenViewModel.makeListPointsHum01(any()) } answers { callOriginal() }
        every { statsScreenViewModel.makeListPointsTemp(any()) } answers { callOriginal() }
        every { statsScreenViewModel.makeListPointsLight(any()) } answers { callOriginal() }
        every { statsScreenViewModel.getHourFromDate(any()) } answers { callOriginal() }

    }

    @Test
    fun makeAverageData() {
        assertEquals(2f, microControllerScreenViewModel.makeAverage(cropDataD1[0].hum01))
    }

    @Test
    fun makeListPointsHum() {
        val actual = statsScreenViewModel.makeListPointsHum01(cropDataD1)
        assertEquals(15f, actual[0].x)
        assertEquals(2f, actual[0].y)
    }

    @Test
    fun makeListPointsLight() {
        val actual = statsScreenViewModel.makeListPointsLight(cropDataD1)
        assertEquals(15f, actual[0].x)
        assertEquals(5f, actual[0].y)
    }

    @Test
    fun makeListPointsTemp() {
        val actual = statsScreenViewModel.makeListPointsTemp(cropDataD1)
        assertEquals(15f, actual[0].x)
        assertEquals(1.1f, actual[0].y)
    }

    @Test
    fun getHourFromDate() {
        assertEquals(15f, statsScreenViewModel.getHourFromDate(cropDataD1[0].publishedAt))
    }
}