package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepInData
import com.example.progressfeaturesample.interactors.common.step.StepOutData
import com.example.progressfeaturesample.interactors.common.step.StepWithPosition
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
abstract class ProgressInteractor<S : Step, I : StepInData, O : StepOutData<S>>() {

    // сущность, отвечающая за состав и порядок шагов
    protected abstract val scenario: Scenario<S, O>

    private val stepChangeSubject = BehaviorSubject.create<StepWithPosition<S>>()

    // Observable, на который можно подписаться, чтобы узнать о переходе на другой шаг
    val stepChangeObservable: Observable<StepWithPosition<S>> = stepChangeSubject.hide()

    // текущий активный шаг
    private var currentStepIndex: Int = 0

    /**
     * Метод получения входной информации для шага
     */
    protected abstract fun resolveStepInData(step: S): Single<I>

    /**
     * Метод обработки выходной информации для шага
     */
    protected abstract fun resolveStepOutData(step: O): Completable

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
    fun getDataForStep(step: S): Single<I> = resolveStepInData(step)

    /**
     * Сохранение выходных данные шага
     */
    fun completeStep(stepOut: O): Completable {
        return resolveStepOutData(stepOut).doOnComplete {
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
            StepWithPosition(
                scenario.steps[currentStepIndex],
                currentStepIndex,
                scenario.steps.count()
            )
        )
    }
}
