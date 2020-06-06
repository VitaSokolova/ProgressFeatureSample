package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepOutData
import com.example.progressfeaturesample.interactors.common.step.StepWithPosition

/**
 * Сущность для управления шагами
 */
class StepsManager<S : Step, O : StepOutData<S>>(private val scenario: Scenario<S, O>) {

    var currentStep: S = scenario.steps[0]

    private val steps: List<S>
        get() = scenario.steps

    fun completeStep(step: O) {
        scenario.completeStep(step)

        val stepNumber = steps.indexOf(currentStep)
        if (stepNumber != steps.lastIndex && stepNumber != -1) {
            currentStep = steps[stepNumber + 1]
        }
    }

    fun getCurrentStep(): StepWithPosition<S> = StepWithPosition(
        currentStep,
        steps.indexOf(currentStep),
        steps.count()
    )

    fun backStep() {
        val stepNumber = steps.indexOf(currentStep)
        if (stepNumber != 0 && stepNumber != -1) {
            currentStep = steps[stepNumber - 1]
        }
    }
}