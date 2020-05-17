package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step

interface Scenario<S : Step> {

    val steps: MutableList<S>
    var currentStep: S

    fun getCurrentStepNumber(): Int

    fun completeStep(step: S)

    /**
     * Шаг назад в сценарии
     */
    fun backStep()
}