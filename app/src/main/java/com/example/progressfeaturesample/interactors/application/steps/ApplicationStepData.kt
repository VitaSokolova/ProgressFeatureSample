package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.interactors.common.step.StepData

/**
 *  Класс с входными данными для шага + данными из черновика, если они есть
 */
sealed class ApplicationStepData : StepData<ApplicationStepInData, ApplicationStepOutData> {
    class PersonalInfoStepData(
        val outData: PersonalInfoStepOutData?
    ) : ApplicationStepData()

    class EducationStepData(
        val inData: EducationStepInData,
        val outData: EducationStepOutData?
    ) : ApplicationStepData()

    class ExperienceStepData(
        val outData: ExperienceStepOutData?
    ) : ApplicationStepData()

    class AboutMeStepData(
        val stepOutData: AboutMeStepOutData?
    ) : ApplicationStepData()

    class MotivationStepData(
        val inData: MotivationStepInData,
        val outData: MotivationStepOutData?
    ) : ApplicationStepData()
}