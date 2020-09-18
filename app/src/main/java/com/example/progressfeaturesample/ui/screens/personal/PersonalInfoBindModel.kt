package com.example.progressfeaturesample.ui.screens.personal

import com.example.progressfeaturesample.domain.EducationType
import com.example.progressfeaturesample.interactors.application.steps.PersonalInfoStepOutData
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [PersonalInfoFragmentView].
 */
@PerScreen
class PersonalInfoBindModel @Inject constructor() : BindModel {
    val onNextPressedAction = Action<Unit>()

    val nameAction = Action<String>()
    val surnameAction = Action<String>()
    val educationSelectedAction = Action<EducationType>()
    val experienceCheckedAction = Action<Boolean>(false)

    val draftData = State<PersonalInfoStepOutData>()
}