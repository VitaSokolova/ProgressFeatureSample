package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepInData
import com.example.progressfeaturesample.interactors.common.step.StepOutData
import com.example.progressfeaturesample.interactors.common.step.StepWithPosition
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import ru.surfstudio.android.rx.extension.scheduler.SchedulersProvider

/**
 * Базовый класс для интеракторов пошаговых фич
 */
abstract class ProgressInteractor<S : Step, I : StepInData, O : StepOutData<S>>(val schedulersProvider: SchedulersProvider) {

    // сущность, отвечающая за управление составом шагов и переходы
    protected abstract val scenario: Scenario<S, O>

    private val stepChangeSubject = BehaviorSubject.create<StepWithPosition<S>>()

    // Observable, на который можно подписаться, чтобы изнать о переходе на другой шаг
    val stepChangeObservable: Observable<StepWithPosition<S>> = stepChangeSubject.hide()

    private var currentStepIndex: Int = 0

    protected abstract fun resolveStepOutData(step: O): Completable

    protected abstract fun resolveStepInData(step: S): Single<I>

    fun getDataForStep(step: S): Single<I> = resolveStepInData(step)

    /**
     * Сохраняет выходные данные шага
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
    open fun toPreviousStep() {
        if (currentStepIndex != 0 && currentStepIndex != -1) {
            currentStepIndex -= 1
        }
        notifyStepChanges()
    }


    /**
     * Обновление данных о сценарии для подписчиков
     */
    protected fun notifyStepChanges() {
        stepChangeSubject.onNext(
            StepWithPosition(
                scenario.steps[currentStepIndex],
                currentStepIndex,
                scenario.steps.count()
            )
        )
    }
}
