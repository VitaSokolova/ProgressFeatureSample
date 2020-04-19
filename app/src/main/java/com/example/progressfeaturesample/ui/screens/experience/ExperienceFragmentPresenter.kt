package com.example.progressfeaturesample.ui.screens.experience

import com.example.progressfeaturesample.interactors.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.ExperienceStep
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
            progressInteractor.completeStep(ExperienceStep())
        }
    }
}