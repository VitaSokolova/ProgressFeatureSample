package com.example.progressfeaturesample.ui.screens.personal

import com.example.progressfeaturesample.interactors.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.PersonalInfoStep
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер [PersonalInfoFragmentView].
 */
@PerScreen
class PersonalInfoFragmentPresenter @Inject constructor(
    private val bm: PersonalInfoBindModel,
    private val progressInteractor: ApplicationProgressInteractor,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.onNextPressedAction bindTo {
            progressInteractor.completeStep(PersonalInfoStep())
        }
    }

    override fun onLoad(viewRecreated: Boolean) {
        super.onLoad(viewRecreated)
    }
}