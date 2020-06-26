package com.example.progressfeaturesample.ui.utils

import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepInData
import io.reactivex.Single

inline fun <reified I> Single<ApplicationStepInData>.filter() = this.filter { it is I }.map { it as I }