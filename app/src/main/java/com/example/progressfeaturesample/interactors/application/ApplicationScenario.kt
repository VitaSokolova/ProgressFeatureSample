package com.example.progressfeaturesample.interactors.application

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
 * Выходные данные шага могут влиять на состав шагов, например
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
    override fun completeStep(stepOutData: ApplicationStepOutData) {
        when (stepOutData) {
            is PersonalInfoStepOutData -> {
                changeScenarioAfterPersonalStep(stepOutData.info)
            }
        }
    }

    /**
     * Внесение изменений в сценарий, в зависимости от персональной информации
     */
    private fun changeScenarioAfterPersonalStep(personalInfo: PersonalInfo) {
        applyExperienceToScenario(personalInfo)
        applyEducationToScenario(personalInfo)
    }

    /**
     * Если нет образования - шаг с заполнением места учебы будет исключен
     */
    private fun applyEducationToScenario(personalInfo: PersonalInfo) {
        when {
            personalInfo.education.isNoEducation() -> {
                steps.removeElem { it is EducationStep }
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
    private fun applyExperienceToScenario(personalInfo: PersonalInfo) {
        if (personalInfo.workingExperience) {
            steps.replaceWith({ it is AboutMeStep },
                ExperienceStep
            )
        } else {
            steps.replaceWith({ it is ExperienceStep },
                AboutMeStep
            )
        }
    }
}