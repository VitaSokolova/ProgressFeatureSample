package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepInData
import io.reactivex.Single

/**
 * Сущность, определяющая входящие данные для шага
 */
interface StepInDataResolver<S : Step, I : StepInData> {
    fun resolveStepData(step: S): Single<I>
}
