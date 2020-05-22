package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step

/**
 * Интерфейс, которорому должны удоалетворять все классы, описывающие порядок шагов в фиче
 */
interface Scenario<S : Step> {
    val steps: List<S>
}