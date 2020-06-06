package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepOutData

/**
 * Интерфейс, которорому должны удовлетворять все классы, описывающие порядок шагов в фиче
 */
interface Scenario<S : Step, O : StepOutData<S>> {
    val steps: List<S>

    fun completeStep(stepOut: O)
}