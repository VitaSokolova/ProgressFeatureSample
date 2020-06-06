package com.example.progressfeaturesample.ui.screens.experience

import com.example.progressfeaturesample.domain.WorkingExperience
import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.application.steps.ExperienceStepOut
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер [ExperienceFragmentView].
 */
@PerScreen
class ExperienceFragmentPresenter @Inject constructor(
    private val bm: ExperienceBindModel,
    private val progressInteractor: ApplicationProgressInteractor,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.onNextPressedAction bindTo {
            subscribeIoHandleError(
                progressInteractor.completeStep(
                    ExperienceStepOut(
                        WorkingExperience()
                    )
                ),
                {}
            )
        }
    }
}