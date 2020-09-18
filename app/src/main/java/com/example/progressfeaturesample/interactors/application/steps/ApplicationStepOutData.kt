package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.domain.*
import com.example.progressfeaturesample.interactors.common.step.StepOutData
import java.io.Serializable

/**
 * Класс, описывающий результат работы шага из
 * [com.example.progressfeaturesample.interactors.application.ApplicationScenario]
 */
sealed class ApplicationStepOutData : StepOutData, Serializable

/**
 * Результат шага "Персональная информация"
 */
class PersonalInfoStepOutData(
    val info: PersonalInfo
) : ApplicationStepOutData()

/**
 * Результат шага "Образование"
 */
class EducationStepOutData(
    val education: Education
) : ApplicationStepOutData()

/**
 * Результат шага "Места работы"
 */
class ExperienceStepOutData(
    val experience: WorkingExperience
) : ApplicationStepOutData()

/**
 * Результат шага "Обо мне"
 */
class AboutMeStepOutData(
    val info: AboutMe
) : ApplicationStepOutData()

/**
 * Результат шага "Выбор причин"
 */
class MotivationStepOutData(
    val motivation: List<Motivation>
) : ApplicationStepOutData()