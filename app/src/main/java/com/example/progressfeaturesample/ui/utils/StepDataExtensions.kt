package com.example.progressfeaturesample.ui.utils

import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepInData
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepOutData
import com.example.progressfeaturesample.interactors.common.step.StepData
import io.reactivex.Single

inline fun <reified I> Single<out StepData<ApplicationStepInData, ApplicationStepOutData>>.filter() =
    this.filter { it is I }.map { it as I }