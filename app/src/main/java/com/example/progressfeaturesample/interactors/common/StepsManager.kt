package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepWithPosition

/**
 * Сущность для управления шагами, хранит текущий шаг, переключает шаги
 */
abstract class StepsManager<S : Step> {

    abstract var currentStep: S

    private val stepsCount: Int
        get() = getSteps().count()

    private val currentStepNumber: Int
        get() = getSteps().indexOf(currentStep)

    abstract fun getSteps(): List<S>

    fun getCurrentStep(): StepWithPosition<S> = StepWithPosition(
        currentStep,
        currentStepNumber,
        stepsCount
    )

    fun completeStep(step: S) {
        val stepNumber = currentStepNumber
        if (stepNumber != getSteps().lastIndex && stepNumber != -1) {
            currentStep = getSteps()[stepNumber + 1]
        }
    }

    fun backStep() {
        val stepNumber = currentStepNumber
        if (stepNumber != 0 && stepNumber != -1) {
            currentStep = getSteps()[stepNumber - 1]
        }
    }
}

/**
 * Сущность для управления шагами, в случае, когда набор шагов неизменный
 */
class FixedStepsManager<S : Step>(val scenario: Scenario<S>) : StepsManager<S>() {
    override var currentStep: S = scenario.steps[0]

    override fun getSteps(): List<S> = scenario.steps
}

/**
 * Сущность для управления шагами, в случае, когда количество шагов может изменяться
 * позволяет добавлять и убирать шаги из сценарий фичи
 */
class MutableStepsManager<S : Step>(scenario: Scenario<S>) : StepsManager<S>() {

    override var currentStep = scenario.steps[0]

    override fun getSteps(): List<S> = steps.toList()

    private var steps: MutableList<S> = scenario.steps.toMutableList()

    fun replaceStep(oldStep: S, newStep: S) {
        val index = steps.indexOfFirst { it == oldStep }
        if (index != -1) {
            steps.removeAt(index)
            steps.add(index, newStep)
        }
    }

    fun removeStep(step: S) {
        val index = steps.indexOfFirst { it == step }
        if (index != -1) {
            steps.removeAt(index)
        }
    }

    fun addStep(index: Int, newStep: S) {
        if (!isStepExist(newStep)) {
            steps.add(index, newStep)
        }
    }

    fun isStepExist(step: S): Boolean {
        return steps.any { it == step }
    }
}