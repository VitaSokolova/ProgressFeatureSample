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

    // сущность, отвечающая за получение входных данных
    protected abstract val stepInDataResolver: StepInDataResolver<S, I>
    // сущность, отвечающая за сохранение выходных данных
    protected abstract val stepOutDataResolver: StepOutDataResolver<O>

    protected val stepChangeSubject = BehaviorSubject.create<StepWithPosition<S>>()
    // Observable, на который можно подписаться, чтобы изнать о переходе на другой шаг
    val stepChangeObservable: Observable<StepWithPosition<S>> = stepChangeSubject.hide()

    // сущность, отвечающая за управление составом шагов и переходы
    abstract fun getStepManager(): StepsManager<S>

    /**
     * Возвращает входные данные для шага
     */
    open fun getDataForStep(step: S): Single<I> = stepInDataResolver.resolveStepData(step)

    /**
     * Сохраняет выходные данные шага
     */
    open fun completeStep(stepOut: O): Completable {
        return stepOutDataResolver.resolveStep(stepOut).doOnComplete {
            getStepManager().completeStep(stepOut.step)
            notifyStepChanges()
        }
    }

    /**
     * Переход на предыдущий шаг
     */
    open fun toPreviousStep() {
        getStepManager().backStep()
        notifyStepChanges()
    }


    /**
     * Обновление данных о сценарии для подписчиков
     */
    protected fun notifyStepChanges() {
        stepChangeSubject.onNext(
            getStepManager().getCurrentStep()
        )
    }
}
