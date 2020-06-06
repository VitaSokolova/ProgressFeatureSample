package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.domain.*
import com.example.progressfeaturesample.interactors.common.step.StepOutData

/**
 * Класс, описывающий результат работы шага из
 * [com.example.progressfeaturesample.interactors.application.ApplicationScenario]
 */
sealed class ApplicationStepOut : StepOutData<ApplicationSteps>

/**
 * Результат шага "Персональная информация"
 */
class PersonalInfoStepOut(
    val info: PersonalInfo,
    override val step: ApplicationSteps = PersonalInfoStep
) : ApplicationStepOut()

/**
 * Результат шага "Образование"
 */
class EducationStepOut(
    val education: Education,
    override val step: ApplicationSteps = EducationStep
) : ApplicationStepOut()

/**
 * Результат шага "Места работы"
 */
class ExperienceStepOut(
    val experience: WorkingExperience,
    override val step: ApplicationSteps = ExperienceStep
) : ApplicationStepOut()

/**
 * Результат шага "Обо мне"
 */
class AboutMeStepOut(
    val info: AboutMe,
    override val step: ApplicationSteps = AboutMeStep
) : ApplicationStepOut()

/**
 * Результат шага "Выбор причин"
 */
class MotivationStepOut(
    val motivation: Motivation,
    override val step: ApplicationSteps = AboutMeStep
) : ApplicationStepOut()