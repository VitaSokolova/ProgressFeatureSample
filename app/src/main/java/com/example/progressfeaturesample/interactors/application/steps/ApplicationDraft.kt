package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.interactors.application.steps.ApplicationSteps.*
import java.io.Serializable

/**
 * Черновик заявки
 */
class ApplicationDraft(
    val outDataMap: MutableMap<ApplicationSteps, ApplicationStepOutData> = mutableMapOf()
) : Serializable {
    fun getPersonalInfoOutData() = outDataMap[PERSONAL_INFO] as? PersonalInfoStepOutData
    fun getEducationStepOutData() = outDataMap[EDUCATION] as? EducationStepOutData
    fun getExperienceStepOutData() = outDataMap[EXPERIENCE] as? ExperienceStepOutData
    fun getAboutMeStepOutData() = outDataMap[ABOUT_ME] as? AboutMeStepOutData
    fun getMotivationStepOutData() = outDataMap[MOTIVATION] as? MotivationStepOutData

    fun clear() {
        outDataMap.clear()
    }
}