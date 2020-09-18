package com.example.progressfeaturesample.interactors.application.steps

import java.io.Serializable

/**
 * Черновик заявки
 */
class ApplicationDraft(
    val outDataMap: MutableMap<ApplicationSteps, ApplicationStepOutData> = mutableMapOf()
) : Serializable {
    fun getPersonalInfoOutData() =
        outDataMap[ApplicationSteps.PERSONAL_INFO] as? PersonalInfoStepOutData

    fun getEducationStepOutData() = outDataMap[ApplicationSteps.EDUCATION] as? EducationStepOutData
    fun getExperienceStepOutData() =
        outDataMap[ApplicationSteps.EXPERIENCE] as? ExperienceStepOutData

    fun getAboutMeStepOutData() = outDataMap[ApplicationSteps.ABOUT_ME] as? AboutMeStepOutData
    fun getMotivationStepOutData() =
        outDataMap[ApplicationSteps.MOTIVATION] as? MotivationStepOutData

    fun clear() {
        outDataMap.clear()
    }
}