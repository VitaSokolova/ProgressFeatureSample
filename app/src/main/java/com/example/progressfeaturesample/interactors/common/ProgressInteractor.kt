package com.example.progressfeaturesample.interactors.common

import androidx.annotation.CallSuper
import com.example.progressfeaturesample.interactors.common.step.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

const val UNKNOWN_STEP_INDEX = -1
/**
 * Базовый класс для интеракторов пошаговых фич
 * S - входной шаг
 * I - входные данные для шагов
 * O - выходные данные для шагов
 */
abstract class ProgressInteractor<S : Step, I : StepInData, O : StepOutData> {

    // сущность, отвечающая за состав и порядок шагов
    protected abstract val scenario: Scenario<S, O>

    private val stepChangeSubject = BehaviorSubject.create<StepWithPosition<S>>()

    // Observable, на который можно подписаться, чтобы узнать о переходе на другой шаг
    val stepChangeObservable: Observable<StepWithPosition<S>> = stepChangeSubject.hide()

    // текущий активный шаг
    private var currentStepIndex: Int
        get() = stepChangeSubject.value?.position ?: 0
        set(value) {
            stepChangeSubject.onNext(
                StepWithPosition(
                    step = scenario.steps[value],
                    position = value,
                    allStepsCount = scenario.steps.count()
                )
            )
        }

    /**
     * Метод получения входной информации для шага
     */
    protected abstract fun resolveStepInData(step: S): Single<out StepData<I, O>>

    /**
     * Метод обработки выходной информации для шага
     */
    protected abstract fun saveStepOutData(stepOut: O): Completable

    /**
     * Инициализация работы интерактора
     */
    @CallSuper
    open fun initProgressFeature() {
        currentStepIndex = 0
    }

    /**
     * Завершение работы интерактора
     */
    @CallSuper
    open fun closeProgressFeature() {
        currentStepIndex = 0
    }

    /**
     * Предоставление входных параметров для шага
     */
    fun getDataForStep(step: S): Single<out StepData<I, O>> = resolveStepInData(step)

    /**
     * Завершение текущего шага и переход к следующему
     */
    fun completeStep(stepOut: O): Completable {
        return saveStepOutData(stepOut).doOnComplete {
            scenario.reactOnStepCompletion(stepOut)
            if (currentStepIndex !in listOf(scenario.steps.lastIndex, UNKNOWN_STEP_INDEX)) {
                currentStepIndex += 1
            }
        }
    }

    /**
     * Переход на предыдущий шаг
     */
    fun toPreviousStep() {
        if (currentStepIndex !in listOf(0, UNKNOWN_STEP_INDEX)) {
            currentStepIndex -= 1
        }
    }
}
