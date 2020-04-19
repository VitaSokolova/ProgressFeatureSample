package com.example.progressfeaturesample.ui.screens.education

import com.example.progressfeaturesample.interactors.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.EducationStep
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер [EducationFragmentView].
 */
@PerScreen
class EducationFragmentPresenter @Inject constructor(
    private val bm: EducationBindModel,
    private val progressInteractor: ApplicationProgressInteractor,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.onNextPressedAction bindTo {
            progressInteractor.completeStep(EducationStep())
        }
    }
}