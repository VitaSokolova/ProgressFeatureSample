package com.example.progressfeaturesample.ui.screens.education

import com.example.progressfeaturesample.domain.Education
import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepData
import com.example.progressfeaturesample.interactors.application.steps.EducationStep
import com.example.progressfeaturesample.interactors.application.steps.EducationStepOutData
import com.example.progressfeaturesample.ui.utils.filter
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
            subscribeIoHandleError(progressInteractor.completeStep(
                EducationStepOutData(
                    Education()
                )
            ),
                {}
            )
        }

        //т.к. данные обязаны прийти из предыдущего шага, это быстро и не трубуют доп. запросов
        subscribeIoHandleError(
            progressInteractor.getDataForStep(EducationStep).filter<ApplicationStepData.EducationStepData>(),
            {
                bm.educationTypeState.accept(it.stepInData.educationType)
            },
            {}
        )
    }
}