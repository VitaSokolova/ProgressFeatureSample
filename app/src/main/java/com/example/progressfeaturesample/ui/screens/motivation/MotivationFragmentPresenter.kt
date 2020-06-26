package com.example.progressfeaturesample.ui.screens.motivation

import com.example.progressfeaturesample.domain.Motivation
import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepData
import com.example.progressfeaturesample.interactors.application.steps.MotivationStep
import com.example.progressfeaturesample.interactors.application.steps.MotivationStepOutData
import com.example.progressfeaturesample.ui.utils.filter
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
                    MotivationStepOutData(
                        listOf(
                            Motivation("Mew")
                        )
                    )
                )
            ) {
                sendApplication()
            }
        }

        getStepInputData()
    }

    /**
     * Отправка финальной заявки
     */
    private fun sendApplication() {
        subscribeIoHandleError(
            progressInteractor.sendApplication()
        ) {
            // реакция на успешную отправку
        }
    }

    /**
     * Получаем входные данные для шага
     */
    private fun getStepInputData() {
        subscribeIoHandleError(
            progressInteractor.getDataForStep(MotivationStep)
                .filter<ApplicationStepData.MotivationStepData>()
                .doOnSubscribe {
                    changeMotivationState(LoadStatus.LOADING)
                },
            {
                changeMotivationState(
                    LoadStatus.NORMAL,
                    it.motivationStepInData.predefinedValues
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