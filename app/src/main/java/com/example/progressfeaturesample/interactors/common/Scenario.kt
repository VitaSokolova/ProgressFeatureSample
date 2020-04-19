package com.example.progressfeaturesample.interactors.common

interface Scenario<S : Step> {

    val steps: List<S>
    var currentStep: S

    fun getCurrentStepNumber(): Int

    fun completeStep(step: S)

    /**
     * Шаг назад в сценарии
     */
    fun backStep()
}