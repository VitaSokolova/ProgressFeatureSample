package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.interactors.common.step.Step

/**
 * Класс для ограничения иерархии классов с шагами
 */
enum class ApplicationSteps : Step {
    PERSONAL_INFO,// Шаг с персональными данными
    EDUCATION,// Шаг с инф-цией об образовании
    EXPERIENCE,// Шаг с инф-цией об опыте работы
    ABOUT_ME,// Шаг для написания эссе "о себе"
    MOTIVATION// Шаг для выбора, что интересно в данной вакансии
}