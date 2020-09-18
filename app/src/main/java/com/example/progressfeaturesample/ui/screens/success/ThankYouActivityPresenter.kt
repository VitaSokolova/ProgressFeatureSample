package com.example.progressfeaturesample.ui.screens.success

import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер [ThankYouActivityView].
 */
@PerScreen
class ThankYouActivityPresenter @Inject constructor(
        private val bm: ThankYouBindModel,
        private val activityNavigator: ActivityNavigator,
        private val progressInteractor: ApplicationProgressInteractor,
        basePresenterDependency: BasePresenterDependency
): BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.closeAction bindTo {
            activityNavigator.finishAffinity()
            progressInteractor.closeProgressFeature()
        }
    }
}