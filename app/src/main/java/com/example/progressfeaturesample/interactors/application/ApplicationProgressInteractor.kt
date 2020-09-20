package com.example.progressfeaturesample.interactors.application

import com.example.progressfeaturesample.domain.Application
import com.example.progressfeaturesample.interactors.application.steps.*
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepData.*
import com.example.progressfeaturesample.interactors.application.steps.ApplicationSteps.*
import com.example.progressfeaturesample.interactors.common.ProgressInteractor
import io.reactivex.Completable
import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import javax.inject.Inject

/**
 * Интерактор фичи подачи заявления
 * @param dataRepository репозиторий, оборачивающий работу с сетью
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
        this.draft.apply {
            clear()
            outDataMap.putAll(draft.outDataMap)
        }
    }

    override fun closeProgressFeature() {
        super.closeProgressFeature()
        draft.clear()
    }

    /**
     * Метод получения входной информации для шага
     */
    override fun resolveStepInData(step: ApplicationSteps): Single<ApplicationStepData> {
        return when (step) {
            PERSONAL_INFO -> Single.just(
                PersonalInfoStepData(
                    draft.getPersonalInfoOutData()
                )
            )
            EDUCATION -> getDataForEducationStep().map { stepInData ->
                EducationStepData(
                    stepInData,
                    draft.getEducationStepOutData()
                )
            }
            EXPERIENCE -> Single.just(
                ExperienceStepData(
                    draft.getExperienceStepOutData()
                )
            )
            ABOUT_ME -> Single.just(
                AboutMeStepData(
                    draft.getAboutMeStepOutData()
                )
            )
            MOTIVATION -> dataRepository.loadMotivationVariants().map { reasonsList ->
                MotivationStepData(
                    stepInData = MotivationStepInData(reasonsList),
                    stepOutData = draft.getMotivationStepOutData()
                )
            }
        }
    }

    /**
     * Сохранение выходных данных шага в черновик
     */
    override fun saveStepOutData(stepData: ApplicationStepOutData): Completable {
        return Completable.fromAction {
            when (stepData) {
                is PersonalInfoStepOutData -> {
                    draft.outDataMap[PERSONAL_INFO] = stepData
                }
                is EducationStepOutData -> {
                    draft.outDataMap[EDUCATION] = stepData
                }
                is ExperienceStepOutData -> {
                    draft.outDataMap[EXPERIENCE] = stepData
                }
                is AboutMeStepOutData -> {
                    draft.outDataMap[ABOUT_ME] = stepData
                }
                is MotivationStepOutData -> {
                    draft.outDataMap[MOTIVATION] = stepData
                }
            }
        }
    }

    /**
     * Отправка заявки
     */
    fun sendApplication(): Completable {
        val builder = Application.Builder().apply {
            draft.outDataMap.values.forEach { data ->
                when (data) {
                    is PersonalInfoStepOutData -> personalInfo(data.info)
                    is EducationStepOutData -> education(data.education)
                    is ExperienceStepOutData -> experience(data.experience)
                    is AboutMeStepOutData -> experience(data.info)
                    is MotivationStepOutData -> motivation(data.motivation)
                }
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
}