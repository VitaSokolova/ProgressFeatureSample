package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.interactors.common.step.Step

/**
 * Класс для ограничения иерархии классов с шагами
 */
sealed class ApplicationSteps : Step
// Шаг с персональными данными
object PersonalInfoStep : ApplicationSteps()
// Шаг с инф-цией об образовании
object EducationStep : ApplicationSteps()
// Шаг с инф-цией об опыте работы
object ExperienceStep : ApplicationSteps()
// Шаг для написания эссе "о себе"
object AboutMeStep : ApplicationSteps()
// Шаг для выбора, что интересно в данной вакансии
object MotivationStep : ApplicationSteps()