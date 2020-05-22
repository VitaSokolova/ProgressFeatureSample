package com.example.progressfeaturesample.ui.screens.motivation

import com.example.progressfeaturesample.domain.Motivation
import com.example.progressfeaturesample.interactors.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.MotivationStep
import com.example.progressfeaturesample.interactors.MotivationStepIn
import com.example.progressfeaturesample.interactors.MotivationStepOut
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import ru.surfstudio.android.utilktx.data.wrapper.loadable.LoadStatus
import ru.surfstudio.android.utilktx.data.wrapper.loadable.LoadableData
import ru.surfstudio.android.utilktx.data.wrapper.selectable.SelectableData
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

        getStepInputData()
    }

    /**
     * Получаем входные данные для шага
     */
    private fun getStepInputData() {
        subscribeIoHandleError(
            progressInteractor.getDataForStep(MotivationStep())
                .map { it as MotivationStepIn }
                .doOnSubscribe {
                    changeMotivationState(LoadStatus.LOADING)
                }, // пока не придумала, как обойтись без каста
            {
                changeMotivationState(
                    LoadStatus.NORMAL,
                    it.predefinedValues
                )
            },
            {
                changeMotivationState(LoadStatus.ERROR)
            }
        )
    }

    private fun changeMotivationState(status: LoadStatus, data: List<Motivation> = emptyList()) {
        bm.motivationVariantsState.accept(
            LoadableData(
                data.map { SelectableData(it, false) },
                status
            )
        )
    }
}