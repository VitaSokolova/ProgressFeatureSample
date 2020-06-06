package com.example.progressfeaturesample.interactors.application.steps

import com.example.progressfeaturesample.interactors.common.step.Step

sealed class ApplicationSteps : Step
object PersonalInfoStep : ApplicationSteps()
object EducationStep : ApplicationSteps()
object ExperienceStep : ApplicationSteps()
object AboutMeStep : ApplicationSteps()
object MotivationStep : ApplicationSteps()