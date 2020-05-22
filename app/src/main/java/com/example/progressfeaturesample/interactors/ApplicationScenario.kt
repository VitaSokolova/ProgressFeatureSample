package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.interactors.common.Scenario

class ApplicationScenario : Scenario<ApplicationSteps> {

    override val steps: MutableList<ApplicationSteps> = mutableListOf(
        PersonalInfoStep(),
        EducationStep(),
        ExperienceStep(),
        MotivationStep()
    )
}