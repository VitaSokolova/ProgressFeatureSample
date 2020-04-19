package com.example.progressfeaturesample.interactors.common

class StepWithPosition<S : Step>(
    val step: S,
    val position: Int,
    val allStepsCount: Int
)