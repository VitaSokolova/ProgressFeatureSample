package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.domain.Application
import com.example.progressfeaturesample.interactors.common.ProgressInteractor
import com.example.progressfeaturesample.interactors.common.StepsManager
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
    // билдер, для построения заявки
    private val builder = Application.Builder()
    private val scenario = ApplicationScenario()
    override val stepManager: StepsManager<ApplicationSteps, ApplicationStepOut> = StepsManager(scenario)

    fun initScenario() = notifyStepChanges()

    override fun resolveStepInData(step: ApplicationSteps): Single<ApplicationStepIn> {
        return when (step) {
            is EducationStep -> getDataForEducationStep()
            is MotivationStep -> getDataForMotivationStep()
            else -> Single.just(EmptyStepIn())
        }
    }

    override fun resolveStepOutData(step: ApplicationStepOut): Completable {
        return Completable.fromAction {
            when (step) {
                is PersonalInfoStepOut -> builder.personalInfo(step.info)
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

    private fun getDataForEducationStep(): Single<ApplicationStepIn> {
        return Single.just(EducationStepIn(builder.getPersonalInfo().education))
    }

    private fun getDataForMotivationStep(): Single<ApplicationStepIn> {
        return dataRepository.loadMotivationVariants().map {
            MotivationStepIn(it)
        }
    }
}