package com.example.progressfeaturesample.ui.utils

import com.example.progressfeaturesample.interactors.ApplicationStepIn
import io.reactivex.Single

inline fun <reified I> Single<ApplicationStepIn>.filter() = this.filter { it is I }.map { it as I }