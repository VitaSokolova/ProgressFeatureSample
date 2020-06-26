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
) : ProgressInteractor<ApplicationSteps, ApplicationStepInData, ApplicationStepOutData>() {

    // сценарий оформления
    override val scenario = ApplicationScenario()

    // билдер, для построения заявки
    private val builder = Application.Builder()

    /**
     * Метод получения входной информации для шага
     */
    override fun resolveStepInData(step: ApplicationSteps): Single<ApplicationStepInData> {
        return when (step) {
            is EducationStep -> getDataForEducationStep()
            is MotivationStep -> getDataForMotivationStep()
            else -> Single.just(EmptyStepInData)
        }
    }

    /**
     * Сохранение выходных данных шага в билдер и отправка заявления
     */
    override fun saveStepOutData(stepData: ApplicationStepOutData): Completable {
        return Completable.fromAction {
            when (stepData) {
                is PersonalInfoStepOutData -> builder.personalInfo(stepData.info)
                is EducationStepOutData -> builder.education(stepData.education)
                is ExperienceStepOutData -> builder.experience(stepData.experience)
                is AboutMeStepOutData -> builder.experience(stepData.info)
                is MotivationStepOutData -> {
                    builder.motivation(stepData.motivation)
                    dataRepository.loadApplication(builder.build())
                }
            }
        }
    }

    private fun getDataForEducationStep(): Single<ApplicationStepInData> {
        return Single.just(
            EducationStepInData(
                builder.getPersonalInfo().education
            )
        )
    }

    private fun getDataForMotivationStep(): Single<ApplicationStepInData> {
        return dataRepository.loadMotivationVariants().map {
            MotivationStepInData(it)
        }
    }
}