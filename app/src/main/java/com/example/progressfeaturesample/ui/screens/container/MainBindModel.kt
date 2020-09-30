package com.example.progressfeaturesample.ui.screens.container

import com.example.progressfeaturesample.interactors.application.steps.ApplicationStep
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [MainActivity].
 */
@PerScreen
class MainBindModel @Inject constructor() : BindModel {
    val currentStepState = State<ApplicationStep>()
    val currentStepCount = State(StepPosition(-1, 0))

    val onBackPressedClicked = Action<Unit>()
}

class StepPosition(
    val position: Int,
    val allStepsCount: Int
)