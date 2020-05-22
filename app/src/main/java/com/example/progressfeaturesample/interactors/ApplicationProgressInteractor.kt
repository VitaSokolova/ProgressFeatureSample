package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.domain.Application
import com.example.progressfeaturesample.domain.EducationType
import com.example.progressfeaturesample.interactors.common.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.rx.extension.scheduler.SchedulersProvider
import javax.inject.Inject

/**
 * Интерактор фичи подачи заявления
 */
@PerApplication
class ApplicationProgressInteractor @Inject constructor(
    val dataRepository: ApplicationDataRepository,
    schedulersProvider: SchedulersProvider
) : ProgressInteractor<ApplicationSteps, ApplicationStepIn, ApplicationStepOut>(
    schedulersProvider
) {
    override val stepInDataResolver = ApplicationStepInDataResolver()
    override val stepOutDataResolver = ApplicationStepOutDataResolver()

    // билдер, для построения заявки
    private val builder = Application.Builder()

    private val applicationStepsManager = MutableStepsManager(
        ApplicationScenario()
    )

    override fun getStepManager(): StepsManager<ApplicationSteps> = applicationStepsManager

    fun initScenario() = notifyStepChanges()

    inner class ApplicationStepInDataResolver : StepInDataResolver<ApplicationSteps, ApplicationStepIn> {

        override fun resolveStepData(step: ApplicationSteps): Single<ApplicationStepIn> {
            return when (step) {
                is EducationStep -> getDataForEducationStep()
                is MotivationStep -> getDataForMotivationStep()
                else -> Single.just(EmptyStepIn())
            }
        }

        fun getDataForEducationStep(): Single<ApplicationStepIn> {
            return Single.just(EducationStepIn(builder.getPersonalInfo().education))
        }

        fun getDataForMotivationStep(): Single<ApplicationStepIn> {
            return dataRepository.loadMotivationVariants().map {
                MotivationStepIn(it)
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
                        dataRepository.loadApplication(builder.build())
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
                applicationStepsManager.removeStep(EducationStep())
            } else {
                applicationStepsManager.addStep(1, EducationStep())
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
                applicationStepsManager.replaceStep(AboutMeStep(), ExperienceStep())
            } else {
                applicationStepsManager.replaceStep(ExperienceStep(), AboutMeStep())
            }
        }
    }
}