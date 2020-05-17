package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.interactors.common.Scenario

class ApplicationScenario : Scenario<ApplicationSteps> {

    override val steps: MutableList<ApplicationSteps> = mutableListOf(
        PersonalInfoStep(),
        EducationStep(),
        ExperienceStep(),
        MotivationStep()
    )

    override var currentStep = steps[0]

    override fun getCurrentStepNumber(): Int {
        return steps.indexOf(currentStep)
    }

    override fun completeStep(step: ApplicationSteps) {
        val stepNumber = getCurrentStepNumber()
        if (stepNumber != steps.lastIndex && stepNumber != -1) {
            currentStep = steps[stepNumber + 1]
        }
    }

    override fun backStep() {
        val stepNumber = getCurrentStepNumber()
        if (stepNumber != 0 && stepNumber != -1) {
            currentStep = steps[stepNumber - 1]
        }
    }

    fun replaceStep(oldStep: ApplicationSteps, newStep: ApplicationSteps) {
        val index = steps.indexOfFirst { it == oldStep }
        if (index != -1) {
            steps.removeAt(index)
            steps.add(index, newStep)
        }
    }

    fun removeStep(step: ApplicationSteps) {
        val index = steps.indexOfFirst { it == step }
        if (index != -1) {
            steps.removeAt(index)
        } else {
            error("${step.javaClass.name} doesn't exist")
        }
    }

    fun addStep(index: Int, newStep: ApplicationSteps) {
        if (!isStepExist(newStep)) {
            steps.add(index, newStep)
        }
    }

    fun isStepExist(step: ApplicationSteps): Boolean {
        return steps.any { it == step }
    }
}