package com.example.progressfeaturesample.interactors.common.step

/**
 * Модель для описания шага и его позиции в сценарии
 */
class StepWithPosition<S : Step>(
    val step: S,
    val position: Int,
    val allStepsCount: Int
)