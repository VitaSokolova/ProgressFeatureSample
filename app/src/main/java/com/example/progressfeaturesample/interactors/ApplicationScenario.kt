package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.interactors.common.Scenario

class ApplicationScenario : Scenario<ApplicationSteps> {

    override val steps: List<ApplicationSteps> = listOf(
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
}