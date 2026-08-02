package com.owen282000.lifedashboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.Instant

class WorkoutCaloriesTest {
    private val start = Instant.parse("2026-08-02T10:00:00Z")
    private val end = Instant.parse("2026-08-02T10:30:00Z")
    private val qz = HealthRecordSource(QZ_FITNESS_PACKAGE, "QZ")
    private val googleFit = HealthRecordSource("com.google.android.apps.fitness", "Google Fit")

    @Test
    fun qzWorkoutUsesSameSourceTotalCaloriesWhenActiveCaloriesAreAbsent() {
        val result = resolveWorkoutCalories(
            sessionSource = qz,
            activeCalories = listOf(ActiveCaloriesData(45.0, start, end, googleFit)),
            totalCalories = listOf(TotalCaloriesData(238.0, start, end, qz))
        )

        assertEquals(238.0, result?.calories ?: 0.0, 0.001)
        assertEquals("total", result?.kind)
        assertEquals(QZ_FITNESS_PACKAGE, result?.source?.packageName)
    }

    @Test
    fun qzWorkoutPrefersSameSourceActiveCaloriesIfQzAddsThemLater() {
        val result = resolveWorkoutCalories(
            sessionSource = qz,
            activeCalories = listOf(ActiveCaloriesData(210.0, start, end, qz)),
            totalCalories = listOf(TotalCaloriesData(238.0, start, end, qz))
        )

        assertEquals(210.0, result?.calories ?: 0.0, 0.001)
        assertEquals("active", result?.kind)
    }

    @Test
    fun nonQzWorkoutKeepsExistingActiveCalorieBehaviour() {
        val result = resolveWorkoutCalories(
            sessionSource = googleFit,
            activeCalories = listOf(ActiveCaloriesData(123.0, start, end, googleFit)),
            totalCalories = listOf(TotalCaloriesData(180.0, start, end, googleFit))
        )

        assertEquals(123.0, result?.calories ?: 0.0, 0.001)
        assertEquals("active", result?.kind)
    }

    @Test
    fun nonQzWorkoutDoesNotFallBackToTotalCalories() {
        val result = resolveWorkoutCalories(
            sessionSource = googleFit,
            activeCalories = emptyList(),
            totalCalories = listOf(TotalCaloriesData(180.0, start, end, googleFit))
        )

        assertNull(result)
    }
}
