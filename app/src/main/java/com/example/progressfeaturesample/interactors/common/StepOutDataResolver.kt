package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepOutData
import io.reactivex.Completable

/**
 * Сущность, обрабатывающая выходные данные
 */
interface StepOutDataResolver<O : StepOutData<out Step>> {

    fun resolveStep(step: O): Completable
}