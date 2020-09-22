package com.example.progressfeaturesample.ui.screens.motivation

import com.example.progressfeaturesample.domain.Motivation
import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepData
import com.example.progressfeaturesample.interactors.application.steps.ApplicationSteps
import com.example.progressfeaturesample.interactors.application.steps.MotivationStepOutData
import com.example.progressfeaturesample.ui.screens.success.ThankYouRoute
import com.example.progressfeaturesample.ui.utils.filter
import io.reactivex.rxkotlin.withLatestFrom
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
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
    private val activityNavigator: ActivityNavigator,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.onNextPressedAction.observable.withLatestFrom(
            bm.motivationVariantsState.observable.map {
                it.data.filter { it.isSelected }.map { it.data }
            }
        ) bindTo { (_, motivations) ->
            subscribeIoHandleError(
                progressInteractor.completeStep(
                    MotivationStepOutData(motivations)
                )
            ) {
                sendApplication()
            }
        }

        bm.motivationCheckedAction.observable.distinctUntilChanged() bindTo { (motivation, isChecked) ->
            bm.motivationVariantsState.change { data ->
                data.apply {
                    data.data.map {
                        if (it.data == motivation) {
                            it.isSelected = isChecked
                        }
                    }
                }
            }
        }

        subscribeIoHandleError(
            progressInteractor.getDataForStep(ApplicationSteps.MOTIVATION)
                .filter<ApplicationStepData.MotivationStepData>(),
            {
                it.stepOutData?.motivation?.let {
                    bm.draftCommand.accept(it)
                }
            },
            {}
        )

        getStepInputData()
    }

    /**
     * Отправка финальной заявки
     */
    private fun sendApplication() {
        bm.applicationSendingState.accept(LoadStatus.LOADING)
        subscribeIoHandleError(
            progressInteractor.sendApplication(),
            {
                // реакция на успешную отправку
                activityNavigator.start(ThankYouRoute())
                bm.applicationSendingState.accept(LoadStatus.NORMAL)
            },
            {
                bm.applicationSendingState.accept(LoadStatus.ERROR)
            }
        )
    }

    /**
     * Получаем входные данные для шага
     */
    private fun getStepInputData() {
        subscribeIoHandleError(
            progressInteractor.getDataForStep(ApplicationSteps.MOTIVATION)
                .filter<ApplicationStepData.MotivationStepData>()
                .doOnSubscribe {
                    changeMotivationState(LoadStatus.LOADING)
                },
            {
                val draft = getDraftValues()
                changeMotivationState(
                    LoadStatus.NORMAL,
                    it.stepInData.values.map { SelectableData(it, draft.contains(it)) }
                )
            },
            {
                changeMotivationState(LoadStatus.ERROR)
            }
        )
    }

    private fun getDraftValues(): List<Motivation> {
        return if (bm.draftCommand.hasValue) {
            bm.draftCommand.value
        } else {
            emptyList()
        }
    }

    private fun changeMotivationState(
        status: LoadStatus,
        data: List<SelectableData<Motivation>> = emptyList()
    ) {
        bm.motivationVariantsState.accept(
            LoadableData(
                data,
                status
            )
        )
    }
}