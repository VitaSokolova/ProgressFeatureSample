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

abstract class ProgressInteractor<S : Step, I : StepInData, O : StepOutData<S>>(val schedulersProvider: SchedulersProvider) {

    protected abstract val scenario: Scenario<S>
    protected abstract val stepInDataResolver: StepInDataResolver<S, I>
    protected abstract val stepOutDataResolver: StepOutDataResolver<O>

    protected val stepOrderPublishSubject = BehaviorSubject.create<Int>()
    val stepOrderObservable = stepOrderPublishSubject.hide()

    protected val stepChangeSubject = BehaviorSubject.create<StepWithPosition<S>>()
    val stepChangeObservable: Observable<StepWithPosition<S>> = stepChangeSubject.hide()


    open fun getDataForStep(step: S): Single<I> =
        stepInDataResolver.resolveStepData(step)

    open fun completeStep(stepOut: O): Completable {
        return stepOutDataResolver.resolveStep(stepOut).doOnComplete {
            scenario.completeStep(stepOut.step)
            notifyStepChanges()
        }
    }

    /**
     * Перейти на предыдущий шаг
     */
    open fun toPreviousStep() =
        scenario.apply {
            backStep()
            notifyStepChanges()
        }


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
