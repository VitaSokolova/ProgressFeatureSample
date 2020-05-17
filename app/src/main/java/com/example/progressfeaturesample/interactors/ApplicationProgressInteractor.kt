package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.domain.Application
import com.example.progressfeaturesample.domain.EducationType
import com.example.progressfeaturesample.interactors.common.ProgressInteractor
import com.example.progressfeaturesample.interactors.common.StepInDataResolver
import com.example.progressfeaturesample.interactors.common.StepOutDataResolver
import io.reactivex.Completable
import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.rx.extension.scheduler.SchedulersProvider
import javax.inject.Inject

@PerApplication
class ApplicationProgressInteractor @Inject constructor(
    val dataInteractor: ApplicationDataInteractor,
    schedulersProvider: SchedulersProvider
) : ProgressInteractor<ApplicationSteps, ApplicationStepIn, ApplicationStepOut>(
    schedulersProvider
) {
    override val scenario = ApplicationScenario()

    override val stepInDataResolver = ApplicationStepInDataResolver()
    override val stepOutDataResolver = ApplicationStepOutDataResolver()

    val builder = Application.Builder()

    fun initScenario() = notifyStepChanges()

    inner class ApplicationStepInDataResolver :
        StepInDataResolver<ApplicationSteps, ApplicationStepIn> {

        override fun resolveStepData(step: ApplicationSteps): Single<ApplicationStepIn> {
            return when (step) {
                is EducationStep -> Single.just(EducationStepIn(builder.personal.education))
                is MotivationStep -> dataInteractor.loadMotivationVariants().map {
                    MotivationStepIn(it)
                }
                else -> Single.just(EmptyStepIn())
            }
        }
    }

    inner class ApplicationStepOutDataResolver : StepOutDataResolver<ApplicationStepOut> {

        override fun resolveStep(step: ApplicationStepOut): Completable {
            return Completable.fromAction {
                when (step) {
                    is PersonalInfoStepOut -> {
                        builder.personalInfo(step.info)
                        applyExperienceToScenario(step.info.workingExperience)
                        applyEducationToScenario(step.info.education)
                    }
                    is EducationStepOut -> builder.education(step.education)
                    is ExperienceStepOut -> builder.experience(step.experience)
                    is AboutMeStepOut -> builder.experience(step.info)
                    is MotivationStepOut -> {
                        builder.motivation(step.motivation)
                        dataInteractor.loadApplication(builder.build())
                    }
                }
            }
        }

        /**
         * Метод, изменяющий список шагов, в зависимости от выбранного образования
         *
         * Т.к. пользователь может возвращаться назад и выбирать другие варианты,
         * обновление сценария должно быть симметричным
         */
        private fun applyEducationToScenario(education: EducationType) {
            if (education == EducationType.NO_EDUCATION) {
                scenario.removeStep(EducationStep())
            } else {
                scenario.addStep(1, EducationStep())
            }
        }

        /**
         * Метод, изменяющий список шагов, в зависимости от выбранного опыта работы
         *
         * Т.к. пользователь может возвращаться назад и выбирать другие варианты,
         * обновление сценария должно быть симметричным
         */
        private fun applyExperienceToScenario(hasExperience: Boolean) {
            if (hasExperience) {
                scenario.replaceStep(AboutMeStep(), ExperienceStep())
            } else {
                scenario.replaceStep(ExperienceStep(), AboutMeStep())
            }
        }
    }
}