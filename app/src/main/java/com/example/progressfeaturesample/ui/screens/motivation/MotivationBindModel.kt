package com.example.progressfeaturesample.ui.screens.motivation

import com.example.progressfeaturesample.domain.Motivation
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.android.utilktx.data.wrapper.loadable.LoadableData
import ru.surfstudio.android.utilktx.data.wrapper.selectable.SelectableData
import javax.inject.Inject

/**
 * Модель [MotivationFragmentView].
 */
@PerScreen
class MotivationBindModel @Inject constructor() : BindModel {
    val onNextPressedAction = Action<Unit>()

    val motivationVariantsState = State<LoadableData<List<SelectableData<Motivation>>>>()
}