package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.domain.EducationType
import com.example.progressfeaturesample.domain.Motivation
import com.example.progressfeaturesample.interactors.common.step.StepInData

/**
 * Класс, описывающий входные данные для работы шагов из
 * [com.example.progressfeaturesample.interactors.application.ApplicationScenario]
 */
sealed class ApplicationStepInData : StepInData

/**
 * Входные данные для шага об образовании
 */
class EducationStepInData(val educationType: EducationType) : ApplicationStepInData()

/**
 * Входные данные для шага о мотивации
 */
class MotivationStepInData(val predefinedValues: List<Motivation>) : ApplicationStepInData()

/**
 * Заглушка, обозначающая, что на вход шагу ничего не нужно
 */
object EmptyStepInData : ApplicationStepInData()