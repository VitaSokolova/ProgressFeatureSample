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

    // черновик заявки
    private val draft: ApplicationDraft = ApplicationDraft()

    fun applyDraft(draft: ApplicationDraft) {
        this.draft.outDataMap.putAll(draft.outDataMap)
    }

    fun getDraft(): ApplicationDraft = draft

    /**
     * Метод получения входной информации для шага
     */
    override fun resolveStepInData(stepData: ApplicationSteps): Single<ApplicationStepData> {
        return when (stepData) {
            PersonalInfoStep -> Single.just(
                ApplicationStepData.PersonalInfoStepData(
                    draft.getPersonalInfoOutData()
                )
            )
            EducationStep -> getDataForEducationStep().map {
                ApplicationStepData.EducationStepData(
                    it,
                    draft.getEducationStepOutData()
                )
            }
            ExperienceStep -> Single.just(
                ApplicationStepData.ExperienceStepData(
                    draft.getExperienceStepOutData()
                )
            )
            AboutMeStep -> Single.just(
                ApplicationStepData.AboutMeStepData(
                    draft.getAboutMeStepOutData()
                )
            )
            MotivationStep -> getDataForMotivationStep().map {
                ApplicationStepData.MotivationStepData(
                    it,
                    draft.getMotivationStepOutData()
                )
            }
        }
    }

    /**
     * Сохранение выходных данных шага в билдер и отправка заявления
     */
    override fun saveStepOutData(stepData: ApplicationStepOutData): Completable {
        return Completable.fromAction {
            when (stepData) {
                is PersonalInfoStepOutData -> {
                    draft.outDataMap[PersonalInfoStep] = stepData
                }
                is EducationStepOutData -> {
                    draft.outDataMap[EducationStep] = stepData
                }
                is ExperienceStepOutData -> {
                    draft.outDataMap[ExperienceStep] = stepData
                }
                is AboutMeStepOutData -> {
                    draft.outDataMap[AboutMeStep] = stepData
                }
                is MotivationStepOutData -> {
                    draft.outDataMap[MotivationStep] = stepData
                }
            }
        }
    }

    fun sendApplication(): Completable {
        // билдер, для построения заявки
        val builder = Application.Builder()
        draft.outDataMap.values.forEach { data ->
            when (data) {
                is PersonalInfoStepOutData -> builder.personalInfo(data.info)
                is EducationStepOutData -> builder.education(data.education)
                is ExperienceStepOutData -> builder.experience(data.experience)
                is AboutMeStepOutData -> builder.experience(data.info)
                is MotivationStepOutData -> builder.motivation(data.motivation)
            }
        }
        return dataRepository.loadApplication(builder.build())
    }

    private fun getDataForEducationStep(): Single<EducationStepInData> {
        return Single.just(
            EducationStepInData(
                draft.getPersonalInfoOutData()?.info?.education ?: error("Not enough data")
            )
        )
    }

    private fun getDataForMotivationStep(): Single<MotivationStepInData> {
        return dataRepository.loadMotivationVariants().map {
            MotivationStepInData(it)
        }
    }
}