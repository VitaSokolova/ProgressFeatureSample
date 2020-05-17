package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepOutData
import io.reactivex.Completable

/**
 * Определяет исходящие данные для шага
 */
interface StepOutDataResolver<O : StepOutData<out Step>> {

    fun resolveStep(step: O): Completable
}