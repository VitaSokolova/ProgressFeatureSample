package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.interactors.common.step.StepData

sealed class ApplicationStepData : StepData<ApplicationStepInData, ApplicationStepOutData> {
    class PersonalInfoStepData(
        val stepOutData: PersonalInfoStepOutData?
    ) : ApplicationStepData()

    class EducationStepData(
        val stepInData: EducationStepInData,
        val stepOutData: EducationStepOutData?
    ) : ApplicationStepData()

    class ExperienceStepData(
        val experienceStepOutData: ExperienceStepOutData?
    ) : ApplicationStepData()

    class AboutMeStepData(
        val aboutMeStepOutData: AboutMeStepOutData?
    ) : ApplicationStepData()

    class MotivationStepData(
        val motivationStepInData: MotivationStepInData,
        val motivationStepOutData: MotivationStepOutData?
    ) : ApplicationStepData()
}