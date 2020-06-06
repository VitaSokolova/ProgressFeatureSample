package com.example.progressfeaturesample.interactors.application

import com.example.progressfeaturesample.domain.Application
import com.example.progressfeaturesample.interactors.application.steps.*
import com.example.progressfeaturesample.interactors.common.ProgressInteractor
import io.reactivex.Completable
import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import javax.inject.Inject

/**
 * Интерактор фичи подачи заявления
 */
@PerApplication
class ApplicationProgressInteractor @Inject constructor(
    private val dataRepository: ApplicationDataRepository
) : ProgressInteractor<ApplicationSteps, ApplicationStepIn, ApplicationStepOut>() {

    // сценарий оформления
    override val scenario = ApplicationScenario()

    // билдер, для построения заявки
    private val builder = Application.Builder()

    /**
     * Метод получения входной информации для шага
     */
    override fun resolveStepInData(step: ApplicationSteps): Single<ApplicationStepIn> {
        return when (step) {
            is EducationStep -> getDataForEducationStep()
            is MotivationStep -> getDataForMotivationStep()
            else -> Single.just(EmptyStepIn)
        }
    }

    /**
     * Сохранение выходных данных шага в билдер и отправка заявления
     */
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
        return Single.just(
            EducationStepIn(
                builder.getPersonalInfo().education
            )
        )
    }

    private fun getDataForMotivationStep(): Single<ApplicationStepIn> {
        return dataRepository.loadMotivationVariants().map {
            MotivationStepIn(it)
        }
    }
}