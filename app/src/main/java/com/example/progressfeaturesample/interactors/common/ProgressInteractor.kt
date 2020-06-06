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
    protected abstract val stepManager: StepsManager<S, O>

    private val stepChangeSubject = BehaviorSubject.create<StepWithPosition<S>>()

    // Observable, на который можно подписаться, чтобы изнать о переходе на другой шаг
    val stepChangeObservable: Observable<StepWithPosition<S>> = stepChangeSubject.hide()

    protected abstract fun resolveStepOutData(step: O): Completable

    protected abstract fun resolveStepInData(step: S): Single<I>

    fun getDataForStep(step: S): Single<I> = resolveStepInData(step)

    /**
     * Сохраняет выходные данные шага
     */
    fun completeStep(stepOut: O): Completable {
        return resolveStepOutData(stepOut).doOnComplete {
            stepManager.completeStep(stepOut)
            notifyStepChanges()
        }
    }

    /**
     * Переход на предыдущий шаг
     */
    open fun toPreviousStep() {
        stepManager.backStep()
        notifyStepChanges()
    }


    /**
     * Обновление данных о сценарии для подписчиков
     */
    protected fun notifyStepChanges() {
        stepChangeSubject.onNext(
            stepManager.getCurrentStep()
        )
    }
}
