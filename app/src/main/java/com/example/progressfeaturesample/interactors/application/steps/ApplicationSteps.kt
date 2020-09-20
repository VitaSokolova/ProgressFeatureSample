package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.interactors.common.step.Step

/**
 * Шаги в фиче заполнения заявки
 */
enum class ApplicationSteps : Step {
    PERSONAL_INFO,  // персональные данные
    EDUCATION,      // образование
    EXPERIENCE,     // опыт работы
    ABOUT_ME,       // эссе "о себе"
    MOTIVATION      // что интересно в данной вакансии
}