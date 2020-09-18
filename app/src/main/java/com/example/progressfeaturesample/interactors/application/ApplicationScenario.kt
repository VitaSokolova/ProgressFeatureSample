package com.example.progressfeaturesample.interactors.application

import com.example.progressfeaturesample.domain.EducationType
import com.example.progressfeaturesample.domain.PersonalInfo
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepOutData
import com.example.progressfeaturesample.interactors.application.steps.ApplicationSteps
import com.example.progressfeaturesample.interactors.application.steps.PersonalInfoStepOutData
import com.example.progressfeaturesample.interactors.common.Scenario
import com.example.progressfeaturesample.utils.addAfter
import com.example.progressfeaturesample.utils.removeElem
import com.example.progressfeaturesample.utils.replaceWith

/**
 * Класс, описывающий порядок шагов при оформлении заявки
 * Например, выходные данные шага могут влиять на состав шагов и сценарий будет меняться
 */
class ApplicationScenario : Scenario<ApplicationSteps, ApplicationStepOutData> {

    override val steps: MutableList<ApplicationSteps> = mutableListOf(
        ApplicationSteps.PERSONAL_INFO,
        ApplicationSteps.EDUCATION,
        ApplicationSteps.EXPERIENCE,
        ApplicationSteps.MOTIVATION
    )

    /**
     * Внесение изменений в сценарий, в зависимости от выходной информации шага
     */
    override fun completeStep(stepOut: ApplicationStepOutData) {
        when (stepOut) {
            is PersonalInfoStepOutData -> {
                changeScenarioAfterPersonalStep(stepOut.info)
            }
        }
    }

    /**
     * Внесение изменений в сценарий, в зависимости от персональной информации
     */
    private fun changeScenarioAfterPersonalStep(personalInfo: PersonalInfo) {
        applyExperienceToScenario(personalInfo.hasWorkingExperience)
        applyEducationToScenario(personalInfo.education)
    }

    /**
     * Если нет образования - шаг с заполнением места учебы будет исключен
     */
    private fun applyEducationToScenario(education: EducationType) {
        when {
            education.isNoEducation() -> {
                steps.removeElem { it === ApplicationSteps.EDUCATION }
            }
            !steps.contains(ApplicationSteps.EDUCATION) -> {
                steps.addAfter(
                    { it == ApplicationSteps.PERSONAL_INFO },
                    ApplicationSteps.EDUCATION
                )
            }
        }
    }

    /**
     * Если у пользователя нет опыта работы,
     * шаг заполнения мест работы будет заменен на шаг рассказа о себе
     */
    private fun applyExperienceToScenario(hasWorkingExperience: Boolean) {
        if (hasWorkingExperience) {
            steps.replaceWith(
                { it == ApplicationSteps.ABOUT_ME },
                ApplicationSteps.EXPERIENCE
            )
        } else {
            steps.replaceWith(
                { it == ApplicationSteps.EXPERIENCE },
                ApplicationSteps.ABOUT_ME
            )
        }
    }
}