package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.interactors.common.Step

sealed class ApplicationSteps : Step
class PersonalInfoStep : ApplicationSteps()
class EducationStep : ApplicationSteps()
class ExperienceStep : ApplicationSteps()
class MotivationStep : ApplicationSteps()