package com.example.progressfeaturesample.ui.screens.education

import com.example.progressfeaturesample.domain.Education
import com.example.progressfeaturesample.domain.EducationType
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Command
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [EducationFragmentView].
 */
@PerScreen
class EducationBindModel @Inject constructor() : BindModel {
    val onNextPressedAction = Action<Unit>()
    val placeChangedAction = Action<String>()
    val facultyChangedAction = Action<String>()
    val specialtyChangedAction = Action<String>()
    val degreeChangedAction = Action<String>()
    val dateFromChangedAction = Action<String>()
    val dateToChangedAction = Action<String>()

    val educationState = State(Education())
    val draftCommand = Command<Education>()
    val educationTypeState = State<EducationType>()
}