package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 * Базовый класс для интеракторов пошаговых фич
 * S - входной шаг
 * I - входные данные для шагов
 * O - выходные данные для шагов
 */
abstract class ProgressInteractor<S : Step, I : StepInData, O : StepOutData> {

    // сущность, отвечающая за состав и порядок шагов
    protected abstract val scenario: Scenario<S, O>

    private val stepChangeSubject = BehaviorSubject.create<StepPositionData<S>>()

    // Observable, на который можно подписаться, чтобы узнать о переходе на другой шаг
    val stepChangeObservable: Observable<StepPositionData<S>> = stepChangeSubject.hide()

    // текущий активный шаг
    private var currentStepIndex: Int = 0

    /**
     * Метод получения входной информации для шага
     */
    protected abstract fun resolveStepInData(stepData: S): Single<out StepData<I, O>>

    /**
     * Метод обработки выходной информации для шага
     */
    protected abstract fun saveStepOutData(stepData: O): Completable

    /**
     * Инициализация работы интерактора
     */
    open fun initProgressFeature(){
        currentStepIndex = 0
        notifyStepChanges()
    }

    /**
     * Предоставление входные параметров для шага
     */
    fun getDataForStep(step: S): Single<out StepData<I, O>> = resolveStepInData(step)

    /**
     * Сохранение выходных данные шага
     */
    fun completeStep(stepOut: O): Completable {
        return saveStepOutData(stepOut).doOnComplete {
            scenario.completeStep(stepOut)

            if (currentStepIndex != scenario.steps.lastIndex && currentStepIndex != -1) {
                currentStepIndex += 1
            }

            notifyStepChanges()
        }
    }

    /**
     * Переход на предыдущий шаг
     */
    fun toPreviousStep() {
        if (currentStepIndex != 0 && currentStepIndex != -1) {
            currentStepIndex -= 1
        }
        notifyStepChanges()
    }


    /**
     * Обновление данных о сценарии для подписчиков
     */
    private fun notifyStepChanges() {
        stepChangeSubject.onNext(
            StepPositionData(
                scenario.steps[currentStepIndex],
                currentStepIndex,
                scenario.steps.count()
            )
        )
    }
}
