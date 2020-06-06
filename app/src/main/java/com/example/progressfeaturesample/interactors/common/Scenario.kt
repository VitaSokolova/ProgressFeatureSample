package com.example.progressfeaturesample.interactors.common

import com.example.progressfeaturesample.interactors.common.step.Step
import com.example.progressfeaturesample.interactors.common.step.StepOutData

/**
 * Интерфейс, которому должны удовлетворять все классы, описывающие порядок шагов в фиче
 */
interface Scenario<S : Step, O : StepOutData<S>> {
    /**
     * Список шагов
     */
    val steps: List<S>

    /**
     * Внесение изменений в сценарий, в зависимости от выходной информации шага
     */
    fun completeStep(stepOut: O)
}