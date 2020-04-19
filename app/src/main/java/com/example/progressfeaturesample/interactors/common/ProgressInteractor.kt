package com.example.progressfeaturesample.interactors.common

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class ProgressInteractor<S : Step> {

    protected abstract val scenario: Scenario<S>
//    protected abstract val stepInDataResolver: StepInDataResolver<S, I>
//    protected abstract val stepOutDataResolver: StepOutDataResolver<O>

//    protected val stepCountPublishSubject = BehaviorSubject.create<Int>()
//    val stepCountObservable = stepCountPublishSubject.hide()
//    protected val stepOrderPublishSubject = BehaviorSubject.create<Int>()
//    val stepOrderObservable = stepOrderPublishSubject.hide()

    protected val stepChangeSubject = BehaviorSubject.create<StepWithPosition<S>>()
    val stepChangeObservable: Observable<StepWithPosition<S>> = stepChangeSubject.hide()

    open fun completeStep(stepOut: S) {
        scenario.completeStep(stepOut)
        notifyStepChanges()
    }

    /**
     * Перейти на предыдущий шаг
     */
    open fun toPreviousStep() =
        scenario.apply {
            backStep()
            notifyStepChanges()
        }
//
//    open fun getDataForStep(step: S): Single<I> =
//        stepInDataResolver.resolveStepData(scenario!!.currentStep)

    /**
     * обновление данных о сценарии для подписчиков
     */
    protected fun notifyStepChanges() {
        scenario.let {
            stepChangeSubject.onNext(
                StepWithPosition(
                    it.currentStep,
                    it.getCurrentStepNumber(),
                    it.steps.count()
                )
            )
        }
    }
}
