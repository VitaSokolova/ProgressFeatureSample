package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.domain.*
import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepInData
import com.example.progressfeaturesample.interactors.common.step.StepOutData

sealed class ApplicationSteps : Step
class PersonalInfoStep : ApplicationSteps() {
    override fun equals(other: Any?): Boolean {
        return other is PersonalInfoStep
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class EducationStep : ApplicationSteps() {
    override fun equals(other: Any?): Boolean {
        return other is EducationStep
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class ExperienceStep : ApplicationSteps() {
    override fun equals(other: Any?): Boolean {
        return other is ExperienceStep
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class AboutMeStep : ApplicationSteps() {
    override fun equals(other: Any?): Boolean {
        return other is AboutMeStep
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class MotivationStep : ApplicationSteps() {
    override fun equals(other: Any?): Boolean {
        return other is MotivationStep
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

sealed class ApplicationStepIn : StepInData
class EducationStepIn(val educationType: EducationType) : ApplicationStepIn()
class MotivationStepIn(val predefinedValues: List<Motivation>) : ApplicationStepIn()
class EmptyStepIn() : ApplicationStepIn()

sealed class ApplicationStepOut : StepOutData<ApplicationSteps>

class PersonalInfoStepOut(
    val info: PersonalInfo,
    override val step: ApplicationSteps = PersonalInfoStep()
) : ApplicationStepOut()

class EducationStepOut(
    val education: Education,
    override val step: ApplicationSteps = EducationStep()
) : ApplicationStepOut()

class ExperienceStepOut(
    val experience: WorkingExperience,
    override val step: ApplicationSteps = ExperienceStep()
) : ApplicationStepOut()

class AboutMeStepOut(
    val info: AboutMe,
    override val step: ApplicationSteps = AboutMeStep()
) : ApplicationStepOut()

class MotivationStepOut(
    val motivation: Motivation,
    override val step: ApplicationSteps = AboutMeStep()
) : ApplicationStepOut()