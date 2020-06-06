package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.domain.PersonalInfo
import com.example.progressfeaturesample.interactors.common.Scenario
import com.example.progressfeaturesample.utils.addAfter
import com.example.progressfeaturesample.utils.removeElem
import com.example.progressfeaturesample.utils.replaceWith

class ApplicationScenario : Scenario<ApplicationSteps, ApplicationStepOut> {

    override val steps: MutableList<ApplicationSteps> = mutableListOf(
        PersonalInfoStep,
        EducationStep,
        ExperienceStep,
        MotivationStep
    )

    override fun completeStep(stepOut: ApplicationStepOut) {
        when (stepOut) {
            is PersonalInfoStepOut -> {
                changeScenarioAfterPersonalStep(stepOut.info)
            }
        }
    }

    private fun changeScenarioAfterPersonalStep(personalInfo: PersonalInfo) {
        applyExperienceToScenario(personalInfo)
        applyEducationToScenario(personalInfo)
    }

    private fun applyEducationToScenario(personalInfo: PersonalInfo) {
        when {
            personalInfo.education.isNoEducation() -> {
                steps.removeElem { it is EducationStep }
            }
            !steps.contains(EducationStep) -> {
                steps.addAfter(
                    { it is PersonalInfoStep },
                    EducationStep
                )
            }
        }
    }

    private fun applyExperienceToScenario(personalInfo: PersonalInfo) {
        if (personalInfo.workingExperience) {
            steps.replaceWith({ it is AboutMeStep }, ExperienceStep)
        } else {
            steps.replaceWith({ it is ExperienceStep }, AboutMeStep)
        }
    }
}