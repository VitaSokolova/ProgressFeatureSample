package com.example.progressfeaturesample.ui.screens.motivation

import com.example.progressfeaturesample.domain.Motivation
import com.example.progressfeaturesample.interactors.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.MotivationStepOut
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер [MotivationFragmentView].
 */
@PerScreen
class MotivationFragmentPresenter @Inject constructor(
    private val bm: MotivationBindModel,
    private val progressInteractor: ApplicationProgressInteractor,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.onNextPressedAction bindTo {
            subscribeIoHandleError(
                progressInteractor.completeStep(
                    MotivationStepOut(
                        Motivation("Mew")
                    )
                ), {})
        }
    }
}