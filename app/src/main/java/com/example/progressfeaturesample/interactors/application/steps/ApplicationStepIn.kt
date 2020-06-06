package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.domain.EducationType
import com.example.progressfeaturesample.domain.Motivation
import com.example.progressfeaturesample.interactors.common.step.StepInData

/**
 * Класс, описывающий входные данные для работы шагов из
 * [com.example.progressfeaturesample.interactors.application.ApplicationScenario]
 */
sealed class ApplicationStepIn : StepInData

/**
 * Входные данные для шага об образовании
 */
class EducationStepIn(val educationType: EducationType) : ApplicationStepIn()

/**
 * Входные данные для шага о мотивации
 */
class MotivationStepIn(val predefinedValues: List<Motivation>) : ApplicationStepIn()

/**
 * Заглушка, обозначающая, что на вход шагу ничего не нужно
 */
object EmptyStepIn : ApplicationStepIn()