package com.example.progressfeaturesample.interactors.application.steps

/**
 * Черновик заявки
 */
class ApplicationDraft(
    val outDataMap: MutableMap<ApplicationSteps, ApplicationStepOutData> = mutableMapOf()
) {
    fun getPersonalInfoOutData() = outDataMap[PersonalInfoStep] as? PersonalInfoStepOutData
    fun getEducationStepOutData() = outDataMap[EducationStep] as? EducationStepOutData
    fun getExperienceStepOutData() = outDataMap[ExperienceStep] as? ExperienceStepOutData
    fun getAboutMeStepOutData() = outDataMap[AboutMeStep] as? AboutMeStepOutData
    fun getMotivationStepOutData() = outDataMap[MotivationStep] as? MotivationStepOutData
}