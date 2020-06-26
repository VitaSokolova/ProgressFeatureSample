package com.example.progressfeaturesample.ui.screens.about

import com.example.progressfeaturesample.domain.AboutMe
import com.example.progressfeaturesample.interactors.application.steps.AboutMeStepOutData
import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер [AboutMeFragmentView].
 */
@PerScreen
class AboutMeFragmentPresenter @Inject constructor(
    private val bm: AboutMeBindModel,
    private val progressInteractor: ApplicationProgressInteractor,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.onNextPressedAction bindTo {
            subscribeIoHandleError(progressInteractor.completeStep(
                AboutMeStepOutData(
                    AboutMe("I'm breathtaking")
                )
            ),
                {}
            )
        }
    }
}